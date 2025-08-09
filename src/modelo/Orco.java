/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.Random;


/**
 *
 * @author jainer 
 * @author Cristian Rojas
 */
public class Orco extends Personaje {
    private boolean sanacionActiva;

    public Orco(int id, String nombre, Raza raza, int fuerza, int energia, Arma arma) {
        super(id, nombre, raza, fuerza, energia, 100, arma);
        this.sanacionActiva = false;
    }

    @Override
public int atacar(Personaje oponente, int distancia) {
    int danio = 0;
    String armaNombre = this.getArma().getNombre();

    if (armaNombre.equalsIgnoreCase("Hacha")) {
        danio = new Random().nextInt(5) + 1;
        oponente.aplicarSangrado(2); // 2 turnos de sangrado
    } else if (armaNombre.equalsIgnoreCase("Martillo")) {
        danio = new Random().nextInt(5) + 1;
    }

    oponente.recibirDanio(danio);
    return danio;
}

    @Override
    public void sanar() {
        if (!sanacionActiva) {
            int vidaRestaurada = (int)((100 - getVida()) * 0.25); // 25% de la vida perdida
            setVida(Math.min(100, getVida() + vidaRestaurada));
            sanacionActiva = true;
        } else {
            int vidaAdicional = (int)((100 - getVida()) * 0.15); // 15% adicional el siguiente turno
            setVida(Math.min(100, getVida() + vidaAdicional));
            sanacionActiva = false;
        }
    }
}
