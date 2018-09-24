/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.persistence;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.csw.bibilioteca.entities.SalaEntity;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Juan Nicolás García
 */
@Stateless
public class SalaPersistence {
    
        private static final Logger LOGGER = Logger.getLogger(SalaPersistence.class.getName());
        
    @PersistenceContext(unitName = "DnsPU")
    protected EntityManager em;
    public SalaEntity create(SalaEntity sala)
    {
        LOGGER.log(Level.INFO, "Creando sala nueva");
        em.persist(sala);
        LOGGER.log(Level.INFO, "Sala creada");
        return sala;
    }
    public List<SalaEntity> findAll()
    {
        LOGGER.log(Level.INFO,"Consultando todos las salas");
        Query q = em.createQuery("select u from SalaEntity u");
        return q.getResultList();
    }
      public SalaEntity find (Long bibliotecaId,Long salaId)
    {
        LOGGER.log(Level.INFO,"Consultando sala con id={0} de la biblioteca con id = "+bibliotecaId,salaId);
        TypedQuery q = em.createQuery("select p from SalaEntity p where (p.biblioteca.id = :bibliotecaId) and (p.id = :salaId)", SalaEntity.class);
        q.setParameter("bibliotecaId", bibliotecaId);
        q.setParameter("salaId", salaId);
        List<SalaEntity> resultado = q.getResultList();
        SalaEntity sala = null;
        if(resultado == null)
        {
            sala = null;
        }
        else if(resultado.isEmpty())
        {
            sala = null;
        }
        else
        {
            sala = resultado.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar la sala con id = {0} de la biblioteca con id =" + bibliotecaId  , salaId);
        return sala;
    }
    public SalaEntity update(SalaEntity salaEntity)
    {
        LOGGER.log(Level.INFO, "Actualizando sala con id = {0}",salaEntity.getId() );
        return em.merge(salaEntity);
    }
     public void delete(Long salaId)
    {
        LOGGER.log(Level.INFO,"Borrando sala con id={0}",salaId);
        SalaEntity sala = em.find(SalaEntity.class, salaId);
        em.remove(sala);
    }
     public SalaEntity findUbicacion(String ubicacion)
    {
        LOGGER.log(Level.INFO,"Consultando salas con la ubicacion",ubicacion);
        
        TypedQuery query = em.createQuery("Select e From SalaEntity e where e.ubicacion = :ubicacion", SalaEntity.class);
        
        query = query.setParameter("ubicacion", ubicacion);
        
        List<SalaEntity> salasConUbicacion = query.getResultList();
        
        SalaEntity result;
        
        if(salasConUbicacion == null)
        {
            result = null;
        }
        else if(salasConUbicacion.isEmpty())
        {
            result = null;
        }
        else
        {
            result = salasConUbicacion.get(0);
        }
        LOGGER.log(Level.INFO,"Saliendo de consultar salas por ubicacion", ubicacion);
        return result;
    }
}
