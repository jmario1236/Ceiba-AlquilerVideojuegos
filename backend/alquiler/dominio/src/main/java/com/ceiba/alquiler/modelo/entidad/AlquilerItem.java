package com.ceiba.alquiler.modelo.entidad;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static com.ceiba.dominio.ValidadorArgumento.validarPositivo;
import lombok.Getter;

@Getter
public class AlquilerItem {
	private static final String ADICIONE_UN_VIDEOJUEGO = "Adicione un videojuego";
	private static final String INGRESE_UNA_CANTIDAD = "Ingrese una cantidad";
	private static final String SE_DEBE_INGRESAR_PRECIO = "Se debe ingresar un precio item";
	private static final String INGRESE_UNA_CANTIDAD_MATOR_CERO = "Ingrese cantidad mayor a cero";
	private static final String INGRESE_UNA_PRECIO_MATOR_CERO = "Ingrese precio mayor a cero";
	
	private Long id;
	private VideoJuegoId videoJuego;
	private Double precio;
	private Integer cantidad;
	
	public AlquilerItem(Long id, VideoJuegoId videoJuego, Integer cantidad, Double precio) {
		validarObligatorio(videoJuego, ADICIONE_UN_VIDEOJUEGO);
		validarObligatorio(cantidad, INGRESE_UNA_CANTIDAD);
		validarObligatorio(precio, SE_DEBE_INGRESAR_PRECIO);
		validarPositivo(cantidad.doubleValue(),INGRESE_UNA_CANTIDAD_MATOR_CERO);
		validarPositivo(precio,INGRESE_UNA_PRECIO_MATOR_CERO);
		this.id = id;
		this.videoJuego = videoJuego;
		this.precio =  precio;
		this.cantidad = cantidad;
	}	
	
	public Double generarImporte() {
		return precio * cantidad.doubleValue();
	}	
	
}
