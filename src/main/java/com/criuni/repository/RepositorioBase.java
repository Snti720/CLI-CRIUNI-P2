package com.criuni.repository;

import com.criuni.model.IModelo;

import java.util.Map;

public abstract class RepositorioBase<K, T extends IModelo<K>> implements IRepositorio<K, T>{
    protected Map<K, T> modelMap;

    @Override
    public void agregar(T modelo){
        modelMap.put(modelo.getClave(), modelo);
    }

    @Override
    public Map<K, T> obtenerTodos(){
        return modelMap;
    }
}
