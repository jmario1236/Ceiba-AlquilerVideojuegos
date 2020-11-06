package com.ceiba.alquiler.comando.manejador.videojuego;

import org.springframework.stereotype.Component;

import com.ceiba.alquiler.comando.fabrica.FabricaVideoJuego;
import com.ceiba.alquiler.servicio.videojuego.ServicioEliminarVideoJuego;
import com.ceiba.manejador.ManejadorComando;

@Component
public class ManejadorEliminarVideoJuego implements ManejadorComando<Long>{

	
	private final ServicioEliminarVideoJuego servicioEliminarVideoJuego;
	private final FabricaVideoJuego fabricaVideoJuego;	

	public ManejadorEliminarVideoJuego(ServicioEliminarVideoJuego servicioEliminarVideoJuego,
			FabricaVideoJuego fabricaVideoJuego) {
		this.servicioEliminarVideoJuego = servicioEliminarVideoJuego;
		this.fabricaVideoJuego = fabricaVideoJuego;
	}

	public void ejecutar(Long id) {
		servicioEliminarVideoJuego.ejecutar(fabricaVideoJuego.crearId(id));
	}

}
