/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.dtos;

import co.edu.uniandes.csw.bibilioteca.entities.LibroDigitalEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *OBJETO DE TRANSFERENCIA DE UN LibroDigital
 * @author Daniel Preciado
 * * Al serializarse como JSON esta clase implementa el siguiente modelo: <br>
 * <pre>
 *   {
 *      "id": number,
 *      "name": string
 *   }
 * </pre> Por ejemplo un servicio se representa asi:<br>
 *
 * <pre>
 *
 *   {
 *      "id": 000001,
 *      "nombre": "100 años de soledad"
 *      "isbn": "9789631420494"
 *      "autor": "Gabriel Garcia Marquez"
 *      "editorial": "Harper"
 *      "edicion": "tercera edicion"
 *      "idioma": "español"
 *      "calificacion": 5
 *   }
 *
 * </pre>
 */
public class LibroDigitalDTO implements Serializable {
    
    //__________________________________________________________________________
    //Atributos
    //__________________________________________________________________________
    
    /**
     * id del libro digital
     */
     private Long id;

    /**
     * Nombre del libro
     */
    private String nombre; 
    
    /**
     * Codigo identificador del libro
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
    //Constructores
    //__________________________________________________________________________
    
    /**
     * constructor vacio
     */
    public LibroDigitalDTO()
    {
        
    }
    
     /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param libroDigitalEntity: Es la entidad que se va a convertir a DTO
     */
    public LibroDigitalDTO (LibroDigitalEntity libroDigitalEntity)
    {
        if (libroDigitalEntity != null)
        {
            this.id = libroDigitalEntity.getId();
            this.nombre = libroDigitalEntity.getNombre();
            this.isbn = libroDigitalEntity.getIsbn();
            this.autor = libroDigitalEntity.getAutor();
            this.editorial = libroDigitalEntity.getEditorial();
            this.edicion = libroDigitalEntity.getEdicion();
            this.idioma = libroDigitalEntity.getIdioma();
  //el          this.calificacionPromedio = libroDigitalEntity.getCalificacion();
            
        }
        
    }

    //__________________________________________________________________________
    //Metodos
    //__________________________________________________________________________
    
    /**
     * obtiene el ID de un libro digital
     * @return id
     */
    public Long getId() 
    {
        return id;
    }
    
    /**
     * asigna un Id al libro digital
     * @param id 
     */
    public void setId(Long id) 
    {
        this.id = id;
    }
    
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
    
    /**
     * 
     * @return 
     */
    public LibroDigitalEntity toEntity()
    {
        LibroDigitalEntity libro = new LibroDigitalEntity();
        libro.setAutor(autor);
        libro.setCalificacionPromedio(calificacionPromedio);
        libro.setEdicion(edicion);
        libro.setEditorial(editorial);
        libro.setId(id);
        libro.setIdioma(idioma);
        libro.setIsbn(isbn);
        libro.setNombre(nombre);
        return libro;
    }
    @Override
    public String toString() 
    {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
