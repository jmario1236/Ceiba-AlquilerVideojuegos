package com.ceiba.alquiler.modelo.entidad;

import lombok.Getter;
import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static com.ceiba.dominio.ValidadorArgumento.validarNumerico;

@Getter
public class Cliente {
	private static final String SE_DEBE_INGRESAR_IDENTIFICACION = "Se debe ingresar identificaci�n";
	private static final String LA_IDENTIFICACION_DEBE_SER_NUMERICA = "La identificaci�n debe ser numerica";
	private static final String SE_DEBE_INGRESAR_NOMBRE = "Se debe ingresar nombre";
	private static final String SE_DEBE_INGRESAR_TELEFONO= "Se debe ingresar telefono";
	private Long id;
	private String identificacion;
	private String nombre;
	private String apellido;
	private String telefono;
	private String direccion;	

	public Cliente(Long id, String identificacion, String nombre, String apellido, String telefono, String direccion) {
		validarObligatorio(identificacion, SE_DEBE_INGRESAR_IDENTIFICACION);
		validarNumerico(identificacion, LA_IDENTIFICACION_DEBE_SER_NUMERICA);
		validarObligatorio(nombre, SE_DEBE_INGRESAR_NOMBRE);
		validarObligatorio(telefono, SE_DEBE_INGRESAR_TELEFONO);
		this.id = id;
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.direccion = direccion;
	}
	
}
