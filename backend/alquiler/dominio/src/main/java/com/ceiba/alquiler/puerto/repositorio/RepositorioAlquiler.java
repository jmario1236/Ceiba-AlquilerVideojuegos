package com.ceiba.alquiler.puerto.repositorio;

import com.ceiba.alquiler.modelo.entidad.Alquiler;

public interface RepositorioAlquiler {

	/**
     * Permite crear un alquiler
     * @param alquiler
     * @return el id generado
     */
	Long crear(Alquiler alquiler);
	
	/**
     * Permite saber si un cliente tiene un alquiler vigente
     * @param identificacion
     * @return true si existe, false si no
     */
	boolean existeAlquilerVigente(String identificacion);
	
}
