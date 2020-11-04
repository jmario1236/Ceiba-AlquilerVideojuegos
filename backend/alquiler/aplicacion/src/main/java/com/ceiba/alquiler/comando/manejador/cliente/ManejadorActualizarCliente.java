package com.ceiba.alquiler.comando.manejador.cliente;

import org.springframework.stereotype.Component;

import com.ceiba.alquiler.comando.ComandoCliente;
import com.ceiba.alquiler.comando.fabrica.FabricaCliente;
import com.ceiba.alquiler.modelo.entidad.Cliente;
import com.ceiba.alquiler.servicio.cliente.ServicioCrearCliente;
import com.ceiba.manejador.ManejadorComando;

@Component
public class ManejadorActualizarCliente implements ManejadorComando<ComandoCliente>{
	
	private final FabricaCliente fabricaCliente;
	private final ServicioCrearCliente servicioCrearCliente;
	
	public ManejadorActualizarCliente(FabricaCliente fabricaCliente, ServicioCrearCliente servicioCrearCliente) {		
		this.fabricaCliente = fabricaCliente;
		this.servicioCrearCliente = servicioCrearCliente;
	}

	@Override
	public void ejecutar(ComandoCliente comando) {
		Cliente cliente = fabricaCliente.crear(comando);
		servicioCrearCliente.ejecutar(cliente);
	}
	
	
}
