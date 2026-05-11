package com.criuni.repository;

import com.criuni.controller.ControladorLibro;
import com.criuni.model.Libro;

import java.util.HashMap;
import java.util.Map;

public class RepositorioLibro {
    private Map<String, Libro> stockLibros;

    public RepositorioLibro() {
        stockLibros = new HashMap<>();
    }

    public void agregarLibro(Libro libro){
        stockLibros.put(libro.getIsbn(), libro);
    }

    public Map<String, Libro> obtenerTodos(){
        return stockLibros;
    }
}
