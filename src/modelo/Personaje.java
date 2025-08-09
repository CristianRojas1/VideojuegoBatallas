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
public abstract class Personaje {

    private int idPersonaje;
    private String nombre;
    private Raza raza;
    private int fuerza;
    private int energia;
    private int vida;
    private Arma arma;
    private int turnosSangrado;
    private int danioSangrado;
    private int posicion;

    public Personaje(int idPersonaje, String nombre, Raza raza, int fuerza, int energia, int vida, Arma arma) {
        this.idPersonaje = idPersonaje;
        this.nombre = nombre;
        this.raza = raza;
        this.fuerza = fuerza;
        this.energia = energia;
        this.vida = vida;
        this.arma = arma;
        this.turnosSangrado = 0;
        this.danioSangrado = 0;
    }

    // Métodos abstractos para ser implementados por las razas
    public abstract int atacar(Personaje oponente, int distancia);

    public abstract void sanar();

    // Recibir daño y actualizar vida
    public void recibirDanio(int danio) {
        this.vida = Math.max(0, this.vida - danio);
    }

    // Aplicar efecto de sangrado
    public void aplicarSangrado(int turnos) {
    this.turnosSangrado = turnos;
}

    // Procesar sangrado al final de cada turno
    public void procesarSangrado() {
    if (turnosSangrado > 0) {
        this.recibirDanio(3); // pierde 3 de vida por turno
        turnosSangrado--;
        System.out.println(this.getNombre() + " pierde 3 de vida por sangrado.");
    }
}
    
    // Lógica avanzar y retroceder
    public void avanzar() {
    this.posicion += 1;
}

    public void retroceder() {
    if (this.posicion > 0) {
        this.posicion -= 1;
        }
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    // Getters y setters
    public int getIdPersonaje() {
        return idPersonaje;
    }

    public void setIdPersonaje(int idPersonaje) {
        this.idPersonaje = idPersonaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Raza getRaza() {
        return raza;
    }

    public void setRaza(Raza raza) {
        this.raza = raza;
    }

    public int getFuerza() {
        return fuerza;
    }

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }
}