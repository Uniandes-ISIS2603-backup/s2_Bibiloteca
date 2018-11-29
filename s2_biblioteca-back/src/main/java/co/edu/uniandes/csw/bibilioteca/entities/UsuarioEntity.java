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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable{
    //Nombre del usuario
    private String nombre;
    //Email del usuario
    private String email;
    //Telefono del usuario
    private String telefono;
    //Multa del usuario 
    private Integer multa;
    
    @PodamExclude
    @OneToMany (mappedBy = "usuario" , cascade = CascadeType.PERSIST )
    private List<ReservaEntity> reservas; 
    
    @PodamExclude
    @OneToMany (mappedBy = "usuario" , cascade = CascadeType.PERSIST )
    private List<PrestamoEntity> prestamos;
    
    @PodamExclude
    @OneToMany(mappedBy = "usuario" , cascade = CascadeType.PERSIST )
    private List<ComentarioEntity> comentarios;
    
    @PodamExclude
    @ManyToMany(mappedBy = "usuarios")
    private List<VideoDigitalEntity> videoDigitalEntity = new ArrayList<>();

    /**
     * Relación con el usuario.
     */
    @PodamExclude
    @ManyToOne
    private BibliotecaEntity biblioteca;
    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }

    public List<PrestamoEntity> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<PrestamoEntity> prestamos) {
        this.prestamos = prestamos;
    }

    public List<ComentarioEntity> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }
    
    public String getNombre() {
        return nombre;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefono() {
        return telefono;
    }
    public Integer getMulta() {
        return multa;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
     public void setEmail(String email) {
        this.email = email;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setMulta(Integer multa)
    {
        this.multa = multa;
    }
    
     /**
     * Obtiene la colección de videos digitales.
     *
     * @return colección videos digitales.
     */
    public List<VideoDigitalEntity> getVideosDigitales() {
        return videoDigitalEntity;
    }

    /**
     * Establece el valor de la colección de videosDigitales.
     *
     * @param videosDigitales nuevo valor de la colección.
     */
    public void setVideosDigitales(List<VideoDigitalEntity> videosDigitales) {
        this.videoDigitalEntity = videosDigitales;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    //El equals de la clase usuario
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
        final UsuarioEntity other = (UsuarioEntity) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.telefono, other.telefono)) {
            return false;
        }
        if (!Objects.equals(this.multa, other.multa)) {
            return false;
        }
        if (!Objects.equals(this.reservas, other.reservas)) {
            return false;
        }
        if (!Objects.equals(this.prestamos, other.prestamos)) {
            return false;
        }
        if (!Objects.equals(this.comentarios, other.comentarios)) {
            return false;
        }
        if (!Objects.equals(this.videoDigitalEntity, other.videoDigitalEntity)) {
            return false;
        }
        if (!Objects.equals(this.biblioteca, other.biblioteca)) {
            return false;
        }
        return true;
    }
    
    
}
