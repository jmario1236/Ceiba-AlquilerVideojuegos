package com.ceiba.alquiler.modelo.entidad;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

import lombok.Getter;

@Getter
public class Estado {
	private static final String VIGENTE = "VIGENTE";
	private static final String FINALIZADO = "FINALIZADO";
	private static final String VALOR_INCORRECTO = "Valor para estado es incorrecto";
	
	private String value;
	
	public Estado(String value) {
		if(!value.equals(VIGENTE) && !value.equals(FINALIZADO)) {
			throw new ExcepcionValorInvalido(VALOR_INCORRECTO);
		}
		this.value = value;
	}
}
