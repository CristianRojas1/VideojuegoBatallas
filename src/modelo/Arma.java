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
public class Arma {

    private int idArma;
    private String nombre;
    private String tipo;
    private int danioMinimo;
    private int danioMaximo;
    private String modificadores;

    public Arma(int idArma, String nombre, String tipo, int danioMinimo, int danioMaximo, String modificadores) {
        this.idArma = idArma;
        this.nombre = nombre;
        this.tipo = tipo;
        this.danioMinimo = danioMinimo;
        this.danioMaximo = danioMaximo;
        this.modificadores = modificadores;
    }
    
    public int calcularDanio(int distancia) {
    int base = new Random().nextInt(danioMaximo - danioMinimo + 1) + danioMinimo;

    if (this.nombre.equals("Rifle francotirador") && distancia > 1) {
        return new Random().nextInt(6) + 5; // 5–10
    } else if (this.nombre.equals("Aire") && distancia > 1) {
        return new Random().nextInt(9) + 4; // 4–12
    }

    return base;
}

    // Getters y setters
    public int getIdArma() {
        return idArma;
    }

    public void setidArma(int idArma) {
        this.idArma = idArma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDanioMinimo() {
        return danioMinimo;
    }

    public void setDanioMinimo(int danioMinimo) {
        this.danioMinimo = danioMinimo;
    }

    public int getDanioMaximo() {
        return danioMaximo;
    }

    public void setDanioMaximo(int danioMaximo) {
        this.danioMaximo = danioMaximo;
    }

    public String getModificadores() {
        return modificadores;
    }

    public void setModificadores(String modificadores) {
        this.modificadores = modificadores;
    }
}
