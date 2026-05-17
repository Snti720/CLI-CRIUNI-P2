package com.criuni.controller;

import com.criuni.vista.CRIUNI;

import java.util.Map;
import java.util.Optional;

public interface IControlador<K, T> {
    void setVista(CRIUNI vista);
    Map<K, T> mostrarTodosActivo();
    Optional<T> buscar(K id);
    int verificarEntero(String entrada);
}
