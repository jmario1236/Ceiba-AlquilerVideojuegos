package com.ceiba.alquiler.comando.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.alquiler.comando.ComandoVideoJuego;
import com.ceiba.alquiler.modelo.entidad.VideoJuego;
import com.ceiba.alquiler.modelo.entidad.VideoJuegoId;

@Component
public class FabricaVideoJuego {
	public VideoJuego crear(ComandoVideoJuego comando) {
		return new VideoJuego(crearId(comando.getId()), 
				comando.getCodigo(), 
				comando.getNombre(), 
				comando.getGenero(),
				comando.getPrecio(), 
				comando.getStock());
	}
	
	public VideoJuegoId crearId(Long id) {
		return id == null? null :new VideoJuegoId(id);
	}
}
