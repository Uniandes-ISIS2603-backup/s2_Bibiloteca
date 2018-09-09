/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.dtos;

import co.edu.uniandes.csw.bibilioteca.entities.PrestamoEntity;
import co.edu.uniandes.csw.biblioteca.adapters.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author estudiante
 */
public class PrestamoDTO implements Serializable
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
    private Boolean retornado;
     
    /**
    * Se refiere al identificador de cada prestamo
    */
    private Long id;
    
    
    //----------------------------------------------
    // CONSTRUCTOR
    //----------------------------------------------
     /**
     * Constructor por defecto.
     */
    public PrestamoDTO()
    {
        
    }
    
     /**
     * Constructor a partir de la entidad
     *
     * @param prestamoEntity La entidad de la biblioteca
     */
    public PrestamoDTO(PrestamoEntity prestamoEntity) {
        if (prestamoEntity != null) 
        {
            this.fechaDeEntrega = prestamoEntity.getFechaDeEntrega();
            this.fechaDeSalida = prestamoEntity.getFechaDeSalida();
            this.retornado = prestamoEntity.getRetornado();
            this.id = prestamoEntity.getId();
            // Ac´á iría la relación con el usuario.
        }
    }
    
    //----------------------------------------------
    // METODOS
    //----------------------------------------------
    
    /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad del libro asociado.
     */
    public PrestamoEntity toEntity() {
        PrestamoEntity prestamoEntity = new PrestamoEntity();
        prestamoEntity.setId(this.id);
        prestamoEntity.setFechaDeEntrega(this.fechaDeEntrega);
        prestamoEntity.setFechaDeSalida(this.fechaDeSalida);
        prestamoEntity.setRetornado(this.retornado);
        //Faltaría el usuarioEnttity cuando mi compañero lo haga       
        return prestamoEntity;
    }
    
    /**
     * Devuelve el ID del prestamo
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el ID del prestamo
     *
     * @param pId the id to set
     */
    public void setId(Long pId) {
        this.id = pId;
    }
    
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
        this.fechaDeEntrega= pFechaDeEntrega;
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
