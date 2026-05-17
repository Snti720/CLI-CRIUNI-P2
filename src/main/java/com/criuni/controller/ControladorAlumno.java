package com.criuni.controller;

import com.criuni.model.Alumno;
import com.criuni.repository.RepositorioAlumno;

import java.time.DateTimeException;
import java.time.LocalDate;

public class ControladorAlumno extends ControladorCRUDBase<Integer, Alumno> {

    public ControladorAlumno(RepositorioAlumno repoAlumno) {
        repo = repoAlumno;
    }

    public void crearAlumno(Alumno alumno){
        Alumno registro;
        if (!existe(alumno.getClave()) && emailCorrecto(alumno.getEmail())){
            repo.agregar(alumno);
            vista.mostrarMensajeFinal("Alumno " + alumno.getNombreCompleto() + " registrado exitosamente.");
        } else {
            registro = repo.obtenerTodos().get(alumno.getClave());
            if (!registro.isActivo()){
                vista.mostrarMensaje("Este alumno existe en la base de datos pero está dado de baja. ¿Desea reactivarlo?\n\t1. Sí\n\t2. No");
                switch (vista.respuesta()){
                    case 1:
                        registro.setActivo(true);
                        vista.mostrarMensajeFinal("Alumno reactivado con éxito.");
                        break;
                    case 2:
                        break;
                    default:
                        vista.mostrarMensajeFinal("Respuesta inválida. Operación cancelada.");
                }
            } else {
                vista.mostrarMensajeFinal("Este alumno ya existe en el sistema y se encuentra activo.");
            }
        }
    }

    public boolean emailCorrecto(String email){
        if (email == null || email.isEmpty()) {
            return false;
        }

        if (email.contains(" ")) {
            return false;
        }

        for (int i = 0; i < email.length(); i++) {
            char c = email.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '@' || c == '.' || c == '_' || c == '-')) {
                return false;
            }
        }

        int primerArroba = email.indexOf('@');
        int ultimoArroba = email.lastIndexOf('@');

        if (primerArroba == -1 || primerArroba != ultimoArroba || primerArroba == 0 ||
                primerArroba == email.length() - 1) {
            return false;
        }

        String dominio = email.substring(primerArroba + 1);

        if (dominio.startsWith("-") || dominio.startsWith(".") || dominio.endsWith("-") || dominio.endsWith(".")) {
            return false;
        }

        int posicionPunto = dominio.indexOf('.');

        if (posicionPunto == -1 || posicionPunto == 0 || posicionPunto == dominio.length() - 1) {
            return false;
        }

        if (posicionPunto < 2) {
            return false;
        }

        if (email.endsWith(".")) {
            return false;
        }

        return true;
    }

    public LocalDate fechaNacimiento() {
        int anhoActual = LocalDate.now().getYear();
        while (true) {
            try {
                String diaStr = vista.pedir("Ingrese día de nacimiento (1-31):");
                String mesStr = vista.pedir("Ingrese mes de nacimiento (1-12):");
                String anhoStr = vista.pedir("Ingrese año de nacimiento (Ej: 1990):");
                int dia = Integer.parseInt(diaStr);
                int mes = Integer.parseInt(mesStr);
                int anho = Integer.parseInt(anhoStr);

                if (anho < 1900 || anho > anhoActual) {
                    vista.mostrarMensaje("Error: Año de nacimiento inválido (debe estar entre 1900 y " + anhoActual + ").");
                    continue;
                }

                return LocalDate.of(anho, mes, dia);
            } catch (NumberFormatException e) {
                vista.mostrarMensaje("Error: Día, mes o año deben ser números enteros. Intente de nuevo.");
            } catch (DateTimeException e) {
                vista.mostrarMensaje("Error: Fecha inválida (ej: 30 de febrero, mes 13). Intente de nuevo.");
            } catch (Exception e) {
                vista.mostrarMensaje("Ocurrió un error inesperado. Intente de nuevo.");
            }
        }
    }

    public void borrar(Integer id){
        Alumno alumno;
        if (existe(id)){
            alumno = mostrar(id);
            if (alumno.isActivo()){
                alumno.setActivo(false);
                vista.mostrarMensajeFinal("Alumno dado de baja exitosamente.");
            } else {
                vista.mostrarMensajeFinal("Este alumno ya se encuentra inactivo (dado de baja).");
            }
        } else {
            noExisteAlumno();
        }
    }

    public void modificarNombre(Integer clave){
        Alumno alumno = buscar(clave).orElse(null);
        if (alumno != null){
            alumno.setNombreCompleto(vista.pedir("Ingrese nuevo nombre completo:"));
            vista.mostrarMensajeFinal("Nombre modificado con éxito.");
        } else {
            noExisteAlumno();
        }
    }

    public void modificarFacultad(Integer clave){
        Alumno alumno = buscar(clave).orElse(null);
        if (alumno != null){
            alumno.setFacultad(vista.pedir("Ingrese nueva facultad:"));
            vista.mostrarMensajeFinal("Facultad modificada con éxito.");
        } else {
            noExisteAlumno();
        }
    }

    public void modificarEmail(Integer clave){
        Alumno alumno = buscar(clave).orElse(null);
        if (alumno != null){
            alumno.setEmail(verificarEmail(vista.pedir("Ingrese nuevo email:")));
            vista.mostrarMensajeFinal("Email modificado con éxito.");
        } else {
            noExisteAlumno();
        }
    }

    public void modificarNumeroTelefono(Integer clave){
        Alumno alumno = buscar(clave).orElse(null);
        if (alumno != null){
            alumno.setTelefono(verificarEntero(vista.pedir("Ingrese nuevo número de teléfono:")));
            vista.mostrarMensajeFinal("Teléfono modificado con éxito.");
        } else {
            noExisteAlumno();
        }
    }

    public void noExisteAlumno(){
        vista.mostrarMensajeFinal("Error: No existe ningún alumno con ese documento de identidad.");
    }

    @Override
    protected boolean isActivo(Alumno alumno){
        return alumno.isActivo();
    }

    public String verificarEmail(String entrada){
        while (!emailCorrecto(entrada)){
            entrada = vista.pedir("Formato de email incorrecto, intente de nuevo (sin mayúsculas ni espacios):");
        }
        return entrada;
    }
}