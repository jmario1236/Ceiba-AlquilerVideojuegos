package com.ceiba.alquiler.controlador.alquiler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.ComandoRespuesta;
import com.ceiba.alquiler.comando.ComandoCrearAlquiler;
import com.ceiba.alquiler.comando.manejador.ManejadorCrearAlquiler;
import com.ceiba.alquiler.comando.manejador.ManejadorFinalizarAlquiler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/alquileres")
@CrossOrigin(origins = "http://localhost:4200")
@Api(tags = { "Controlador comando de alquileres" })
public class ComandoControladorAlquiler {

	private final ManejadorCrearAlquiler manejadorCrearAlquiler;
	private final ManejadorFinalizarAlquiler manejadorFinalizarAlquiler;

	@Autowired
	public ComandoControladorAlquiler(ManejadorCrearAlquiler manejadorCrearAlquiler, ManejadorFinalizarAlquiler manejadorFinalizarAlquiler) {
		this.manejadorCrearAlquiler = manejadorCrearAlquiler;
		this.manejadorFinalizarAlquiler = manejadorFinalizarAlquiler;
	}
	
	@PostMapping
    @ApiOperation("Crear alquiler")
	public ComandoRespuesta<Long> crear(@RequestBody ComandoCrearAlquiler comando){
		return manejadorCrearAlquiler.ejecutar(comando);
	}
	
	
	@PutMapping(value="/{id}")
    @ApiOperation("Finalizar alquiler")
	public void finalizar(@RequestBody ComandoCrearAlquiler comando, @PathVariable Long id){
		comando.setId(id);
		manejadorFinalizarAlquiler.ejecutar(comando);
	}
	
}
