package com.ceiba.alquiler.servicio;

import com.ceiba.alquiler.modelo.entidad.Cliente;
import com.ceiba.alquiler.puerto.repositorio.RepositorioCliente;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;

public class ServicioEliminarCliente {
	private static final String EL_CLIENTE_NO_EXISTE = "El cliente no existe";
	private RepositorioCliente repositorioCliente;
	
	public ServicioEliminarCliente(RepositorioCliente repositorioCliente) {
		this.repositorioCliente = repositorioCliente;
	}
	
	public void ejecutar(Cliente cliente) {
		validarExisteCliente(cliente.getIdentificacion());
		repositorioCliente.eliminar(cliente.getId());
	}
	
	private void validarExisteCliente(String identificacion) {
		boolean existe = repositorioCliente.existe(identificacion);
		if(!existe) {
			throw new ExcepcionSinDatos(EL_CLIENTE_NO_EXISTE);
		}
	}
}
