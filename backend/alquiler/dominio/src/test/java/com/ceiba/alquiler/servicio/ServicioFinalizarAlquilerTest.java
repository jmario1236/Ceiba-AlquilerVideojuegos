package com.ceiba.alquiler.servicio;



import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.alquiler.modelo.entidad.Alquiler;
import com.ceiba.alquiler.puerto.repositorio.RepositorioAlquiler;
import com.ceiba.alquiler.servicio.testdatabuilder.AlquilerTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;

public class ServicioFinalizarAlquilerTest {
	
	private RepositorioAlquiler repositorioAlquiler;
	
	
	@Test
	public void alquilerInvalidoTest() {
		//arrange
		Alquiler alquiler = new AlquilerTestDataBuilder().build();
		repositorioAlquiler = Mockito.mock(RepositorioAlquiler.class);			
		Mockito.when(repositorioAlquiler.existe(alquiler.getId())).thenReturn(false);
		ServicioFinalizarAlquiler servicio = new ServicioFinalizarAlquiler(repositorioAlquiler);		
		//act - assert
		BasePrueba.assertThrows(() -> servicio.ejecutar(alquiler), ExcepcionSinDatos.class, "Alquiler no valido");
		
		
	}
	
	@Test
	public void ejecutarTest() {
		//arrange
		Alquiler alquiler = new AlquilerTestDataBuilder().build();
		repositorioAlquiler = Mockito.mock(RepositorioAlquiler.class);			
		Mockito.when(repositorioAlquiler.existe(alquiler.getId())).thenReturn(true);
		Mockito.when(repositorioAlquiler.consultar(alquiler.getId())).thenReturn(alquiler);
		ServicioFinalizarAlquiler servicio = new ServicioFinalizarAlquiler(repositorioAlquiler);		
		//act 
		servicio.ejecutar(alquiler);
		
		//assert
		Mockito.verify(repositorioAlquiler, Mockito.times(1)).finalizar(alquiler);
		
		
	}
}
