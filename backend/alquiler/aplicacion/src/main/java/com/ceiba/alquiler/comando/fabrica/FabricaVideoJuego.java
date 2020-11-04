package com.ceiba.alquiler.comando.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.alquiler.comando.ComandoVideoJuego;
import com.ceiba.alquiler.modelo.entidad.VideoJuego;

@Component
public class FabricaVideoJuego {
	public VideoJuego crear(ComandoVideoJuego comando) {
		return new VideoJuego(comando.getId(), comando.getCodigo(), comando.getNombre(), comando.getGenero(),
				comando.getPrecio(), comando.getStock());
	}
}
