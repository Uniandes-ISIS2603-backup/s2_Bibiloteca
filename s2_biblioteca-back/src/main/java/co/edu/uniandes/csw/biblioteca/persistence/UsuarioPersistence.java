/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.persistence;

import co.edu.uniandes.csw.bibilioteca.entities.UsuarioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Juan Nicolás García
 */
@Stateless
public class UsuarioPersistence {
    private static final Logger LOGGER = Logger.getLogger(UsuarioPersistence.class.getName());
    @PersistenceContext(unitName = "DnsPU")
    protected EntityManager em;
    
    public UsuarioEntity create(UsuarioEntity usuario)
    {
        LOGGER.log(Level.INFO, "Creando usuario nuevo");
        em.persist(usuario);
        LOGGER.log(Level.INFO, "Usuario creado");
        return usuario;
    }
    public List<UsuarioEntity> findAll()
    {
        LOGGER.log(Level.INFO, "Consultando todos los usuarios");
        Query q = em.createQuery("Select from UsuarioEntity");
        return  q.getResultList();
        
    }
    public UsuarioEntity find (Long usuarioID)
    {
        LOGGER.log(Level.INFO, "Consultando usuario con id = {0}",usuarioID );
        return em.find(UsuarioEntity.class, usuarioID);
    }
    
}
