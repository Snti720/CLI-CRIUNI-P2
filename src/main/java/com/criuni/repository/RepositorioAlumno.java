package com.criuni.repository;

import com.criuni.model.Alumno;

import java.util.HashMap;
import java.util.Map;

public class RepositorioAlumno {
    private Map<String, Alumno> alumnosRegistrados;

    public RepositorioAlumno(){
        alumnosRegistrados = new HashMap<>();
    }

    public void agregarAlumno(Alumno alumno){
        alumnosRegistrados.put(alumno.getCi(), alumno);
    }

    public Map<String, Alumno> obtenerTodos(){
        return alumnosRegistrados;
    }
}
