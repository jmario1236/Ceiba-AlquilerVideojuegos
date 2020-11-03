package com.ceiba.alquiler.servicio.cliente;

import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.alquiler.modelo.entidad.Cliente;
import com.ceiba.alquiler.puerto.repositorio.RepositorioCliente;
import com.ceiba.alquiler.servicio.testdatabuilder.ClienteTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;

public class ServicioActualizarClienteTest {
	
	@Test
	public void validarClienteNoExisteTest() {
		// arrange
		Cliente cliente = new ClienteTestDataBuilder().build();
		RepositorioCliente repositorio = Mockito.mock(RepositorioCliente.class);
		Mockito.when(repositorio.existe(cliente.getIdentificacion())).thenReturn(false);
		ServicioActualizarCliente servicio = new ServicioActualizarCliente(repositorio);
		
		// act-assert
		BasePrueba.assertThrows(() -> servicio.ejecutar(cliente), ExcepcionSinDatos.class, "El cliente no existe");
		
	}
}
