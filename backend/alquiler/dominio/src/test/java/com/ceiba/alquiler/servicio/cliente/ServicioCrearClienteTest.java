package com.ceiba.alquiler.servicio.cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.alquiler.modelo.entidad.Cliente;
import com.ceiba.alquiler.puerto.repositorio.RepositorioCliente;
import com.ceiba.alquiler.servicio.testdatabuilder.ClienteTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

public class ServicioCrearClienteTest {

	@Test
	public void crearClienteTest() {
		//arrange
		Cliente cliente = new ClienteTestDataBuilder().build();
		RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
		Mockito.when(repositorioCliente.crear(cliente)).thenReturn(1L);
		ServicioCrearCliente servicio = new ServicioCrearCliente(repositorioCliente);
		
		//act
		Long id = servicio.ejecutar(cliente);
		
		//assert
		assertEquals(1L, id.longValue());	
	}
	
	@Test
	public void validarIdentificacionExistenteTest() {
		//arrange
		Cliente cliente = new ClienteTestDataBuilder().build();
		RepositorioCliente repositorioCliente = Mockito.mock(RepositorioCliente.class);
		Mockito.when(repositorioCliente.existe(cliente.getIdentificacion())).thenReturn(true);
		ServicioCrearCliente servicio = new ServicioCrearCliente(repositorioCliente);
		
		//act-assert		
		BasePrueba.assertThrows(() -> servicio.ejecutar(cliente), ExcepcionDuplicidad.class, "El cliente ya existe en el sistema");
	}
}
