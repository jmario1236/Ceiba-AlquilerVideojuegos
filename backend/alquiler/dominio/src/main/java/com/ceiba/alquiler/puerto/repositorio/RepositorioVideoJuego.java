package com.ceiba.alquiler.puerto.repositorio;

import com.ceiba.alquiler.modelo.entidad.VideoJuego;

public interface RepositorioVideoJuego {
	
	/**
     * Permite crear un videojuego
     * @param videoJuego
     * @return el id generado
     */
	Long crear(VideoJuego videoJuego);

	/**
     * Permite saber si un videojuego existe mediante su codigo
     * @param codigo
     * @return true si existe, false si no.
     */
    boolean existe(String codigo);

    /**
     * Permite actualizar un videojuego
     * @param videojuego
     */
	void actualizar(VideoJuego videoJuego);

	/**
     * Permite elimminar un videojuego con su id
     * @param id del videojuego
     */
	void eliminar(Long id);
}
