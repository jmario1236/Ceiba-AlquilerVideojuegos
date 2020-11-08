package com.ceiba.alquiler.controlador.alquiler;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.alquiler.consulta.ManejadorConsultarAlquilerPorCliente;
import com.ceiba.alquiler.modelo.dto.DtoAlquiler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/alquileres")
@Api(tags={"Controlador consulta alquiler"})
public class ConsultarControladorAlquiler {
	
	private final ManejadorConsultarAlquilerPorCliente manejadorConsultarAlquilerPorCliente;
	
	
	public ConsultarControladorAlquiler(ManejadorConsultarAlquilerPorCliente manejadorConsultarAlquilerPorCliente) {		
		this.manejadorConsultarAlquilerPorCliente = manejadorConsultarAlquilerPorCliente;
	}

	@GetMapping(value="/cliente/{id}")
    @ApiOperation("Consultar Clientes con criterios")
    public DtoAlquiler consultarCriterios(@PathVariable Long id) {
        return this.manejadorConsultarAlquilerPorCliente.ejecutar(id);
    }
}
