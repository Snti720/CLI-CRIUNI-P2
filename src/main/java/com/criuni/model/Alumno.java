package com.criuni.model;

import java.time.LocalDate;

public class Alumno implements IModelo<Integer> {
    private String nombreCompleto;
    final private int ci;
    private String email;
    private int telefono;
    private LocalDate fechaNacimiento;
    private String facultad;
    private boolean activo;

    public Alumno(String nombreCompleto, int ci, String email, int telefono, String facultad) {
        this.nombreCompleto = nombreCompleto;
        this.ci = ci;
        this.email = email;
        this.telefono = telefono;
        this.facultad = facultad;
        activo = true;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getCi() {
        return ci;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento){
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFacultad() {
        return facultad;
    }

    public void setFacultad(String facultad) {
        this.facultad = facultad;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public Integer getClave() {
        return getCi();
    }

    @Override
    public String toString() {
        return nombreCompleto + " | " + facultad + " | " + email;
    }
}
