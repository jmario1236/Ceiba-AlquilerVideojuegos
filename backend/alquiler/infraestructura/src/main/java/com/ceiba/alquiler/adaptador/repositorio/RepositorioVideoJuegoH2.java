package com.ceiba.alquiler.adaptador.repositorio;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ceiba.alquiler.modelo.entidad.VideoJuego;
import com.ceiba.alquiler.modelo.entidad.VideoJuegoId;
import com.ceiba.alquiler.puerto.repositorio.RepositorioVideoJuego;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

@Repository
public class RepositorioVideoJuegoH2 implements RepositorioVideoJuego {

	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;
	private static final String CODIGO = "codigo";
	private static final String ID = "id";
	private static final String STOCK = "stock";

	@SqlStatement(namespace = "videojuego", value = "crear")
	private static String sqlCrear;

	@SqlStatement(namespace = "videojuego", value = "existe")
	private static String sqlExiste;

	@SqlStatement(namespace = "videojuego", value = "existeId")
	private static String sqlExisteId;

	@SqlStatement(namespace = "videojuego", value = "actualizar")
	private static String sqlActualizar;
	
	@SqlStatement(namespace = "videojuego", value = "actualizarStock")
	private static String sqlActualizarStock;

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
	public VideoJuego consultar(VideoJuegoId id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id.getId());
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlConsultar,
				paramSource, (rs, rowNum) -> new VideoJuego(new VideoJuegoId(rs.getLong(ID)), 
															rs.getString(CODIGO),
															rs.getString("nombre"), 
															rs.getString("genero"), 
															rs.getDouble("precio"), 
															rs.getInt(STOCK)));

	}

	@Override
	public int obtenerStock(VideoJuegoId id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue(ID, id.getId());		
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlObtenerStock,
				paramSource, Integer.class);
	}

	@Override
	public void actualizar(VideoJuego videoJuego) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue(ID, videoJuego.getId().getId());
		paramSource.addValue("nombre", videoJuego.getNombre());
		paramSource.addValue(CODIGO, videoJuego.getCodigo());
		paramSource.addValue("genero", videoJuego.getGenero());
		paramSource.addValue("precio", videoJuego.getPrecio());
		paramSource.addValue(STOCK, videoJuego.getStock());
		this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlActualizar, paramSource);
	}

	@Override
	public void eliminar(VideoJuegoId id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue(ID, id.getId());
        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
	}

	@Override
	public boolean existeId(VideoJuegoId id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue(ID, id.getId());
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteId,
				paramSource, Boolean.class);

	}

	@Override
	public void actualizarStock(VideoJuego videoJuego) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue(ID, videoJuego.getId().getId());
		paramSource.addValue(STOCK, videoJuego.getStock());
		this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlActualizarStock, paramSource);
	}

}
