package com.criuni.controller;

import com.criuni.model.IModelo;
import com.criuni.repository.RepositorioBase;
import com.criuni.vista.CRIUNI;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class ControladorCRUDBase<K, T extends IModelo<K>> implements IControlador<K, T> {
    protected RepositorioBase<K, T> repo;
    protected CRIUNI vista;

    protected T mostrar(K id){
        return repo.obtenerTodos().get(id);
    }

    protected boolean existe(K id){
        return repo.obtenerTodos().containsKey(id);
    }

    @Override
    public Map<K, T> mostrarTodosActivo(){
        if (repo.obtenerTodos()==null || repo.obtenerTodos().isEmpty()){
            vista.mostrarMensajeFinal("Aun no hay datos");
            return Collections.emptyMap();
        } else {
            return activos(repo.obtenerTodos());
        }
    }

    public Map<K, T> mostrarTodos() {
        if (repo.obtenerTodos()==null || repo.obtenerTodos().isEmpty()){
            vista.mostrarMensajeFinal("Aun no hay datos");
            return Collections.emptyMap();
        } else {
            return repo.obtenerTodos();
        }
    }

    protected Map<K, T> activos(Map<K, T> repoTotal){
        Map<K, T> activos = new HashMap<>();
        repoTotal.forEach((clave, modelo) -> {
            if (isActivo(modelo)){
                activos.put(clave, modelo);
            }
        });
        return activos;
    }

    protected boolean isActivo(T modelo){
        return false;
    }

    public final void setVista(CRIUNI vista){
        this.vista = vista;
    }

    @Override
    public Optional<T> buscar(K id){
        if (existe(id)){
            return Optional.of(mostrar(id));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public int verificarEntero(String entrada) {
        while (!entrada.matches("\\d+")) {
            vista.mostrarMensaje("Entrada inválida, ingrese número sin simbolos ni letras");
            entrada = vista.pedir("Intente de nuevo: ");
        }
        return Integer.parseInt(entrada);
    }
}
