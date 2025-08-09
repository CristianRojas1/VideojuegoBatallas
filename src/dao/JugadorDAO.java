/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Jugador;
/**
 *
 * @author jainer Said Garcia Gonzalez
 */
public class JugadorDAO {
    public void guardarJugador(Jugador jugador) throws SQLException {
        String sql = "INSERT INTO Jugador (nombre, partidasGanadas, partidasPerdidas) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jugador.getNombre());
            stmt.setInt(2, jugador.getPartidasGanadas());
            stmt.setInt(3, jugador.getPartidasPerdidas());
            stmt.executeUpdate();
        }
    }

    public void actualizarJugador(Jugador jugador) throws SQLException {
        String sql = "UPDATE Jugador SET nombre = ?, partidasGanadas = ?, partidasPerdidas = ? WHERE idJugador = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, jugador.getNombre());
            stmt.setInt(2, jugador.getPartidasGanadas());
            stmt.setInt(3, jugador.getPartidasPerdidas());
            stmt.setInt(4, jugador.getIdJugador());
            stmt.executeUpdate();
        }
    }

    public Jugador obtenerJugador(int idJugador) throws SQLException {
        String sql = "SELECT * FROM Jugador WHERE idJugador = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idJugador);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Jugador(rs.getInt("idJugador"), rs.getString("nombre"),
                    rs.getInt("partidasGanadas"), rs.getInt("partidasPerdidas"));
            }
            return null;
        }
    }
    
    public List<Jugador> obtenerJugadores() throws SQLException {
    List<Jugador> jugadores = new ArrayList<>();
    String sql = "SELECT * FROM Jugador";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Jugador jugador = new Jugador(
                rs.getInt("idJugador"),
                rs.getString("nombre"),
                rs.getInt("partidasGanadas"),
                rs.getInt("partidasPerdidas")
            );
            jugadores.add(jugador);
        }
    }
    return jugadores;
    }
}