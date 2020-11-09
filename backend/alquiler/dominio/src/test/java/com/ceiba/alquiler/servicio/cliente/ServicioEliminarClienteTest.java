package com.ceiba.alquiler.servicio.cliente;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.alquiler.modelo.entidad.Cliente;
import com.ceiba.alquiler.puerto.repositorio.RepositorioCliente;
import com.ceiba.alquiler.servicio.testdatabuilder.ClienteTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.BasePrueba;



public class ServicioEliminarClienteTest {
	@Test
	public void validarClienteNoExisteTest() {
		// arrange
		Cliente cliente = new ClienteTestDataBuilder().build();
		RepositorioCliente repositorio = Mockito.mock(RepositorioCliente.class);
		Mockito.when(repositorio.existeId(cliente.getId())).thenReturn(false);
		ServicioEliminarCliente servicio = new ServicioEliminarCliente(repositorio);
		
		// act-assert
		BasePrueba.assertThrows(() -> servicio.ejecutar(cliente.getId()), ExcepcionSinDatos.class, "El cliente no existe");
		
	}
	
	@Test
	public void validarEjecutar() {
		// arrange
		Cliente cliente = new ClienteTestDataBuilder().build();
		RepositorioCliente repositorio = Mockito.mock(RepositorioCliente.class);
		Mockito.when(repositorio.existeId(cliente.getId())).thenReturn(true);
		ServicioEliminarCliente servicio = new ServicioEliminarCliente(repositorio);
		Mockito.doNothing().when(repositorio).eliminar(cliente.getId());
		//act
		servicio.ejecutar(cliente.getId());
		
		//assert
		Mockito.verify(repositorio, Mockito.times(1)).eliminar(cliente.getId());
		
	}
	
}
