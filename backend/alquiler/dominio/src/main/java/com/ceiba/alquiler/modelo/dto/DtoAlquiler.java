package com.ceiba.alquiler.modelo.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoAlquiler {
	private Long id;
	private List<DtoAlquilerItem> items;
	private DtoCliente cliente;
	private LocalDate fechaAlquiler;
	private LocalDate fechaMaximaEntrega;
	private LocalDate fechaEntrega;
	private String estado;
	private Double total;
	private Double subtotal;
	private Double totalAdiccional;
	private Double totalMulta;
}
