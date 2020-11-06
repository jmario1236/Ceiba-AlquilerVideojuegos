package com.ceiba.alquiler.puerto.repositorio;

import com.ceiba.alquiler.modelo.entidad.Alquiler;
import com.ceiba.alquiler.modelo.entidad.ClienteId;

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
	boolean existeAlquilerVigente(ClienteId cliente);
	
	/**
     * Permite el alquiler existe
     * @param id
     * @return true si existe, false si no
     */
	boolean existe(Long id);
	
	/**
     * Permite consultar un alquiler a traves de su id
     * @param id
     * @return alquiler consultado
     */
	Alquiler consultar(Long id);
	
	/**
     * Permite el finalizar alquiler 
     * @param alquilers
     * 
     */
	void finalizar(Alquiler alquiler);
}
