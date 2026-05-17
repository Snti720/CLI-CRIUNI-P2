package com.criuni.model;

public class Libro implements IModelo<String> {
    private String titulo;
    private String autor;
    private String editorial;
    final private String isbn;
    private int anhoPublicacion;
    private int cantidad;

    public Libro(String titulo, String autor, String editorial, String isbn, int anhoPublicacion, int cantidad){
        this.titulo = titulo;
        this.autor = autor;
        this.editorial = editorial;
        this.isbn = isbn;
        this.anhoPublicacion = anhoPublicacion;
        this.cantidad = cantidad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getAnhoPublicacion() {
        return anhoPublicacion;
    }

    public void setAnhoPublicacion(int anhoPublicacion) {
        this.anhoPublicacion = anhoPublicacion;
    }

    public int getCantidad() {
        return cantidad;
    }

    private void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String getClave() {
        return getIsbn();
    }

    @Override
    public String toString() {
        return titulo + " por " + autor + " (Stock: " + cantidad + ")";
    }

    public void agregarLibros(int cantidad){
        setCantidad(getCantidad() + cantidad);
    }

    public void restarLibros(int cantidad){
        setCantidad(getCantidad()-cantidad);
    }
}
