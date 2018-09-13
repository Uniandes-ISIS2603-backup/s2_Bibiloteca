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
import javax.persistence.ManyToOne;

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
    private Date fechaReserva;

    /**
     * Una reserva solo tiene un usuario
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    private UsuarioEntity usuario;
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
