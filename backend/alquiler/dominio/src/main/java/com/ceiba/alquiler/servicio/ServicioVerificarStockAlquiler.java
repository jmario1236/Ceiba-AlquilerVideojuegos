package com.ceiba.alquiler.servicio;

import com.ceiba.alquiler.modelo.entidad.AlquilerItem;
import com.ceiba.alquiler.puerto.repositorio.RepositorioVideoJuego;

public class ServicioVerificarStockAlquiler {


	private RepositorioVideoJuego repositorioVideoJuego;
	
	public ServicioVerificarStockAlquiler(RepositorioVideoJuego repositorioVideoJuego) {
		this.repositorioVideoJuego = repositorioVideoJuego;
	}
	
	public boolean ejecutar(AlquilerItem alquilerItem) {
		int stock = repositorioVideoJuego.obtenerStock(alquilerItem.getVideoJuego().getCodigo());
		return stock >= alquilerItem.getCantidad();
	}

}
