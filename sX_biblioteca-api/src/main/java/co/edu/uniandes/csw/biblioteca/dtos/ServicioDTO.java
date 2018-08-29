/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.dtos;

import java.io.Serializable;

/**
 *OBJETO DE TRANSFERENCIA DE UN SERVICIO
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
 *      "idObjeto": "1"
 *      "nombreServicio": "reservarLibro"
 *      "descripcion": "reserva un libro en una fecha dada"
 *  
 *   }
 *
 * </pre>
 */
public class ServicioDTO implements Serializable{
    
    //__________________________________________________________________________
    //Atributos
    //__________________________________________________________________________
    
    /**
     * identificador del servicio
     */
    private long id;
    
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
    //Constructores
    //__________________________________________________________________________
    
    /**
     * constructor A
     */
    public void ServicioDTO ()
    {
    }

    /**
     * constructor B
     * @param id
     * @param idObjeto
     * @param nombreServicio
     * @param descripcion 
     */
    public ServicioDTO(long id, int idObjeto, String nombreServicio, String descripcion) 
    {
        this.id = id;
        this.idObjeto = idObjeto;
        this.nombreServicio = nombreServicio;
        this.descripcion = descripcion;
    }
    
    //__________________________________________________________________________
    //Metodos
    //__________________________________________________________________________

    /**
     *  retorna el id del servicio
     * @return id
     */
    public long getId() 
    {
        return id;
    }

    /**
     * asigna un pId al id del servicio
     * @param id 
     */
    public void setId(long id) 
    {
        this.id = id;
    }

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
