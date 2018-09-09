/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bibilioteca.entities;

import java.io.Serializable;

/**
 *
 * @author Juan Nicolás García
 */
public class SalaEntity extends BaseEntity implements Serializable{
    private String ubicacion;
    private Boolean disponibilidad;
    private Integer capacidad;
    
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
    public void setUbicacion(String ubicacion)
    {
        this.ubicacion = ubicacion;
    }
    public void setDisponibilidad(Boolean disponibilidad)
    {
        this.disponibilidad = disponibilidad;
    }
    public void setCapacidad(Integer capacidad)
    {
        this.capacidad = capacidad;
    }
    
}
