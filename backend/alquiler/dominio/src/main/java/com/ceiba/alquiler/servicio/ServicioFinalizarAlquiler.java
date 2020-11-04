package com.ceiba.alquiler.servicio;

import com.ceiba.alquiler.modelo.entidad.Alquiler;
import com.ceiba.alquiler.puerto.repositorio.RepositorioAlquiler;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;

public class ServicioFinalizarAlquiler {

	private static final String ALQUILER_NO_VALIDO = "Alquiler no valido";
	private RepositorioAlquiler repositorioAlquiler;
	
	public ServicioFinalizarAlquiler(RepositorioAlquiler repositorioAlquiler) {
		this.repositorioAlquiler = repositorioAlquiler;
	}

	public void ejecutar(Alquiler alquiler) {
		validarAlquiler(alquiler);
		alquiler = repositorioAlquiler.consultar(alquiler.getId());
		alquiler.finalizarAlquiler();
		repositorioAlquiler.finalizar(alquiler);
	}
	
	private void validarAlquiler(Alquiler alquiler) {
		boolean existe = repositorioAlquiler.existe(alquiler.getId());
		if(!existe) {
			throw new ExcepcionSinDatos(ALQUILER_NO_VALIDO);
		}
	}

}
