/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.persistence;

import co.edu.uniandes.csw.bibilioteca.entities.BibliotecaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para Biblioteca. Se conecta a través del Entity
 * Manager de javax.persistance con la base de datos SQL.
 * @author David Eduardo Saavedra Hernandez
 */
@Stateless
public class BibliotecaPersistence {
   
    private static final Logger LOGGER = Logger.getLogger(BibliotecaPersistence.class.getName());

    @PersistenceContext(unitName = "DnsPU")
    protected EntityManager em;
    
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param bibliotecaEntity  objeto libro que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public BibliotecaEntity create(BibliotecaEntity bibliotecaEntity) {
        LOGGER.log(Level.INFO, "Creando una nueva Biblioteca");
        em.persist(bibliotecaEntity);
        LOGGER.log(Level.INFO, "Biblioteca Creada");
        return bibliotecaEntity;
    }
    
     /**
     * Devuelve todos las bibliotecas de la base de datos.
     *
     * @return una lista con todos las bibliotecas que encuentre en la base de datos,
     * "select u from BookEntity u" es como un "select * from BookEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<BibliotecaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos las bibliotecas");
        Query q = em.createQuery("select u from BibliotecaEntity u");
        return q.getResultList();
    }
    
     /**
     * Busca si hay alguna Biblioteca con el id que se envía por parámetro
     *
     * @param bibliotecaId: id correspondiente a la biblioteca buscada.
     * @return una biblioteca
     */
    public BibliotecaEntity find(Long bibliotecaId) 
    {
        LOGGER.log(Level.INFO, "Consultando la biblioteca con id={0}", bibliotecaId);
        return em.find(BibliotecaEntity.class, bibliotecaId);
    }
    
     /**
     * Actualiza una biblioteca.
     *
     * @param bibliotecaEntity: la bilioteca que viene con los nuevos cambios. Por ejemplo
     * el nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return una biblioteca con los cambios aplicados.
     */
    public BibliotecaEntity update(BibliotecaEntity bibliotecaEntity) {
        LOGGER.log(Level.INFO, "Actualizando la bilioteca con id={0}", bibliotecaEntity.getId());
        return em.merge(bibliotecaEntity);
    }

    /**
     *
     * Borra una biblioteca de la base de datos recibiendo como argumento el id de la
     * biblioteca
     *
     * @param bibliotecaId: id correspondiente a la biblioteca a borrar.
     */
    public void delete(Long bibliotecaId) 
    {
        LOGGER.log(Level.INFO, "Borrando la biblioteca con id={0}", bibliotecaId);
        BibliotecaEntity biliotecaEntity = em.find(BibliotecaEntity.class, bibliotecaId);
        em.remove(biliotecaEntity);
    }
    
    /**
     * Busca si hay alguna biblioteca con el nombre que se envía por parámetro
     *
     * @param name: Nombre de la biblioteca que se está buscando
     * @return null si no existe ninguna biblioteca con el nombre del parámetro.
     * Si existe alguna devuelve la primera.
     */
    public BibliotecaEntity findByName(String name) 
    {
        
        LOGGER.log(Level.INFO, "Consultando editorial por nombre ", name);
        
        // Se crea un query para buscar bibliotecas con el nombre que recibe el método como argumento. ":name" es un placeholder que debe ser remplazado
        
        TypedQuery query = em.createQuery("Select e From BibliotecaEntity e where e.name = :name", BibliotecaEntity.class);
        // Se remplaza el placeholder ":name" con el valor del argumento 
        query = query.setParameter("name", name);
        // Se invoca el query se obtiene la lista resultado
        List<BibliotecaEntity> sameName = query.getResultList();
        BibliotecaEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar biblioteca por nombre ", name);
        return result;
    }
}
