package com.ceiba.alquiler.adaptador.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.alquiler.modelo.dto.DtoCliente;
import com.ceiba.alquiler.puerto.dao.DaoCliente;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

@Component
public class DaoClienteH2 implements DaoCliente{
	
	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
	
	
	 public DaoClienteH2(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		super();
		this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	}

	@SqlStatement(namespace="cliente", value="listar")
	   private static String sqlListar;

	@Override
	public List<DtoCliente> listar() {
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoCliente());
	}

}
