package com.ceiba.alquiler.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoCrearAlquilerItem {
	private Long id;
	private Long videoJuego;
	private Integer cantidad;
	private Double precio;
}
