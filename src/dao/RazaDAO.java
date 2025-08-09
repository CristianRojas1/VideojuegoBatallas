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
import modelo.Raza;

/**
 *
 * @author jainer Said Garcia Gonzalez
 */

public class RazaDAO {
    public void guardarRaza(Raza raza) throws SQLException {
        String sql = "INSERT INTO Raza (idRaza, nombre, descripcion) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, raza.getIdRaza());
            stmt.setString(2, raza.getNombre());
            stmt.setString(3, raza.getDescripcion());
            stmt.executeUpdate();
        }
    }

    public List<Raza> obtenerRazas() throws SQLException {
        List<Raza> razas = new ArrayList<>();
        String sql = "SELECT * FROM Raza";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                razas.add(new Raza(rs.getInt("idRaza"), rs.getString("nombre"), rs.getString("descripcion")));
            }
        }
        return razas;
    }
}
