package com.ceiba.alquiler.puerto.dao;

import java.util.List;

import com.ceiba.alquiler.modelo.dto.DtoVideoJuego;

public interface DaoVideoJuego {
	 /**
     * Permite listar los videojuegos del sistemas
     * @return los videojuegos
     */
	List<DtoVideoJuego> listar();
	
	 /**
     * Permite listar los videojuegos del sistemas
     * @param criterio de busqueda
     * @return los videojuegos
     */
	List<DtoVideoJuego> consultar(String criterio);
	
	 /**
     * Obtiene un videojuego del sistema
     * @param id del videojuego
     * @return el videojuego
     */
	DtoVideoJuego consultarId(Long id);
}
