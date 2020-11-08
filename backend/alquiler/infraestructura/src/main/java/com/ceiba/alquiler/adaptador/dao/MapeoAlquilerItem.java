package com.ceiba.alquiler.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.ceiba.alquiler.modelo.dto.DtoAlquilerItem;
import com.ceiba.alquiler.modelo.dto.DtoVideoJuego;
import com.ceiba.infraestructura.jdbc.MapperResult;

public class MapeoAlquilerItem implements RowMapper<DtoAlquilerItem>, MapperResult{

	@Override
	public DtoAlquilerItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		Long id = rs.getLong("id");
		DtoVideoJuego videoJuego = new DtoVideoJuego(rs.getLong("id_videojuego"), null, null, null, null, 0);		
		Integer cantidad = rs.getInt("cantidad");
		return new DtoAlquilerItem(id, videoJuego, null, cantidad);
	}

}
