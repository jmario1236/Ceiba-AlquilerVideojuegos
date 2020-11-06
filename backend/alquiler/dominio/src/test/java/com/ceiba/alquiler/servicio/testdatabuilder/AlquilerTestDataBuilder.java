package com.ceiba.alquiler.servicio.testdatabuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ceiba.alquiler.modelo.entidad.Alquiler;
import com.ceiba.alquiler.modelo.entidad.AlquilerItem;
import com.ceiba.alquiler.modelo.entidad.ClienteId;

public class AlquilerTestDataBuilder {
	private Long id;
	private ClienteId cliente;
	private LocalDate fechaAlquiler;
	private LocalDate fechaMaximaEntrega;
	private List<AlquilerItem> items;
	
	public AlquilerTestDataBuilder() {
		cliente = new ClienteTestDataBuilder().build().getId();
		fechaAlquiler = LocalDate.parse("2020-11-03");
		fechaMaximaEntrega = LocalDate.parse("2020-11-06");		
		items = new AlquilerItemTestDataBuilder().buildList();
	}
	
	public AlquilerTestDataBuilder conCliente(ClienteId cliente) {
		this.cliente = cliente;
		return this;
	}
	
	public AlquilerTestDataBuilder conItems(List<AlquilerItem> items) {
		this.items = items != null ? new ArrayList<>(items): null;
		return this;
	}
	
	public AlquilerTestDataBuilder conId(Long id) {
		this.id = id;
		return this;
	}
	
	public AlquilerTestDataBuilder conFechaAlquiler(LocalDate fechaAlquiler) {
		this.fechaAlquiler = fechaAlquiler;
		return this;
	}
	
	public AlquilerTestDataBuilder conFechaMaximaEntrega(LocalDate fechaMaximaEntrega) {
		this.fechaMaximaEntrega = fechaMaximaEntrega;
		return this;
	}
	
	public Alquiler build() {
		return new Alquiler(id, cliente, fechaAlquiler, fechaMaximaEntrega, items);
	}
	
}
