package com.ceiba.alquiler.consulta;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.alquiler.modelo.dto.DtoCliente;
import com.ceiba.alquiler.puerto.dao.DaoCliente;

@Component
public class ManejadorConsultarCriterioClientes {
	private final DaoCliente daoCliente;

	public ManejadorConsultarCriterioClientes(DaoCliente daoCliente) {		
		this.daoCliente = daoCliente;
	}
	
	public List<DtoCliente> ejecutar(String criterio) {		
		return daoCliente.consultar(criterio);
	}
}
