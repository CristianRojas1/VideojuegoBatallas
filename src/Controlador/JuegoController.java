/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Vista.ConsolaView;
import dao.*;
import modelo.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jainer Said Garcia Gonzalez
 * @author Cristian Rojas
 */

public class JuegoController {
    private ConsolaView vista;
    private RazaDAO razaDAO;
    private ArmaDAO armaDAO;
    private PersonajeDAO personajeDAO;
    private JugadorDAO jugadorDAO;
    private PartidaDAO partidaDAO;

    public JuegoController() {
        this.vista = new ConsolaView();
        this.razaDAO = new RazaDAO();
        this.armaDAO = new ArmaDAO();
        this.personajeDAO = new PersonajeDAO();
        this.jugadorDAO = new JugadorDAO();
        this.partidaDAO = new PartidaDAO();
    }

    public void iniciarJuego() {
    try {
        while (true) {
            int opcion = vista.mostrarMenuPrincipal();
            if (opcion == 1) {
                jugarPartida();
            } else if (opcion == 2) {
                int idSeleccionado = mostrarJugadoresYSeleccionar();
                if (idSeleccionado != -1) {
                    mostrarEstadisticas(idSeleccionado);
                } else {
                    vista.mostrarMensaje("Selección inválida o no hay jugadores.");
                }
            } else if (opcion == 3) {
                vista.mostrarMensaje("¡Gracias por jugar!");
                break;
            } else {
                vista.mostrarMensaje("Opción inválida. Intenta de nuevo.");
            }
        }
    } catch (SQLException e) {
        vista.mostrarMensaje("Error en la base de datos: " + e.getMessage());
    }
}

    private void jugarPartida() throws SQLException {
        // Obtener el siguiente ID disponible para Jugador
        int nextIdJugador = obtenerSiguienteIdJugador() + 1;
        Jugador jugador1 = new Jugador(nextIdJugador, vista.solicitarNombreJugador(1), 0, 0);
        Jugador jugador2 = new Jugador(nextIdJugador + 1, vista.solicitarNombreJugador(2), 0, 0);
        jugadorDAO.guardarJugador(jugador1);
        jugadorDAO.guardarJugador(jugador2);
        

        // Seleccionar raza y arma
        Raza raza1 = vista.seleccionarRaza(1);
        Arma arma1 = vista.seleccionarArma(raza1, 1);
        Raza raza2 = vista.seleccionarRaza(2);
        Arma arma2 = vista.seleccionarArma(raza2, 2);

        // Crear personajes
        Personaje personaje1 = crearPersonaje(1, raza1, arma1, jugador1);
        Personaje personaje2 = crearPersonaje(2, raza2, arma2, jugador2);
        personajeDAO.guardarPersonaje(personaje1);
        personajeDAO.guardarPersonaje(personaje2);
        personaje1.setPosicion(0);
        personaje2.setPosicion(4);

        // Simular combate
        Partida partida = new Partida(0, jugador1, jugador2, personaje1, personaje2); 
        int turno = 1;
        while (personaje1.getVida() > 0 && personaje2.getVida() > 0) {
            vista.mostrarEstadoCombate(turno, personaje1, personaje2);
            int accion1 = vista.solicitarAccion(1);
            int accion2 = vista.solicitarAccion(2);
            procesarAccion(personaje1, personaje2, accion1);
            procesarAccion(personaje2, personaje1, accion2);
            
            personaje1.procesarSangrado();
            personaje2.procesarSangrado();
            turno++;
        }

        // Determinar ganador
        Jugador ganador = (personaje1.getVida() > 0) ? jugador1 : jugador2;
        partida.setGanador(ganador);
        jugadorDAO.actualizarJugador(jugador1);
        jugadorDAO.actualizarJugador(jugador2);
        partidaDAO.guardarPartida(partida);
        vista.mostrarResultado(ganador, personaje1, personaje2);
    }

    private Personaje crearPersonaje(int numeroJugador, Raza raza, Arma arma, Jugador jugador) throws SQLException {
        int nextIdPersonaje = obtenerSiguienteIdPersonaje() + 1;
        String nombre = vista.solicitarNombrePersonaje(numeroJugador);
        Personaje personaje;
        switch (raza.getNombre()) {
            case "Humano":
            personaje = new Humano(nextIdPersonaje, nombre, raza, 50, 100, arma);
            break;
        case "Elfo":
            int vidaInicial = arma.getNombre().equalsIgnoreCase("Baculo de Agua") ? 115 : 100;
            personaje = new Elfo(nextIdPersonaje, nombre, raza, 40, vidaInicial, arma);
            personaje.setVida(vidaInicial);
            break;
        case "Orco":
            personaje = new Orco(nextIdPersonaje, nombre, raza, 50, 100, arma);
            break;
        case "Bestia":
            personaje = new Bestia(nextIdPersonaje, nombre, raza, 50, 100, arma);
            break;
        default:
            throw new SQLException("Raza no válida");
        }
        return personaje;
    }

    public int mostrarJugadoresYSeleccionar() {
    System.out.println("===== Jugadores disponibles =====");
    try {
        List<Jugador> jugadores = jugadorDAO.obtenerJugadores();

        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores disponibles.");
            return -1;
        }

        for (Jugador jugador : jugadores) {
            System.out.println("ID: " + jugador.getIdJugador() + " - " + jugador.getNombre());
        }

        System.out.print("Ingrese el ID del jugador que desea seleccionar: ");
        Scanner scanner = new Scanner(System.in);
        int idSeleccionado = scanner.nextInt();

        // Aquí podrías validar si el ID existe en la lista
        boolean existe = jugadores.stream()
                .anyMatch(j -> j.getIdJugador() == idSeleccionado);

        if (existe) {
            return idSeleccionado;
        } else {
            System.out.println("ID inválido.");
        }

    } catch (SQLException e) {
        System.out.println("Error al obtener jugadores: " + e.getMessage());
    } catch (Exception e) {
        System.out.println("Error al seleccionar jugador: " + e.getMessage());
    }

    return -1;
}
    
    private void mostrarEstadisticas(int idJugador) throws SQLException {
    Jugador jugador = jugadorDAO.obtenerJugador(idJugador);
    if (jugador != null) {
        vista.mostrarEstadisticas(jugador);
    } else {
        vista.mostrarMensaje("No hay estadísticas disponibles para ese jugador.");
    }
    }

    private int obtenerSiguienteIdJugador() throws SQLException {
        String sql = "SELECT ISNULL(MAX(idJugador), 0) as maxId FROM Jugador";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("maxId");
            }
            return 0;
        }
    }

    private int obtenerSiguienteIdPersonaje() throws SQLException {
        String sql = "SELECT ISNULL(MAX(idPersonaje), 0) as maxId FROM Personaje";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("maxId");
            }
            return 0;
        }
    }
    
    private void procesarAccion(Personaje actual, Personaje enemigo, int accion) {
        int distancia = Math.abs(actual.getPosicion() - enemigo.getPosicion());

        switch (accion) {
            case 1: // Atacar
                if (distancia == 1) {
                 actual.atacar(enemigo, distancia);
                } else {
                    vista.mostrarMensaje("¡Estás demasiado lejos para atacar!");
                }
                break;
            case 2: // Sanar
                actual.sanar();
                break;
            case 3: // Avanzar
                if (actual.getPosicion() < enemigo.getPosicion()) {
                    actual.avanzar();
                } else {
                    vista.mostrarMensaje("No puedes avanzar más.");
                }
                break;
            case 4: // Retroceder
                if (actual.getPosicion() > 0) {
                    actual.retroceder();
                } else {
                    vista.mostrarMensaje("No puedes retroceder más.");
                }
                break;
            default:
                vista.mostrarMensaje("Acción inválida.");
        }
    }
}
