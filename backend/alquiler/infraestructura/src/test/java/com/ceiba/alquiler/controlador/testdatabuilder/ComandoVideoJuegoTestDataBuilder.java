package com.ceiba.alquiler.controlador.testdatabuilder;

import com.ceiba.alquiler.comando.ComandoVideoJuego;

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
	
	public ComandoVideoJuegoTestDataBuilder conCodigo(String codigo) {
		this.codigo = codigo;
		return this;
	}
	
	public ComandoVideoJuegoTestDataBuilder conId(Long id) {
		this.id = id;
		return this;
	}
	
	public ComandoVideoJuego build() {
		return new ComandoVideoJuego(id, codigo, nombre, genero, precio, stock);
	}
}
