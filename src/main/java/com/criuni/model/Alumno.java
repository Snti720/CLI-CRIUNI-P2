package com.criuni.model;

import java.time.LocalDate;

public class Alumno {
    private String nombreCompleto;
    final private String ci;
    private String email;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String facultad;
    private boolean activo;

    public Alumno(String nombreCompleto, String ci, String email, String telefono, LocalDate fechaNacimiento, String facultad) {
        this.nombreCompleto = nombreCompleto;
        this.ci = ci;
        this.email = email;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.facultad = facultad;
        activo = true;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCi() {
        return ci;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
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
}
