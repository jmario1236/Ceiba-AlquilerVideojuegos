package com.ceiba.alquiler.servicio;

import java.util.List;

import com.ceiba.alquiler.modelo.entidad.Alquiler;
import com.ceiba.alquiler.modelo.entidad.AlquilerItem;
import com.ceiba.alquiler.modelo.entidad.Cliente;
import com.ceiba.alquiler.puerto.repositorio.RepositorioAlquiler;
import com.ceiba.alquiler.puerto.repositorio.RepositorioCliente;
import com.ceiba.alquiler.puerto.repositorio.RepositorioVideoJuego;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

public class ServicioCrearAlquiler {
	
	private static final String CLIENTE_TIENE_UN_ALQUILER_VIGENTE = "El cliente tiene un alquiler vigente en el sistema";
	private static final String VIDEOJUEGO_NO_EXISTE = "VideoJuego no existe";
	private static final String EL_CLIENTE_NO_EXISTE = "El cliente no existe";
	
	private RepositorioAlquiler repositorioAlquiler;
	private RepositorioCliente repositorioCliente;
	private RepositorioVideoJuego repositorioVideoJuego;

	public ServicioCrearAlquiler(RepositorioAlquiler repositorioAlquiler,
			RepositorioCliente repositorioCliente, RepositorioVideoJuego repositorioVideoJuego) {
		this.repositorioAlquiler = repositorioAlquiler;
		this.repositorioCliente = repositorioCliente;
		this.repositorioVideoJuego = repositorioVideoJuego;
	}

	public Long ejecutar(Alquiler alquiler) {
		validarSiClienteTieneAlquilerVigente(alquiler.getCliente());
		validarVideoJuegosExisten(alquiler.getItems());
		validarClienteExiste(alquiler.getCliente());
		alquiler.cambiarEstadoVigente();		
		return repositorioAlquiler.crear(alquiler);
	}
	
	public void validarClienteExiste(Cliente cliente) {
		boolean existe = repositorioCliente.existe(cliente.getIdentificacion());
		if(!existe) {
			throw new ExcepcionSinDatos(EL_CLIENTE_NO_EXISTE);
		}
	}
	
	public void validarVideoJuegosExisten(List<AlquilerItem> items) {
		for(AlquilerItem item : items) {
			boolean existe = repositorioVideoJuego.existe(item.getVideoJuego().getCodigo());
			if(!existe) {
				throw new ExcepcionSinDatos(VIDEOJUEGO_NO_EXISTE);
			}
		}
	}
	
	private void validarSiClienteTieneAlquilerVigente(Cliente cliente) {
		boolean tieneVigente = repositorioAlquiler.existeAlquilerVigente(cliente.getIdentificacion());
		if(tieneVigente) {
			throw new ExcepcionValorInvalido(CLIENTE_TIENE_UN_ALQUILER_VIGENTE);
		}
	}

}
