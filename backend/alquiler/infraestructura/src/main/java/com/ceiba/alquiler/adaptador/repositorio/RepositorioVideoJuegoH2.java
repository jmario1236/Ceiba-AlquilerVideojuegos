package com.ceiba.alquiler.adaptador.repositorio;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ceiba.alquiler.modelo.entidad.VideoJuego;
import com.ceiba.alquiler.puerto.repositorio.RepositorioVideoJuego;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;

@Repository
public class RepositorioVideoJuegoH2 implements RepositorioVideoJuego{
	
	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="videojuego", value="crear")
    private static String sqlCrear;
    
    @SqlStatement(namespace="videojuego", value="existe")
    private static String sqlExiste;
    
    @SqlStatement(namespace="videojuego", value="existeId")
    private static String sqlExisteId;
    
    @SqlStatement(namespace="videojuego", value="actualizar")
    private static String sqlActualizar;
    
    
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
		paramSource.addValue("codigo", codigo);
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,paramSource, Boolean.class);
	}

	@Override
	public VideoJuego consultar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int obtenerStock(String codigo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void actualizar(VideoJuego videoJuego) {
		this.customNamedParameterJdbcTemplate.actualizar(videoJuego, sqlActualizar);		
	}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean existeId(Long id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteId,paramSource, Boolean.class);

	}

}
