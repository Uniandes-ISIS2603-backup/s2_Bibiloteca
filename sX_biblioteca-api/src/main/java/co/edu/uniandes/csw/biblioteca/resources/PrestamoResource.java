/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import co.edu.uniandes.csw.bibilioteca.entities.PrestamoEntity;
import java.util.logging.Level;
import javax.ws.rs.*;
import co.edu.uniandes.csw.biblioteca.dtos.PrestamoDTO;
import co.edu.uniandes.csw.biblioteca.ejb.PrestamoLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * Clase que implementa el recurso "prestamo".
 *
 * @author David Saavedra
 * @version 1.0
 */
@Path("prestamos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class PrestamoResource {
    
    private static final Logger LOGGER = Logger.getLogger(PrestamoResource.class.getName());

    @Inject
    private PrestamoLogic prestamoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

     /**
     * Crea un prestamo con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param prestamo {@link PrestamoDTO} - El prestamo que se desea
     * guardar.
     * @return JSON {@link PrestamoDTO} - El prestamo guardado con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la editorial.
     */
    @POST
    public PrestamoDTO createPrestamo(PrestamoDTO prestamo) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PrestamoResource createPrestamo: input: {0}", prestamo.toString());
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        PrestamoEntity prestamoEntity = prestamo.toEntity();
        // Invoca la lógica para crear el nuevo prestamo
        PrestamoEntity nuevoPrestamoEntity = prestamoLogic.createPrestamo(prestamoEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        PrestamoDTO nuevoPrestamoDTO = new PrestamoDTO(nuevoPrestamoEntity);
        LOGGER.log(Level.INFO, "PrestamoResource createPrestamo: output: {0}", nuevoPrestamoDTO.toString());
        return nuevoPrestamoDTO;
    }
    
     /**
     * Busca y devuelve todas los prestamos que existen en la aplicacion.
     *
     * @return JSONArray {@link PrestamoDTO} - Los prestamos
     * encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public Collection<PrestamoDTO> getPrestamos() {
        LOGGER.info("PrestamoResource getPrestamos: input: void");
        List<PrestamoDTO> listaDTOs = listEntity2DTO(prestamoLogic.getPrestamos());
        LOGGER.log(Level.INFO, "EditorialResource getReviews: output: {0}", listaDTOs.toString());
        return listaDTOs;
    }
    
     /**
     * Busca el prestamo con el id asociado recibido en la URL y la devuelve.
     *
     * @param prestamoId Identificador del prestamo que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link PrestamoDTO} - La editorial buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el prestamo.
     */
    @GET
    @Path("{prestamoId: \\d+}")
    public PrestamoDTO getPrestamo(@PathParam("prestamosId") Long prestamoId) {
         LOGGER.log(Level.INFO, "PrestamoResource getPrestamo: input: {0}", prestamoId);
        PrestamoEntity prestamoEntity = prestamoLogic.getPrestamo(prestamoId);
        if (prestamoEntity == null) {
            throw new WebApplicationException("El recurso /prestamos/" + prestamoId + " no existe.", 404);
        }
        PrestamoDTO prestamoDTO = new PrestamoDTO(prestamoEntity);
        LOGGER.log(Level.INFO, "PrestamoResource getPrestamo: output: {0}", prestamoDTO.toString());
        return prestamoDTO;
    }
    
    /**
     * Actualiza el prestamo con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param prestamoId Identificador del prestamo que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param prestamo {@link PrestamoDTO} El prestamo que se desea
     * guardar.
     * @return JSON {@link PrestamoDTO} - El prestamo guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el prestamo a
     * actualizar.
     */
    @PUT
    @Path("{prestamosId: \\d+}")
    public PrestamoDTO actualizarPrestamo(@PathParam("prestamosId") Long prestamoId, PrestamoDTO prestamo)throws WebApplicationException {
        LOGGER.log(Level.INFO, "PrestamoResource updatePrestamo: input: id:{0} , editorial: {1}", new Object[]{prestamoId, prestamo.toString()});
        prestamo.setId(prestamoId);
        if (prestamoLogic.getPrestamo(prestamoId) == null) {
            throw new WebApplicationException("El recurso /prestamos/" + prestamoId + " no existe.", 404);
        }
        PrestamoDTO prestamoDTO = new PrestamoDTO(prestamoLogic.updatePrestamo(prestamoId, prestamo.toEntity()));
        LOGGER.log(Level.INFO, "PrestamoResource getPrestamo: output: {0}", prestamoDTO.toString());
        return prestamoDTO;
    }
    
    /**
     * Borra el prestamo con el id asociado recibido en la URL.
     *
     * @param prestamosId Identificador del prestamo que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar el prestamo.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el prestamo.
     */
    @DELETE
    @Path("{prestamosId: \\d+}")
    public void eliminarPrestamo(@PathParam("prestamosId") Long prestamosId) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "PrestamoResource deletePrestamo: input: {0}", prestamosId);
        if (prestamoLogic.getPrestamo(prestamosId) == null) {
            throw new WebApplicationException("El recurso /prestamos/" + prestamosId + " no existe.", 404);
        }
        prestamoLogic.deletePrestamo(prestamosId);
        LOGGER.info("PrestamoResource deletePrestamo: output: void");
    }
    
    /**
     * Lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos PrestamoeEntity a una lista de
     * objetos PrestamoDTO (json)
     *
     * @param entityList corresponde a la lista de prestamos de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de prestamos en forma DTO (json)
     */
    private List<PrestamoDTO> listEntity2DTO(List<PrestamoEntity> entityList) {
        List<PrestamoDTO> list = new ArrayList<PrestamoDTO>();
        for (PrestamoEntity entity : entityList) {
            list.add(new PrestamoDTO(entity));
        }
        return list;
    }
}
