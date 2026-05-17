package com.criuni.controller;

import com.criuni.model.Libro;
import com.criuni.repository.RepositorioLibro;

public class ControladorLibro extends ControladorCRUDBase<String, Libro> {

    public ControladorLibro(RepositorioLibro repoLibro){
        repo = repoLibro;
    }

    public boolean crearLibro(Libro modelo) {
        if (!repo.obtenerTodos().containsKey(modelo.getClave())){
            repo.agregar(modelo);
            vista.mostrarMensajeFinal("Libro '" + modelo.getTitulo() + "' registrado en stock exitosamente.");
            return true;
        } else {
            Libro libro = repo.obtenerTodos().get(modelo.getClave());
            vista.mostrarMensaje("\nEl libro '" + libro.getTitulo() + "' (ISBN: " + libro.getIsbn() + ") ya existe en el sistema.");
            vista.mostrarMensaje("¿Qué desea hacer?\n\t1. Sí, añadir más copias al stock actual\n\t2. No, cancelar operación\n\t3. Es un error, volver a intentar");
            switch (vista.respuesta()){
                case 1:
                    int cantidad = verificarEntero(vista.pedir("Ingrese cuántas copias desea agregar:"));
                    if (1 <= cantidad){
                        libro.agregarLibros(cantidad);
                        vista.mostrarMensajeFinal("Stock actualizado con éxito. Nuevo stock total: " + libro.getCantidad() + " unidades.");
                    } else {
                        vista.mostrarMensajeFinal("Cantidad inválida.");
                    }
                    return true;
                case 2:
                    vista.mostrarMensajeFinal("Operación cancelada.");
                    return true;
                case 3:
                    return false;
                default:
                    vista.mostrarMensajeFinal("Respuesta inválida. Operación cancelada.");
                    return true;
            }
        }
    }

    @Override
    protected boolean isActivo(Libro libro){
        return libro.getCantidad() > 0;
    }

    public void noExisteLibro(){
        vista.mostrarMensajeFinal("Error: No existe ningún libro con el ISBN especificado.");
    }

    public void modificarTitulo(String isbn){
        Libro libro = buscar(isbn).orElse(null);
        if (libro != null){
            libro.setTitulo(vista.pedir("Ingrese nuevo título:"));
            vista.mostrarMensajeFinal("Título del libro modificado.");
        } else {
            noExisteLibro();
        }
    }

    public void modificarAutor(String isbn){
        Libro libro = buscar(isbn).orElse(null);
        if (libro != null){
            libro.setAutor(vista.pedir("Ingrese nuevo autor:"));
            vista.mostrarMensajeFinal("Autor del libro modificado.");
        } else {
            noExisteLibro();
        }
    }

    public void modificarEditorial(String isbn){
        Libro libro = buscar(isbn).orElse(null);
        if (libro != null){
            libro.setEditorial(vista.pedir("Ingrese nueva editorial:"));
            vista.mostrarMensajeFinal("Editorial del libro modificada.");
        } else {
            noExisteLibro();
        }
    }

    public void modificarAnhoPublicacion(String isbn){
        Libro libro = buscar(isbn).orElse(null);
        if (libro != null){
            libro.setAnhoPublicacion(verificarEntero(vista.pedir("Ingrese nuevo año de publicación:")));
            vista.mostrarMensajeFinal("Año de publicación modificado.");
        } else {
            noExisteLibro();
        }
    }

    public void borrar(String isbn){
        Libro libro = buscar(isbn).orElse(null);
        if (libro != null){
            if (libro.getCantidad() > 0){
                libro.restarLibros(libro.getCantidad());
                vista.mostrarMensajeFinal("El stock del libro ha sido retirado por completo. Queda fuera de catálogo.");
            } else {
                vista.mostrarMensajeFinal("Este libro ya se encontraba fuera de stock.");
            }
        } else {
            noExisteLibro();
        }
    }
}