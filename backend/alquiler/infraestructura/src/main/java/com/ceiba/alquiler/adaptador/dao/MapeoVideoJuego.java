package com.ceiba.alquiler.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ceiba.alquiler.modelo.dto.DtoVideoJuego;
import com.ceiba.infraestructura.jdbc.MapperResult;

public class MapeoVideoJuego implements RowMapper<DtoVideoJuego>, MapperResult{

	@Override
	public DtoVideoJuego mapRow(ResultSet resultSet, int rowNum) throws SQLException {		
		Long id = resultSet.getLong("id");
        String nombre = resultSet.getString("nombre");
        String codigo = resultSet.getString("codigo");
        String genero = resultSet.getString("genero");
        Double precio = resultSet.getDouble("precio");
        Integer stock = resultSet.getInt("stock");
		return new DtoVideoJuego(id, codigo, nombre, genero, precio, stock);
	}

}
