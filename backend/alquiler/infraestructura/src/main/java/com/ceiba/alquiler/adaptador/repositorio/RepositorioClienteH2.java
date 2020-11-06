package com.ceiba.alquiler.adaptador.repositorio;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ceiba.alquiler.modelo.entidad.Cliente;
import com.ceiba.alquiler.modelo.entidad.ClienteId;
import com.ceiba.alquiler.puerto.repositorio.RepositorioCliente;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

@Repository
public class RepositorioClienteH2 implements RepositorioCliente{

	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
	
	@SqlStatement(namespace = "cliente", value = "crear")
	private static String sqlCrear;

	@SqlStatement(namespace = "cliente", value = "existe")
	private static String sqlExiste;

	@SqlStatement(namespace = "cliente", value = "existeId")
	private static String sqlExisteId;

	@SqlStatement(namespace = "cliente", value = "actualizar")
	private static String sqlActualizar;
	
	@SqlStatement(namespace = "cliente", value = "eliminar")
	private static String sqlEliminar;
	
	
	public RepositorioClienteH2(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		super();
		this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	}

	@Override
	public Long crear(Cliente cliente) {
		return this.customNamedParameterJdbcTemplate.crear(cliente, sqlCrear);
	}

	@Override
	public boolean existe(String identificacion) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("identificacion", identificacion);
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,
				paramSource, Boolean.class);
	}

	@Override
	public void actualizar(Cliente cliente) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", cliente.getId().getId());
		paramSource.addValue("nombre", cliente.getNombre());
		paramSource.addValue("apellido", cliente.getApellido());
		paramSource.addValue("telefono", cliente.getTelefono());
		paramSource.addValue("identificacion", cliente.getIdentificacion());
		paramSource.addValue("direccion", cliente.getDireccion());
		this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlActualizar, paramSource);		
	}

	@Override
	public void eliminar(ClienteId id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id.getId());
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
	}

	@Override
	public boolean existeId(ClienteId id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id.getId());
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteId,
				paramSource, Boolean.class);

	}
	
}
