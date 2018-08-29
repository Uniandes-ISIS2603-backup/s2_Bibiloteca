/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.dtos;

import co.edu.uniandes.csw.biblioteca.adapters.*;
import java.sql.Date;

/**
 *
 * @author estudiante
 */
public class PrestamoDTO 
{
    //--------------------------------------------
    // ATRIBUTOS
    //--------------------------------------------
    
    /**
     * Se refiera a la fecha en la que se presta el libro
     */
    private Date fechaDeSalida;
   
    /**
     * Se refiera a la fecha en la que se debe entregar el libro
     */
    private Date fechaDeEntrega;
    
    /**
     * Se refiere a si el libro fue devuelto o no
     */ 
    private boolean retornado;
    
    
    //----------------------------------------------
    // CONSTRUCTOR
    //----------------------------------------------
    
    public PrestamoDTO()
    {
        
    }
    
    //----------------------------------------------
    // METODOS
    //----------------------------------------------
    
    /**
     * Método para cambiar la fecha de salida
     * @param pFechaDeSalida Nueva fecha de salida
     */
    public void setFechaDeSalida(Date pFechaDeSalida)
    {
        this.fechaDeSalida= pFechaDeSalida;
    }
    
    /**
     * Método que da la fecha de salida del libro
     * @return Fecha De salida
     */
    public Date getFechaDeSalida()
    {
        return fechaDeSalida;
    }
    
    /**
     * Método para cambiar la fecha de entrega
     * @param pFechaDeEntrega Nueva fecha de entrega
     */
    public void setFechaDeEntrega(Date pFechaDeEntrega)
    {
        this.fechaDeSalida= pFechaDeEntrega;
    }
    
    /**
     * Método que da la fecha de entrega del libro
     * @return Fecha De entrega
     */
    public Date getFechaDeEntrega()
    {
        return fechaDeEntrega;
    }

    /**
     * Método para cambiar si el libro ha sido retornado o no
     * @param pRetornado Booleano con el valor para retornado
     */
    public void setRetornado (boolean pRetornado)
    {
        retornado = pRetornado;
    }
    
    /**
     * Devuelve si el libro ha sido retornado o no
     * @return  true: si el libro ha sido retornado
     *          false: si el libro no ha sido retornado
     */
    public boolean darRetornado()
    {
        return retornado;
    }
    
   
}
