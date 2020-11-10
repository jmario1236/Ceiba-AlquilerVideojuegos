package com.ceiba.alquiler.controlador.testdatabuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ceiba.alquiler.comando.ComandoCrearAlquiler;
import com.ceiba.alquiler.comando.ComandoCrearAlquilerItem;

public class ComandoCrearAlquilerTestDataBuilder {
	private Long id;
	private LocalDate fechaAlquiler;
	private LocalDate fechaMaximaEntrega;
	private Long cliente;
	private List<ComandoCrearAlquilerItem> items;
	
	public ComandoCrearAlquilerTestDataBuilder() {
		fechaAlquiler = LocalDate.parse("2020-11-03");
		fechaMaximaEntrega = LocalDate.parse("2020-11-06");
		cliente = 2L;
		generarItems();
	}
	
	public ComandoCrearAlquilerTestDataBuilder conId(Long id) {
		this.id = id;
		return this;
	}
	
	public ComandoCrearAlquilerTestDataBuilder conCliente(Long cliente) {
		this.cliente = cliente;
		return this;
	}
	
	private void generarItems() {
		items = new ArrayList<ComandoCrearAlquilerItem>();
		items.add(new ComandoCrearAlquilerItem(null, 2L, 3, 5000.0));
		items.add(new ComandoCrearAlquilerItem(null, 3L , 1, 5000.0));
	}
	
	public ComandoCrearAlquiler build() {
		return new ComandoCrearAlquiler(id, fechaAlquiler, fechaMaximaEntrega, cliente, items);
	}
}
