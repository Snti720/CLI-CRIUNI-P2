package com.criuni.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Prestamo {
    private static int contadorId = 1;
    final private int idPrestamo;
    private List<Libro> libros;
    private Alumno alumno;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolución;
    private LocalDate fechaDevuelto;
    private boolean estadoDevolución;

    public Prestamo(Alumno alumno, LocalDate fechaPrestamo, LocalDate fechaDevolución) {
        idPrestamo = contadorId++;
        libros = new ArrayList<>();
        this.alumno = alumno;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolución = fechaDevolución;
        estadoDevolución = true;
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

    public LocalDate getFechaDevolución() {
        return fechaDevolución;
    }

    public void setFechaDevolución(LocalDate fechaDevolución) {
        this.fechaDevolución = fechaDevolución;
    }

    public LocalDate getFechaDevuelto() {
        return fechaDevuelto;
    }

    public void setFechaDevuelto(LocalDate fechaDevuelto) {
        this.fechaDevuelto = fechaDevuelto;
    }

    public boolean isEstadoDevolución() {
        return estadoDevolución;
    }

    public void setEstadoDevolución(boolean estadoDevolución) {
        this.estadoDevolución = estadoDevolución;
    }
}
