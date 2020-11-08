package com.ceiba.alquiler.controlador.cliente;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.alquiler.consulta.ManejadorConsultarCriterioClientes;
import com.ceiba.alquiler.consulta.ManejadorListarClientes;
import com.ceiba.alquiler.modelo.dto.DtoCliente;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/clientes")
@Api(tags={"Controlador consulta clientes"})
public class ConsultaControladorCliente {

	private final ManejadorListarClientes manejadorListarClientes;
	private final ManejadorConsultarCriterioClientes manejadorConsultarCriterioClientes;

	public ConsultaControladorCliente(ManejadorListarClientes manejadorListarClientes, ManejadorConsultarCriterioClientes manejadorConsultarCriterioClientes) {		
		this.manejadorListarClientes = manejadorListarClientes;
		this.manejadorConsultarCriterioClientes = manejadorConsultarCriterioClientes;
	}
	
	@GetMapping	
    @ApiOperation("Listar Clientes")
    public List<DtoCliente> listar() {
        return this.manejadorListarClientes.ejecutar();
    }
	
	@GetMapping(value="/consultar/{criterio}")
    @ApiOperation("Consultar Clientes con criterios")
    public List<DtoCliente> consultarCriterios(@PathVariable String criterio) {
        return this.manejadorConsultarCriterioClientes.ejecutar(criterio);
    }
}
