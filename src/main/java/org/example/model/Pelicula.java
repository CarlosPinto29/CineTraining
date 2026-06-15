package org.example.model;

public class Pelicula {

    private String titulo;
    private int duracion;

    public Pelicula(String titulo, int duracion) {
        this.titulo = titulo;
        this.duracion = duracion;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getDuracion() {
        return duracion;
    }

    public void mostrarInfo() {
        System.out.println("Título: " + titulo);
        System.out.println("Duración: " + duracion + " minutos");
    }

    @Override
    public String toString() {
        return titulo + " (" + duracion + " min)";
    }
}
