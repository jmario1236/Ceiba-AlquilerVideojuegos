package com.ceiba.alquiler.consulta;

import org.springframework.stereotype.Component;

import com.ceiba.alquiler.modelo.dto.DtoAlquiler;
import com.ceiba.alquiler.puerto.dao.DaoAlquiler;

@Component
public class ManejadorConsultarAlquilerPorCliente {
	private final DaoAlquiler daoAlquiler;

	public ManejadorConsultarAlquilerPorCliente(DaoAlquiler daoAlquiler) {
		this.daoAlquiler = daoAlquiler;
	}

	public DtoAlquiler ejecutar(Long id) {		
		return daoAlquiler.consultarPorCliente(id);
	}
	
	
	
}
