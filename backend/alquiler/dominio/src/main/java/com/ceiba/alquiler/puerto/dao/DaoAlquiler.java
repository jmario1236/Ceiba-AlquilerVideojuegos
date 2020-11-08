package com.ceiba.alquiler.puerto.dao;

import java.util.List;

import com.ceiba.alquiler.modelo.dto.DtoAlquiler;

public interface DaoAlquiler {
	
	List<DtoAlquiler> listar();
	
	DtoAlquiler consultarPorCliente(Long id);
	
	DtoAlquiler consultar(Long id);
}
