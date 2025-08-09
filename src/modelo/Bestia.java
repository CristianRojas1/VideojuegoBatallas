/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.Random;


/**
 *
 * @author jainer said Garcia Gonzalez
 * @author Cristian Rojas
 */
public class Bestia extends Personaje {
    public Bestia(int id, String nombre, Raza raza, int fuerza, int energia, Arma arma) {
        super(id, nombre, raza, fuerza, energia, 100, arma);
    }

    @Override
public int atacar(Personaje oponente, int distancia) {
    int danio = 0;
    String armaNombre = this.getArma().getNombre();

    if (armaNombre.equalsIgnoreCase("Puños")) {
        danio = 25;
        this.recibirDanio(10); // el atacante se daña al atacar
    } else if (armaNombre.equalsIgnoreCase("Espada")) {
        danio = new Random().nextInt(10) + 1; // 1–10
    }

    oponente.recibirDanio(danio);
    return danio;
}

    @Override
    public void sanar() {
        int vidaRestaurada = (int)((100 - getVida()) * 0.45); // 45% de la vida perdida
        setVida(Math.min(100, getVida() + vidaRestaurada));
    }
}
