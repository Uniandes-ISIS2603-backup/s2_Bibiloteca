/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.SalaEntity;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.SalaPersistence;
import java.util.logging.Logger;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Nicolás García
 */
@Stateless
public class SalaLogic {
    private static final Logger LOGGER = Logger.getLogger(UsuarioLogic.class.getName());
    @Inject
    private SalaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
    /**
     * Crea una sala en la persistencia.
     *
     * @param salaEntity La entidad que representa  a
     * persistir.
     * @return La entidad de la sala luego de persistirla.
     * @throws BusinessLogicException Si la sala a persistir ya existe.
     */
    public SalaEntity createSala(SalaEntity salaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del usuario");
        // Verifica la regla de negocio que dice que no puede haber dos salas con la misma ubicacion.
        if (persistence.findUbicacion(salaEntity.getUbicacion()) != null) {
           throw new BusinessLogicException("Ya existe una sala con la ubicacion \"" + salaEntity.getUbicacion() + "\"");
        }
        // Invoca la persistencia para crear la sala
        persistence.create(salaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la sala");
        return salaEntity;
    }
    
    /**
     *
     * Obtener todas las salas existentes en la base de datos.
     *
     * @return una lista de salas.
     */
    public List<SalaEntity> getSalas() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las salas");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<SalaEntity> salas = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las salas");
        return salas;
    }
    
     /**
     *
     * Obtener una sala por medio de su id.
     *
     * @param salaId: id del Usuario para ser buscado.
     * @return la sala solicitada por medio de su id.
     */
    public SalaEntity getSala(Long salaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la sala con id = {0}", salaId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        SalaEntity salaEntity = persistence.find(salaId);
        if (salaEntity == null) {
            LOGGER.log(Level.SEVERE, "La sala con el id = {0} no existe", salaId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la sala con id = {0}", salaId);
        return salaEntity;
    }
    
     /**
     *
     * Actualizar una sala.
     * @param salaId: id de la sala para buscarla en la base de
     * datos.
     * @param salaEntity: sala con los cambios para ser actualizada,
     * por ejemplo la disponibilidad.
     * @return el usuario con los cambios actualizados en la base de datos.
     */
    public SalaEntity updateSala(Long salaId, SalaEntity salaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la sala con id = {0}", salaId);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        SalaEntity newEntity = persistence.update(salaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la sala con id = {0}", salaEntity.getId());
        return newEntity;
    }
     public void deleteSala(Long salaId)
    {
        LOGGER.log(Level.INFO,"Comienza el proceso de eliminar la sala con id = {0}",salaId);
        persistence.delete(salaId);
        LOGGER.log(Level.INFO, "El proceso de borrado de la sala con id = {0} ha terminado",salaId);
    }
}
