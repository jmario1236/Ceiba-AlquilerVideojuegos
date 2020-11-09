package com.ceiba.alquiler.puerto.dao;


import com.ceiba.alquiler.modelo.dto.DtoAlquiler;

public interface DaoAlquiler {
	
	DtoAlquiler consultarPorCliente(Long id);
	
}
