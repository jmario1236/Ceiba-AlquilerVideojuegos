package com.ceiba.alquiler.adaptador.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import com.ceiba.alquiler.modelo.dto.DtoCliente;
import com.ceiba.alquiler.puerto.dao.DaoCliente;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

@Component
public class DaoClienteH2 implements DaoCliente{
	
	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
	private static final String PORCENTAJE = "%";
	
	@SqlStatement(namespace="cliente", value="listar")
	private static String sqlListar;
	
	@SqlStatement(namespace="cliente", value="consultarCriterio")
	private static String sqlConsultarCriterio;
	
	@SqlStatement(namespace="cliente", value="consultarId")
	private static String sqlConsultarId;
	
	
	 public DaoClienteH2(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		super();
		this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	}

	

	@Override
	public List<DtoCliente> listar() {
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoCliente());
	}

	@Override
	public List<DtoCliente> consultar(String criterio) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("criterio", PORCENTAJE.concat(criterio).concat(PORCENTAJE));
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlConsultarCriterio,paramSource , new MapeoCliente());
	}



	@Override
	public DtoCliente consultarId(Long id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlConsultarId,paramSource , new MapeoCliente());
	}
	
	

}
