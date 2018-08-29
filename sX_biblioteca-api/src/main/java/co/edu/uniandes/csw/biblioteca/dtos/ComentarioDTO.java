/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.dtos;

import java.util.Date;

/**
 *
 * @author Daniel Montoya
 */
public class ComentarioDTO {
    
    //Calificacion asignada al libro
    private double numeroEstrellas; 
    
    //Comentario del usuario sobre el libro
    private String texto;
    
    //Nombre del usuario que le puso el comentario
    private String nombreUsuario;
    
    //Fecha en la que el comentario fue creado
    private Date fecha;
    
    private Long id; 
    
    public ComentarioDTO (double pEstrellas , String pTexto , String pNombreUsuario , Date pFecha, Long pid)
    {
        numeroEstrellas = pEstrellas;
        texto = pTexto;
        nombreUsuario = pNombreUsuario;
        fecha = pFecha;
        id = pid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
    
}
