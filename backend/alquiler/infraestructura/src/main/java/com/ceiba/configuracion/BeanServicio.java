package com.ceiba.configuracion;

import com.ceiba.alquiler.puerto.repositorio.RepositorioVideoJuego;
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

}
