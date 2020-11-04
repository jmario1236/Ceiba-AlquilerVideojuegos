package com.ceiba.alquiler.adaptador.repositorio;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ceiba.alquiler.modelo.entidad.VideoJuego;
import com.ceiba.alquiler.puerto.repositorio.RepositorioVideoJuego;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

@Repository
public class RepositorioVideoJuegoH2 implements RepositorioVideoJuego {

	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
	private static final String CODIGO = "codigo";

	@SqlStatement(namespace = "videojuego", value = "crear")
	private static String sqlCrear;

	@SqlStatement(namespace = "videojuego", value = "existe")
	private static String sqlExiste;

	@SqlStatement(namespace = "videojuego", value = "existeId")
	private static String sqlExisteId;

	@SqlStatement(namespace = "videojuego", value = "actualizar")
	private static String sqlActualizar;

	@SqlStatement(namespace = "videojuego", value = "consultar")
	private static String sqlConsultar;
	
	@SqlStatement(namespace = "videojuego", value = "eliminar")
	private static String sqlEliminar;
	
	@SqlStatement(namespace = "videojuego", value = "obtenerStock")
	private static String sqlObtenerStock;

	public RepositorioVideoJuegoH2(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	}

	@Override
	public Long crear(VideoJuego videoJuego) {
		return this.customNamedParameterJdbcTemplate.crear(videoJuego, sqlCrear);
	}

	@Override
	public boolean existe(String codigo) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue(CODIGO, codigo);
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,
				paramSource, Boolean.class);
	}

	@Override
	public VideoJuego consultar(Long id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlConsultar,
				paramSource, (rs, rowNum) -> new VideoJuego(rs.getLong("id"), 
															rs.getString(CODIGO),
															rs.getString("nombre"), 
															rs.getString("genero"), 
															rs.getDouble("precio"), 
															rs.getInt("stock")));

	}

	@Override
	public int obtenerStock(String codigo) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue(CODIGO, codigo);		
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerStock,
				paramSource, Integer.class);
	}

	@Override
	public void actualizar(VideoJuego videoJuego) {
		this.customNamedParameterJdbcTemplate.actualizar(videoJuego, sqlActualizar);
	}

	@Override
	public void eliminar(Long id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
	}

	@Override
	public boolean existeId(Long id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteId,
				paramSource, Boolean.class);

	}

}
