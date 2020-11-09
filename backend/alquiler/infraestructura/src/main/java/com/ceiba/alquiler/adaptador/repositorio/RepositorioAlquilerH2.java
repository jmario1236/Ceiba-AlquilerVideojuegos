package com.ceiba.alquiler.adaptador.repositorio;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ceiba.alquiler.modelo.entidad.Alquiler;
import com.ceiba.alquiler.modelo.entidad.AlquilerItem;
import com.ceiba.alquiler.modelo.entidad.ClienteId;
import com.ceiba.alquiler.puerto.repositorio.RepositorioAlquiler;
import com.ceiba.infraestructura.excepcion.ExcepcionTecnica;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

@Repository
public class RepositorioAlquilerH2 implements RepositorioAlquiler{
	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

	@SqlStatement(namespace = "alquiler", value = "crear")
	private static String sqlCrear;
	@SqlStatement(namespace = "alquiler", value = "crearItem")
	private static String sqlCrearItem;
	
	@SqlStatement(namespace = "alquiler", value = "existeAlquilerVigente")
	private static String sqlExisteVigencia;
	
	
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
		this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlCrear, paramSource,keyHolder,new String[] { "id" });
		Number key = keyHolder.getKey();
		if(key == null) {
			throw new ExcepcionTecnica("Error en la transaccion");
		}
		id = key.longValue();				 
		KeyHolder keyHolderItem = null;
		for(AlquilerItem item : alquiler.getItems()) {
			keyHolderItem = new GeneratedKeyHolder();
			paramSource = new MapSqlParameterSource();
			paramSource.addValue("idAlquiler", id);
			paramSource.addValue("idVideojuego", item.getVideoJuego().getId());
			paramSource.addValue("cantidad", item.getCantidad());
			this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlCrearItem, paramSource,keyHolderItem,new String[] { "id" });
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
		return false;
	}

	@Override
	public Alquiler consultar(Long id) {
		return null;
	}

	@Override
	public void finalizar(Alquiler alquiler) {
		// TODO Auto-generated method stub		
	}

}
