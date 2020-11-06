package com.ceiba.alquiler.comando.fabrica;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ceiba.alquiler.comando.ComandoCrearAlquiler;
import com.ceiba.alquiler.modelo.entidad.Alquiler;
import com.ceiba.alquiler.modelo.entidad.AlquilerItem;
import com.ceiba.alquiler.modelo.entidad.ClienteId;
import com.ceiba.alquiler.modelo.entidad.VideoJuegoId;

@Component
public class FabricaAlquiler {
	public Alquiler crear(ComandoCrearAlquiler comando) {
		List<AlquilerItem> items = comando.getItems().stream().map(item -> new AlquilerItem(item.getId(),
				new VideoJuegoId(item.getVideoJuego()), item.getCantidad(), item.getPrecio()))
				.collect(Collectors.toList());
		return new Alquiler(comando.getId(), new ClienteId(comando.getCliente()), comando.getFechaAlquiler(),
				comando.getFechaMaximaEntrega(), items);
	}
}
