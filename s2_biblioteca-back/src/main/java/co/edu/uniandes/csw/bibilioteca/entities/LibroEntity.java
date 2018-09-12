/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bibilioteca.entities;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.eclipse.persistence.jpa.config.Cascade;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Daniel Montoya
 */
@Entity
public class LibroEntity extends BaseEntity implements Serializable{
    
     //Nombre del libro
    private String nombre; 
    
    //Codigo id del libro
    private String isbn; 
    
    //Autor del libro
    private String autor;
    
    //Editorial del libro
    private String editorial;
    
    //Edicion del libro
    private String edicion;
    
    //Idioma del libro
    private String idioma;
    
    //Unidades que quedan disponibles del libro 
    private Integer unidadesDisponibles;
    
    //Numero de usuarios en lista de Espera
    private Integer usuariosListaEspera;
    
    //Calificacion promedio de los review
    private Integer calificacionPromedio;
    
    @PodamExclude
    @OneToMany(mappedBy = "libro", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private ArrayList<ComentarioEntity> comentarios = new ArrayList<>();

    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private BibliotecaEntity biblioteca;
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(int unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }

    public Integer getUsuariosListaEspera() {
        return usuariosListaEspera;
    }

    public void setUsuariosListaEspera(Integer usuariosListaEspera) {
        this.usuariosListaEspera = usuariosListaEspera;
    }

    public Integer getCalificacionPromedio() {
        return calificacionPromedio;
    }

    public void setCalificacionPromedio(Integer calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }

    public ArrayList<ComentarioEntity> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }
    
    
    
    
    
}
