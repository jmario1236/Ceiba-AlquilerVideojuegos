package com.ceiba.alquiler.puerto.dao;

import java.util.List;

import com.ceiba.alquiler.modelo.dto.DtoCliente;

public interface DaoCliente {
	 /**
     * Permite listar los clientes del sistemas
     * @return los clientes
     */
	List<DtoCliente> listar();
}
