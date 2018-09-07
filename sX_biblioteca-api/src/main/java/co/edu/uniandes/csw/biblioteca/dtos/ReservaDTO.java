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
 *      "id": 000001,
 *      "fechaReserva": "DD/MM/YYYY"
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
    
    //__________________________________________________________________________
    //Constructores
    //__________________________________________________________________________

    /**
     * constructor A
     */
    public ReservaDTO() {
    }
    
    /**
     * Constructor B
     * @param id
     * @param fechaReserva 
     */
    public ReservaDTO(long id, Date fechaReserva) {
        this.id = id;
        this.fechaReserva = fechaReserva;
    }
    
     /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param reservaEntity: Es la entidad que se va a convertir a DTO
     */
    public ReservaDTO(ReservaEntity reservaEntity) {
        if (reservaEntity != null) 
        {
            this.id = reservaEntity.getId();
            this.fechaReserva = reservaEntity.getFechaReserva();
        }
    }
    
    
    //__________________________________________________________________________
    //Metodos
    //__________________________________________________________________________

    /**
     * devuelve el id de una reserva
     * @return  id
     */
    public long getId() {
        return id;
    }

    /**
     * modifica el id de una reserva
     * @param id 
     */
    public void setId(long id) {
        this.id = id;
    }

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
    
     /**
     * Convertir el DTO Reserva a Entity
     * @return Un Entity con los valores del DTO
     */
    public ReservaEntity toEntity() 
    {
        ReservaEntity reservaEntity = new ReservaEntity();
        reservaEntity.setId(this.id);
        reservaEntity.setFechaReserva(this.fechaReserva);
        return reservaEntity;
    }
}
