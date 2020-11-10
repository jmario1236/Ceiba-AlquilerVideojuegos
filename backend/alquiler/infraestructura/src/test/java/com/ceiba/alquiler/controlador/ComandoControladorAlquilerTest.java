package com.ceiba.alquiler.controlador;

import static org.hamcrest.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ceiba.ApplicationMock;
import com.ceiba.alquiler.comando.ComandoCrearAlquiler;
import com.ceiba.alquiler.controlador.alquiler.ComandoControladorAlquiler;
import com.ceiba.alquiler.controlador.testdatabuilder.ComandoCrearAlquilerTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(ComandoControladorAlquiler.class)
public class ComandoControladorAlquilerTest {
	
	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mocMvc;

	@Test
	public void crearTest() throws Exception {
		// arrange
		ComandoCrearAlquiler comando = new ComandoCrearAlquilerTestDataBuilder().build();

		// act - assert
		mocMvc.perform(post("/alquileres").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(comando))).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.valor").value(any(Integer.class)));
	}
	
	@Test
	public void finalizarAlquilerTest() throws Exception {
		// arrange
		ComandoCrearAlquiler comando = new ComandoCrearAlquilerTestDataBuilder().conId(2L).conCliente(4L).build();

		// act - assert
		mocMvc.perform(put("/alquileres/{id}",comando.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(comando)))
				.andExpect(status().isOk());
		
		mocMvc.perform(get("/alquileres/cliente/{id}",comando.getCliente())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())				
				.andExpect(jsonPath("$").doesNotExist());
	}
	
}
