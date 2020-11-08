package com.ceiba.alquiler.consulta;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.alquiler.modelo.dto.DtoVideoJuego;
import com.ceiba.alquiler.puerto.dao.DaoVideoJuego;

@Component
public class ManejadorConsultarCriterioVideoJuegos {
private final DaoVideoJuego daoVideoJuego;
	

	public ManejadorConsultarCriterioVideoJuegos(DaoVideoJuego daoVideoJuego) {		
		this.daoVideoJuego = daoVideoJuego;
	}

	public List<DtoVideoJuego> ejecutar(String criterio) {		
		return daoVideoJuego.consultar(criterio);
	}	
}
