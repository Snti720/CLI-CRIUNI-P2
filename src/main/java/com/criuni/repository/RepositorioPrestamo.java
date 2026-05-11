package com.criuni.repository;

import com.criuni.model.Prestamo;

import java.util.HashMap;
import java.util.Map;

public class RepositorioPrestamo {
    private Map<Integer, Prestamo> prestamosRegistrados;

    public RepositorioPrestamo(){
        prestamosRegistrados = new HashMap<>();
    }

    public void agregarPrestamo(Prestamo prestamo){
        prestamosRegistrados.put(prestamo.getIdPrestamo(), prestamo);
    }

    public Map<Integer, Prestamo> obtenerTodos(){
        return prestamosRegistrados;
    }
}
