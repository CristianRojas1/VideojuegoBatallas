/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista;
import modelo.*;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author jaine
 */

public class ConsolaView {
    private Scanner scanner = new Scanner(System.in);

    public int mostrarMenuPrincipal() {
        System.out.println("=== Menu Principal ===");
        System.out.println("1. Jugar Partida");
        System.out.println("2. Ver Estadísticas");
        System.out.println("3. Salir");
        System.out.print("Selecciona una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        return opcion;
    }

    public String solicitarNombreJugador(int numero) {
        System.out.print("Ingresa el nombre del Jugador " + numero + ": ");
        //scanner.nextLine(); Comentar línea!
        return scanner.nextLine();
    }

    public String solicitarNombrePersonaje(int numero) {
        System.out.print("Ingresa el nombre del Personaje " + numero + ": ");
        return scanner.nextLine();
    }

    public Raza seleccionarRaza(int numeroJugador) {
        System.out.println("Jugador " + numeroJugador + ", selecciona una raza:");
        System.out.println("1. Humano");
        System.out.println("2. Elfo");
        System.out.println("3. Orco");
        System.out.println("4. Bestia");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt() - 1;
        scanner.nextLine(); 
        switch (opcion) {
            case 0: return new Raza(1, "Humano", "Usa armas de fuego");
            case 1: return new Raza(2, "Elfo", "Usa magia");
            case 2: return new Raza(3, "Orco", "Usa armas fisicas");
            case 3: return new Raza(4, "Bestia", "Híbrido animal");
            default: System.out.println("Opción inválida. Usando Humano por defecto.");
                     return new Raza(1, "Humano", "Usa armas de fuego");
        }
    }

    public Arma seleccionarArma(Raza raza, int numeroJugador) {
        System.out.println("Jugador " + numeroJugador + ", selecciona un arma para " + raza.getNombre() + ":");
        switch (raza.getNombre()) {
            case "Humano":
                System.out.println("1. Escopeta");
                System.out.println("2. Rifle Francotirador");
                System.out.print("Opción: ");
                int opcionHumano = scanner.nextInt() - 1;
                scanner.nextLine(); // Limpiar el buffer
                return (opcionHumano == 0) ? new Arma(1, "Escopeta", "Fuego", 1, 5, "+2%")
                                           : new Arma(2, "Rifle Francotirador", "Fuego", 1, 5, "");
            case "Elfo":
                System.out.println("1. Baculo de Fuego");
                System.out.println("2. Baculo de Tierra");
                System.out.println("3. Baculo de Aire");
                System.out.println("4. Baculo de Agua");
                System.out.print("Opción: ");
                int opcionElfo = scanner.nextInt() - 1;
                scanner.nextLine(); // Limpiar el buffer
                switch (opcionElfo) {
                    case 0: return new Arma(3, "Baculo de Fuego", "Magica", 1, 5, "+10%");
                    case 1: return new Arma(4, "Baculo de Tierra", "Magica", 1, 5, "+2%, acierta más veces.");
                    case 2: return new Arma(5, "Baculo de Aire", "Magica", 1, 5, "");
                    case 3: return new Arma(6, "Baculo de Agua", "Magica", 1, 5, "La sanación aumenta a 90%.");
                    default: return new Arma(3, "Baculo de Fuego", "Magica", 1, 5, "");
                }
            case "Orco":
                System.out.println("1. Hacha");
                System.out.println("2. Martillo");
                System.out.print("Opción: ");
                int opcionOrco = scanner.nextInt() - 1;
                scanner.nextLine(); 
                return (opcionOrco == 0) ? new Arma(7, "Hacha", "Fisica", 1, 5, "Provoca 2 turnos de sangrado (-3 vida*turno)")
                                         : new Arma(8, "Martillo", "Fisica", 1, 5, "");
            case "Bestia":
                System.out.println("1. Puños");
                System.out.println("2. Espada");
                System.out.print("Opción: ");
                int opcionBestia = scanner.nextInt() - 1;
                scanner.nextLine(); 
                return (opcionBestia == 0) ? new Arma(9, "Puños", "Fisica", 25, 25, "Daño fijo 25 al oponente, pero pierde 10 de vida el atacante")
                                           : new Arma(10, "Espada", "Física", 1, 10, "");
            default:
                System.out.println("Raza no reconocida. Usando Escopeta por defecto.");
                return new Arma(1, "Escopeta", "Fuego", 10, 15, "");
        }
    }

    public int solicitarAccion(int numeroJugador) {
        System.out.println("Turno del Jugador " + numeroJugador + ":");
        System.out.println("1. Atacar");
        System.out.println("2. Sanar");
        System.out.println("3. Avanzar");
        System.out.println("4. Retroceder");
        System.out.print("Selecciona una acción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); 
        return opcion;
    }

    public void mostrarEstadoCombate(int turno, Personaje p1, Personaje p2) {
        System.out.println("\n=== Turno " + turno + " ===");
        System.out.println(p1.getNombre() + ": Vida = " + p1.getVida());
        System.out.println(p2.getNombre() + ": Vida = " + p2.getVida());
        System.out.println(p1.getNombre() + ": Posición " + p1.getPosicion());
        System.out.println(p2.getNombre() + ": Posición " + p2.getPosicion());
        System.out.println("Distancia entre jugadores: " + Math.abs(p1.getPosicion() - p2.getPosicion()));
    }

    public void mostrarResultado(Jugador ganador, Personaje p1, Personaje p2) {
        System.out.println("\n=== Resultado de la Partida ===");
        System.out.println("Ganador: " + ganador.getNombre());
        System.out.println(p1.getNombre() + ": Vida final = " + p1.getVida());
        System.out.println(p2.getNombre() + ": Vida final = " + p2.getVida());
    }

    public void mostrarEstadisticas(Jugador j1) {
        System.out.println("\n=== Estadisticas ===");
        System.out.println(j1.getNombre() + ": Ganadas = " + j1.getPartidasGanadas() + ", Perdidas = " + j1.getPartidasPerdidas());
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}