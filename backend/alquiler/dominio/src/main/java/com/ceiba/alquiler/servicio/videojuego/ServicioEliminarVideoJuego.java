package com.ceiba.alquiler.servicio.videojuego;

import com.ceiba.alquiler.modelo.entidad.VideoJuego;
import com.ceiba.alquiler.puerto.repositorio.RepositorioVideoJuego;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;

public class ServicioEliminarVideoJuego {
	private static final String VIDEOJUEGO_ACTUALIZAR_NO_EXISTE = "Videojuego no existe";
	private RepositorioVideoJuego repositorioVideoJuego;
	
	public ServicioEliminarVideoJuego(RepositorioVideoJuego repositorioVideoJuego) {
		this.repositorioVideoJuego = repositorioVideoJuego;
	}

	public void ejecutar(VideoJuego videoJuego) {
		validarExisteVideoJuego(videoJuego);
		repositorioVideoJuego.eliminar(videoJuego.getId());
	}

	private void validarExisteVideoJuego(VideoJuego videoJuego) {
		boolean existe = repositorioVideoJuego.existe(videoJuego.getCodigo());
		if(!existe) {
			throw new ExcepcionSinDatos(VIDEOJUEGO_ACTUALIZAR_NO_EXISTE);
		}
	}
}
