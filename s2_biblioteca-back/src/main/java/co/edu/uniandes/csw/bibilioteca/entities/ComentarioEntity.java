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
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Daniel Montoya
 */
@Entity
public class ComentarioEntity extends BaseEntity implements Serializable {
    
    private double numeroEstrellas;
    private String texto; 
    private String nombreUsuario;
    private Date fecha;
    
    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private LibroEntity libro;

    public double getNumeroEstrellas() {
        return numeroEstrellas;
    }

    public void setNumeroEstrellas(double numeroEstrellas) {
        this.numeroEstrellas = numeroEstrellas;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public LibroEntity getLibro() {
        return libro;
    }

    public void setLibro(LibroEntity libro) {
        this.libro = libro;
    }
    
    
}
