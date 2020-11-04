package com.ceiba.alquiler.videojuego.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.alquiler.servicio.videojuego.ServicioActualizarVideoJuego;
import com.ceiba.alquiler.videojuego.comando.ComandoVideoJuego;
import com.ceiba.alquiler.videojuego.comando.fabrica.FabricaVideoJuego;
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
