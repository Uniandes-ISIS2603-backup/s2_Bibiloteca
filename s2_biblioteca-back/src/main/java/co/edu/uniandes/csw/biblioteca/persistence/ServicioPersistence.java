/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.persistence;

import co.edu.uniandes.csw.bibilioteca.entities.ServicioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para Servicio. Se conecta a través Entity
 * Manager de javax.persistance con la base de datos SQL.
 * @author Daniel Preciado
 */
@Stateless
public class ServicioPersistence {

    //__________________________________________________________________________
    //Constantes
    //__________________________________________________________________________
    
    /**
     * constante utilizada para dejar huella y registro
     */
    private static final Logger LOGGER = Logger.getLogger(ServicioPersistence.class.getName());
    
    //__________________________________________________________________________
    //Atributos
    //__________________________________________________________________________
    
    /**
     * 
     */
    @PersistenceContext(unitName = "DnsPU")
    protected EntityManager entityManager;
    
    //__________________________________________________________________________
    //Metodos
    //__________________________________________________________________________
    
     /**
     * Método para persisitir la entidad en la base de datos.
     * crea un nuevo servicio en la base de datos
     * @param pServicioEntity objeto servicio que se creará en la base de datos
     * @return devuelve la entidad creada con un id generado por la base de datos.
     */
    public ServicioEntity create(ServicioEntity pServicioEntity) 
    {
        LOGGER.log(Level.INFO, "Creando un servicio nuevo");
        entityManager.persist(pServicioEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un servicio nuevo");
        return pServicioEntity;
    }

    /**
     * Devuelve todos los servicios de la base de datos.
     * @return una lista con todos los servicios que encuentre en la base de
     * datos.
     */
    public List<ServicioEntity> findAll()    
    {
        LOGGER.log(Level.INFO, "Consultando todos los servicios");
        TypedQuery query;
        query = entityManager.createQuery("select u from ServicioEntity u", ServicioEntity.class);
        return query.getResultList();
    }

    /**
     * Busca si hay algun servicio con el id que se envía por parametro
     * @param pServicioId: id correspondiente al servicio buscado.
     * @return un servicio.
     */
    public ServicioEntity find(Long pServicioId) 
    {
        LOGGER.log(Level.INFO, "Consultando servicio con id={0}", pServicioId);
        return entityManager.find(ServicioEntity.class, pServicioId);
    }

    /**
     * Actualiza un servicio.
     * @param pServicioEntity: el servicio que viene con los nuevos cambios.
     * Por ejemplo la descripcion pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un servicio con los cambios aplicados.
     */
    public ServicioEntity update(ServicioEntity pServicioEntity) 
    {
        LOGGER.log(Level.INFO, "Actualizando el servicio con id = {0}", pServicioEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar el servicio con id = {0}", pServicioEntity.getId());
        return entityManager.merge(pServicioEntity);
    }

    /**
     * Borra un servicio de la base de datos recibiendo como parametro el id     *
     * @param pServicioId: id correspondiente al servicio a borrar.
     */
    public void delete(Long pServicioId)
    {
        LOGGER.log(Level.INFO, "Borrando el servicio con id = {0}", pServicioId);
        ServicioEntity entityToDelete = entityManager.find(ServicioEntity.class, pServicioId);
        entityManager.remove(entityToDelete);
        LOGGER.log(Level.INFO, "Saliendo de borrar el servicio con id = {0}", pServicioId);
    }

    /**
     * Busca si hay algun servicio con el nombre que se envía por parametro
     * @param pNombreServicio: nombre del servicio que se está buscando
     * @return null si no existe ningun servicio con el nombre dado.
     * Si existe alguno devuelve el primero.
     */
    public ServicioEntity findByServiceName(String pNombreServicio)
    {
        LOGGER.log(Level.INFO, "Consultando servicio por nombre ", pNombreServicio);
        TypedQuery query ;
        query = entityManager.createQuery("Select e From ServicioEntity e where e.nombreServicio = :pNombreServicio", ServicioEntity.class);
        query = query.setParameter("pNombreServicio", pNombreServicio);
        List<ServicioEntity> sameName = query.getResultList();
        ServicioEntity result;
        if (sameName == null) 
        {
            result = null;
        } 
        else if (sameName.isEmpty()) 
        {
            result = null;
        } 
        else 
        {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar servicio por nombre ", pNombreServicio);
        return result;
    }
    
}
