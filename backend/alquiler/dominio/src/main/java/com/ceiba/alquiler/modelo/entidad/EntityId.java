package com.ceiba.alquiler.modelo.entidad;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

import lombok.Getter;

@Getter
public class EntityId {
	private static final String ID_INVALIDO = "ID invalido";	
	private Long id;

	public EntityId(Long id) {
		if(id <= 0) {
			throw new ExcepcionValorInvalido(ID_INVALIDO);
		}
		this.id = id;
	}
}
