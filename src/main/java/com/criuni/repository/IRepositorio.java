package com.criuni.repository;

import java.util.Map;

public interface IRepositorio<K, T>{
    void agregar(T modelo);
    Map<K, T> obtenerTodos();
}