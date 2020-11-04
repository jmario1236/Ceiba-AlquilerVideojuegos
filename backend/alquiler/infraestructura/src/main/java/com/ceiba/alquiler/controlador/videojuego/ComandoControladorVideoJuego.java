package com.ceiba.alquiler.controlador.videojuego;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.ComandoRespuesta;
import com.ceiba.alquiler.comando.ComandoVideoJuego;
import com.ceiba.alquiler.comando.manejador.videojuego.ManejadorActualizarVideoJuego;
import com.ceiba.alquiler.comando.manejador.videojuego.ManejadorCrearVideoJuego;
import com.ceiba.alquiler.comando.manejador.videojuego.ManejadorEliminarVideoJuego;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/videojuegos")
@Api(tags = { "Controlador comando videojuegos" })
public class ComandoControladorVideoJuego {

	private ManejadorCrearVideoJuego manejadorCrearVideoJuego;
	private ManejadorEliminarVideoJuego manejadorEliminarVideoJuego;
	private ManejadorActualizarVideoJuego manejadorActualizarVideoJuego;

	@Autowired
	public ComandoControladorVideoJuego(ManejadorCrearVideoJuego manejadorCrearVideoJuego,
			ManejadorEliminarVideoJuego manejadorEliminarVideoJuego,
			ManejadorActualizarVideoJuego manejadorActualizarVideoJuego) {
		this.manejadorCrearVideoJuego = manejadorCrearVideoJuego;
		this.manejadorEliminarVideoJuego = manejadorEliminarVideoJuego;
		this.manejadorActualizarVideoJuego = manejadorActualizarVideoJuego;
	}
	
	@PostMapping
    @ApiOperation("Crear VideoJuego")
	public ComandoRespuesta<Long> crear(@RequestBody ComandoVideoJuego comandoVideoJuego){
		return manejadorCrearVideoJuego.ejecutar(comandoVideoJuego);
	}
	
	@DeleteMapping(value="/{id}")
	@ApiOperation("Eliminar VideoJuego")
	public void eliminar(@PathVariable Long id) {
		manejadorEliminarVideoJuego.ejecutar(id); 
	}
	
	@PutMapping(value="/{id}")
	@ApiOperation("Actualizar VideoJuego")
	public void actualizar(@RequestBody ComandoVideoJuego comandoVideoJuego, @PathVariable Long id) {
		comandoVideoJuego.setId(id);
		manejadorActualizarVideoJuego.ejecutar(comandoVideoJuego);
	}
	
	
}
