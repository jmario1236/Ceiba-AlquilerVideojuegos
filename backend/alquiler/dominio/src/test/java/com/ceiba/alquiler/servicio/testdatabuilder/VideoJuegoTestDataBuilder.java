package com.ceiba.alquiler.servicio.testdatabuilder;

import com.ceiba.alquiler.modelo.entidad.VideoJuego;

public class VideoJuegoTestDataBuilder {
	private Long id;
	private String codigo;
	private String nombre;
	private String genero;
	private Double precio;
	private int stock;
	
	public VideoJuegoTestDataBuilder() {
		codigo = "PRN-1";
		nombre = "Mario bros";
		genero = "Plataforma";
		precio = 5000.0;
		stock = 5;
	}
	
	public VideoJuegoTestDataBuilder conCodigo(String codigo) {
		this.codigo = codigo; 
		return this;
	}
	
	public VideoJuegoTestDataBuilder conPrecion (Double precio) {
		this.precio = precio;
		return this;
	}
	
	public VideoJuego build() {
		return new VideoJuego(id,codigo,nombre,genero,precio,stock);
	}
}
