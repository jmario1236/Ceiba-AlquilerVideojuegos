package com.ceiba.alquiler.servicio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.alquiler.modelo.entidad.AlquilerItem;
import com.ceiba.alquiler.puerto.repositorio.RepositorioVideoJuego;
import com.ceiba.alquiler.servicio.testdatabuilder.AlquilerItemTestDataBuilder;

public class ServicioVerificarStockAlquilerTest {
	
	@Test
	public void hayStockTest() {
		//arrange
		AlquilerItem alquilerItem = new AlquilerItemTestDataBuilder().build();
		RepositorioVideoJuego repositorioVideoJuego = Mockito.mock(RepositorioVideoJuego.class);
		Mockito.when(repositorioVideoJuego.obtenerStock(alquilerItem.getVideoJuego())).thenReturn(2);
		ServicioVerificarStockAlquiler servicio = new ServicioVerificarStockAlquiler(repositorioVideoJuego);
		//act
		boolean hayStock = servicio.ejecutar(alquilerItem);
		//assert
		assertEquals(true, hayStock);
	}
	
	@Test
	public void noHayStockTest() {
		//arrange
		AlquilerItem alquilerItem = new AlquilerItemTestDataBuilder().build();
		RepositorioVideoJuego repositorioVideoJuego = Mockito.mock(RepositorioVideoJuego.class);
		Mockito.when(repositorioVideoJuego.obtenerStock(alquilerItem.getVideoJuego())).thenReturn(1);
		ServicioVerificarStockAlquiler servicio = new ServicioVerificarStockAlquiler(repositorioVideoJuego);
		//act
		boolean hayStock = servicio.ejecutar(alquilerItem);
		//assert
		assertEquals(false, hayStock);
	}
}
