/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bibilioteca.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * Clase que representa una entidad de la clase Reserva
 *
 * @author Daniel Preciado
 */
@Entity
public class ReservaEntity extends BaseEntity implements Serializable { 

    //__________________________________________________________________________
    //Atributos
    //__________________________________________________________________________
    /**
     * fecha de una reserva
     */
   @Temporal(TemporalType.DATE)
    private Date fechaReserva;

    /**
     * Una reserva solo tiene un usuario
     */
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;
    
    /**
     * Relacion con el libro. 
     */
    @PodamExclude
    @ManyToOne
    private LibroEntity libro;
    
     /**
     * Relacion con el video. 
     */
    @PodamExclude
    @ManyToOne
    private VideoEntity video;
    
     /**
     * Relacion con el videoDigital 
     */
    @PodamExclude
    @ManyToOne
    private VideoDigitalEntity videoDigital;
        
     /**
     * Relacion con la Sala.
     */
    @PodamExclude
    @ManyToOne
    private SalaEntity sala;
    
    //__________________________________________________________________________
    //Metodos
    //__________________________________________________________________________

    /**
     * obtiene la fecha de una reserva
     *
     * @return
     */
    public Date getFechaReserva() {
        return fechaReserva;
    }

    /**
     * configura la fecha de una reserva
     *
     * @param fechaReserva
     */
    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

}
