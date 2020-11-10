package com.ceiba.alquiler.adaptador.repositorio;

import java.util.List;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ceiba.alquiler.modelo.entidad.Alquiler;
import com.ceiba.alquiler.modelo.entidad.AlquilerItem;
import com.ceiba.alquiler.modelo.entidad.ClienteId;
import com.ceiba.alquiler.modelo.entidad.VideoJuegoId;
import com.ceiba.alquiler.puerto.repositorio.RepositorioAlquiler;
import com.ceiba.infraestructura.excepcion.ExcepcionTecnica;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

@Repository
public class RepositorioAlquilerH2 implements RepositorioAlquiler {
	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

	@SqlStatement(namespace = "alquiler", value = "crear")
	private static String sqlCrear;
	@SqlStatement(namespace = "alquiler", value = "crearItem")
	private static String sqlCrearItem;

	@SqlStatement(namespace = "alquiler", value = "existeAlquilerVigente")
	private static String sqlExisteVigencia;

	@SqlStatement(namespace = "alquiler", value = "existe")
	private static String sqlExiste;

	@SqlStatement(namespace = "alquiler", value = "consultar")
	private static String sqlConsultar;

	@SqlStatement(namespace = "alquiler", value = "listarItemsAlquiler")
	private static String sqlListarItemsAlquiler;

	@SqlStatement(namespace = "alquiler", value = "finalizar")
	private static String sqlFinalizar;

	public RepositorioAlquilerH2(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	}

	@Override
	public Long crear(Alquiler alquiler) {
		Long id = null;
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("idCliente", alquiler.getCliente().getId());
		paramSource.addValue("fechaAlquiler", alquiler.getFechaAlquiler());
		paramSource.addValue("fechaMaximaEntrega", alquiler.getFechaMaximaEntrega());
		paramSource.addValue("estado", alquiler.getEstado().getValue());
		paramSource.addValue("total", alquiler.getTotal());
		paramSource.addValue("subtotal", alquiler.getSubtotal());
		paramSource.addValue("totalAdicional", alquiler.getTotalAdiccional());
		paramSource.addValue("totalMulta", alquiler.getTotalMulta());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlCrear, paramSource, keyHolder,
				new String[] { "id" });
		Number key = keyHolder.getKey();
		if (key == null) {
			throw new ExcepcionTecnica("Error en la transaccion");
		}
		id = key.longValue();
		KeyHolder keyHolderItem = null;
		for (AlquilerItem item : alquiler.getItems()) {
			keyHolderItem = new GeneratedKeyHolder();
			paramSource = new MapSqlParameterSource();
			paramSource.addValue("idAlquiler", id);
			paramSource.addValue("idVideojuego", item.getVideoJuego().getId());
			paramSource.addValue("cantidad", item.getCantidad());
			this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlCrearItem, paramSource,
					keyHolderItem, new String[] { "id" });
		}
		return id;
	}

	@Override
	public boolean existeAlquilerVigente(ClienteId cliente) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", cliente.getId());
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteVigencia,
				paramSource, Boolean.class);
	}

	@Override
	public boolean existe(Long id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,
				paramSource, Boolean.class);
	}

	@Override
	public Alquiler consultar(Long id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id_alquiler", id);
		List<AlquilerItem> items = this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
				.query(sqlListarItemsAlquiler, paramSource, (rs, rowNum) -> new AlquilerItem(rs.getLong("id"),
						new VideoJuegoId(rs.getLong("id_videojuego")), rs.getInt("cantidad"), Double.valueOf(1)));

		paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlConsultar,
				paramSource, (rs, rowNum) -> {
					Alquiler alquiler = new Alquiler(rs.getLong("id"), new ClienteId(rs.getLong("id_cliente")),
							rs.getTimestamp("fecha_alquiler").toLocalDateTime().toLocalDate(),
							rs.getTimestamp("fecha_maxima_entrega").toLocalDateTime().toLocalDate(), items);
					alquiler.cambiarEstadoVigente();
					return alquiler;
				});
	}

	@Override
	public void finalizar(Alquiler alquiler) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", alquiler.getId());
		paramSource.addValue("estado", alquiler.getEstado().getValue());
		paramSource.addValue("fecha_entrega", alquiler.getFechaEntrega());
		this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlFinalizar, paramSource);
	}

}
