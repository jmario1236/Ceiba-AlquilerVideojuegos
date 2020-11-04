package com.ceiba.alquiler.videojuego.comando.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.alquiler.modelo.entidad.VideoJuego;
import com.ceiba.alquiler.videojuego.comando.ComandoVideoJuego;

@Component
public class FabricaVideoJuego {
	public VideoJuego crear(ComandoVideoJuego comando) {
		return new VideoJuego(comando.getId(), comando.getCodigo(), comando.getNombre(), comando.getGenero(),
				comando.getPrecio(), comando.getStock());
	}
}
