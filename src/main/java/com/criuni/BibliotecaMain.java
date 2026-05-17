package com.criuni;

import com.criuni.controller.ControladorAlumno;
import com.criuni.controller.ControladorLibro;
import com.criuni.controller.ControladorPrestamo;
import com.criuni.repository.RepositorioAlumno;
import com.criuni.repository.RepositorioLibro;
import com.criuni.repository.RepositorioPrestamo;
import com.criuni.vista.CRIUNI;

public class BibliotecaMain {
    public static void main(String[] args){
        RepositorioAlumno repoAlumno = new RepositorioAlumno();
        RepositorioLibro repoLibro = new RepositorioLibro();
        RepositorioPrestamo repoPrestamo = new RepositorioPrestamo();
        ControladorAlumno alumnoControlador = new ControladorAlumno(repoAlumno);
        ControladorLibro libroControlador = new ControladorLibro(repoLibro);
        ControladorPrestamo prestamoControlador = new ControladorPrestamo(repoPrestamo);
        CRIUNI vista = new CRIUNI(alumnoControlador, libroControlador, prestamoControlador);
        alumnoControlador.setVista(vista);
        libroControlador.setVista(vista);
        prestamoControlador.setVista(vista);
        vista.inicio();
    }
}
