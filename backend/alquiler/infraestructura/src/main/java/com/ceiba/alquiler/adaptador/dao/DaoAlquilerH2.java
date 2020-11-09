package com.ceiba.alquiler.adaptador.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import com.ceiba.alquiler.modelo.dto.DtoAlquiler;
import com.ceiba.alquiler.modelo.dto.DtoAlquilerItem;
import com.ceiba.alquiler.modelo.dto.DtoVideoJuego;
import com.ceiba.alquiler.puerto.dao.DaoAlquiler;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

@Component
public class DaoAlquilerH2 implements DaoAlquiler, MapperResult {

	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
	private final DaoVideoJuegoH2 daoVideoJuego;

	@SqlStatement(namespace = "alquiler", value = "consultarPorCliente")
	private static String sqlConsultarPorCliente;

	@SqlStatement(namespace = "alquiler", value = "listarItemsAlquiler")
	private static String sqlListarItemsAlquiler;

	public DaoAlquilerH2(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate,
			DaoVideoJuegoH2 daoVideoJuego) {
		this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
		this.daoVideoJuego = daoVideoJuego;
	}

	
	@Override
	public DtoAlquiler consultarPorCliente(Long id) {
		List<DtoAlquilerItem> items = null;
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);
		List<DtoAlquiler> alquilers = this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
				.query(sqlConsultarPorCliente, paramSource, new MapeoAlquiler());
		if (alquilers.isEmpty()) {
			return null;
		}
		DtoAlquiler alquiler = alquilers.get(0);
		paramSource = new MapSqlParameterSource();
		paramSource.addValue("id_alquiler", alquiler.getId());
		items = this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarItemsAlquiler,
				paramSource, new MapeoAlquilerItem());
		items = items.stream().map(item -> {
			DtoAlquilerItem itemNew = null;
			DtoVideoJuego videoJuego = daoVideoJuego.consultarId(item.getVideoJuego().getId());
			if (videoJuego != null) {
				itemNew = new DtoAlquilerItem(item.getId(), videoJuego, videoJuego.getPrecio(), item.getCantidad());
			}
			return itemNew;
		}).collect(Collectors.toList());
		alquiler = new DtoAlquiler(alquiler.getId(), items, null, alquiler.getFechaAlquiler(),
				alquiler.getFechaMaximaEntrega(), alquiler.getFechaEntrega(), alquiler.getEstado(), alquiler.getTotal(),
				alquiler.getSubtotal(), alquiler.getTotalAdiccional(), alquiler.getTotalMulta());
		return alquiler;
	}

	
}
