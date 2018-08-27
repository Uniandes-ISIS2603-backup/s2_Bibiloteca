/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.dtos;

import co.edu.uniandes.csw.biblioteca.adapters.*;
import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class BibliotecaDTO implements Serializable
{
    
   //---------------------------------------------------------
   // ATRIBUTOS
   //---------------------------------------------------------
    
   /**
    * Se refiere al identificador de cada biblioteca
    */
    private Long identificador;
    
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
    
   //---------------------------------------------------------
   // CONSTRUCTOR
   //---------------------------------------------------------
    
   /**
     * Constructor por defecto.
     */
    public BibliotecaDTO() 
    {
        
    }
    
    //----------------------------------------------------------
    // METODOS
    //----------------------------------------------------------
    
     /**
     * Devuelve el ID de la Bblioteca
     *
     * @return the id
     */
    public Long getId() {
        return identificador;
    }

    /**
     * Modifica el ID de la biblioteca
     *
     * @param id the id to set
     */
    public void setId(Long id) {
        this.identificador = id;
    }

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
    
}
