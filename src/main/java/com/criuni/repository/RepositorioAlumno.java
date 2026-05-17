package com.criuni.repository;

import com.criuni.model.Alumno;

import java.util.HashMap;

public class RepositorioAlumno extends RepositorioBase<Integer, Alumno> {
    public RepositorioAlumno(){
        modelMap = new HashMap<>();
    }
}
