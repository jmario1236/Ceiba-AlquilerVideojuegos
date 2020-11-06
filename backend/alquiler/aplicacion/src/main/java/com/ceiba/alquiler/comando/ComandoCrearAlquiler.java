package com.ceiba.alquiler.comando;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoCrearAlquiler {
	private Long id;
	private LocalDate fechaAlquiler;
	private LocalDate fechaMaximaEntrega;
	private Long cliente;
	private List<ComandoCrearAlquilerItem> items;
}
