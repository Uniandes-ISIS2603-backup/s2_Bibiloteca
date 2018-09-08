/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.persistence;

import co.edu.uniandes.csw.bibilioteca.entities.VideoDigitalEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JuanBautista
 */

    @Stateless
public class VideoDigitalPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(VideoDigitalPersistence.class.getName());
    
    @PersistenceContext(unitName = "dnsDB")
    protected EntityManager eman;
    
    
    public VideoDigitalEntity create(VideoDigitalEntity videodigital)
    {
        LOGGER.log(Level.INFO,"Creando video digital nuevo");
        eman.persist(videodigital);
        LOGGER.log(Level.INFO,"Video digital creado");
        return videodigital;
    }
    
}
