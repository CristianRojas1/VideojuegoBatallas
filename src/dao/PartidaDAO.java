/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Partida;

/**
 *
 * @author jainer said Garcia Gonzalez
 */

public class PartidaDAO {
    public void guardarPartida(Partida partida) throws SQLException {
        String sql = "INSERT INTO partida (jugador1_id, jugador2_id, personaje1_id, personaje2_id, ganador_id) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, partida.getJugador1().getIdJugador());
            stmt.setInt(2, partida.getJugador2().getIdJugador());
            stmt.setInt(3, partida.getPersonaje1().getIdPersonaje());  
            stmt.setInt(4, partida.getPersonaje2().getIdPersonaje());  

            if (partida.getGanador() != null) {
                stmt.setInt(5, partida.getGanador().getIdJugador());
            } else {
                stmt.setNull(5, java.sql.Types.INTEGER);
            }

            stmt.executeUpdate();
        }
    }
}

