package com.ceiba.alquiler.adaptador.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import com.ceiba.alquiler.modelo.dto.DtoVideoJuego;
import com.ceiba.alquiler.puerto.dao.DaoVideoJuego;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

@Component
public class DaoVideoJuegoH2 implements DaoVideoJuego{
	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
	private static final String PORCENTAJE = "%";

	    @SqlStatement(namespace="videojuego", value="listar")
	    private static String sqlListar;
	    
	    @SqlStatement(namespace="videojuego", value="consultarCriterio")
		private static String sqlConsultarCriterio;
	    
	    @SqlStatement(namespace="videojuego", value="consultarId")
		private static String sqlConsultarId;

	    public DaoVideoJuegoH2(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
	        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	    }

	    @Override
	    public List<DtoVideoJuego> listar() {
	        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoVideoJuego());
	    }

		@Override
		public List<DtoVideoJuego> consultar(String criterio) {
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("criterio", PORCENTAJE.concat(criterio).concat(PORCENTAJE));
			return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlConsultarCriterio,paramSource ,  new MapeoVideoJuego());
		}

		@Override
		public DtoVideoJuego consultarId(Long id) {
			MapSqlParameterSource paramSource = new MapSqlParameterSource();
			paramSource.addValue("id", id);
			return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlConsultarId,paramSource , new MapeoVideoJuego());

		}
}
