package com.criuni.repository;

import com.criuni.controller.ControladorLibro;
import com.criuni.model.Libro;

import java.util.HashMap;
import java.util.Map;

public class RepositorioLibro extends RepositorioBase<String, Libro>{
    public RepositorioLibro() {
        modelMap = new HashMap<>();
    }
}