/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.persistence;

import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Daniel Montoya
 */
@Stateless
public class LibroPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(LibroPersistence.class.getName());
    
    @PersistenceContext(unitName = "BibliotecaPU")
    protected EntityManager em;
    
    
    public LibroEntity create(LibroEntity libro)
    {
        LOGGER.log(Level.INFO,"Creando libro nuevo");
        em.persist(libro);
        LOGGER.log(Level.INFO,"Libro creado");
        return libro;
    }
    
    public List<LibroEntity> findAll()
    {
        LOGGER.log(Level.INFO,"Consultando todos los libros");
        Query q = em.createQuery("select u from LibroEntity u");
        return q.getResultList();
    }
    
    public LibroEntity find (Long libroId)
    {
        LOGGER.log(Level.INFO,"Consultando libro con id={0}",libroId);
        return em.find(LibroEntity.class, libroId);
    }
    
    public LibroEntity update(LibroEntity libroEntity)
    {
        LOGGER.log(Level.INFO,"Actualiazando libro con id={0}",libroEntity.getId());
        return em.merge(libroEntity);
    }
    
    public void delete(Long libroId)
    {
        LOGGER.log(Level.INFO,"Borrando libro con id={0}",libroId);
        LibroEntity libro = em.find(LibroEntity.class, libroId);
        em.remove(libro);
    }
    
    public LibroEntity findISBN(String isbn)
    {
        LOGGER.log(Level.INFO,"Consultando libros con el ISBN",isbn);
        
        TypedQuery query = em.createQuery("Select e From LibroEntity e where e.isbn = :isbn", LibroEntity.class);
        
        query = query.setParameter("isbn", isbn);
        
        List<LibroEntity> librosConMismoISBN = query.getResultList();
        
        LibroEntity result;
        
        if(librosConMismoISBN == null)
        {
            result = null;
        }
        else if(librosConMismoISBN.isEmpty())
        {
            result = null;
        }
        else
        {
            result = librosConMismoISBN.get(0);
        }
        LOGGER.log(Level.INFO,"Saliendo de consultar libros por isbn ", isbn);
        return result;
    }
}
