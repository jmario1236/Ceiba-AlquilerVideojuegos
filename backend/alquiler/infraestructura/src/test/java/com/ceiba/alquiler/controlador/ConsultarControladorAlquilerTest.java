package com.ceiba.alquiler.controlador;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ceiba.ApplicationMock;
import com.ceiba.alquiler.controlador.alquiler.ConsultarControladorAlquiler;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(ConsultarControladorAlquiler.class)
public class ConsultarControladorAlquilerTest {
	
	@Autowired
	private MockMvc mocMvc;
	
	@Test
	public void consultar() throws Exception {
		// arrange
		Long id_cliente = 3L;
		// act - assert
		mocMvc.perform(get("/alquileres/cliente/{id}",id_cliente)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())				
				.andExpect(jsonPath("$.estado", is("VIGENTE")));
	}
	
	@Test
	public void consultarNoExiste() throws Exception {
		// arrange
		Long id_cliente = 4L;
		// act - assert
		mocMvc.perform(get("/alquileres/cliente/{id}",id_cliente)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())				
				.andExpect(jsonPath("$").doesNotExist());
	}
}
