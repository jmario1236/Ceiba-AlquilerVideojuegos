package com.ceiba.alquiler.puerto.dao;

import java.util.List;

import com.ceiba.alquiler.modelo.dto.DtoVideoJuego;

public interface DaoVideoJuego {
	 /**
     * Permite listar los videojuegos del sistemas
     * @return los videojuegos
     */
	List<DtoVideoJuego> listar();
}
