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
}
