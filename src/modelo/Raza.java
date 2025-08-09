/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author jainer
 * @author Cristian Rojas
 */
public class Raza {

    private int idRaza;
    private String nombre;
    private String descripcion;

    public Raza(int idRaza, String nombre, String descripcion) {
        this.idRaza = idRaza;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters y setters
    public int getIdRaza() {
        return idRaza;
    }

    public void setIdRaza(int idRaza) {
        this.idRaza = idRaza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

