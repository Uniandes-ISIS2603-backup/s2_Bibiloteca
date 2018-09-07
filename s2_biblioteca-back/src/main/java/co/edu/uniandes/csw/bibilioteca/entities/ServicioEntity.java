/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bibilioteca.entities;

import java.io.Serializable;

/**
 * Clase que representa una entidad
 * de la clase Servicio
 * @author Daniel Preciado
 */
public class ServicioEntity extends BaseEntity implements Serializable{
       
    //__________________________________________________________________________
    //Atributos
    //__________________________________________________________________________
    
    /**
     * identificador del objeto que se va a utilizar para el servicio
     */
    private int idObjeto;
    
    /**
     * nombre del servicio que se va a prestar
     */
    private String nombreServicio;
    
    /**
     * descripcion del servicio que se ofrece
     */
    private String descripcion;

          
    //__________________________________________________________________________
    //Metodos
    //__________________________________________________________________________


    /**
     * obtiene el id del objeto por el cual se solicita el servicio
     * @return 
     */
    public int getIdObjeto()
    {
        return idObjeto;
    }

    /**
     * asigna el id del objeto por el cual se solicita el servicio
     * @param idObjeto 
     */
    public void setIdObjeto(int idObjeto) 
    {
        this.idObjeto = idObjeto;
    }

    /**
     * devuelve el nombre del servicio
     * @return nombreServicio
     */
    public String getNombreServicio() 
    {
        return nombreServicio;
    }

    /**
     * asigna el nombre al servicio
     * @param nombreServicio 
     */
    public void setNombreServicio(String nombreServicio) 
    {
        this.nombreServicio = nombreServicio;
    }

    /**
     * obtiene la descripcion del servicio
     * @return 
     */
    public String getDescripcion() 
    {
        return descripcion;
    }

    /**
     * configura la descripcion del servicio
     * @param descripcion 
     */
    public void setDescripcion(String descripcion) 
    {
        this.descripcion = descripcion;
    }
    
}
