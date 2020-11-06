package com.ceiba.alquiler.comando.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.alquiler.comando.ComandoCliente;
import com.ceiba.alquiler.modelo.entidad.Cliente;
import com.ceiba.alquiler.modelo.entidad.ClienteId;

@Component
public class FabricaCliente {

	public Cliente crear(ComandoCliente comandoCliente) {
		return new Cliente(crearId(comandoCliente.getId()), comandoCliente.getIdentificacion(),
				comandoCliente.getNombre(), comandoCliente.getApellido(), comandoCliente.getTelefono(),
				comandoCliente.getDireccion());
	}

	public ClienteId crearId(Long id) {
		return id == null ? null : new ClienteId(id);
	}
}
