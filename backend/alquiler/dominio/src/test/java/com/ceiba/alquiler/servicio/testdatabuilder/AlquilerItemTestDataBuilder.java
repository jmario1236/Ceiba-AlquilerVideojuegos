package com.ceiba.alquiler.servicio.testdatabuilder;

import java.util.ArrayList;
import java.util.List;

import com.ceiba.alquiler.modelo.entidad.AlquilerItem;
import com.ceiba.alquiler.modelo.entidad.VideoJuego;

public class AlquilerItemTestDataBuilder {
	private Long id;
	private Long idAlquiler;
	private VideoJuego videoJuego;
	private Integer cantidad;
	
	public AlquilerItemTestDataBuilder() {
		videoJuego = new VideoJuegoTestDataBuilder().build();
		cantidad = 2;
		idAlquiler = 1L;
	}
	
	public AlquilerItemTestDataBuilder conId(Long id) {
		this.id = id;
		return this;
	}
	
	public AlquilerItemTestDataBuilder conCantidad(Integer cantidad) {
		this.cantidad = cantidad;
		return this;
	}
	
	public AlquilerItemTestDataBuilder conVideoJuego(VideoJuego videoJuego) {
		this.videoJuego = videoJuego;
		return this;
	}
	
	public AlquilerItem build() {
		return new AlquilerItem(id, videoJuego, cantidad, idAlquiler); 
	}
	
	public List<AlquilerItem> buildList(){
		List<AlquilerItem> items = new ArrayList<>();
		items.add(build());
		items.add(conVideoJuego(new VideoJuegoTestDataBuilder().conCodigo("STN-01").build()).build());
		items.add(conVideoJuego(new VideoJuegoTestDataBuilder().conCodigo("STN-02").build()).build());
		return items;
	}
	
}
