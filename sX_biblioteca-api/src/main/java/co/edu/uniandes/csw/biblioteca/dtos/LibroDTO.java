/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.adapters;

/**
 *
 * @author Daniel Montoya
 */
public class LibroDTO {
    
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
    
    public LibroDTO (String pnombre , String pIsbn , String pAutor, String pEditorial , String pEdicion , String pIdioma, int pUnidadesDisponibles)
    {
        nombre = pnombre;
        isbn = pIsbn;
        autor = pAutor;
        editorial = pEditorial;
        edicion = pEdicion;
        idioma=pIdioma;
        unidadesDisponibles = pUnidadesDisponibles;
        usuariosListaEspera = 0;
        calificacionPromedio = 0 ;
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
    
}
