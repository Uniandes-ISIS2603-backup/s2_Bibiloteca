/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bibilioteca.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan Nicolás García
 */
@Entity
public class SalaEntity extends BaseEntity implements Serializable {

    private String ubicacion;
    private Boolean disponibilidad;
    private Integer capacidad;

    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private BibliotecaEntity biblioteca;
    
    
   @PodamExclude
    @OneToMany(mappedBy = "sala", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ReservaEntity> reservas = new ArrayList<>();

    public String getUbicacion() {
        return ubicacion;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setDisponibilidad(Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public BibliotecaEntity getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(BibliotecaEntity biblioteca) {
        this.biblioteca = biblioteca;
    }
    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    //El equals de la clase sala
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
        final SalaEntity other = (SalaEntity) obj;
        if (!Objects.equals(this.ubicacion, other.ubicacion)) {
            return false;
        }
        if (!Objects.equals(this.disponibilidad, other.disponibilidad)) {
            return false;
        }
        if (!Objects.equals(this.capacidad, other.capacidad)) {
            return false;
        }
        if (!Objects.equals(this.biblioteca, other.biblioteca)) {
            return false;
        }
        if (!Objects.equals(this.reservas, other.reservas)) {
            return false;
        }
        return true;
    }

    

}
