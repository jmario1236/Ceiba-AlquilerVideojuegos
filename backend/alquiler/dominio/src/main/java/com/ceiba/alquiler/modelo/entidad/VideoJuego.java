package com.ceiba.alquiler.modelo.entidad;



import lombok.Getter;
import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;
import static com.ceiba.dominio.ValidadorArgumento.validarPositivo;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;


@Getter
public class VideoJuego {
	private static final String SE_DEBE_INGRESAR_CODIGO = "Se debe ingresar un codigo";
	private static final String SE_DEBE_INGRESAR_NOMBRE = "Se debe ingresar un nombre";
	private static final String SE_DEBE_INGRESAR_PRECIO = "Se debe ingresar un precio";
	private static final String PRECIO_CON_VALOR_INCORRECTO = "Precio con valor incorrecto";
	private static final String SIN_STOCK_PARA_ALQUILER = "Sin stock para alquiler";
	private Long id;
	private String codigo;
	private String nombre;
	private String genero;
	private Double precio;
	private int stock;
	
	public VideoJuego(Long id, String codigo, String nombre, String genero, Double precio, int stock) {
		validarObligatorio(codigo, SE_DEBE_INGRESAR_CODIGO);
		validarObligatorio(nombre, SE_DEBE_INGRESAR_NOMBRE);
		validarObligatorio(precio, SE_DEBE_INGRESAR_PRECIO);		
		validarPositivo(precio, PRECIO_CON_VALOR_INCORRECTO);
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.genero = genero;
		this.precio = precio;
		this.stock = stock;
	}
	
	public void quitarDelStock(int cantidad) {
		if(stock  == 0 || stock < cantidad) {
			throw new ExcepcionValorInvalido(SIN_STOCK_PARA_ALQUILER);
		}
		stock -= cantidad;
	}
	
	public void agregarAlStock(int cantidad) {
		if(cantidad > 0) {
			stock += cantidad;
		}		
	}

	
	
	
	
}
