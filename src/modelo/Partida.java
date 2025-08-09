/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author jainer Garcia Gonzalez
 * @author Cristian Rojas
 */
public class Partida {

    private int idPartida;
    private Jugador jugador1;
    private Jugador jugador2;
    private Personaje personaje1;
    private Personaje personaje2;
    private Jugador ganador;

    public Partida(int idPartida, Jugador jugador1, Jugador jugador2, Personaje personaje1, Personaje personaje2) {
        this.idPartida = idPartida;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.personaje1 = personaje1;
        this.personaje2 = personaje2;
        this.ganador = null;
    }

    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
        if (ganador.equals(jugador1)) {
            jugador1.incrementarGanadas();
            jugador2.incrementarPerdidas();
        } else {
            jugador2.incrementarGanadas();
            jugador1.incrementarPerdidas();
        }
    }

    // Getters y setters
    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    public Personaje getPersonaje1() {
        return personaje1;
    }

    public void setPersonaje1(Personaje personaje1) {
        this.personaje1 = personaje1;
    }

    public Personaje getPersonaje2() {
        return personaje2;
    }

    public void setPersonaje2(Personaje personaje2) {
        this.personaje2 = personaje2;
    }

    public Jugador getGanador() {
        return ganador;
    }
}
