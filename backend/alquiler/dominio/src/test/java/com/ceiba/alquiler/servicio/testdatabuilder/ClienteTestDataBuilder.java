package com.ceiba.alquiler.servicio.testdatabuilder;

import com.ceiba.alquiler.modelo.entidad.Cliente;

public class ClienteTestDataBuilder {
	private Long id;
	private String identificacion;
	private String nombre;
	private String apellido;
	private String telefono;
	private String direccion;
	
	public ClienteTestDataBuilder() {
		identificacion = "1143354930";
		nombre = "Nombreprueba";
		apellido ="Apellidoprueba";
		telefono ="000000";
		direccion = "Carrera xx calle #xx-xx";
	}
	
	public ClienteTestDataBuilder conIdentificacion(String identificacion) {
		this.identificacion = identificacion;
		return this;
	}
	
	public Cliente build() {
		return new Cliente(id, identificacion, nombre, apellido, telefono, direccion);
	}
}
