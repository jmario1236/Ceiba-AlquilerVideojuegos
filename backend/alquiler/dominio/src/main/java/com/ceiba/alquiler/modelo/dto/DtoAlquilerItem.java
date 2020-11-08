package com.ceiba.alquiler.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoAlquilerItem {
	private Long id;
	private DtoVideoJuego videoJuego;
	private Double precio;
	private Integer cantidad;
}
