package com.ceiba.configuracion;

import com.ceiba.alquiler.puerto.repositorio.RepositorioAlquiler;
import com.ceiba.alquiler.puerto.repositorio.RepositorioCliente;
import com.ceiba.alquiler.puerto.repositorio.RepositorioVideoJuego;
import com.ceiba.alquiler.servicio.ServicioCrearAlquiler;
import com.ceiba.alquiler.servicio.cliente.ServicioActualizarCliente;
import com.ceiba.alquiler.servicio.cliente.ServicioCrearCliente;
import com.ceiba.alquiler.servicio.cliente.ServicioEliminarCliente;
import com.ceiba.alquiler.servicio.videojuego.ServicioActualizarVideoJuego;
import com.ceiba.alquiler.servicio.videojuego.ServicioCrearVideoJuego;
import com.ceiba.alquiler.servicio.videojuego.ServicioEliminarVideoJuego;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio { 
	
    @Bean
    public ServicioCrearVideoJuego servicioCrearVideoJuego (RepositorioVideoJuego repositorioVideoJuego) {
    	return new ServicioCrearVideoJuego(repositorioVideoJuego);
    }
    
    @Bean
    public ServicioEliminarVideoJuego servicioEliminarVideoJuego (RepositorioVideoJuego repositorioVideoJuego) {
    	return new ServicioEliminarVideoJuego(repositorioVideoJuego);
    }
    
    @Bean
    public ServicioActualizarVideoJuego servicioActualizarVideoJuego (RepositorioVideoJuego repositorioVideoJuego) {
    	return new ServicioActualizarVideoJuego(repositorioVideoJuego);
    }
    
    @Bean
    public ServicioCrearCliente servicioCrearCliente (RepositorioCliente repositorioCliente) {
    	return new ServicioCrearCliente(repositorioCliente);
    }
    
    @Bean
    public ServicioEliminarCliente servicioEliminarCliente (RepositorioCliente repositorioCliente) {
    	return new ServicioEliminarCliente(repositorioCliente);
    }
    
    @Bean
    public ServicioActualizarCliente servicioActualizarCliente (RepositorioCliente repositorioCliente) {
    	return new ServicioActualizarCliente(repositorioCliente);
    }

    @Bean
    public ServicioCrearAlquiler servicioCrearAlquiler (RepositorioAlquiler repositorioAlquiler, RepositorioCliente repositorioCliente, RepositorioVideoJuego repositorioVideoJuego) {
    	return new ServicioCrearAlquiler(repositorioAlquiler, repositorioCliente, repositorioVideoJuego);
    }
}
