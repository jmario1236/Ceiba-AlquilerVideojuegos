package com.ceiba.alquiler.servicio.videojuego;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.alquiler.modelo.entidad.VideoJuego;
import com.ceiba.alquiler.puerto.repositorio.RepositorioVideoJuego;
import com.ceiba.alquiler.servicio.testdatabuilder.VideoJuegoTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;

public class ServicioActualizarVideoJuegoTest {
	@Test
	public void validarVideoJuegoNoExisteTest() {
		// arrange
		VideoJuego videoJuego = new VideoJuegoTestDataBuilder().build();
		RepositorioVideoJuego repositorio = Mockito.mock(RepositorioVideoJuego.class);
		Mockito.when(repositorio.existeId(videoJuego.getId())).thenReturn(false);
		ServicioActualizarVideoJuego servicio = new ServicioActualizarVideoJuego(repositorio);
		
		// act-assert
		BasePrueba.assertThrows(() -> servicio.ejecutar(videoJuego), ExcepcionSinDatos.class, "Videojuego no existe");
		
	}
}
