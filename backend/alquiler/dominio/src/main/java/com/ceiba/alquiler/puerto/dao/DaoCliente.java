package com.ceiba.alquiler.puerto.dao;

import java.util.List;

import com.ceiba.alquiler.modelo.dto.DtoCliente;

public interface DaoCliente {
	 /**
     * Permite listar los clientes del sistemas
     * @return los clientes
     */
	List<DtoCliente> listar();
	
	
	 /**
     * Permite listar los clientes del sistemas a partir de un criterio
     * @param criterio de busqueda
     * @return los clientes
     */
	List<DtoCliente> consultar(String criterio);
	
	/**
     * Obtiene un cliente del sistema a partir de su id
     * @param id
     * @return un cliente
     */
	DtoCliente consultarId(Long id);
}
