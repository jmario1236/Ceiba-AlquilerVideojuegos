package com.ceiba.alquiler.servicio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.alquiler.modelo.entidad.Alquiler;
import com.ceiba.alquiler.modelo.entidad.AlquilerItem;
import com.ceiba.alquiler.puerto.repositorio.RepositorioAlquiler;
import com.ceiba.alquiler.puerto.repositorio.RepositorioCliente;
import com.ceiba.alquiler.puerto.repositorio.RepositorioVideoJuego;
import com.ceiba.alquiler.servicio.testdatabuilder.AlquilerItemTestDataBuilder;
import com.ceiba.alquiler.servicio.testdatabuilder.AlquilerTestDataBuilder;
import com.ceiba.alquiler.servicio.testdatabuilder.VideoJuegoTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

public class ServicioCrearAlquilerTest {
	
	private static final String CLIENTE_TIENE_UN_ALQUILER_VIGENTE = "El cliente tiene un alquiler vigente en el sistema";
	
	private RepositorioAlquiler repositorioAlquiler;
	private RepositorioCliente repositorioCliente;
	private RepositorioVideoJuego repositorioVideoJuego;
	
	
	
	@Test
	public void crearAlquilerTest() {
		//arrange
		Alquiler alquiler = new AlquilerTestDataBuilder().build();
		repositorioAlquiler = Mockito.mock(RepositorioAlquiler.class);
		repositorioCliente = Mockito.mock(RepositorioCliente.class);
		repositorioVideoJuego = Mockito.mock(RepositorioVideoJuego.class);
		Mockito.when(repositorioAlquiler.crear(alquiler)).thenReturn(1L);
		Mockito.when(repositorioCliente.existe(Mockito.anyString())).thenReturn(true);
		Mockito.when(repositorioVideoJuego.existe(Mockito.anyString())).thenReturn(true);
		Mockito.when(repositorioVideoJuego.consultar(Mockito.anyLong())).thenReturn(
				new VideoJuegoTestDataBuilder().build(), new VideoJuegoTestDataBuilder().build(), new VideoJuegoTestDataBuilder().build());
		Mockito.when(repositorioAlquiler.existeAlquilerVigente(alquiler.getCliente().getIdentificacion())).thenReturn(false);
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
		repositorioAlquiler = Mockito.mock(RepositorioAlquiler.class);
		repositorioCliente = Mockito.mock(RepositorioCliente.class);
		repositorioVideoJuego = Mockito.mock(RepositorioVideoJuego.class);
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
		repositorioAlquiler = Mockito.mock(RepositorioAlquiler.class);
		repositorioCliente = Mockito.mock(RepositorioCliente.class);
		repositorioVideoJuego = Mockito.mock(RepositorioVideoJuego.class);
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
		repositorioAlquiler = Mockito.mock(RepositorioAlquiler.class);
		repositorioCliente = Mockito.mock(RepositorioCliente.class);
		repositorioVideoJuego = Mockito.mock(RepositorioVideoJuego.class);
		Mockito.when(repositorioAlquiler.existeAlquilerVigente(alquiler.getCliente().getIdentificacion())).thenReturn(false);
		Mockito.when(repositorioAlquiler.crear(alquiler)).thenReturn(1L);
		Mockito.when(repositorioCliente.existe(Mockito.anyString())).thenReturn(true);
		Mockito.when(repositorioVideoJuego.existe(Mockito.anyString())).thenReturn(false);
		ServicioCrearAlquiler servicio = new ServicioCrearAlquiler(repositorioAlquiler, repositorioCliente, repositorioVideoJuego);
		
		// act-assert
		BasePrueba.assertThrows(() -> servicio.ejecutar(alquiler), ExcepcionSinDatos.class, "VideoJuego no existe");
		
	}
	
	@Test
	public void validarVideoJuegosAlquilerNoStockTest() {
		//arrange		
		AlquilerItem item = new AlquilerItemTestDataBuilder().conCantidad(20).build();
		Alquiler alquiler = new AlquilerTestDataBuilder().conItems(Arrays.asList(item)).build();
		repositorioAlquiler = Mockito.mock(RepositorioAlquiler.class);
		repositorioCliente = Mockito.mock(RepositorioCliente.class);
		repositorioVideoJuego = Mockito.mock(RepositorioVideoJuego.class);
		Mockito.when(repositorioAlquiler.existeAlquilerVigente(alquiler.getCliente().getIdentificacion())).thenReturn(false);
		Mockito.when(repositorioAlquiler.crear(alquiler)).thenReturn(1L);
		Mockito.when(repositorioCliente.existe(Mockito.anyString())).thenReturn(true);
		Mockito.when(repositorioVideoJuego.existe(Mockito.anyString())).thenReturn(true);
		Mockito.when(repositorioVideoJuego.consultar(Mockito.anyLong())).thenReturn(new VideoJuegoTestDataBuilder().build());
		ServicioCrearAlquiler servicio = new ServicioCrearAlquiler(repositorioAlquiler, repositorioCliente, repositorioVideoJuego);
		
		// act-assert
		BasePrueba.assertThrows(() -> servicio.ejecutar(alquiler), ExcepcionValorInvalido.class, "Sin stock para alquiler");
		
	}
	
	

}
