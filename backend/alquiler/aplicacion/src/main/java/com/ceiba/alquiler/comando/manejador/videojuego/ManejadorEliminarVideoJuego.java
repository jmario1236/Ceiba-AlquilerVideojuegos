package com.ceiba.alquiler.comando.manejador.videojuego;

import org.springframework.stereotype.Component;

import com.ceiba.alquiler.servicio.videojuego.ServicioEliminarVideoJuego;
import com.ceiba.manejador.ManejadorComando;

@Component
public class ManejadorEliminarVideoJuego implements ManejadorComando<Long>{

	
	private final ServicioEliminarVideoJuego servicioEliminarVideoJuego;

	public ManejadorEliminarVideoJuego(ServicioEliminarVideoJuego servicioEliminarVideoJuego) {		
		this.servicioEliminarVideoJuego = servicioEliminarVideoJuego;
	}

	public void ejecutar(Long id) {
		servicioEliminarVideoJuego.ejecutar(id);
	}

}
