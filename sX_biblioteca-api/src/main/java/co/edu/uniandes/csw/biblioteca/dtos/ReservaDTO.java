/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.dtos;

import co.edu.uniandes.csw.bibilioteca.entities.ReservaEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *OBJETO DE TRANSFERENCIA DE UNA RESERVA
 * @author Daniel Preciado
 * * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "name": string
 *   }
 * </pre> Por ejemplo un servicio se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 000001
 *      "fechaReserva": "DD/MM/YYYY"
 *      "idRecursoReservado":12354564
 *  
 *   }
 *
 * </pre>
 */
public class ReservaDTO implements Serializable {

    
    //__________________________________________________________________________
    //Atributos
    //__________________________________________________________________________
    
    /**
     * identificador de la reserva
     */
    private long id;
    
    /**
     * fecha de una reserva
     */
    private Date fechaReserva;
    
    /**
     * identificador del recurso que se quiere reservar
     */
    private Long idRecursoReservado;
    
    /**
     * tipo del recurso reservado
     */
    private String tipoRecurso;
    
    /**
     * estado de una reserva
     * hace referencia a la disponibilidad del recurso reservado
     */
    private boolean  estado;
    
    /**
     * usuario al que esta relacionada una reserva
     * dado que de reserva a usuario hay cardinalidad 1
     */
    private UsuarioDTO usuario;
    
    //__________________________________________________________________________
    //Constructores
    //__________________________________________________________________________

    /**
     * constructor A
     */
    public ReservaDTO() 
    {
    }
    
    
     /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param reservaEntity: Es la entidad que se va a convertir a DTO
     */
    public ReservaDTO(ReservaEntity reservaEntity) 
    {
        if (reservaEntity != null) 
        {
            this.id = reservaEntity.getId();
            this.fechaReserva = reservaEntity.getFechaReserva();
            this.idRecursoReservado = reservaEntity.getIdRecursoReservado();
            this.tipoRecurso = reservaEntity.getTipoRecurso();
            this.estado = reservaEntity.getEstado();
            if (reservaEntity.getUsuario() != null) 
            {
                this.usuario = new UsuarioDTO(reservaEntity.getUsuario());
            } else
            {
                this.usuario = null;
            }
        }
    }
    
    
    
    //__________________________________________________________________________
    //Metodos
    //__________________________________________________________________________

    /**
     * devuelve el id de una reserva
     * @return  id
     */
    public Long getId() 
    {
        return id;
    }

    /**
     * modifica el id de una reserva
     * @param id 
     */
    public void setId(Long id) 
    {
        this.id = id;
    }

    /**
     * obtiene la fecha de una reserva
     * @return 
     */
    public Date getFechaReserva()
    {
        return fechaReserva;
    }

    /**
     * configura la fecha de una reserva
     * @param fechaReserva 
     */
    public void setFechaReserva(Date fechaReserva)
    {
        this.fechaReserva = fechaReserva;
    }

    /**
     * obtiene el id del recurso que se esta reservando
     * @return 
     */
    public Long getIdRecursoReservado() 
    {
        return idRecursoReservado;
    }

    /**
     * asigna el id del recurso que se esta reservando
     * @param idRecursoReservado 
     */
    public void setIdRecursoReservado(Long idRecursoReservado) 
    {
        this.idRecursoReservado = idRecursoReservado;
    }
    
     /**
     * obtiene el tipo del recurso que se esta reservando 
     * @return el tipoRecurso
     */
    public String getTipoRecurso() 
    {
        return tipoRecurso;
    }

    /**
     * asigna el tipo del recurso que se esta reservando
     * @param tipoRecurso el tipoRecurso a asignar
     * este debe corresponde a uno de los identificadores
     */
    public void setTipoRecurso(String tipoRecurso) 
    {
        if(tipoRecurso.equals("LIBRO") || tipoRecurso.equals("VIDEO")|| tipoRecurso.equals("SALA")|| tipoRecurso.equals("VIDEODIGITAL")|| tipoRecurso.equals("LIBRODIGITAL"))
        {
            this.tipoRecurso = tipoRecurso;
        }
        else
        {
             this.tipoRecurso = "";
        }
        
    }
    
    /**
     * obiene el estado actual de una reserva
     * @return  true = disponible & false != disponible
     */
    public boolean getEstado() 
    {
        return estado;
    }

    /**
     * asigna el estado de una reserva
     * @param estado 
     */
    public void setEstado(boolean estado) 
    {
        this.estado = estado;
    }
    
    /**
     * obtiene el usuario asociado a una re
     * @return 
     */
    public UsuarioDTO getUsuario() 
    {
        return usuario;
    }
    
    /**
     * asigna un usuario que solicita una reserva
     * @param usuario 
     */
    public void setUsuario(UsuarioDTO usuario) 
    {
        this.usuario = usuario;
    }
    
     /**
     * Convertir el DTO Reserva a Entity
     * @return Un Entity con los valores del DTO
     */
    public ReservaEntity toEntity() 
    {
        ReservaEntity reservaEntity = new ReservaEntity();
        reservaEntity.setId(this.id);
        reservaEntity.setFechaReserva(this.fechaReserva);
        reservaEntity.setEstado(this.estado);
        reservaEntity.setIdRecursoReservado(this.idRecursoReservado);
        reservaEntity.setTipoRecurso(this.tipoRecurso);
        if(this.usuario != null)
        {
            reservaEntity.setUsuario(this.usuario.toEntity());
        }
        else
        {
            reservaEntity.setUsuario(null);
        }
        return reservaEntity;
    }
}
