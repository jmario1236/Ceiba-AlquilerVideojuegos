package com.ceiba.alquiler.comando.manejador.cliente;

import org.springframework.stereotype.Component;

import com.ceiba.alquiler.comando.fabrica.FabricaCliente;
import com.ceiba.alquiler.servicio.cliente.ServicioEliminarCliente;
import com.ceiba.manejador.ManejadorComando;
@Component
public class ManejadorEliminarCliente implements ManejadorComando<Long>{
	
	private final ServicioEliminarCliente servicioEliminarCliente;	
	private final FabricaCliente fabricaCliente;

	public ManejadorEliminarCliente(ServicioEliminarCliente servicioEliminarCliente, FabricaCliente fabricaCliente) {		
		this.servicioEliminarCliente = servicioEliminarCliente;
		this.fabricaCliente = fabricaCliente;
	}

	@Override
	public void ejecutar(Long comando) {
		servicioEliminarCliente.ejecutar(fabricaCliente.crearId(comando));
	}

}
