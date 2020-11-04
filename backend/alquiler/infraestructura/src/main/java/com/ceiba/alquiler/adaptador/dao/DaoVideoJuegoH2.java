package com.ceiba.alquiler.adaptador.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.alquiler.modelo.dto.DtoVideoJuego;
import com.ceiba.alquiler.puerto.dao.DaoVideoJuego;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

@Component
public class DaoVideoJuegoH2 implements DaoVideoJuego{
	 private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

	    @SqlStatement(namespace="videojuego", value="listar")
	    private static String sqlListar;

	    public DaoVideoJuegoH2(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
	        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	    }

	    @Override
	    public List<DtoVideoJuego> listar() {
	        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoVideoJuego());
	    }
}
