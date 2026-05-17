package com.criuni.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Prestamo implements IModelo<Integer> {
    private static int contadorId = 1;
    final private int idPrestamo;
    private List<Libro> libros;
    private Alumno alumno;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private LocalDate fechaDevuelto;
    private boolean estadoDevolucion;

    public Prestamo(Alumno alumno, LocalDate fechaPrestamo, LocalDate fechaDevolucion) {
        idPrestamo = contadorId++;
        libros = new ArrayList<>();
        this.alumno = alumno;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        estadoDevolucion = false;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public LocalDate getFechaDevuelto() {
        return fechaDevuelto;
    }

    public void setFechaDevuelto(LocalDate fechaDevuelto) {
        this.fechaDevuelto = fechaDevuelto;
    }

    public boolean estaDevuelto() {
        return estadoDevolucion;
    }

    public void setEstadoDevolucion(boolean estadoDevolucion) {
        this.estadoDevolucion = estadoDevolucion;
    }

    @Override
    public Integer getClave() {
        return getIdPrestamo();
    }

    @Override
    public String toString() {
        return "ID:" + idPrestamo + " | " + alumno.getNombreCompleto() + " | " + libros.size() + " libros";
    }
}
