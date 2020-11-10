package com.ceiba.alquiler.modelo.entidad;


import lombok.Getter;

@Getter
public class EntityId {	
	private Long id;

	public EntityId(Long id) {		
		this.id = id;
	}
}
