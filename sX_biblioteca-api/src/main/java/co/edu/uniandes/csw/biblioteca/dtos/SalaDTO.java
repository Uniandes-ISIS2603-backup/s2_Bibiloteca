/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.adapters;

/**
 *
 * @author Juan Nicolás García
 */
public class SalaDTO {
    private String ubicacion;
    private Boolean disponibilidad;
    private Integer capacidad;
    
    public SalaDTO(String pUbicacion, Boolean pDisponibilidad, Integer pCapacidad)
    {
        ubicacion = pUbicacion;
        disponibilidad = pDisponibilidad;
        capacidad = pCapacidad;
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
