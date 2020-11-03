package com.ceiba.alquiler.servicio.videojuego;

import com.ceiba.alquiler.modelo.entidad.VideoJuego;
import com.ceiba.alquiler.puerto.repositorio.RepositorioVideoJuego;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

public class ServicioCrearVideoJuego {
	private static final String EL_VIDEOJUEGO_YA_EXISTE = "El videojuego ya existe en el sistema";

	private RepositorioVideoJuego repositorioVideoJuego;
	
	public ServicioCrearVideoJuego(RepositorioVideoJuego repositorioVideoJuego) {
		this.repositorioVideoJuego = repositorioVideoJuego;
	}

	public Long ejecutar(VideoJuego videoJuego) {	
		validarExistencia(videoJuego);
		return repositorioVideoJuego.crear(videoJuego);
	}
	
	private void validarExistencia(VideoJuego videoJuego) {
		boolean existe = repositorioVideoJuego.existe(videoJuego.getCodigo());
		if(existe) {
			throw new ExcepcionDuplicidad(EL_VIDEOJUEGO_YA_EXISTE);
		}
	}

}
