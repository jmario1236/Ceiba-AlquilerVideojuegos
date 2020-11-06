package com.ceiba.alquiler.modelo.entidad;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.junit.Test;

import com.ceiba.BasePrueba;
import com.ceiba.alquiler.servicio.testdatabuilder.AlquilerTestDataBuilder;
import com.ceiba.alquiler.servicio.testdatabuilder.VideoJuegoTestDataBuilder;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;

public class AlquilerTest {
	
	private static final int NUMERO_DIAS_ALQUILER = 3;
	
	
	@Test
	public void validarDiasAdicionales() {
		//arrange
		LocalDate fechaInicial = LocalDate.parse("2020-11-03");
		double PORCENTAJE_ADICIONAL = 0.1;
		LocalDate fechaFinal = LocalDate.parse("2020-11-08");
		long diasDiff = ChronoUnit.DAYS.between(fechaInicial, fechaFinal) - NUMERO_DIAS_ALQUILER;
		//act
		Alquiler alquiler = new AlquilerTestDataBuilder()
				.conFechaMaximaEntrega(fechaFinal)
				.conFechaAlquiler(fechaInicial)
				.build();		
		//assert
		assertEquals(
				alquiler.getSubtotal()*PORCENTAJE_ADICIONAL*diasDiff, 
				alquiler.getTotalAdiccional().doubleValue());
		
	}
	
	@Test
	public void validarDiasMultas() {
		//arrange
		LocalDate fechaInicial = LocalDate.parse("2020-10-29");		
		LocalDate fechaFinal = LocalDate.parse("2020-11-01");
		LocalDate hoy = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		double PORCENTAJE_MULTA = 0.2;
		long diasMulta = ChronoUnit.DAYS.between(fechaFinal, hoy);
		//act
		Alquiler alquiler = new AlquilerTestDataBuilder()
				.conFechaMaximaEntrega(fechaFinal)
				.conFechaAlquiler(fechaInicial)
				.build();		
		//assert
		assertEquals(
				alquiler.getSubtotal()*PORCENTAJE_MULTA*diasMulta, 
				alquiler.getTotalMulta().doubleValue());
		
	}
	
	@Test
	public void validarEstadoVigente() {
		//arrange
		String vigente = "VIGENTE";
		LocalDate fechaInicial = LocalDate.parse("2020-11-03");		
		LocalDate fechaFinal = LocalDate.parse("2020-11-08");		
		Alquiler alquiler = new AlquilerTestDataBuilder()
				.conFechaMaximaEntrega(fechaFinal)
				.conFechaAlquiler(fechaInicial)
				.build();
		//act
		alquiler.cambiarEstadoVigente();
		
		//assert
		assertEquals(
				vigente, 
				alquiler.getEstado().getValue());		
	}
	
	@Test
	public void validarEstadoVigenteSinLista() {
		//arrange		
		LocalDate fechaInicial = LocalDate.parse("2020-11-03");		
		LocalDate fechaFinal = LocalDate.parse("2020-11-08");		
		Alquiler alquiler = new AlquilerTestDataBuilder()
				.conFechaMaximaEntrega(fechaFinal)
				.conItems(null)
				.conFechaAlquiler(fechaInicial)
				.build();
		
		// act-assert
		BasePrueba.assertThrows(() -> alquiler.cambiarEstadoVigente(), ExcepcionSinDatos.class, "El alquiler debe tener al menos un juego");	
	}
	
	
	@Test
	public void validarAgregarVideoJuego() {
		//arrange		
		LocalDate fechaInicial = LocalDate.parse("2020-11-03");		
		LocalDate fechaFinal = LocalDate.parse("2020-11-06");		
		VideoJuego videoJuego = new VideoJuegoTestDataBuilder().build();
		Alquiler alquiler = new AlquilerTestDataBuilder()
				.conFechaMaximaEntrega(fechaFinal)
				.conId(1L)
				.conItems(null)
				.conFechaAlquiler(fechaInicial)
				.build();
		
		//act
		alquiler.agregarItem(null, videoJuego.getId(), 1, videoJuego.getPrecio());
		
		
		// assert
		assertEquals(videoJuego.getPrecio(), alquiler.getSubtotal());
	}
	
	@Test
	public void validarEstadoFinalizado() {
		//arrange	
		String finalizado = "FINALIZADO";
		LocalDate fechaInicial = LocalDate.parse("2020-11-03");		
		LocalDate fechaFinal = LocalDate.parse("2020-11-08");		
		Alquiler alquiler = new AlquilerTestDataBuilder()
				.conFechaMaximaEntrega(fechaFinal)
				.conFechaAlquiler(fechaInicial)
				.conId(1L)
				.build();
		//act
		alquiler.cambiarEstadoVigente();
		alquiler.finalizarAlquiler();
		
		//assert
		assertEquals(
				finalizado, 
				alquiler.getEstado().getValue());		
	}
	
}
