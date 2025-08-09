/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Arma;
import modelo.Bestia;
import modelo.Elfo;
import modelo.Humano;
import modelo.Orco;
import modelo.Personaje;
import modelo.Raza;

/**
 *
 * @author jainer Said Garcia Gonzalez
 */

public class PersonajeDAO {
    public void guardarPersonaje(Personaje personaje) throws SQLException {
        String sql = "INSERT INTO Personaje (nombre, idRaza, fuerza, energia, vida, idArma) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, personaje.getNombre());
            stmt.setInt(2, personaje.getRaza().getIdRaza());
            stmt.setInt(3, personaje.getFuerza());
            stmt.setInt(4, personaje.getEnergia());
            stmt.setInt(5, personaje.getVida());
            stmt.setInt(6, personaje.getArma().getIdArma());
            stmt.executeUpdate();
        }
    }

    public Personaje obtenerPersonaje(int idPersonaje) throws SQLException {
        String sql = "SELECT p.*, r.nombre as raza_nombre, a.nombre as arma_nombre " +
                    "FROM Personaje p " +
                    "JOIN Raza r ON p.idRaza = r.idRaza " +
                    "JOIN Arma a ON p.idArma = a.idArma " +
                    "WHERE p.idPersonaje = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idPersonaje);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Raza raza = new Raza(rs.getInt("idRaza"), rs.getString("raza_nombre"), rs.getString("descripcion"));
                Arma arma = new Arma(rs.getInt("idArma"), rs.getString("arma_nombre"), 
                    rs.getString("tipo"), rs.getInt("dañoMinimo"), rs.getInt("dañoMaximo"), rs.getString("modificadores"));
                String razaNombre = rs.getString("raza_nombre");
                Personaje personaje;
                switch (razaNombre) {
                    case "Humano":
                        personaje = new Humano(rs.getInt("idPersonaje"), rs.getString("nombre"), raza,
                            rs.getInt("fuerza"), rs.getInt("energia"), arma);
                        break;
                    case "Elfo":
                        personaje = new Elfo(rs.getInt("idPersonaje"), rs.getString("nombre"), raza,
                            rs.getInt("fuerza"), rs.getInt("energia"), arma);
                        break;
                    case "Orco":
                        personaje = new Orco(rs.getInt("idPersonaje"), rs.getString("nombre"), raza,
                            rs.getInt("fuerza"), rs.getInt("energia"), arma);
                        break;
                    case "Bestia":
                        personaje = new Bestia(rs.getInt("idPersonaje"), rs.getString("nombre"), raza,
                            rs.getInt("fuerza"), rs.getInt("energia"), arma);
                        break;
                    default:
                        return null;
                }
                personaje.setVida(rs.getInt("vida"));
                return personaje;
            }
            return null;
        }
    }
}