/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.Random;

/**
 *
 * @author jainer Said Garcia Gonzalez
 * @author Cristian Rojas
 */

public class Elfo extends Personaje {
    public Elfo(int id, String nombre, Raza raza, int fuerza, int energia, Arma arma) {
        super(id, nombre, raza, fuerza, energia, arma.getNombre().equals("Agua") ? 115 : 100, arma);
    }

    @Override
public int atacar(Personaje oponente, int distancia) {
    int danio = 0;
    String armaNombre = this.getArma().getNombre();

    if (armaNombre.equalsIgnoreCase("Baculo de Aire")) {
        if (distancia > 1) {
            danio = new Random().nextInt(9) + 4; // 4–12
        } else {
            danio = new Random().nextInt(5) + 1; // 1–5
        }
    } else if (armaNombre.equalsIgnoreCase("Baculo de Fuego")) {
        danio = (int) (new Random().nextInt(5) + 1 + 0.10); // +10%
    } else if (armaNombre.equalsIgnoreCase("Baculo de Tierra")) {
        danio = (int) (new Random().nextInt(5) + 1 + 0.02); // +2%
    } else if (armaNombre.equalsIgnoreCase("Baculo de Agua")) {
        danio = new Random().nextInt(5) + 1;
    }

    oponente.recibirDanio(danio);
    return danio;
}

    @Override
    public void sanar() {
        int porcentaje = getArma().getNombre().equals("Baculo de Agua") ? 90 : 75; // 90% para Agua, 75% para otros
        int vidaRestaurada = (int)((100 - getVida()) * (porcentaje / 100.0));
        setVida(Math.min(getArma().getNombre().equals("Baculo de Agua") ? 115 : 100, getVida() + vidaRestaurada));
    }
}

