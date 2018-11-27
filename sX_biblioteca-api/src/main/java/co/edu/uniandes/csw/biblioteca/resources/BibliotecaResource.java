/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import co.edu.uniandes.csw.bibilioteca.entities.BibliotecaEntity;
import java.util.logging.Level;
import javax.ws.rs.*;
import co.edu.uniandes.csw.biblioteca.dtos.BibliotecaDTO;
import co.edu.uniandes.csw.biblioteca.dtos.BibliotecaDetailDTO;
import co.edu.uniandes.csw.biblioteca.ejb.BibliotecaLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 * Clase que implementa el recurso "bibliotecas".
 *
 * @author David Saavedra
 * @version 1.0
 */
@Path("bibliotecas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class BibliotecaResource {
    
    private static final Logger LOGGER = Logger.getLogger(BibliotecaResource.class.getName());
    
    @Inject
    private BibliotecaLogic bibliotecaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una nueva biblioteca con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param biblioteca {@link BibliotecaDTO} - La biblioteca que se desea
     * guardar.
     * @return JSON {@link EditorialDTO} - La editorial guardada con el atributo
     * id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la biblioteca
     */
     @POST
    public BibliotecaDTO createBiblioteca(BibliotecaDTO biblioteca) throws BusinessLogicException 
    {
       LOGGER.log(Level.INFO, "BibliotecaResource createBiblioteca: input: {0}", biblioteca.toString());
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        BibliotecaEntity bibliotecaEntity = biblioteca.toEntity();
        // Invoca la lógica para crear la editorial nueva
        BibliotecaEntity nuevoEditorialEntity = bibliotecaLogic.createBiblioteca(bibliotecaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        BibliotecaDTO nuevoBibliotecaDTO = new BibliotecaDTO(nuevoEditorialEntity);
        LOGGER.log(Level.INFO, "BibliotecaResource createBiblioteca: output: {0}", nuevoBibliotecaDTO.toString());
        return nuevoBibliotecaDTO;
    }
    
    /**
     * Busca y devuelve todas las bibliotecas que existen en la aplicacion.
     *
     * @return JSONArray {@link BibliotecaDetailDTO} - Las editoriales
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<BibliotecaDetailDTO> getBibliotecas() throws BusinessLogicException 
    {
        LOGGER.info("BibliotecaResource getBibliotecas: input: void");
        List<BibliotecaDetailDTO> listaBibliotecas = listEntity2DetailDTO(bibliotecaLogic.getBibliotecas());
        LOGGER.log(Level.INFO, "BibliotecaResource getBibliotecas: output: {0}", listaBibliotecas.toString());
        return listaBibliotecas;
    }
    
    
    /**
     * Busca la biblioteca con el id asociado recibido en la URL y la devuelve.
     *
     * @param bibliotecaId: Identificador de la biblioteca que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link BiblitoecaDetailDTO} - La editorial buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la editorial.
     */
    @GET
    @Path("{bibliotecasId: \\d+}")
    public BibliotecaDetailDTO getBiblioteca(@PathParam("bibliotecasId") Long bibliotecaId) throws WebApplicationException
    {
       LOGGER.log(Level.INFO, "BibliotecaResource getBiblioteca: input: {0}", bibliotecaId);
        BibliotecaEntity bibliotecaEntity = bibliotecaLogic.getBiblioteca(bibliotecaId);
        if (bibliotecaEntity == null) {
            throw new WebApplicationException("El recurso /bibliotecas/" + bibliotecaId + " no existe.", 404);
        }
        BibliotecaDetailDTO detailDTO = new BibliotecaDetailDTO(bibliotecaEntity);
        LOGGER.log(Level.INFO, "EditorialResource getEditorial: output: {0}", detailDTO.toString());
        return detailDTO;
    }
        
    
    /**
     * Actualiza la biblitoeca con el id recibido en la URL con la informacion
     * que se recibe en el cuerpo de la petición.
     *
     * @param bibliotecasId Identificador de la biblioteca que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param biblioteca {@link BiblitoecaDetailDTO} La biblioteca que se desea
     * guardar.
     * @return JSON {@link BiblitoecaDetailDTO} - La biblioteca guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la biblioteca a
     * actualizar.
     */
    @PUT
     @Path("{bibliotecasId: \\d+}")
    public BibliotecaDTO actualizarBiblioteca(@PathParam("bibliotecasId") Long bibliotecasId, BibliotecaDTO biblioteca) throws BusinessLogicException 
    {
       LOGGER.log(Level.INFO, "BiblitoecaResource updateBiblioteca: input: id:{0} , biblioteca: {1}", new Object[]{bibliotecasId, biblioteca.toString()});
        biblioteca.setId(bibliotecasId);
        if (bibliotecaLogic.getBiblioteca(bibliotecasId) == null) {
            throw new WebApplicationException("El recurso /bibliotecas/" + bibliotecasId + " no existe.", 404);
        }
        BibliotecaDetailDTO detailDTO = new BibliotecaDetailDTO(bibliotecaLogic.updateBiblioteca(bibliotecasId, biblioteca.toEntity()));
        LOGGER.log(Level.INFO, "BibliotecaResource updateBiblioteca: output: {0}", detailDTO.toString());
        return detailDTO;
    }
    
    @DELETE
     @Path("{bibliotecasId: \\d+}")
    public void eliminarBiblioteca(@PathParam("bibliotecasId") Long bibliotecasId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "BibliotecaResource deleteBiblioteca: input: {0}", bibliotecasId);
        if (bibliotecaLogic.getBiblioteca(bibliotecasId) == null) 
        {
            throw new WebApplicationException("El recurso /bibliotecas/" + bibliotecasId + " no existe.", 404);
        }
        bibliotecaLogic.deleteBiblioteca(bibliotecasId);
        LOGGER.info("BibliotecaResource deleteBiblioteca: output: void");
    }
    
    
     /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos BibliotecaEntity a una lista de
     * objetos BibliotecaDetailDTO (json)
     *
     * @param entityList corresponde a la lista de editoriales de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de biblioteca en forma DTO (json)
     */
    private List<BibliotecaDetailDTO> listEntity2DetailDTO(List<BibliotecaEntity> entityList) {
        List<BibliotecaDetailDTO> list = new ArrayList<>();
        for (BibliotecaEntity entity : entityList) {
            list.add(new BibliotecaDetailDTO(entity));
        }
        return list;
    }
    
        

}
