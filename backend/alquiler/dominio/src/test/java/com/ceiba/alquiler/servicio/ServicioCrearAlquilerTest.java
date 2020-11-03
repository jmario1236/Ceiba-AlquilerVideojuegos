package com.ceiba.alquiler.servicio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.alquiler.modelo.entidad.Alquiler;
import com.ceiba.alquiler.puerto.repositorio.RepositorioAlquiler;
import com.ceiba.alquiler.puerto.repositorio.RepositorioCliente;
import com.ceiba.alquiler.puerto.repositorio.RepositorioVideoJuego;
import com.ceiba.alquiler.servicio.testdatabuilder.AlquilerTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

public class ServicioCrearAlquilerTest {
	
	private static final String CLIENTE_TIENE_UN_ALQUILER_VIGENTE = "El cliente tiene un alquiler vigente en el sistema";
	
	@Test
	public void crearAlquilerTest() {
		//arrange
		Alquiler alquiler = new AlquilerTestDataBuilder().build();
		RepositorioAlquiler repositorioAlquiler = Mockito.mock(RepositorioAlquiler.class);
		RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
		RepositorioVideoJuego repositorioVideoJuego = Mockito.mock(RepositorioVideoJuego.class);
		Mockito.when(repositorioAlquiler.crear(alquiler)).thenReturn(1L);
		Mockito.when(repositorioCliente.existe(Mockito.anyString())).thenReturn(true);
		Mockito.when(repositorioVideoJuego.existe(Mockito.anyString())).thenReturn(true);
		ServicioCrearAlquiler servicio = new ServicioCrearAlquiler(repositorioAlquiler, repositorioCliente, repositorioVideoJuego);
		
		//act
		Long id = servicio.ejecutar(alquiler);
		
		//assert
		assertEquals(1L, id.longValue());	
	}
	
	@Test
	public void validarAlquilerVigenteTest() {
		//arrange
		Alquiler alquiler = new AlquilerTestDataBuilder().build();
		RepositorioAlquiler repositorioAlquiler = Mockito.mock(RepositorioAlquiler.class);
		RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
		RepositorioVideoJuego repositorioVideoJuego = Mockito.mock(RepositorioVideoJuego.class);
		Mockito.when(repositorioAlquiler.existeAlquilerVigente(alquiler.getCliente().getIdentificacion())).thenReturn(true);
		Mockito.when(repositorioAlquiler.crear(alquiler)).thenReturn(1L);
		Mockito.when(repositorioCliente.existe(Mockito.anyString())).thenReturn(true);
		Mockito.when(repositorioVideoJuego.existe(Mockito.anyString())).thenReturn(true);
		ServicioCrearAlquiler servicio = new ServicioCrearAlquiler(repositorioAlquiler, repositorioCliente, repositorioVideoJuego);
		
		// act-assert
		BasePrueba.assertThrows(() -> servicio.ejecutar(alquiler), ExcepcionValorInvalido.class, CLIENTE_TIENE_UN_ALQUILER_VIGENTE);
		
	}
	
	@Test
	public void validarClienteTest() {
		//arrange
		Alquiler alquiler = new AlquilerTestDataBuilder().build();
		RepositorioAlquiler repositorioAlquiler = Mockito.mock(RepositorioAlquiler.class);
		RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
		RepositorioVideoJuego repositorioVideoJuego = Mockito.mock(RepositorioVideoJuego.class);
		Mockito.when(repositorioAlquiler.existeAlquilerVigente(alquiler.getCliente().getIdentificacion())).thenReturn(false);
		Mockito.when(repositorioAlquiler.crear(alquiler)).thenReturn(1L);
		Mockito.when(repositorioCliente.existe(Mockito.anyString())).thenReturn(false);
		Mockito.when(repositorioVideoJuego.existe(Mockito.anyString())).thenReturn(true);
		ServicioCrearAlquiler servicio = new ServicioCrearAlquiler(repositorioAlquiler, repositorioCliente, repositorioVideoJuego);
		
		// act-assert
		BasePrueba.assertThrows(() -> servicio.ejecutar(alquiler), ExcepcionSinDatos.class, "El cliente no existe");
		
	}
	
	@Test
	public void validarVideoJuegosAlquilerTest() {
		//arrange
		Alquiler alquiler = new AlquilerTestDataBuilder().build();
		RepositorioAlquiler repositorioAlquiler = Mockito.mock(RepositorioAlquiler.class);
		RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
		RepositorioVideoJuego repositorioVideoJuego = Mockito.mock(RepositorioVideoJuego.class);
		Mockito.when(repositorioAlquiler.existeAlquilerVigente(alquiler.getCliente().getIdentificacion())).thenReturn(false);
		Mockito.when(repositorioAlquiler.crear(alquiler)).thenReturn(1L);
		Mockito.when(repositorioCliente.existe(Mockito.anyString())).thenReturn(true);
		Mockito.when(repositorioVideoJuego.existe(Mockito.anyString())).thenReturn(false);
		ServicioCrearAlquiler servicio = new ServicioCrearAlquiler(repositorioAlquiler, repositorioCliente, repositorioVideoJuego);
		
		// act-assert
		BasePrueba.assertThrows(() -> servicio.ejecutar(alquiler), ExcepcionSinDatos.class, "VideoJuego no existe");
		
	}
}
