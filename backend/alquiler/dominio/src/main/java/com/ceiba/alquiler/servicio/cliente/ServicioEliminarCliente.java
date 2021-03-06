package com.ceiba.alquiler.servicio.cliente;

import com.ceiba.alquiler.modelo.entidad.ClienteId;
import com.ceiba.alquiler.puerto.repositorio.RepositorioCliente;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;

public class ServicioEliminarCliente {
	private static final String EL_CLIENTE_NO_EXISTE = "El cliente no existe";
	private RepositorioCliente repositorioCliente;
	
	public ServicioEliminarCliente(RepositorioCliente repositorioCliente) {
		this.repositorioCliente = repositorioCliente;
	}
	
	public void ejecutar(ClienteId id) {
		validarExisteCliente(id);
		repositorioCliente.eliminar(id);
	}
	
	private void validarExisteCliente(ClienteId id) {
		boolean existe = repositorioCliente.existeId(id);
		if(!existe) {
			throw new ExcepcionSinDatos(EL_CLIENTE_NO_EXISTE);
		}
	}
}
