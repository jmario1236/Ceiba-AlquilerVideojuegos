package com.ceiba.alquiler.controlador.testdatabuilder;

import com.ceiba.alquiler.comando.ComandoCliente;

public class ComandoClienteTestDataBuilder {
	private Long id;
	private String identificacion;
	private String nombre;
	private String apellido;
	private String telefono;
	private String direccion;
	
	public ComandoClienteTestDataBuilder() {
		identificacion = "1143354928";
		nombre = "Alma prueba";
		apellido ="Apellidoprueba";
		telefono ="000000";
		direccion = "Centro";
	}
	
	public ComandoClienteTestDataBuilder conId(Long id) {
		this.id = id;
		return this;
	}
	
	public ComandoClienteTestDataBuilder conIdentificacion(String identificion) {
		this.identificacion = identificion;
		return this;
	}
	
	public ComandoClienteTestDataBuilder conApellido(String apellido) {
		this.apellido = apellido;
		return this;
	}
	
	public ComandoCliente build() {
		return new ComandoCliente(id, identificacion, nombre, apellido, telefono, direccion);
	}
}
