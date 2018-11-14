/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bibilioteca.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    private ArrayList<ReservaEntity> reservas; 
    
    @PodamExclude
    @OneToMany (mappedBy = "usuario" , cascade = CascadeType.PERSIST )
    private ArrayList<PrestamoEntity> prestamos;
    
    @PodamExclude
    @OneToMany(mappedBy = "usuario" , cascade = CascadeType.PERSIST )
    private ArrayList<ComentarioEntity> comentarios;
    
    @PodamExclude
    @ManyToMany(mappedBy = "usuarios")
    private List<VideoDigitalEntity> videoDigitalEntity = new ArrayList<>();

    /**
     * Relación con el usuario.
     */
    @PodamExclude
    @ManyToOne
    private BibliotecaEntity biblioteca;
    public ArrayList<ReservaEntity> getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList<ReservaEntity> reservas) {
        this.reservas = reservas;
    }

    public ArrayList<PrestamoEntity> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(ArrayList<PrestamoEntity> prestamos) {
        this.prestamos = prestamos;
    }

    public ArrayList<ComentarioEntity> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<ComentarioEntity> comentarios) {
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
    
}
