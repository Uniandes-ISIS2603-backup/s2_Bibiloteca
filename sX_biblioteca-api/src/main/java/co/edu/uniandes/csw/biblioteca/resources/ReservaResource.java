/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import co.edu.uniandes.csw.biblioteca.dtos.ReservaDTO;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
     * @throws BusinessLogicException
     * @return 
     */
    @POST
    public ReservaDTO createReserva (ReservaDTO pReserva)throws BusinessLogicException
    {
        return pReserva;
    }
    
        
    /**
     * OBTIENE TODOS LAS RESERVAS
     * @return
     * @throws BusinessLogicException 
     */
    @GET
    public List<ReservaDTO> getReservas()throws BusinessLogicException
    {
        return null;
    }
        
    /**
     * OBTIENE UNA RESERVA
     * @param reservasId recibe por parametro el id
     * de la reserva que esta buscando
     * @throws BusinessLogicException
     * @return 
     */
    @GET
    @Path("{reservasId: \\d+}")
    public ReservaDTO getReserva (@PathParam("reservasId") Long reservasId) throws BusinessLogicException
    {
       return null; 
    }
    
    /**
     * ACTUALIZA UNA RESERVA
     * @param reservaId recibe por parametro el id de la reserva que va a actualizar
     * @param reserva
     * @return 
     * @throws BusinessLogicException 
     */
    @PUT
    @Path("{reservasId: \\d+}")
    public ReservaDTO updateReserva(@PathParam("reservaId") Long reservaId, ReservaDTO reserva) throws BusinessLogicException
    {
       return null; 
    }
    
    /**
     * ELIMINA UNA RESERVA
     * @param reservaId recibe por parametro el id de la reserva que va a eliminar
     * @return
     * @throws BusinessLogicException 
     */
    @DELETE
    @Path("{reservasId: \\d+}")
    public ReservaDTO deleteLibro(@PathParam("reservaId") Long reservaId) throws BusinessLogicException
    {
       return null; 
    }
    
}
