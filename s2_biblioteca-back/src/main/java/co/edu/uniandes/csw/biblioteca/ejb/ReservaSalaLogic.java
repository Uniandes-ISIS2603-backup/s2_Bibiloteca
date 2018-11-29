/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.ReservaEntity;
import co.edu.uniandes.csw.bibilioteca.entities.SalaEntity;
import co.edu.uniandes.csw.biblioteca.persistence.ReservaPersistence;
import co.edu.uniandes.csw.biblioteca.persistence.SalaPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de reserva y sala.
 * @author Daniel Preciado
 */
@Stateless
public class ReservaSalaLogic {
    
    //__________________________________________________________________________
    //Constantes
    //__________________________________________________________________________
    
    /**
     * constante para dejar huella y registro
     */

    //__________________________________________________________________________
    //Atributos
    //__________________________________________________________________________
   
     /**
      * persistencia de una reserva 
      */
     @Inject
     private ReservaPersistence  reservaPersistence ;
     
     /**
      * presistencia de una sala
      */
     @Inject
    private  SalaPersistence salaPersistence;
     
     
    
     
    //__________________________________________________________________________
    //Metodos
    //__________________________________________________________________________

    /**
     * Borrar un libro de una reserva. Este metodo se utiliza para borrar la
     * relacion de una sala y una reserva.
     * @param pSalaId la sala que se desea borrar de la reserva.
     */
     /**
    public void removeLibro(Long pSalaId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la reserva de la sala con id = {0}", pSalaId);
        SalaEntity salaEntity = salaPersistence.find(pSalaId);
        ReservaEntity reservaEntity = reservaPersistence.findByIdRecursoReservado(pSalaId, "LIBRO");
        reservaEntity.setLibro(null);
        salaEntity.getReservas().remove(reservaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la reserva de la sala con id = {0}", reservaEntity.getId());
    }
    */
}