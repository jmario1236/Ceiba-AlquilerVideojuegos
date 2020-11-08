package com.ceiba.alquiler.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ceiba.alquiler.modelo.dto.DtoAlquiler;
import com.ceiba.infraestructura.jdbc.MapperResult;

public class MapeoAlquiler implements RowMapper<DtoAlquiler>, MapperResult{

	@Override
	public DtoAlquiler mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		return new DtoAlquiler(id, null, null, extraerLocalDate(rs, "fecha_alquiler"),
				extraerLocalDate(rs, "fecha_maxima_entrega"), extraerLocalDate(rs, "fecha_entrega"),
				rs.getString("estado"), rs.getDouble("total"), rs.getDouble("subtotal"),
				rs.getDouble("total_adicional"), rs.getDouble("total_multa"));
	}

}
