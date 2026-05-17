package com.criuni.repository;

import com.criuni.model.Prestamo;

import java.util.HashMap;

public class RepositorioPrestamo extends RepositorioBase<Integer, Prestamo> {
    public RepositorioPrestamo(){
        modelMap = new HashMap<>();
    }
}
