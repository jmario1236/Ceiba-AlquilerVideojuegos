package com.ceiba.alquiler.controlador.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.ComandoRespuesta;
import com.ceiba.alquiler.comando.ComandoCliente;
import com.ceiba.alquiler.comando.manejador.cliente.ManejadorActualizarCliente;
import com.ceiba.alquiler.comando.manejador.cliente.ManejadorCrearCliente;
import com.ceiba.alquiler.comando.manejador.cliente.ManejadorEliminarCliente;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clientes")
@Api(tags = { "Controlador comando clientes" })
public class ComandoControladorCliente {

	private final ManejadorActualizarCliente manejadorActualizarCliente;
	private final ManejadorCrearCliente manejadorCrearCliente;
	private final ManejadorEliminarCliente manejadorEliminarCliente;
	
	@Autowired
	public ComandoControladorCliente(ManejadorActualizarCliente manejadorActualizarCliente,
			ManejadorCrearCliente manejadorCrearCliente, ManejadorEliminarCliente manejadorEliminarCliente) {		
		this.manejadorActualizarCliente = manejadorActualizarCliente;
		this.manejadorCrearCliente = manejadorCrearCliente;
		this.manejadorEliminarCliente = manejadorEliminarCliente;
	}
	
	@PostMapping
    @ApiOperation("Crear Cliente")
	public ComandoRespuesta<Long> crear(@RequestBody ComandoCliente comando){
		return manejadorCrearCliente.ejecutar(comando);
	}
	
	@DeleteMapping(value="/{id}")
	@ApiOperation("Eliminar Cliente")
	public void eliminar(@PathVariable Long id) {
		manejadorEliminarCliente.ejecutar(id); 
	}
	
	@PutMapping(value="/{id}")
	@ApiOperation("Actualizar Cliente")
	public void actualizar(@RequestBody ComandoCliente comando, @PathVariable Long id) {
		comando.setId(id);
		manejadorActualizarCliente.ejecutar(comando);
	}
	
}
