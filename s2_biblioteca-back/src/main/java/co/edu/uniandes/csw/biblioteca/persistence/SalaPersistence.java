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
        LOGGER.log(Level.INFO, "Consultando todas las salas");
        Query q = em.createQuery("Select from SalaEntity");
        return  q.getResultList();
        
    }
    public SalaEntity find (Long salaID)
    {
        LOGGER.log(Level.INFO, "Consultando sala con id = {0}",salaID );
        return em.find(SalaEntity.class, salaID);
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
}
