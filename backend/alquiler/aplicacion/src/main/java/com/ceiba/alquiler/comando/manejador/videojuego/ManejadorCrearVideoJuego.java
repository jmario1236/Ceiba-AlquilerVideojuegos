package com.ceiba.alquiler.comando.manejador.videojuego;

import org.springframework.stereotype.Component;

import com.ceiba.ComandoRespuesta;
import com.ceiba.alquiler.comando.ComandoVideoJuego;
import com.ceiba.alquiler.comando.fabrica.FabricaVideoJuego;
import com.ceiba.alquiler.modelo.entidad.VideoJuego;
import com.ceiba.alquiler.servicio.videojuego.ServicioCrearVideoJuego;
import com.ceiba.manejador.ManejadorComandoRespuesta;

@Component
public class ManejadorCrearVideoJuego implements ManejadorComandoRespuesta<ComandoVideoJuego, ComandoRespuesta<Long>>{

	private final FabricaVideoJuego fabricaVideoJuego;
	private final ServicioCrearVideoJuego servicioCrearVideoJuego;
	
	
	
	public ManejadorCrearVideoJuego(FabricaVideoJuego fabricaVideoJuego,
			ServicioCrearVideoJuego servicioCrearVideoJuego) {		
		this.fabricaVideoJuego = fabricaVideoJuego;
		this.servicioCrearVideoJuego = servicioCrearVideoJuego;
	}

	public ComandoRespuesta<Long> ejecutar(ComandoVideoJuego comandoVideoJuego) {
		VideoJuego videoJuego = fabricaVideoJuego.crear(comandoVideoJuego);
		return new ComandoRespuesta<>(servicioCrearVideoJuego.ejecutar(videoJuego));
	}

}
