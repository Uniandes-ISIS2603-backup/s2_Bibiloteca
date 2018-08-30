/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.dtos;

import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import java.io.Serializable;
import java.util.ArrayList;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Daniel Montoya
 */
public class LibroDTO implements Serializable{
    
    private Long id;

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
    private int unidadesDisponibles;
    
    //Numero de usuarios en lista de Espera
    private int usuariosListaEspera;
    
    //Calificacion promedio de los review
    private int calificacionPromedio;
    
    public LibroDTO(){
        
    }
    public LibroDTO (LibroEntity libro)
    {
        nombre = libro.getNombre();
        isbn = libro.getIsbn();
        autor = libro.getAutor();
        editorial = libro.getEditorial();
        edicion = libro.getEdicion();
        idioma=libro.getIdioma();
        unidadesDisponibles = libro.getUnidadesDisponibles();
        usuariosListaEspera = 0;
        calificacionPromedio = 0 ;
        id = libro.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
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

    public int getUsuariosListaEspera() {
        return usuariosListaEspera;
    }

    public void setUsuariosListaEspera(int usuariosListaEspera) {
        this.usuariosListaEspera = usuariosListaEspera;
    }

    public int getCalificacionPromedio() {
        return calificacionPromedio;
    }

    public void setCalificacionPromedio(int calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }

    public LibroEntity toEntity(){
        LibroEntity libro = new LibroEntity();
        libro.setAutor(autor);
        libro.setCalificacionPromedio(calificacionPromedio);
        libro.setEdicion(edicion);
        libro.setEditorial(editorial);
        libro.setId(id);
        libro.setIdioma(idioma);
        libro.setIsbn(isbn);
        libro.setNombre(nombre);
        libro.setUnidadesDisponibles(unidadesDisponibles);
        libro.setUsuariosListaEspera(usuariosListaEspera);
        return libro;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
