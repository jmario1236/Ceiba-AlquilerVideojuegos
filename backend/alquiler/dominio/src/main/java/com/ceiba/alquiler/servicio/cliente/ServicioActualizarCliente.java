package com.ceiba.alquiler.servicio.cliente;

import com.ceiba.alquiler.modelo.entidad.Cliente;
import com.ceiba.alquiler.puerto.repositorio.RepositorioCliente;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;

public class ServicioActualizarCliente {
	private static final String EL_CLIENTE_NO_EXISTE = "El cliente no existe";

	private RepositorioCliente repositorioCliente;
	
	public ServicioActualizarCliente(RepositorioCliente repositorioCliente) {
		this.repositorioCliente = repositorioCliente;
	}

	public void ejecutar(Cliente cliente) {
		validarExisteCliente(cliente);
		repositorioCliente.actualizar(cliente);
	}

	private void validarExisteCliente(Cliente cliente) {
		boolean existe = repositorioCliente.existeId(cliente.getId());
		if(!existe) {
			throw new ExcepcionSinDatos(EL_CLIENTE_NO_EXISTE);
		}
	}

	
}
