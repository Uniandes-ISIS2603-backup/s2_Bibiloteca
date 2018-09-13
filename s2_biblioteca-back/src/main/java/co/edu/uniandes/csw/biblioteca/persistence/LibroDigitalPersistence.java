/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.persistence;

import co.edu.uniandes.csw.bibilioteca.entities.LibroDigitalEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para LibroDigital. Se conecta a través Entity
 * Manager de javax.persistance con la base de datos SQL.
 * @author Daniel Preciado
 */
@Stateless
public class LibroDigitalPersistence {
    
    //__________________________________________________________________________
    //Constantes
    //__________________________________________________________________________
    
    /**
     * constante utilizada para dejar huella y registro
     */
    private static final Logger LOGGER = Logger.getLogger(LibroDigitalPersistence.class.getName());
    
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
     * crea un nuevo LibroDigital en la base de datos
     * @param pLibroDigitalEntity objeto LibroDigital que se creará en la base de datos
     * @return devuelve la entidad creada con un id generado por la base de datos.
     */
    public LibroDigitalEntity create(LibroDigitalEntity pLibroDigitalEntity) 
    {
        LOGGER.log(Level.INFO, "Creando un libroDigital nuevo");
        entityManager.persist(pLibroDigitalEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un libroDigital nuevo");
        return pLibroDigitalEntity;
    }

    /**
     * Devuelve todos los LibroDigitales de la base de datos.
     * @return una lista con todos los LibroDigitales que encuentre en la base de
     * datos.
     */
    public List<LibroDigitalEntity> findAll()    
    {
        LOGGER.log(Level.INFO, "Consultando todos los LibrosDigitales");
        TypedQuery query;
        query = entityManager.createQuery("select u from LibroDigitalEntity u", LibroDigitalEntity.class);
        return query.getResultList();
    }

    /**
     * Busca si hay algun LibroDigital con el id que se envía por parametro
     * @param pLibroDigitalId: id correspondiente al LibroDigital buscado.
     * @return un LibroDigital.
     */
    public LibroDigitalEntity find(Long pLibroDigitalId) 
    {
        LOGGER.log(Level.INFO, "Consultando libro digital con id={0}", pLibroDigitalId);
        return entityManager.find(LibroDigitalEntity.class, pLibroDigitalId);
    }

    /**
     * Actualiza un LibroDigital.
     * @param pLibroDigitalEntity: el LibroDigital que viene con los nuevos cambios.
     * Por ejemplo la edicion pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un LibroDigital con los cambios aplicados.
     */
    public LibroDigitalEntity update(LibroDigitalEntity pLibroDigitalEntity )
    {
        LOGGER.log(Level.INFO, "Actualizando el LibroDigital con id = {0}", pLibroDigitalEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar el LibroDigital con id = {0}", pLibroDigitalEntity.getId());
        return entityManager.merge(pLibroDigitalEntity);
    }

    /**
     * Borra un LibroDigital de la base de datos recibiendo como parametro el id     *
     * @param pLibroDigitalId: id correspondiente al LibroDigital a borrar.
     */
    public void delete(Long pLibroDigitalId)
    {
        LOGGER.log(Level.INFO, "Borrando el LibroDigital con id = {0}", pLibroDigitalId);
        LibroDigitalEntity entityToDelete = entityManager.find(LibroDigitalEntity.class, pLibroDigitalId);
        entityManager.remove(entityToDelete);
        LOGGER.log(Level.INFO, "Saliendo de borrar el LibroDigital con id = {0}", pLibroDigitalId);
    }

    /**
     * Busca si hay algun LibroDigital con el nombre que se envía por parametro
     * @param pNombreLibroDigital: nombre del LibroDigital que se está buscando
     * @return null si no existe ningun LibroDigital con el nombre dado.
     * Si existe alguno devuelve el primero.
     */
    public LibroDigitalEntity findByServiceName(String pNombreLibroDigital)
    {
        LOGGER.log(Level.INFO, "Consultando LibroDigital por nombre ", pNombreLibroDigital);
        TypedQuery query ;
        query = entityManager.createQuery("Select e From LibroDigitalEntity e where e.nombre = :pNombreServicio", LibroDigitalEntity.class);
        query = query.setParameter("pNombreServicio", pNombreLibroDigital);
        List<LibroDigitalEntity> sameName = query.getResultList();
        LibroDigitalEntity result;
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
        LOGGER.log(Level.INFO, "Saliendo de consultar el LibroDigital por nombre ", pNombreLibroDigital);
        return result;
    }
    
}
