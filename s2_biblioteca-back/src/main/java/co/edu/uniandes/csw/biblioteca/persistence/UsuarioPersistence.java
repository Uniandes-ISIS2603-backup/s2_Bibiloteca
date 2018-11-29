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
import javax.persistence.TypedQuery;

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
        TypedQuery q = em.createQuery("Select u from UsuarioEntity u",UsuarioEntity.class);
        return  q.getResultList();
        
    }
    public UsuarioEntity find (Long usuarioID)
    {
        LOGGER.log(Level.INFO, "Consultando usuario con id = {0}",usuarioID );
        return em.find(UsuarioEntity.class, usuarioID);
    }
    public UsuarioEntity update(UsuarioEntity usuarioEntity)
    {
        LOGGER.log(Level.INFO, "Actualizando usuario con id = {0}",usuarioEntity.getId() );
        return em.merge(usuarioEntity);
    }
     public void delete(Long usuarioId)
    {
        LOGGER.log(Level.INFO,"Borrando usuario con id={0}",usuarioId);
        UsuarioEntity usuario = em.find(UsuarioEntity.class, usuarioId);
        em.remove(usuario);
    }
     public UsuarioEntity findNombre(String nombre)
    {
        LOGGER.log(Level.INFO,"Consultando usuarios con el nombre = {0}",nombre);
        
        TypedQuery query = em.createQuery("Select e From UsuarioEntity e where e.nombre = :nombre", UsuarioEntity.class);
        
        query = query.setParameter("nombre", nombre);
        
        List<UsuarioEntity> usuariosConNombre = query.getResultList();
        
        UsuarioEntity result;
        
        if(usuariosConNombre == null)
        {
            result = null;
        }
        else if(usuariosConNombre.isEmpty())
        {
            result = null;
        }
        else
        {
            result = usuariosConNombre.get(0);
        }
        LOGGER.log(Level.INFO,"Saliendo de consultar usuarios por nombre = {0} ", nombre);
        return result;
    }
}
