package com.ceiba.alquiler.servicio.videojuego;


import com.ceiba.alquiler.puerto.repositorio.RepositorioVideoJuego;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;

public class ServicioEliminarVideoJuego {
	private static final String VIDEOJUEGO_ACTUALIZAR_NO_EXISTE = "Videojuego no existe";
	private RepositorioVideoJuego repositorioVideoJuego;
	
	public ServicioEliminarVideoJuego(RepositorioVideoJuego repositorioVideoJuego) {
		this.repositorioVideoJuego = repositorioVideoJuego;
	}

	public void ejecutar(Long id) {
		validarExisteVideoJuego(id);
		repositorioVideoJuego.eliminar(id);
	}

	private void validarExisteVideoJuego(Long id) {
		boolean existe = repositorioVideoJuego.existeId(id);
		if(!existe) {
			throw new ExcepcionSinDatos(VIDEOJUEGO_ACTUALIZAR_NO_EXISTE);
		}
	}
}
