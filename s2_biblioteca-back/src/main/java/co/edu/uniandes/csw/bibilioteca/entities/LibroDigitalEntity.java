/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bibilioteca.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 * Clase que representa una entidad
 * de la clase LibroDigital
 * @author Daniel Preciado
 */
@Entity
public class LibroDigitalEntity extends BaseEntity implements Serializable  {
    
    //__________________________________________________________________________
    //Atributos
    //__________________________________________________________________________
    
   /**
    * Nombre del libro
    */
    private String nombre; 
    
    /**
     * Codigo id del libro
     */
    private String isbn; 
    
    /**
     * Autor del libro
     */
    private String autor;
    
    /**
     * Editorial del libro
     */
    private String editorial;
    
    /**
     * Edicion del libro
     */
    private String edicion;
    
    /**
     * Idioma del libro
     */
    private String idioma;

    /**
     * Calificacion promedio de los review
     */
    private Integer calificacionPromedio;
    
    //__________________________________________________________________________
    //Metodos
    //__________________________________________________________________________
    
    /**
     * obtiene el nombre de un libro digital
     * @return nombre
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * asigna el nombre al librodigital
     * @param nombre 
     */
    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    /**
     * ontiene el isbn de un libro digital
     * @return isbn
     */
    public String getIsbn()
    {
        return isbn;
    }
    
    /**
     * asigna el isbn al libro digital
     * @param isbn 
     */
    public void setIsbn(String isbn) 
    {
        this.isbn = isbn;
    }

    /**
     * obtiene el nombre del autor del libro digital
     * @return autor
     */
    public String getAutor() 
    {
        return autor;
    }
   
    /**
     * asigna el autor del libro digital
     * @param autor 
     */
    public void setAutor(String autor) 
    {
        this.autor = autor;
    }
    
    /**
     * obtiene la editorial de ub libro digital
     * @return editorial
     */
    public String getEditorial() 
    {
        return editorial;
    }
    
    /**
     * asigna la editorial a un libro digital
     * @param editorial 
     */
    public void setEditorial(String editorial)
    {
        this.editorial = editorial;
    }
    
    /**
     * obtiene la edición de un libro digital
     * @return edicion
     */
    public String getEdicion() 
    {
        return edicion;
    }
    
    /**
     * asigna la edición ee un libro digital
     * @param edicion 
     */
    public void setEdicion(String edicion)
    {
        this.edicion = edicion;
    }
    
    /**
     * devuelve el idioma del libro digital
     * @return idioma
     */
    public String getIdioma() 
    {
        return idioma;
    }
    
    /**
     * asigna el idioma en el cual se encuentra el libro digital
     * @param idioma 
     */
    public void setIdioma(String idioma)
    {
        this.idioma = idioma;
    }
    
    /**
     * obtiene la calificación promedio de un libro digital
     * @return calificacionPromedio
     */
    public Integer getCalificacionPromedio()
    {
        return calificacionPromedio;
    }
    
    /**
     * asigna una calificacionPromedio ql libro digital
     * @param calificacionPromedio 
     */
    public void setCalificacionPromedio(Integer calificacionPromedio)
    {
        this.calificacionPromedio = calificacionPromedio;
    }
    
     
}
