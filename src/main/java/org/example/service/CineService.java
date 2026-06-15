package org.example.service;


import org.example.model.*;

import org.example.util.Consola;

import java.util.ArrayList;
import java.util.List;


public class CineService {
    private List<Persona> clientes;
    private List<Funcion> funciones;

    public CineService() {

        clientes = new ArrayList<>();
        funciones = new ArrayList<>();

        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {

        Pelicula p1 = new Pelicula("Avengers", 180);
        Pelicula p2 = new Pelicula("Batman", 150);

        funciones.add(new Funcion(p1, "18:00", 20));
        funciones.add(new Funcion(p2, "20:30", 15));
    }

    public void iniciar() {

        int opcion;

        do {

            mostrarMenu();

            try {

                opcion = Integer.parseInt(Consola.SC.nextLine());

                switch (opcion) {

                    case 1:
                        crearCliente();
                        break;

                    case 2:
                        verCartelera();
                        break;

                    case 3:
                        comprarEntrada();
                        break;

                    case 4:
                        verEntradasCliente();
                        break;

                    case 0:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("Opción inválida");
                }

            } catch (Exception e) {
                System.out.println("Ingrese un número válido");
                opcion = -1;
            }

        } while (opcion != 0);
    }

    private void mostrarMenu() {

        System.out.println("\n===== CINE APP =====");
        System.out.println("1. Crear cliente");
        System.out.println("2. Ver cartelera");
        System.out.println("3. Comprar entrada");
        System.out.println("4. Ver entradas cliente");
        System.out.println("0. Salir");
    }

    private void crearCliente() {

        System.out.print("Nombre: ");
        String nombre = Consola.SC.nextLine();

        Cliente cliente = new Cliente(nombre);

        clientes.add(cliente);

        System.out.println("Cliente registrado");
    }

    private void verCartelera() {

        System.out.println("\n===== CARTELERA =====");

        for (int i = 0; i < funciones.size(); i++) {

            Funcion f = funciones.get(i);

            System.out.println(
                    (i + 1) +
                            ") " +
                            f.getPelicula().getTitulo() +
                            " | " +
                            f.getHorario() +
                            " | Disponibles: " +
                            f.getAsientosDisponibles()
            );
        }
    }

    private void comprarEntrada() {

        if (clientes.isEmpty()) {
            System.out.println("No existen clientes registrados.");
            return;
        }

        try {

            System.out.println("\n===== CLIENTES =====");
            verClientes();

            System.out.print("Seleccione cliente: ");
            int clienteIndex = Integer.parseInt(Consola.SC.nextLine()) - 1;

            if (clienteIndex < 0 || clienteIndex >= clientes.size()) {
                System.out.println("Cliente inválido.");
                return;
            }

            System.out.println("\n===== CARTELERA =====");
            verCartelera();

            System.out.print("Seleccione función: ");
            int funcionIndex = Integer.parseInt(Consola.SC.nextLine()) - 1;

            if (funcionIndex < 0 || funcionIndex >= funciones.size()) {
                System.out.println("Función inválida.");
                return;
            }

            Funcion funcion = funciones.get(funcionIndex);

            Cliente cliente = (Cliente) clientes.get(clienteIndex);

            System.out.print("¿Cuántas entradas desea comprar? (1 o 2): ");
            int cantidad = Integer.parseInt(Consola.SC.nextLine());

            if (cantidad < 1 || cantidad > 2) {
                System.out.println("Solo se pueden comprar 1 o 2 entradas.");
                return;
            }

            for (int i = 1; i <= cantidad; i++) {

                System.out.println("\n===============================");
                System.out.println("ENTRADA #" + i);
                System.out.println("===============================");

                mostrarMapaAsientos(funcion);

                System.out.print("Seleccione asiento (1-20): ");
                int asiento = Integer.parseInt(Consola.SC.nextLine());

                Entrada entrada = new Entrada(cliente, funcion, asiento);

                boolean vendido = funcion.venderEntrada(entrada);

                if (vendido) {

                    cliente.agregarEntrada(entrada);

                    System.out.println(" Asiento " + asiento + " reservado correctamente.");

                } else {

                    System.out.println(" El asiento ya está ocupado o no hay disponibilidad.");
                    i--;
                }
            }

        } catch (Exception e) {

            System.out.println("Error: ingrese datos válidos.");
        }
    }

    private void mostrarMapaAsientos(Funcion funcion) {

        System.out.println("\n========== PANTALLA ==========\n");

        int asiento = 1;

        for (int fila = 0; fila < 4; fila++) {

            for (int columna = 0; columna < 5; columna++) {

                boolean ocupado = false;

                for (Entrada entrada : funcion.getEntradasVendidas()) {

                    if (entrada.getNumeroAsiento() == asiento) {
                        ocupado = true;
                        break;
                    }
                }

                if (ocupado) {
                    System.out.printf("[X%02d] ", asiento);
                } else {
                    System.out.printf("[ %02d] ", asiento);
                }

                asiento++;
            }

            System.out.println();
        }

        System.out.println("\n[X] = Ocupado");
        System.out.println("[ ] = Disponible");
    }

    private void verEntradasCliente() {

        if (clientes.isEmpty()) {
            System.out.println("No existen clientes");
            return;
        }

        verClientes();

        System.out.print("Seleccione cliente: ");
        int indice =
                Integer.parseInt(Consola.SC.nextLine()) - 1;

        if (indice < 0 || indice >= clientes.size()) {
            System.out.println("Cliente inválido");
            return;
        }

        Cliente cliente = (Cliente) clientes.get(indice);

        if (cliente.getEntradas().isEmpty()) {
            System.out.println("No tiene entradas");
            return;
        }

        for (Entrada entrada : cliente.getEntradas()) {
            entrada.mostrarResumen();
        }
    }

    private void verClientes() {

        for (int i = 0; i < clientes.size(); i++) {

            System.out.println(
                    (i + 1) + ". " +
                            clientes.get(i).getNombre()
            );
        }
    }
}
