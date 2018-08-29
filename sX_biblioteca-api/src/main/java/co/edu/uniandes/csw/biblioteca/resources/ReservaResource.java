/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import co.edu.uniandes.csw.biblioteca.dtos.ReservaDTO;
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
@Path("reservas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ReservaResource {
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
     * CREA UNA NUEVA RESERVA
     * @param pServicio
     * @return 
     */
    @POST
    public ReservaDTO createReserva (ReservaDTO pReserva)
    {
        pReserva = null;
        return pReserva;
    }
        
    /**
     * OBTIENE UNA RESERVA
     * @param reservasId
     * @return 
     */
    @GET
    @Path("{reservasId: \\d+}")
    public ReservaDTO getServicio (@PathParam("reservasID") Long reservasId)
    {
       return null; 
    }
    
}
