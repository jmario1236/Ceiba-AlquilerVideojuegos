package com.ceiba.alquiler.modelo.entidad;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

public final class Validador {
	
	private Validador() {}
	
	public static void validarFechasNoMayorQue(LocalDate fechaMayor, LocalDate fechaMenor, String mensaje) {
		boolean esMayor = fechaMayor.isAfter(fechaMenor);
		if(!esMayor) {
			throw new ExcepcionValorInvalido(mensaje);
		}
	}
	
	public static void validarFechasNumeroDias(LocalDate fechaInicial, LocalDate fechaEstimada,int dias ,String mensaje) {		
		long diasDiff = ChronoUnit.DAYS.between(fechaInicial, fechaEstimada) - dias;
		if( diasDiff < 0) {
			throw new ExcepcionValorInvalido(mensaje);
		}
	}
}
