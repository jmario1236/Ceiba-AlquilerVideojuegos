package com.ceiba.alquiler.comando.manejador.cliente;

import org.springframework.stereotype.Component;

import com.ceiba.alquiler.comando.ComandoCliente;
import com.ceiba.alquiler.comando.fabrica.FabricaCliente;
import com.ceiba.alquiler.modelo.entidad.Cliente;
import com.ceiba.alquiler.servicio.cliente.ServicioActualizarCliente;
import com.ceiba.manejador.ManejadorComando;

@Component
public class ManejadorActualizarCliente implements ManejadorComando<ComandoCliente>{
	
	private final FabricaCliente fabricaCliente;
	private final ServicioActualizarCliente servicioActualizarCliente;
	
	public ManejadorActualizarCliente(FabricaCliente fabricaCliente, ServicioActualizarCliente servicioActualizarCliente) {		
		this.fabricaCliente = fabricaCliente;
		this.servicioActualizarCliente = servicioActualizarCliente;
	}

	@Override
	public void ejecutar(ComandoCliente comando) {
		Cliente cliente = fabricaCliente.crear(comando);
		servicioActualizarCliente.ejecutar(cliente);
	}
	
	
}
