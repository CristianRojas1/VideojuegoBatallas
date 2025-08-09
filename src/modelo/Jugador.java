/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author jainer Said Garcia Gonzalez
 * @author Cristian Rojas
 */
public class Jugador {

    private int idJugador;
    private String nombre;
    private int partidasGanadas;
    private int partidasPerdidas;

    public Jugador(int idJugador, String nombre, int partidasGanadas, int partidasPerdidas) {
        this.idJugador = idJugador;
        this.nombre = nombre;
        this.partidasGanadas = partidasGanadas;
        this.partidasPerdidas = partidasPerdidas;
    }

    // Métodos para actualizar estadísticas
    public void incrementarGanadas() {
        this.partidasGanadas++;
    }

    public void incrementarPerdidas() {
        this.partidasPerdidas++;
    }

    // Getters y setters
    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPartidasGanadas() {
        return partidasGanadas;
    }

    public void setPartidasGanadas(int partidasGanadas) {
        this.partidasGanadas = partidasGanadas;
    }

    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }

    public void setPartidasPerdidas(int partidasPerdidas) {
        this.partidasPerdidas = partidasPerdidas;
    }

}
