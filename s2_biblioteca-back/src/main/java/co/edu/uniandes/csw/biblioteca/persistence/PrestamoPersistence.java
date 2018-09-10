/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.persistence;

import co.edu.uniandes.csw.bibilioteca.entities.PrestamoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Clase que maneja la persistencia para Prestamo. Se conecta a través del Entity
 * Manager de javax.persistance con la base de datos SQL.
 *
 * @author David Eduardo Saavedra Hernandeza
 */
@Stateless
public class PrestamoPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(PrestamoPersistence.class.getName());

    @PersistenceContext(unitName = "DnsPU")
    protected EntityManager em;
    
     /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param prestamoEntity objeto prestamo que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PrestamoEntity create(PrestamoEntity prestamoEntity) {
        LOGGER.log(Level.INFO, "Creando un prestamo nuevo");
        em.persist(prestamoEntity);
        LOGGER.log(Level.INFO, "Libro creado");
        return prestamoEntity;
    }
    
     /**
     * Devuelve todos los prestamos de la base de datos.
     *
     * @return una lista con todos los prestamos que encuentre en la base de datos,
     * "select u from PrestamoEntity u" es como un "select * from PrestamoEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<PrestamoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los prestamos");
        Query q = em.createQuery("select u from PrestamosEntity u");
        return q.getResultList();
    }
    
    /**
     * Busca si hay algun prestamo con el id que se envía por parámetro
     *
     * @param prestamoID: id correspondiente a al prestamo buscado.
     * @return un prestamo.
     */
    public PrestamoEntity find(Long prestamoID) {
        LOGGER.log(Level.INFO, "Consultando prestamo con id={0}", prestamoID);
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from PrestamoEntity where id=id;" - "SELECT * FROM table_name WHERE condition;" en SQL.
         */
        return em.find(PrestamoEntity.class, prestamoID);
    }
    
     /**
     * Actualiza un prestamo.
     *
     * @param prestamoEntity: el prestamo que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una editorial con los cambios aplicados.
     */
    public PrestamoEntity update( PrestamoEntity prestamoEntity) {
        LOGGER.log(Level.INFO, "Actualizando prestamo con id = {0}", prestamoEntity.getId());
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        la editorial con los cambios, esto es similar a 
        "UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        LOGGER.log(Level.INFO, "Saliendo de actualizar el prestamo con id = {0}", prestamoEntity.getId());
        return em.merge(prestamoEntity);
    }
    
     /**
     *
     * Borra un prestamo de la base de datos recibiendo como argumento el id
     * del prestamo
     *
     * @param pretamoId: id correspondiente al prestamo a borrar.
     */
    public void delete(Long pretamoId) {
        LOGGER.log(Level.INFO, "Borrando prestamo con id = {0}", pretamoId);
        PrestamoEntity entity = em.find(PrestamoEntity.class, pretamoId);
        /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from PrestamoEntity where id=id;" - "DELETE FROM table_name WHERE condition;" en SQL.*/
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar el prestamo con id = {0}", pretamoId);
    }
}
