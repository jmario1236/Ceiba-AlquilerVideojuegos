package com.ceiba.alquiler.servicio.videojuego;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.alquiler.modelo.entidad.VideoJuego;
import com.ceiba.alquiler.puerto.repositorio.RepositorioVideoJuego;
import com.ceiba.alquiler.servicio.testdatabuilder.VideoJuegoTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

public class ServicioCrearVideoJuegoTest {
	
	@Test
	public void crearCrearVideoJuegoTest() {
		//arrange
		VideoJuego videoJuego = new VideoJuegoTestDataBuilder().build();
		RepositorioVideoJuego repositorio = Mockito.mock(RepositorioVideoJuego.class);
		Mockito.when(repositorio.existe(videoJuego.getCodigo())).thenReturn(false);
		Mockito.when(repositorio.crear(videoJuego)).thenReturn(1L);
		ServicioCrearVideoJuego servicio = new ServicioCrearVideoJuego(repositorio);
		// act
		Long id = servicio.ejecutar(videoJuego);
		// assert
		assertEquals(1L, id.longValue());		
	}
	
	@Test
	public void validarExisteVideoJuegoTest() {
		//arrange
		VideoJuego videoJuego = new VideoJuegoTestDataBuilder().build();
		RepositorioVideoJuego repositorio = Mockito.mock(RepositorioVideoJuego.class);
		Mockito.when(repositorio.existe(videoJuego.getCodigo())).thenReturn(true);
		ServicioCrearVideoJuego servicio = new ServicioCrearVideoJuego(repositorio);
		// act-assert
		BasePrueba.assertThrows(() -> servicio.ejecutar(videoJuego), ExcepcionDuplicidad.class, "El videojuego ya existe en el sistema");	
	}
}
