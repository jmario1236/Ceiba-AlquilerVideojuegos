package com.ceiba.alquiler.controlador.videojuego;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.alquiler.consulta.ManejadorListarVideoJuegos;
import com.ceiba.alquiler.modelo.dto.DtoVideoJuego;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/videojuegos")
@CrossOrigin(origins = "http://localhost:4200")
@Api(tags={"Controlador consulta videojuegos"})
public class ConsultaControladorVideoJuego {
	
	private final ManejadorListarVideoJuegos manejadorListarVideoJuegos;   

    public ConsultaControladorVideoJuego(ManejadorListarVideoJuegos manejadorListarVideoJuegos) {		
		this.manejadorListarVideoJuegos = manejadorListarVideoJuegos;
	}

	@GetMapping
    @ApiOperation("Listar VideoJuegos")
    public List<DtoVideoJuego> listar() {
        return this.manejadorListarVideoJuegos.ejecutar();
    }
}
