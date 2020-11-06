package com.ceiba.alquiler.controlador.videojuego;

import static org.hamcrest.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.ceiba.alquiler.comando.ComandoVideoJuego;
import com.ceiba.alquiler.controlador.testdatabuilder.ComandoVideoJuegoTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(ComandoControladorVideoJuego.class)
public class ComandoControladorVideoJuegoTest {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mocMvc;

	@Test
	public void crear() throws Exception {
		// arrange
		ComandoVideoJuego comandoVideoJuego = new ComandoVideoJuegoTestDataBuilder().build();

		// act - assert
		mocMvc.perform(post("/videojuegos").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(comandoVideoJuego))).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.valor").value(any(Integer.class)));
	}
	
	@Test
    public void actualizar() throws Exception{
        // arrange
        Long id = 2L;
        ComandoVideoJuego comandoVideoJuego = new ComandoVideoJuegoTestDataBuilder().conCodigo("MRK-01").build();

        // act - assert
        mocMvc.perform(put("/videojuegos/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoVideoJuego)))
                .andExpect(status().isOk());
    }

	@Test
	public void eliminar() throws Exception {
		// arrange
		Long id = 1L;

		// act - assert
		mocMvc.perform(
				delete("/videojuegos/{id}", id)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
