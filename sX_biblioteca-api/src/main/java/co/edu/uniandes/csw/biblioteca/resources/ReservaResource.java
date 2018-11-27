/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import co.edu.uniandes.csw.bibilioteca.entities.ReservaEntity;
import co.edu.uniandes.csw.biblioteca.dtos.ReservaDTO;
import co.edu.uniandes.csw.biblioteca.dtos.ReservaDetailDTO;
import co.edu.uniandes.csw.biblioteca.ejb.ReservaLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import java.util.ArrayList;
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
import javax.ws.rs.WebApplicationException;

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
        // Invoca la lógica para crear la reserva nueva
        ReservaEntity reservaEntityAux = reservaLogic.createReserva(reservaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        ReservaDTO reservaDTO = new ReservaDTO(reservaEntityAux);
        LOGGER.log(Level.INFO, "ReservaResource createEditorial: output: {0}", reservaDTO.toString());
        return reservaDTO;
    }
    
        
    /**
     * OBTIENE TODOS LAS RESERVAS
     * @return JSONArray {@link ReservaDetailDTO} - Las reservas
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<ReservaDetailDTO> getReservas()
    {
        LOGGER.info("ReservaResource getReservas: input: void");
        List<ReservaDetailDTO> listaReservas = listEntity2DetailDTO(reservaLogic.getReservas());
        LOGGER.log(Level.INFO, "ReservaResource getReservas: output: {0}", listaReservas.toString());
        return listaReservas;
    }
        
    /**
     * OBTIENE UNA RESERVA
     * @param reservasId recibe por parametro el id
     * de la reserva que esta buscando
     * @throws WebApplicationException
     * @return 
     */
    @GET
    @Path("{reservasId: \\d+}")
    public ReservaDetailDTO getReserva (@PathParam("reservasId") Long reservasId) throws WebApplicationException
    {
        LOGGER.log(Level.INFO, "ReservaResource getEditorial: input: {0}", reservasId);
        ReservaEntity reservaEntity = reservaLogic.getReserva(reservasId);
        if (reservaEntity == null) 
        {
            throw new WebApplicationException("El recurso /reservas/" + reservasId + " no existe.", 404);
        }
        ReservaDetailDTO detailDTO = new ReservaDetailDTO(reservaEntity);
        LOGGER.log(Level.INFO, "EditorialResource getEditorial: output: {0}", detailDTO.toString());
        return detailDTO;
    }
    
    /**
     * ACTUALIZA UNA RESERVA
     * @param reservaId recibe por parametro el id de la reserva que va a actualizar
     * @param reserva
     * @return JSON {@link ReservaDetailDTO} - La reserva guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reserva a
     * actualizar.
     */
    @PUT
    @Path("{reservasId: \\d+}")
    public ReservaDTO updateReserva(@PathParam("reservaId") Long reservaId, ReservaDTO reserva) throws WebApplicationException
    {
        LOGGER.log(Level.INFO, "ReservaResource updateReserva: input: id:{0} , reserva: {1}", new Object[]{reservaId, reserva.toString()});
        reserva.setId(reservaId);
        if (reservaLogic.getReserva(reservaId) == null)
        {
            throw new WebApplicationException("El recurso /reservas/" + reservaId + " no existe.", 404);
        }
        ReservaDetailDTO detailDTO = new ReservaDetailDTO(reservaLogic.updateReserva(reservaId, reserva.toEntity()));
        LOGGER.log(Level.INFO, "ReservaResource updateReserva: output: {0}", detailDTO.toString());
        return detailDTO;

    }
    
    /**
     * ELIMINA UNA RESERVA
     * @param reservaId recibe por parametro el id de la reserva que va a eliminar
    * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la reserva.
     */
    @DELETE
    @Path("{reservasId: \\d+}")
    public void deleteReserva(@PathParam("reservaId") Long reservaId) throws WebApplicationException
    {
        LOGGER.log(Level.INFO, "ReservaResource deleteReserva: input: {0}", reservaId);
        if (reservaLogic.getReserva(reservaId) == null) 
        {
            throw new WebApplicationException("El recurso /reservas/" + reservaId + " no existe.", 404);
        }
        reservaLogic.deleteReserva(reservaId);
        LOGGER.info("ReservaResource deleteReserva: output: void");
    }
    
     /**
     * Convierte una lista de entidades a DTO.
     * Este método convierte una lista de objetos ReservaEntity a una lista de
     * objetos ReservaDetailDTO (json)
     * @param entityList corresponde a la lista de reservas de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de reservas en forma DTO (json)
     */
    private List<ReservaDetailDTO> listEntity2DetailDTO(List<ReservaEntity> entityList) {
        List<ReservaDetailDTO> list = new ArrayList<>();
        for (ReservaEntity entity : entityList) 
        {
            list.add(new ReservaDetailDTO(entity));
        }
        return list;
    }
    
}
