/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import co.edu.uniandes.csw.biblioteca.dtos.ServicioDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * clase que implementa el recurso servicio
 * @author Daniel Preciado
 */
@Path("servicios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ServicioResource {
    
    //__________________________________________________________________________
    // Constanstes
    //__________________________________________________________________________
    /**
     * constante utilizada para dejar huella y registro
     */
    private static final Logger LOGGER = Logger.getLogger(ServicioResource.class.getName());
    
    //__________________________________________________________________________
    //Metodos
    //__________________________________________________________________________

    /**
     * CREA UN NUEVO SERVICIO
     * @param pServicio
     * @return 
     */
    @POST
    public ServicioDTO createServicio (ServicioDTO pServicio)
    {
        pServicio = null;
        return pServicio;
    }
    
    /**
     * OBTIENE UN SERVICIO
     * @param librosId
     * @return 
     */
    @GET
    @Path("{serviciosId: \\d+}")
    public ServicioDTO getServicio (@PathParam("serviciosID") Long serviciosId)
    {
       return null; 
    }
}
