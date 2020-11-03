package com.ceiba.alquiler.puerto.repositorio;

import com.ceiba.alquiler.modelo.entidad.AlquilerItem;

public interface RepositorioAlquilerItem {
	/**
     * Permite crear un alquilerItem
     * @param alquilerItem
     * @return el id generado
     */
	Long crear(AlquilerItem alquilerItem);
}
