package org.example.model;

public class Entrada {
    private Cliente cliente;
    private Funcion funcion;
    private int numeroAsiento;
    private int fila;
    private int columna;

    public Entrada(
            Cliente cliente,
            Funcion funcion,
            int numeroAsiento
            ) {

        this.cliente = cliente;
        this.funcion = funcion;
        this.numeroAsiento = numeroAsiento;
        this.fila = fila;
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void mostrarResumen() {

        System.out.println("----- ENTRADA -----");
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Película: " + funcion.getPelicula().getTitulo());
        System.out.println("Horario: " + funcion.getHorario());
        System.out.println("Asiento: " + numeroAsiento);
    }
}
