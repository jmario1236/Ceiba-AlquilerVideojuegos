package com.ceiba.alquiler.comando;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoVideoJuego {
	private Long id;
	private String codigo;
	private String nombre;
	private String genero;
	private Double precio;
	private int stock;
}
