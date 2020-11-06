package com.ceiba.alquiler.servicio.testdatabuilder;

import java.util.ArrayList;
import java.util.List;

import com.ceiba.alquiler.modelo.entidad.AlquilerItem;
import com.ceiba.alquiler.modelo.entidad.VideoJuegoId;

public class AlquilerItemTestDataBuilder {
	private Long id;
	private VideoJuegoId videoJuego;
	private Double precio;
	private Integer cantidad;
	
	public AlquilerItemTestDataBuilder() {
		videoJuego = new VideoJuegoTestDataBuilder().build().getId();
		cantidad = 2;
		precio = 5000.0;
	}
	
	public AlquilerItemTestDataBuilder conId(Long id) {
		this.id = id;
		return this;
	}
	
	public AlquilerItemTestDataBuilder conCantidad(Integer cantidad) {
		this.cantidad = cantidad;
		return this;
	}
	
	public AlquilerItemTestDataBuilder conVideoJuegoId(VideoJuegoId videoJuego) {
		this.videoJuego = videoJuego;
		return this;
	}
	
	public AlquilerItem build() {
		return new AlquilerItem(id, videoJuego, cantidad, precio); 
	}
	
	public List<AlquilerItem> buildList(){
		List<AlquilerItem> items = new ArrayList<>();
		items.add(build());
		items.add(conVideoJuegoId(new VideoJuegoTestDataBuilder().conId(new VideoJuegoId(2L)).conCodigo("STN-01").build().getId()).build());
		items.add(conVideoJuegoId(new VideoJuegoTestDataBuilder().conId(new VideoJuegoId(3L)).conCodigo("STN-02").build().getId()).build());
		return items;
	}
	
}
