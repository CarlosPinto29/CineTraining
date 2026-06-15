package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Funcion {
    private Pelicula pelicula;
    private String horario;
    private int capacidad;

    private List<Entrada> entradasVendidas;

    private boolean[][] sala;

    public Funcion(Pelicula pelicula, String horario, int capacidad) {
        this.pelicula = pelicula;
        this.horario = horario;
        this.capacidad = capacidad;
        this.entradasVendidas = new ArrayList<>();
        sala = new boolean[5][5];
    }

    public void mostrarSala() {

        System.out.println("\n======================");
        System.out.println("       PANTALLA");
        System.out.println("======================");

        for (int fila = 0; fila < sala.length; fila++) {

            for (int columna = 0; columna < sala[fila].length; columna++) {

                if (sala[fila][columna]) {
                    System.out.print("[X]");
                } else {
                    System.out.print("[O]");
                }
            }

            System.out.println();
        }

        System.out.println();
        System.out.println("O = Disponible");
        System.out.println("X = Ocupado");
    }

    public boolean reservarAsiento(int fila, int columna) {

        if (fila < 0 || fila >= sala.length) {
            return false;
        }

        if (columna < 0 || columna >= sala[0].length) {
            return false;
        }

        if (sala[fila][columna]) {
            return false;
        }

        sala[fila][columna] = true;

        return true;
    }

    public Pelicula getPelicula() {
        return pelicula;
    }

    public String getHorario() {
        return horario;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public List<Entrada> getEntradasVendidas() {
        return entradasVendidas;
    }

    public int getAsientosDisponibles() {
        return capacidad - entradasVendidas.size();
    }

    public boolean asientoOcupado(int asiento) {

        for (Entrada entrada : entradasVendidas) {
            if (entrada.getNumeroAsiento() == asiento) {
                return true;
            }
        }

        return false;
    }

    public boolean venderEntrada(Entrada entrada) {

        if (getAsientosDisponibles() <= 0) {
            return false;
        }

        if (asientoOcupado(entrada.getNumeroAsiento())) {
            return false;
        }

        entradasVendidas.add(entrada);
        return true;
    }

    @Override
    public String toString() {

        return pelicula.getTitulo() +
                " | Horario: " + horario +
                " | Disponibles: " + getAsientosDisponibles();
    }
}
