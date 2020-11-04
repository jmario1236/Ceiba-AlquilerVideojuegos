package com.ceiba.alquiler.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ceiba.alquiler.modelo.dto.DtoCliente;
import com.ceiba.infraestructura.jdbc.MapperResult;

public class MapeoCliente implements RowMapper<DtoCliente>, MapperResult{

	@Override
	public DtoCliente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Long id = resultSet.getLong("id");
        String nombre = resultSet.getString("nombre");
        String apellido = resultSet.getString("apellido");
        String identificacion = resultSet.getString("identificacion");
        String telefono = resultSet.getString("telefono");
        String direccion = resultSet.getString("direccion");
		return new DtoCliente(id, identificacion, nombre, apellido, telefono, direccion);
	}

}
