package com.ceiba.alquiler.modelo.entidad;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static com.ceiba.alquiler.modelo.entidad.Validador.validarFechasNoMayorQue;
import static com.ceiba.alquiler.modelo.entidad.Validador.validarFechasNumeroDias;
import lombok.Getter;

@Getter
public class Alquiler {
	private static final String DEBE_HABER_UN_CLIENTE = "Debe haber un cliente para realizar un alquiler";
	private static final String DEBE_INGRESAR_FECHA_ALQUILER = "Debe ingresar fecha de alquiler";
	private static final String DEBE_INGRESAR_FECHA_MAXIMA_ALQUILER = "Debe ingresar fecha maxima de entrega";
	private static final String EL_ALQUILER_DEBE_TENER_AL_MENOS_UN_JUEGO = "El alquiler debe tener al menos un juego";
	private static final String FECHAS_INVALIDA = "Fechas son invalidas";
	private static final String FECHA_NO_CUMPLE_MINIMO_DIAS = "Fecha maxima ingresada no cumple con el minimo de dias (3)";
	private static final String NO_SE_PUEDE_FINALIZAR_ALQUILER = "No se puede finalizar el alquiler sin registrar";
	private static final String VIGENTE = "VIGENTE";
	private static final String FINALIZADO = "FINALIZADO";

	private static final int NUMERO_DIAS_ALQUILER = 3;
	private static final double PORCENTAJE_ADICIONAL = 0.1;
	private static final double PORCENTAJE_MULTA = 0.2;

	private Long id;
	private ClienteId cliente;
	private LocalDate fechaAlquiler;
	private LocalDate fechaMaximaEntrega;
	private LocalDate fechaEntrega;
	private Estado estado;
	private Double total;
	private Double subtotal;
	private Double totalAdiccional;
	private Double totalMulta;
	private List<AlquilerItem> items;

	public Alquiler(Long id, ClienteId cliente, LocalDate fechaAlquiler, LocalDate fechaMaximaEntrega,
			List<AlquilerItem> items) {
		validarObligatorio(cliente, DEBE_HABER_UN_CLIENTE);
		validarObligatorio(fechaAlquiler, DEBE_INGRESAR_FECHA_ALQUILER);
		validarObligatorio(fechaMaximaEntrega, DEBE_INGRESAR_FECHA_MAXIMA_ALQUILER);
		validarFechasNoMayorQue(fechaMaximaEntrega, fechaAlquiler, FECHAS_INVALIDA);
		validarFechasNumeroDias(fechaAlquiler, fechaMaximaEntrega, NUMERO_DIAS_ALQUILER, FECHA_NO_CUMPLE_MINIMO_DIAS);
		this.id = id;
		this.cliente = cliente;
		this.fechaAlquiler = fechaAlquiler;
		this.fechaMaximaEntrega = fechaMaximaEntrega;
		this.items = items != null ? new ArrayList<>(items): null;
		subtotal = Double.valueOf(0);
		total = Double.valueOf(0);
		totalMulta = Double.valueOf(0);
		totalAdiccional = Double.valueOf(0);
		generarTotal();
	}

	public void agregarItem(Long id, VideoJuegoId videojuego, Integer cantidad, Double precio) {
		if (items == null) {
			items = new ArrayList<>();
		}
		this.items.add(new AlquilerItem(id, videojuego, cantidad, precio));
		generarTotal();
	}

	public void cambiarEstadoVigente() {
		if (items == null || items.isEmpty()) {
			throw new ExcepcionSinDatos(EL_ALQUILER_DEBE_TENER_AL_MENOS_UN_JUEGO);
		}
		this.estado = new Estado(VIGENTE);
	}

	public void finalizarAlquiler() {
		if(id == null || estado == null) {
			throw new ExcepcionSinDatos(NO_SE_PUEDE_FINALIZAR_ALQUILER);
		}
		this.fechaEntrega = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		this.estado = new Estado(FINALIZADO);
	}	
	
	private void generarTotal() {
		if (items == null) {
			return;
		}
		for (AlquilerItem item : items) {
			subtotal += item.generarImporte();
		}
		// Comprobamos si hay dias adiccionales
		generarTotalDiasAdicionales();
		generarMultas();
		total = subtotal + totalAdiccional + totalMulta;
	}

	private void generarMultas() {
		LocalDate hoy = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		if (fechaMaximaEntrega.isBefore(hoy)) {
			long dias = ChronoUnit.DAYS.between(fechaMaximaEntrega, hoy);
			totalMulta = (subtotal * PORCENTAJE_MULTA) * dias;
		}
	}

	private void generarTotalDiasAdicionales() {
		long dias = ChronoUnit.DAYS.between(fechaAlquiler, fechaMaximaEntrega) - NUMERO_DIAS_ALQUILER;		
		if (dias > 0) {
			totalAdiccional = (subtotal * PORCENTAJE_ADICIONAL) * dias;
		}		
	}

}
