package com.ceiba.alquiler.servicio;

import com.ceiba.alquiler.modelo.entidad.Cliente;
import com.ceiba.alquiler.puerto.repositorio.RepositorioCliente;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

public class ServicioCrearCliente {
	private static final String  EL_CLIENTE_YA_EXISTE_EN_EL_SISTEMA = "El cliente ya existe en el sistema";
	
	private RepositorioCliente repositorioCliente;

	public ServicioCrearCliente(RepositorioCliente repositorioCliente) {
		this.repositorioCliente = repositorioCliente;
	}
	
	public Long ejecutar(Cliente cliente) {
		validarExistenciaIdentificacion(cliente);
		return repositorioCliente.crear(cliente);
	}
	
	private void validarExistenciaIdentificacion(Cliente cliente) {
		boolean existe = repositorioCliente.existe(cliente.getIdentificacion());
		if(existe) {
			throw new ExcepcionDuplicidad(EL_CLIENTE_YA_EXISTE_EN_EL_SISTEMA);
		}
	}

}
