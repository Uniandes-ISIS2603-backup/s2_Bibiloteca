/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.dtos;

import co.edu.uniandes.csw.bibilioteca.entities.SalaEntity;

/**
 *
 * @author Juan Nicolás García
 */
public class SalaDTO {
    private String ubicacion;
    private Boolean disponibilidad;
    private Integer capacidad;
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param salaEntity: Es la entidad que se va a convertir a DTO
     */
    public SalaDTO(SalaEntity salaEntity) {
        
    }

    /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa el libro.
     */
 
    public SalaEntity toEntity() 
    {
       SalaEntity bookEntity = new SalaEntity();
       
       return bookEntity;
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
