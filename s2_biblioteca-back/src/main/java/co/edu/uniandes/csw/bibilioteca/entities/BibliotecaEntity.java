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
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class BibliotecaEntity extends BaseEntity implements Serializable {
   
    
    //---------------------------------------------------------
    // ATRIBUTOS
    //---------------------------------------------------------
    
    /**
     * Se refiere a la zona donde se encuentra la biblioteca
     */
    private  String zona;
    
    /**
     * nombre de la biblioteca 
     */
    private String nombre;
    
    /**
     * direccion de la biblioteca
     */
    private String direccion;
    
    /**
     * hora en que abre la biblioteca
     */
    private String horaApertura;
    
    /**
     * hora en que cierra la biblioteca
     */
    private String horaCierre;
   
    /**
     * representa los libros que tiene la biblioteca
     */
    @PodamExclude
    @OneToMany(mappedBy = "biblioteca", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<LibroEntity> libros = new ArrayList<LibroEntity>();
    
    /**
     * representa los videos que tiene la biblioteca
     */
    @PodamExclude
    @OneToMany(mappedBy = "biblioteca", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<VideoEntity> videos = new ArrayList<VideoEntity>();
    
    /**
     * representa las salas que tiene la biblioteca
     */
    @PodamExclude
    @OneToMany(mappedBy = "biblioteca", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<SalaEntity> salas = new ArrayList<SalaEntity>();

  //----------------------------------------------------------
  // METODOS
  //----------------------------------------------------------
    
   
 /**
     * Devuelve la zona de la Bblioteca
     *
     * @return la zona 
     */
    public String getZona() {
        return zona;
    }

    /**
     * Modifica la zona de la biblioteca
     *
     * @param pZona la nueva zona
     */
    public void setZona(String pZona) {
        this.zona = pZona;
    } 
    
    /**
     * Devuelve el nombre de la Bblioteca
     *
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre de la biblioteca
     *
     * @param pNombre el nombre para colocar
     */
    public void setNombre(String pNombre) {
        this.nombre = pNombre;
    }   
    
    /**
     * Devuelve la direccion de la Bblioteca
     *
     * @return la direccion 
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Modifica la direccion de la biblioteca
     *
     * @param pDireccion La nueva direccion
     */
    public void setDireccion(String pDireccion) {
        this.direccion = pDireccion;
    } 
    
    /**
     * Devuelve la hora de apertura de la Bblioteca
     *
     * @return la Hora de apertura 
     */
    public String getHoraApertura() {
        return horaApertura;
    }

    /**
     * Modifica la hora de apertura de la biblioteca
     *
     * @param pHoraApertura La nueva direccion
     */
    public void setHoraApertura (String pHoraApertura) 
    {
        this.horaApertura = pHoraApertura;
    } 
    
     /**
     * Devuelve la hora de cierre de la Bblioteca
     *
     * @return la Hora de cierre
     */
    public String getHoraCierre() {
        return horaCierre;
    }

    /**
     * Modifica la hora de cierre de la biblioteca
     *
     * @param pHoraCierre La nueva direccion
     */
    public void setHoraCierre (String pHoraCierre) 
    {
        this.horaCierre = pHoraCierre;
    } 

     /**
     * Devuelve los videos de la bilioteca.
     *
     * @return Lista de entidades de tipo video
     */
    public List<VideoEntity> getVideos() {
        return videos;
    }

    /**
     * Modifica los videos de la biblioteca.
     *
     * @param pVideos Los nuevos videos.
     */
    public void setVideos(List<VideoEntity> pVideos) {
        this.videos = pVideos;
    }
    
    /**
     * Devuelve los libros de la bilioteca.
     *
     * @return Lista de entidades de tipo libros
     */
    public List<LibroEntity> getLibros() {
        return libros;
    }

    /**
     * Modifica los libros de la biblioteca.
     *
     * @param pLibros Los nuevos libros
     */
    public void setLibros(List<LibroEntity> pLibros) {
        this.libros = pLibros;
    }
    
    /**
     * Devuelve las salas de la bilioteca.
     *
     * @return Lista de entidades de tipo salas
     */
    public List<SalaEntity> getSalas() {
        return salas;
    }

    /**
     * Modifica las salas de la biblioteca.
     *
     * @param pSalas Los nuevos libros
     */
    public void setSalas(List<SalaEntity> pSalas) {
        this.salas = pSalas;
    }
    
}
