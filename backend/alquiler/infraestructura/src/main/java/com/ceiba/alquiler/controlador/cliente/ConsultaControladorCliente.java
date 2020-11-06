package com.ceiba.alquiler.controlador.cliente;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.alquiler.consulta.ManejadorListarClientes;
import com.ceiba.alquiler.modelo.dto.DtoCliente;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clientes")
@Api(tags={"Controlador consulta clientes"})
public class ConsultaControladorCliente {

	private final ManejadorListarClientes manejadorListarClientes;

	public ConsultaControladorCliente(ManejadorListarClientes manejadorListarClientes) {		
		this.manejadorListarClientes = manejadorListarClientes;
	}
	
	@GetMapping
	@CrossOrigin(origins = "http://localhost:4200")
    @ApiOperation("Listar Clientes")
    public List<DtoCliente> listar() {
        return this.manejadorListarClientes.ejecutar();
    }
	
}
