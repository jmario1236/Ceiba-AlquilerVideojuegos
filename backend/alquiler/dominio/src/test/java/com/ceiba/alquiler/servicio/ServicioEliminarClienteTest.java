package com.ceiba.alquiler.servicio;

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
		Mockito.when(repositorio.existe(cliente.getIdentificacion())).thenReturn(false);
		ServicioEliminarCliente servicio = new ServicioEliminarCliente(repositorio);
		
		// act-assert
		BasePrueba.assertThrows(() -> servicio.ejecutar(cliente), ExcepcionSinDatos.class, "El cliente no existe");
		
	}
}
