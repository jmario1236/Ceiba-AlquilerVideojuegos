package com.ceiba.alquiler.puerto.repositorio;

import com.ceiba.alquiler.modelo.entidad.Cliente;

public interface RepositorioCliente {
	 /**
     * Permite crear un cliente
     * @param cliente
     * @return el id generado
     */
    Long crear(Cliente cliente);
    
    /**
     * Permite saber si un cliente existe mediante su identificacion
     * @param identificacion
     * @return true si existe, false si no.
     */
    boolean existe(String identificacion);
    
    /**
     * Permite actualizar un cliente
     * @param cliente
     */
    void actualizar(Cliente cliente);
    
    /**
     * Permite eliminar un cliente
     * @param id
     */
    void eliminar(Long id);
}
