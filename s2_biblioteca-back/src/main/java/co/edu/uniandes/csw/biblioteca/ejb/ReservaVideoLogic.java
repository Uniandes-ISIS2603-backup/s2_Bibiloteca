/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.ReservaEntity;
import co.edu.uniandes.csw.bibilioteca.entities.VideoEntity;
import co.edu.uniandes.csw.biblioteca.persistence.ReservaPersistence;
import co.edu.uniandes.csw.biblioteca.persistence.VideoPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de reserva y video.
 * @author Daniel Preciado
 */
@Stateless
public class ReservaVideoLogic {
    
    //__________________________________________________________________________
    //Constantes
    //__________________________________________________________________________
    
    /**
     * constante para dejar huella y registro
     */
     private static final Logger LOGGER = Logger.getLogger(ReservaLibroLogic.class.getName());

    //__________________________________________________________________________
    //Atributos
    //__________________________________________________________________________
   
     /**
      * persistencia de una reserva 
      */
     @Inject
     private ReservaPersistence  reservaPersistence ;
     
     /**
      * presistencia de un video
      */
     @Inject
    private  VideoPersistence videoPersistence;
     
    //__________________________________________________________________________
    //Metodos
    //__________________________________________________________________________

    /**
     * Borrar un libro de una reserva. Este metodo se utiliza para borrar la
     * relacion de un video y una reserva.
     * @param pVideoId El video que se desea borrar de la reserva.
     */
    public void removeVideo(Long pVideoId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la reserva del video con id = {0}", pVideoId);
        VideoEntity videoEntity = videoPersistence.find(pVideoId);
        ReservaEntity reservaEntity = reservaPersistence.findByIdRecursoReservado(pVideoId, "LIBRO");
        reservaEntity.setLibro(null);
        videoEntity.getReservas().remove(reservaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la reserva del libro con id = {0}", reservaEntity.getId());
    }
    
}