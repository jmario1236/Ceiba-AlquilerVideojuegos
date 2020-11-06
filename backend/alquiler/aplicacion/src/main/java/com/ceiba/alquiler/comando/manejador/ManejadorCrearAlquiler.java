package com.ceiba.alquiler.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.ComandoRespuesta;

import com.ceiba.alquiler.comando.ComandoCrearAlquiler;
import com.ceiba.alquiler.comando.fabrica.FabricaAlquiler;
import com.ceiba.alquiler.modelo.entidad.Alquiler;
import com.ceiba.alquiler.servicio.ServicioCrearAlquiler;
import com.ceiba.manejador.ManejadorComandoRespuesta;

@Component
public class ManejadorCrearAlquiler implements ManejadorComandoRespuesta<ComandoCrearAlquiler, ComandoRespuesta<Long>>{

	private final FabricaAlquiler fabricaAlquiler;
	private final ServicioCrearAlquiler servicioCrearAlquiler;	
	
	
	public ManejadorCrearAlquiler(FabricaAlquiler fabricaAlquiler, ServicioCrearAlquiler servicioCrearAlquiler) {		
		this.fabricaAlquiler = fabricaAlquiler;
		this.servicioCrearAlquiler = servicioCrearAlquiler;
	}

	@Override
	public ComandoRespuesta<Long> ejecutar(ComandoCrearAlquiler comando) {
		Alquiler alquiler = fabricaAlquiler.crear(comando);
		return new ComandoRespuesta<>(servicioCrearAlquiler.ejecutar(alquiler));
	}

}
