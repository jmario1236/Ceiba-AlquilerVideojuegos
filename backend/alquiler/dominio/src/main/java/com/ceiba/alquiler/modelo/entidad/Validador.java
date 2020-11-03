package com.ceiba.alquiler.modelo.entidad;

import java.time.LocalDate;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

public class Validador {
	
	private Validador() {}
	
	public static void validarFechasNoMayorQue(LocalDate fechaMayor, LocalDate fechaMenor, String mensaje) {
		boolean esMayor = fechaMayor.isAfter(fechaMenor);
		if(!esMayor) {
			throw new ExcepcionValorInvalido(mensaje);
		}
	}
	
	public static void validarFechasNumeroDias(LocalDate fechaInicial, LocalDate fechaEstimada,int dias ,String mensaje) {
		LocalDate temp = fechaInicial.plusDays(dias);
		if(!temp.isEqual(fechaEstimada)) {
			throw new ExcepcionValorInvalido(mensaje);
		}
	}
}
