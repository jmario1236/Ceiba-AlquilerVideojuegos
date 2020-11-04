package com.ceiba.alquiler.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoCliente {
	private Long id;
	private String identificacion;
	private String nombre;
	private String apellido;
	private String telefono;
	private String direccion;
}
