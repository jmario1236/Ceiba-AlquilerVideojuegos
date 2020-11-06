package com.ceiba.alquiler.puerto.repositorio;

import com.ceiba.alquiler.modelo.entidad.VideoJuego;
import com.ceiba.alquiler.modelo.entidad.VideoJuegoId;

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
     * Permite buscar videojuego mediante su id
     * @param codigo
     * @return un videojuego
     */
    VideoJuego consultar(VideoJuegoId id);
    
    /**
     * Permite obtener el stock de un videojuego
     * @param codigo
     * @return true si existe, false si no.
     */
    int obtenerStock(VideoJuegoId id);

    /**
     * Permite actualizar un videojuego
     * @param videojuego
     */
	void actualizar(VideoJuego videoJuego);

	/**
     * Permite elimminar un videojuego con su id
     * @param id del videojuego
     */
	void eliminar(VideoJuegoId id);
	
	/**
     * Permite saber si un videojuego existe mediante su id
     * @param id
     * @return true si existe, false si no.
     */
	boolean existeId(VideoJuegoId id);
	
	
	/**
     * Permite actualizar el stock de un videojuego
     * @param videojuego
     */
	void actualizarStock(VideoJuego videoJuego);
}
