package com.ceiba.alquiler.servicio;

import java.util.List;

import com.ceiba.alquiler.modelo.entidad.Alquiler;
import com.ceiba.alquiler.modelo.entidad.AlquilerItem;
import com.ceiba.alquiler.modelo.entidad.VideoJuego;
import com.ceiba.alquiler.puerto.repositorio.RepositorioAlquiler;
import com.ceiba.alquiler.puerto.repositorio.RepositorioVideoJuego;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;

public class ServicioFinalizarAlquiler {

	private static final String ALQUILER_NO_VALIDO = "Alquiler no valido";
	private RepositorioAlquiler repositorioAlquiler;
	private RepositorioVideoJuego repositorioVideoJuego;
	
	public ServicioFinalizarAlquiler(RepositorioAlquiler repositorioAlquiler, RepositorioVideoJuego repositorioVideoJuego) {
		this.repositorioAlquiler = repositorioAlquiler;
		this.repositorioVideoJuego = repositorioVideoJuego;
	}

	public void ejecutar(Alquiler alquiler) {
		validarAlquiler(alquiler);
		alquiler = repositorioAlquiler.consultar(alquiler.getId());
		alquiler.finalizarAlquiler();
		actualizarStock(alquiler.getItems());
		repositorioAlquiler.finalizar(alquiler);
	}
	
	private void actualizarStock(List<AlquilerItem> items) {
		for(AlquilerItem item: items) {
			VideoJuego videoJuego = repositorioVideoJuego.consultar(item.getVideoJuego());	
			videoJuego.agregarAlStock(item.getCantidad());
			repositorioVideoJuego.actualizarStock(videoJuego);
		}
	}
	
	private void validarAlquiler(Alquiler alquiler) {
		boolean existe = repositorioAlquiler.existe(alquiler.getId());
		if(!existe) {
			throw new ExcepcionSinDatos(ALQUILER_NO_VALIDO);
		}
	}

}
