package com.ceiba.alquiler.servicio;



import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.alquiler.modelo.entidad.Alquiler;
import com.ceiba.alquiler.modelo.entidad.VideoJuego;
import com.ceiba.alquiler.modelo.entidad.VideoJuegoId;
import com.ceiba.alquiler.puerto.repositorio.RepositorioAlquiler;
import com.ceiba.alquiler.puerto.repositorio.RepositorioVideoJuego;
import com.ceiba.alquiler.servicio.testdatabuilder.AlquilerTestDataBuilder;
import com.ceiba.alquiler.servicio.testdatabuilder.VideoJuegoTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;

public class ServicioFinalizarAlquilerTest {
	
	private RepositorioAlquiler repositorioAlquiler;
	private RepositorioVideoJuego repositorioVideoJuego;
	
	@Test
	public void alquilerInvalidoTest() {
		//arrange
		Alquiler alquiler = new AlquilerTestDataBuilder().build();
		VideoJuego videoJuego = new VideoJuegoTestDataBuilder().build();
		repositorioAlquiler = Mockito.mock(RepositorioAlquiler.class);	
		repositorioVideoJuego = Mockito.mock(RepositorioVideoJuego.class);	
		Mockito.when(repositorioVideoJuego.consultar(Mockito.any(VideoJuegoId.class))).thenReturn(videoJuego);
		Mockito.when(repositorioAlquiler.existe(alquiler.getId())).thenReturn(false);
		ServicioFinalizarAlquiler servicio = new ServicioFinalizarAlquiler(repositorioAlquiler,repositorioVideoJuego);		
		//act - assert
		BasePrueba.assertThrows(() -> servicio.ejecutar(alquiler), ExcepcionSinDatos.class, "Alquiler no valido");
		
		
	}
	
	@Test
	public void ejecutarTest() {
		//arrange
		Alquiler alquiler = new AlquilerTestDataBuilder().conId(1L).build();
		VideoJuego videoJuego = new VideoJuegoTestDataBuilder().build();
		repositorioAlquiler = Mockito.mock(RepositorioAlquiler.class);		
		repositorioVideoJuego = Mockito.mock(RepositorioVideoJuego.class);	
		Mockito.doNothing().when(repositorioVideoJuego).actualizarStock(Mockito.any());
		Mockito.when(repositorioAlquiler.existe(alquiler.getId())).thenReturn(true);
		Mockito.when(repositorioVideoJuego.consultar(Mockito.any(VideoJuegoId.class))).thenReturn(videoJuego);
		Mockito.when(repositorioAlquiler.consultar(alquiler.getId())).thenReturn(alquiler);
		ServicioFinalizarAlquiler servicio = new ServicioFinalizarAlquiler(repositorioAlquiler,repositorioVideoJuego);		
		//act 
		alquiler.cambiarEstadoVigente();
		servicio.ejecutar(alquiler);
		
		//assert
		Mockito.verify(repositorioAlquiler, Mockito.times(1)).finalizar(alquiler);
		
		
	}
}
