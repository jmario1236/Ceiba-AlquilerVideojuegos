package com.ceiba.alquiler.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoCliente {
	private Long id;
	private String identificacion;
	private String nombre;
	private String apellido;
	private String telefono;
	private String direccion;
}
