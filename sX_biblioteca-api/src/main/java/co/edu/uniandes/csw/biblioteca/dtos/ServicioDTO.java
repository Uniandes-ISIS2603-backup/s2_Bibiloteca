/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.dtos;

import co.edu.uniandes.csw.bibilioteca.entities.ServicioEntity;
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
    private Long id;
    
    /**
     * identificador del objeto que se va a utilizar para el servicio
     */
    private Integer idObjeto;
    
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
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param servicioEntity: Es la entidad que se va a convertir a DTO
     */
    public ServicioDTO(ServicioEntity servicioEntity) {
        if (servicioEntity != null) 
        {
            this.id = servicioEntity.getId();
            this.idObjeto = servicioEntity.getIdObjeto();
            this.nombreServicio = servicioEntity.getNombreServicio();
            this.descripcion = servicioEntity.getDescripcion();
        }
    }
    
    //__________________________________________________________________________
    //Metodos
    //__________________________________________________________________________

    /**
     *  retorna el id del servicio
     * @return id
     */
    public Long getId() 
    {
        return id;
    }

    /**
     * asigna un pId al id del servicio
     * @param id 
     */
    public void setId(Long id) 
    {
        this.id = id;
    }

    /**
     * obtiene el id del objeto por el cual se solicita el servicio
     * @return 
     */
    public Integer getIdObjeto()
    {
        return idObjeto;
    }

    /**
     * asigna el id del objeto por el cual se solicita el servicio
     * @param idObjeto 
     */
    public void setIdObjeto(Integer idObjeto) 
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
    
     /**
     * Convertir el DTO Servicio a Entity
     * @return Un Entity con los valores del DTO
     */
    public ServicioEntity toEntity() 
    {
        ServicioEntity servicioEntity = new ServicioEntity();
        servicioEntity.setId(this.id);
        servicioEntity.setIdObjeto(this.idObjeto);
        servicioEntity.setNombreServicio(this.nombreServicio);
        servicioEntity.setDescripcion(this.descripcion);
        return servicioEntity;
    }
    
}
