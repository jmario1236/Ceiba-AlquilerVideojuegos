package com.ceiba.alquiler.comando.manejador.cliente;

import org.springframework.stereotype.Component;

import com.ceiba.alquiler.servicio.cliente.ServicioEliminarCliente;
import com.ceiba.manejador.ManejadorComando;
@Component
public class ManejadorEliminarCliente implements ManejadorComando<Long>{
	
	private final ServicioEliminarCliente servicioEliminarCliente;	

	public ManejadorEliminarCliente(ServicioEliminarCliente servicioEliminarCliente) {		
		this.servicioEliminarCliente = servicioEliminarCliente;
	}

	@Override
	public void ejecutar(Long comando) {
		servicioEliminarCliente.ejecutar(comando);
	}

}
