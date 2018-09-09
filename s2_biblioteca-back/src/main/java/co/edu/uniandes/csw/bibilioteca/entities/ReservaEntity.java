/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bibilioteca.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;

/**
 * Clase que representa una entidad
 * de la clase Reserva
 * @author estudiante
 */
@Entity
public class ReservaEntity extends BaseEntity implements Serializable {
    
    //__________________________________________________________________________
    //Atributos
    //__________________________________________________________________________
    
    /**
     * fecha de una reserva
     */
    private Date fechaReserva;
    
    //__________________________________________________________________________
    //Metodos
    //__________________________________________________________________________


    /**
     * obtiene la fecha de una reserva
     * @return 
     */
    public Date getFechaReserva() {
        return fechaReserva;
    }

    /**
     * configura la fecha de una reserva
     * @param fechaReserva 
     */
    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
    
    
}
