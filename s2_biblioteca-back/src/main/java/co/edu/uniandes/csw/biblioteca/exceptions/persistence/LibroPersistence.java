/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.exceptions.persistence;

import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Daniel Montoya
 */
@Stateless
public class LibroPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(LibroPersistence.class.getName());
    
    @PersistenceContext(unitName = "BibliotecaPU");
    protected EntityManager em;
    
    
    public LibroEntity createLibro(LibroEntity libro)
    {
        LOGGER.log(Level.INFO,"Creando libro nuevo");
        em.persist(libro);
        LOGGER.log(Level.INFO,"Libro creado");
        return libro;
    }
}
