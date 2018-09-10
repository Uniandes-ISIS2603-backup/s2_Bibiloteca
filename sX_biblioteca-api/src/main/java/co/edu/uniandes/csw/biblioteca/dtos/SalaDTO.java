/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.dtos;

import co.edu.uniandes.csw.bibilioteca.entities.SalaEntity;
import java.io.Serializable;

/**
 *
 * @author Juan Nicolás García
 */
public class SalaDTO  implements Serializable{
    private Long id;
    private String ubicacion;
    private Boolean disponibilidad;
    private Integer capacidad;
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     * @param salaEntity: Es la entidad que se va a convertir a DTO
     */
    public SalaDTO(SalaEntity salaEntity) {
        this.capacidad = salaEntity.getCapacidad();
        this.disponibilidad = salaEntity.getDisponibilidad();
        this.ubicacion = salaEntity.getUbicacion();
        this.id = salaEntity.getId();
    }

    public SalaDTO()
    {
        
    }
    /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa el libro.
     */
 
    public SalaEntity toEntity() 
    {
       SalaEntity sala = new SalaEntity();
       sala.setId(this.id);
       sala.setCapacidad(this.capacidad);
       sala.setDisponibilidad(this.disponibilidad);
       sala.setUbicacion(this.ubicacion);
       
       return sala;
    }
    public String getUbicacion()
    {
        return ubicacion;
    }
    public Boolean getDisponibilidad()
    {
        return disponibilidad;
    }
    public Integer getCapacidad()
    {
        return capacidad;
    }
    public void setDisponibilidad(Boolean pDisponibilidad)
    {
        disponibilidad = pDisponibilidad;
    }
    public void setCapacidad(Integer pCapacidad)
    {
        capacidad = pCapacidad;
    }
}
