package com.ceiba.alquiler.videojuego.controlador.testdatabuilder;

import com.ceiba.alquiler.videojuego.comando.ComandoVideoJuego;

public class ComandoVideoJuegoTestDataBuilder {
	private Long id;
	private String codigo;
	private String nombre;
	private String genero;
	private Double precio;
	private int stock;
	
	public ComandoVideoJuegoTestDataBuilder() {
		codigo = "PRN-1";
		nombre = "Mario bros";
		genero = "Plataforma";
		precio = 5000.0;
		stock = 5;
	}
	
	public ComandoVideoJuego build() {
		return new ComandoVideoJuego(id, codigo, nombre, genero, precio, stock);
	}
}
