/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.Random;

/**
 *
 * @author jainer Said
 * @author Cristian Rojas
 */

public class Humano extends Personaje {
    private int danioRecibido;

    public Humano(int id, String nombre, Raza raza, int fuerza, int energia, Arma arma) {
        super(id, nombre, raza, fuerza, energia, 100, arma);
        this.danioRecibido = 0;
    }

    @Override
public int atacar(Personaje oponente, int distancia) {
    int danio = 0;
    String armaNombre = this.getArma().getNombre();

    if (armaNombre.equalsIgnoreCase("Rifle Francotirador")) {
        if (distancia > 1) {
            danio = new Random().nextInt(6) + 5; // 5–10
        } else {
            danio = new Random().nextInt(5) + 1; // 1–5
        }
    } else if (armaNombre.equalsIgnoreCase("Escopeta")) {
        danio = new Random().nextInt(5) + 1;
        danio += danio * 0.02; // +2%
    }

    oponente.recibirDanio((int) danio);
    return danio;
}

    @Override
    public void sanar() {
        int vidaRestaurada = (int)(danioRecibido * 0.5); // 50% del daño recibido
        setVida(Math.min(100, getVida() + vidaRestaurada));
    }
}

