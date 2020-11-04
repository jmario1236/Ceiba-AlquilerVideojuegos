package com.ceiba.alquiler.comando.manejador.videojuego;

import org.springframework.stereotype.Component;

import com.ceiba.alquiler.comando.ComandoVideoJuego;
import com.ceiba.alquiler.comando.fabrica.FabricaVideoJuego;
import com.ceiba.alquiler.servicio.videojuego.ServicioActualizarVideoJuego;
import com.ceiba.manejador.ManejadorComando;

@Component
public class ManejadorActualizarVideoJuego implements ManejadorComando<ComandoVideoJuego>{
	
	private final FabricaVideoJuego fabricaVideoJuego;
	private final ServicioActualizarVideoJuego servicioActualizarVideoJuego;
	
	

	public ManejadorActualizarVideoJuego(FabricaVideoJuego fabricaVideoJuego,
			ServicioActualizarVideoJuego servicioActualizarVideoJuego) {
		super();
		this.fabricaVideoJuego = fabricaVideoJuego;
		this.servicioActualizarVideoJuego = servicioActualizarVideoJuego;
	}

	public void ejecutar(ComandoVideoJuego comandoVideoJuego) {
		servicioActualizarVideoJuego.ejecutar(fabricaVideoJuego.crear(comandoVideoJuego));		
	}

}
