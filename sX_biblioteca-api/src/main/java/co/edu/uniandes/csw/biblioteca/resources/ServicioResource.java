/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import co.edu.uniandes.csw.biblioteca.dtos.ServicioDTO;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Logger;
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
     * @throws BusinessLogicException
     * @return 
     */
    @POST
    public ServicioDTO createServicio (ServicioDTO pServicio) throws BusinessLogicException
    {
        return pServicio;
    }
    
    /**
     * OBTIENE TODOS LOS SERVICIOS
     * @return
     * @throws BusinessLogicException 
     */
    @GET
    public List<ServicioDTO> getServicios()throws BusinessLogicException
    {
        return null;
    }
    
    /**
     * OBTIENE UN SERVICIO
     * @param servicioId recibe por parametro el id del servicio que va a obtener
     * @throws BusinessLogicException
     * @return 
     */
    @GET
    @Path("{serviciosId: \\d+}")
    public ServicioDTO getServicio (@PathParam("servicioId") Long servicioId) throws BusinessLogicException
    {
       return null; 
    }
    
     /**
     * ACTUALIZA UN SERVICIO
     * @param servicioId recibe por parametro el id del servicio que va a actualizar
     * @param servicio
     * @return 
     * @throws BusinessLogicException 
     */
    @PUT
    @Path("{serviciosId: \\d+}")
    public ServicioDTO updateServicio(@PathParam("servicioId") Long servicioId, ServicioDTO servicio) throws BusinessLogicException
    {
       return null; 
    }
    
    /**
     * ELIMINA UNA RESERVA
     * @param servicioId recibe por parametro el id del servicio que va a eliminar
     * @return
     * @throws BusinessLogicException 
     */
    @DELETE
    @Path("{serviciosId: \\d+}")
    public ServicioDTO deleteServicio(@PathParam("servicioId") Long servicioId) throws BusinessLogicException
    {
       return null; 
    }
}
