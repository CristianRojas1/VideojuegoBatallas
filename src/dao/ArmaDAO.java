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
import java.util.Random;
import modelo.Arma;

/**
 *
 * @author jaine
 */
public class ArmaDAO {
    public void guardarArma(Arma arma) throws SQLException {
        String sql = "INSERT INTO Arma (idArma, nombre, tipo, dannoMinimo, dannoMaximo, modificadores) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, arma.getIdArma());
            stmt.setString(2, arma.getNombre());
            stmt.setString(3, arma.getTipo());
            stmt.setInt(4, arma.getDanioMinimo());
            stmt.setInt(5, arma.getDanioMaximo());
            stmt.setString(6, arma.getModificadores());
            stmt.executeUpdate();
        }
    }

    public List<Arma> obtenerArmas() throws SQLException {
        List<Arma> armas = new ArrayList<>();
        String sql = "SELECT * FROM Arma";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                armas.add(new Arma(rs.getInt("idArma"), rs.getString("nombre"), rs.getString("tipo"),
                    rs.getInt("dannoMinimo"), rs.getInt("dannoMaximo"), rs.getString("modificadores")));
            }
        }
        return armas;
    }
    
    
}