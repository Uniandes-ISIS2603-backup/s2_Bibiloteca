
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.persistence;

import co.edu.uniandes.csw.bibilioteca.entities.VideoDigitalEntity;
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
 * @author JuanBautista
 */

    @Stateless
    public class VideoDigitalPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(VideoDigitalPersistence.class.getName());
    
    @PersistenceContext(unitName = "DnsPU")
    protected EntityManager eman;
    
    
    public VideoDigitalEntity create(VideoDigitalEntity videodigital)
    {
        LOGGER.log(Level.INFO,"Creando video digital nuevo");
        eman.persist(videodigital);
        LOGGER.log(Level.INFO,"Video digital creado");
        return videodigital;
    }
    public List <VideoDigitalEntity> findAll()
    {
       LOGGER.log(Level.INFO,"Consultando todos los videos digitales");
       Query qy=eman.createQuery("select u from VideoDigitalEntity u"); 
       return qy.getResultList();
    }
    
    public VideoDigitalEntity find(Long videoDigitalId)
    {
       LOGGER.log(Level.INFO,"Consultando video digital con id={0}",videoDigitalId);
       return eman.find(VideoDigitalEntity.class,videoDigitalId);
    }
      public VideoDigitalEntity findNombre(String nombre)
    {
        LOGGER.log(Level.INFO,"Consultando videos digitales con el nombre",nombre);
        
        TypedQuery query = eman.createQuery("Select e From VideoDigitalEntity e where e.nombre = :nombre", VideoDigitalEntity.class);
        
        query = query.setParameter("nombre", nombre);
        
        List<VideoDigitalEntity> videosDigitalesConMismoNombre = query.getResultList();
        
        VideoDigitalEntity resultado;
        
        if(videosDigitalesConMismoNombre == null)
        {
            resultado = null;
        }
        else if(videosDigitalesConMismoNombre.isEmpty())
        {
            resultado = null;
        }
        else
        {
            resultado = videosDigitalesConMismoNombre.get(0);
        }
        LOGGER.log(Level.INFO,"Saliendo de consultar videos digitales por nombre ", nombre);
        return resultado;
    }
      public VideoDigitalEntity update(VideoDigitalEntity videoDigitalEntity)
    {
        LOGGER.log(Level.INFO,"Actualizando video digital con id={0}",videoDigitalEntity.getId());
        return eman.merge(videoDigitalEntity);
    }
    
    public void delete(Long videoDigitalId)
    {
        LOGGER.log(Level.INFO,"Borrando video digital con id={0}",videoDigitalId);
        VideoDigitalEntity videoDigital = eman.find(VideoDigitalEntity.class, videoDigitalId);
        eman.remove(videoDigital);
    }
    
}
