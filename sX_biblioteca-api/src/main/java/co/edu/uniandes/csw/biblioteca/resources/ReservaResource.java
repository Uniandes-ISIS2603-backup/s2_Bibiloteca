/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import co.edu.uniandes.csw.bibilioteca.entities.ReservaEntity;
import co.edu.uniandes.csw.biblioteca.dtos.ReservaDTO;
import co.edu.uniandes.csw.biblioteca.ejb.ReservaLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import java.util.List;
import java.util.logging.Level;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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
    // Atributo
    //__________________________________________________________________________
    
    /**
     * atributo utilizado para acceder a la logica de la aplciacion
     */
    @Inject
    private ReservaLogic reservaLogic;
    
    //__________________________________________________________________________
    //Metodos
    //__________________________________________________________________________

    /**
     * CREA UNA NUEVA RESERVA
     * Crea una nueva reserva con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     * @param pReserva {@link ReservaDTO} - La reserva que se desea
     * guardar.
     * @return JSON {@link ReservaDTO} - La reserva guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no es posible crear la reserva por
     * reglas de negocio.
     */
    @POST
    public ReservaDTO createReserva (ReservaDTO pReserva)throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "ReservaResource createReserva: input: {0}", pReserva.toString());
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ReservaEntity reservaEntity = pReserva.toEntity();
        // Invoca la lógica para crear la editorial nueva
        ReservaEntity reservaEntityAux = reservaLogic.createReserva(reservaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        ReservaDTO reservaDTO = new ReservaDTO(reservaEntityAux);
        LOGGER.log(Level.INFO, "EditorialResource createEditorial: output: {0}", reservaDTO.toString());
        return reservaDTO;
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
    public ReservaDTO deleteReserva(@PathParam("reservaId") Long reservaId) throws BusinessLogicException
    {
       return null; 
    }
    
}
