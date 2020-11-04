package com.ceiba.alquiler.comando.manejador.cliente;

import org.springframework.stereotype.Component;

import com.ceiba.ComandoRespuesta;
import com.ceiba.alquiler.comando.ComandoCliente;
import com.ceiba.alquiler.comando.fabrica.FabricaCliente;
import com.ceiba.alquiler.modelo.entidad.Cliente;
import com.ceiba.alquiler.servicio.cliente.ServicioCrearCliente;
import com.ceiba.manejador.ManejadorComandoRespuesta;

@Component
public class ManejadorCrearCliente implements ManejadorComandoRespuesta<ComandoCliente, ComandoRespuesta<Long>>{
	private final FabricaCliente fabricaCliente;
	private final ServicioCrearCliente servicioCrearCliente;
	
	public ManejadorCrearCliente(FabricaCliente fabricaCliente, ServicioCrearCliente servicioCrearCliente) {		
		this.fabricaCliente = fabricaCliente;
		this.servicioCrearCliente = servicioCrearCliente;
	}

	@Override
	public ComandoRespuesta<Long> ejecutar(ComandoCliente comando) {	
		Cliente cliente = fabricaCliente.crear(comando);
		return new ComandoRespuesta<>(servicioCrearCliente.ejecutar(cliente));
	}
	
	
}
