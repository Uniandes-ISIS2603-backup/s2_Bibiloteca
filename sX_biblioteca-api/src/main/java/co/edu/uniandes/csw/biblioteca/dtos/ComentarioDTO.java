/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.dtos;

import co.edu.uniandes.csw.bibilioteca.entities.ComentarioEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Daniel Montoya
 */
public class ComentarioDTO implements Serializable{
    
    //Calificacion asignada al libro
    private Double numeroEstrellas; 
    
    //Comentario del usuario sobre el libro
    private String texto;
    
    //Nombre del usuario que le puso el comentario
    private String nombreUsuario;
    
    //Fecha en la que el comentario fue creado
    private Date fecha;
    
    private Long id; 
    
    public ComentarioDTO(){
        
    }
    public ComentarioDTO (ComentarioEntity comentario)
    {
        this.numeroEstrellas = comentario.getNumeroEstrellas();
        this.texto = comentario.getTexto();
        this.nombreUsuario = comentario.getNombreUsuario();
        this.fecha = comentario.getFecha();
        this.id = comentario.getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNumeroEstrellas() {
        return numeroEstrellas;
    }

    public void setNumeroEstrellas(Double numeroEstrellas) {
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
    
    public ComentarioEntity toEntity(){
        ComentarioEntity comentario = new ComentarioEntity();
        comentario.setId(this.id);
        comentario.setNumeroEstrellas(this.numeroEstrellas);
        comentario.setNombreUsuario(this.nombreUsuario);
        comentario.setFecha(this.fecha);
        comentario.setTexto(this.texto);
      return comentario;          
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}

