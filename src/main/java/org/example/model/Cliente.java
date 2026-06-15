package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Persona {

    private List<Entrada> entradas;

    public Cliente(String nombre) {
        super(nombre);
        this.entradas = new ArrayList<>();
    }

    public void agregarEntrada(Entrada entrada) {
        entradas.add(entrada);
    }

    public List<Entrada> getEntradas() {
        return entradas;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
