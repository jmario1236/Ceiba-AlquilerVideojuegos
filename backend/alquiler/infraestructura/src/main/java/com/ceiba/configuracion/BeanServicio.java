package com.ceiba.configuracion;

import com.ceiba.alquiler.puerto.repositorio.RepositorioVideoJuego;
import com.ceiba.alquiler.servicio.videojuego.ServicioActualizarVideoJuego;
import com.ceiba.alquiler.servicio.videojuego.ServicioCrearVideoJuego;
import com.ceiba.alquiler.servicio.videojuego.ServicioEliminarVideoJuego;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioActualizarUsuario;
import com.ceiba.usuario.servicio.ServicioCrearUsuario;
import com.ceiba.usuario.servicio.ServicioEliminarUsuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearUsuario servicioCrearUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioCrearUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioEliminarUsuario servicioEliminarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioEliminarUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioActualizarUsuario servicioActualizarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioActualizarUsuario(repositorioUsuario);
    }
	
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
