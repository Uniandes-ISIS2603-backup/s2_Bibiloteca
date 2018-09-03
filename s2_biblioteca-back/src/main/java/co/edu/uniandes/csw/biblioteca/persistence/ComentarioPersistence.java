/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.persistence;

import co.edu.uniandes.csw.bibilioteca.entities.ComentarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Daniel Montoya
 */
public class ComentarioPersistence {
        
    private static final Logger LOGGER = Logger.getLogger(LibroPersistence.class.getName());
    
    @PersistenceContext(unitName = "DnsPU")
    protected EntityManager em;
    
    
    public ComentarioEntity create(ComentarioEntity comentario)
    {
        LOGGER.log(Level.INFO,"Creando comentario nuevo");
        em.persist(comentario);
        LOGGER.log(Level.INFO,"comentario creado");
        return comentario;
    }
    
    public ComentarioEntity find (Long libroId,Long comentarioId)
    {
        LOGGER.log(Level.INFO,"Consultando comentario con id={0} del libro con id = "+libroId,comentarioId);
        TypedQuery q = em.createQuery("select p from ComentarioEntity p where (p.libro.id = :libroId) and (p.id = :comentarioId)", ComentarioEntity.class);
        q.setParameter("libroId", libroId);
        q.setParameter("comentarioId", comentarioId);
        List<ComentarioEntity> resultado = q.getResultList();
        ComentarioEntity comentario = null;
        if(resultado == null)
        {
            comentario = null;
        }
        else if(resultado.isEmpty())
        {
            comentario = null;
        }
        else
        {
            comentario = resultado.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar el comentario con id = {0} del libro con id =" + libroId  , comentarioId);
        return comentario;
    }
    
    public ComentarioEntity update(ComentarioEntity comentario)
    {
        LOGGER.log(Level.INFO,"Actualiazando comentario con id={0}",comentario.getId());
        return em.merge(comentario);
    }
    
    public void delete(Long comentarioId)
    {
        LOGGER.log(Level.INFO,"Borrando libro con id={0}",comentarioId);
        ComentarioEntity comentario  = em.find(ComentarioEntity.class, comentarioId);
        em.remove(comentario);
        LOGGER.log(Level.INFO,"Elimanando comentario con id={0}",comentarioId);
    }
    
}
