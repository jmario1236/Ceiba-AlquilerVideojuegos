package com.ceiba.alquiler.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.alquiler.comando.ComandoCrearAlquiler;
import com.ceiba.alquiler.comando.fabrica.FabricaAlquiler;
import com.ceiba.alquiler.servicio.ServicioFinalizarAlquiler;
import com.ceiba.manejador.ManejadorComando;

@Component
public class ManejadorFinalizarAlquiler implements ManejadorComando<ComandoCrearAlquiler>{
	private final FabricaAlquiler fabricaAlquiler;
	private final ServicioFinalizarAlquiler servicioFinalizarAlquiler;

	public ManejadorFinalizarAlquiler(FabricaAlquiler fabricaAlquiler,
			ServicioFinalizarAlquiler servicioFinalizarAlquiler) {

		this.fabricaAlquiler = fabricaAlquiler;
		this.servicioFinalizarAlquiler = servicioFinalizarAlquiler;
	}

	@Override
	public void ejecutar(ComandoCrearAlquiler comando) {
		servicioFinalizarAlquiler.ejecutar(fabricaAlquiler.crear(comando));		
	}

	
}
