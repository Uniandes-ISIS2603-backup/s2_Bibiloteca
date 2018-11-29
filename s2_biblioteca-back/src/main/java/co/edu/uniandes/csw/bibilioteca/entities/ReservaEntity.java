/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bibilioteca.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.ManyToOne;
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
     * id del recurso reservado
     */
    private Long idRecursoReservado;

    /**
     * tipo del recurso resservado
     */
    private String tipoRecurso;

    /**
     * estado de la reserva
     */
    private boolean estado;

    /**
     * Una reserva solo tiene un usuario
     */
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;

    /**
     * Relacion con el libro
     */
    @PodamExclude
    @ManyToOne
    private LibroEntity libro;

    /**
     * Relacion con el video
     */
    @PodamExclude
    @ManyToOne
    private VideoEntity video;

    /**
     * Relacion con la Sala
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

    /**
     * obtiene el id del recurso reservado
     *
     * @return
     */
    public Long getIdRecursoReservado() {
        return idRecursoReservado;
    }

    /**
     * asigna el id del recurso asociado a la reserva
     *
     * @param getIdRecursoReservado
     */
    public void setIdRecursoReservado(Long getIdRecursoReservado) {
        this.idRecursoReservado = getIdRecursoReservado;
    }

    /**
     * @return tipoRecurso
     */
    public String getTipoRecurso() {
        return tipoRecurso;
    }

    /**
     * asigna el tipo del recurso que se esta reservando
     *
     * @param tipoRecurso el tipoRecurso a asignar este debe corresponde a uno
     * de los identificadores
     */
    public void setTipoRecurso(String tipoRecurso) {

        this.tipoRecurso = tipoRecurso;

    }

    /**
     * obtiene el estado actual de una reserva
     *
     * @return
     */
    public boolean getEstado() {
        return estado;
    }

    /**
     * asigna el estado de una reserva
     *
     * @param estado
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * obtiene el usuario de una reserva
     *
     * @return usuario
     */
    public UsuarioEntity getUsuario() {
        return usuario;
    }

    /**
     * asigna el usuario a una reserva
     *
     * @param usuario
     */
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    /**
     * obtiene el libro asociado a una reserva
     *
     * @return null si no hay ningun libro asociado a la reserva & libro en caso
     * de haber uno
     */
    public LibroEntity getLibro() {
        return libro;
    }

    /**
     * asigna un libro a una reserva
     *
     * @param libro
     */
    public void setLibro(LibroEntity libro) {
        this.libro = libro;
    }

    /**
     * obtiene el video asociado a una reserva
     *
     * @return null si no hay ningun video asociado a la reserva & video en caso
     * de haber uno
     */
    public VideoEntity getVideo() {
        return video;
    }

    /**
     * asigna un video a la reserva
     *
     * @param video
     */
    public void setVideo(VideoEntity video) {
        this.video = video;
    }

    /**
     * obtiene la sala asociada a una reserva
     *
     * @return null si no hay ningun sala asociada a la reserva & sala en caso
     * de haber una
     */
    public SalaEntity getSala() {
        return sala;
    }

    /**
     * asigna la sala asociada a una reserva
     *
     * @param sala
     */
    public void setSala(SalaEntity sala) {
        this.sala = sala;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    //El equals de la reserva
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReservaEntity other = (ReservaEntity) obj;
        if (this.estado != other.estado) {
            return false;
        }
        if (!Objects.equals(this.tipoRecurso, other.tipoRecurso)) {
            return false;
        }
        if (!Objects.equals(this.fechaReserva, other.fechaReserva)) {
            return false;
        }
        if (!Objects.equals(this.idRecursoReservado, other.idRecursoReservado)) {
            return false;
        }
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.libro, other.libro)) {
            return false;
        }
        if (!Objects.equals(this.video, other.video)) {
            return false;
        }
        if (!Objects.equals(this.sala, other.sala)) {
            return false;
        }
        return true;
    }

}
