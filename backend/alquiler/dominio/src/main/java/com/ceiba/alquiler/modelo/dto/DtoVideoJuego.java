package com.ceiba.alquiler.modelo.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoVideoJuego {
	private Long id;
	private String codigo;
	private String nombre;
	private String genero;
	private Double precio;
	private int stock;
}
