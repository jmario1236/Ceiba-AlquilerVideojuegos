package com.ceiba.alquiler.modelo.entidad;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static com.ceiba.dominio.ValidadorArgumento.validarPositivo;
import lombok.Getter;

@Getter
public class AlquilerItem {
	private static final String ADICIONE_UN_VIDEOJUEGO = "Adicione un videojuego";
	private static final String ALQUILER_INVALIDO = "Alquiler invalido";
	private static final String INGRESE_UNA_CANTIDAD = "Ingrese una cantidad";
	private static final String INGRESE_UNA_CANTIDAD_MATOR_CERO = "Ingrese cantidad mayor a cero";
	
	private Long id;
	private Long idAlquiler;
	private VideoJuego videoJuego;
	private Integer cantidad;
	
	public AlquilerItem(Long id, VideoJuego videoJuego, Integer cantidad, Long idAlquiler) {
		validarObligatorio(videoJuego, ADICIONE_UN_VIDEOJUEGO);
		validarObligatorio(cantidad, INGRESE_UNA_CANTIDAD);
		validarObligatorio(idAlquiler, ALQUILER_INVALIDO);
		validarPositivo(cantidad.doubleValue(),INGRESE_UNA_CANTIDAD_MATOR_CERO);
		this.id = id;
		this.videoJuego = videoJuego;
		this.cantidad = cantidad;
		this.idAlquiler = idAlquiler;
	}	
	
	public Double generarImporte() {
		return videoJuego.getPrecio() * cantidad.doubleValue();
	}	
	
}
