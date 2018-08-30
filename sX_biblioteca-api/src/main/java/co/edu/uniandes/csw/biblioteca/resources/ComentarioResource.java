/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import co.edu.uniandes.csw.biblioteca.dtos.ComentarioDTO;
import co.edu.uniandes.csw.biblioteca.dtos.LibroDTO;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


/**
 *
 * @author Daniel Montoya
 */
@Produces("application/json")
@Consumes("application/json")

public class ComentarioResource {
    
    /**
     * Crea una nueva reseña con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param libroId El ID del libro del cual se le agrega la reseña
     * @param comentario {@link ComentarioDTO} - La reseña que se desea guardar.
     * @return JSON {@link ComentarioDTO} - La reseña guardada con el atributo id
     * autogenerado.
     * @throws Exception {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la reseña.
     */    
 @POST
 public ComentarioDTO createComentario(@PathParam("libroId") Long libroId , ComentarioDTO comentario) throws BusinessLogicException
 {
     return comentario;
 }
 
     /**
     * Busca y devuelve todas las reseñas que existen en un libro.
     *
     * @param libroId El ID del libro del cual se buscan las reseñas
     * @return JSONArray {@link ComentarioDTO} - Las reseñas encontradas en el
     * libro. Si no hay ninguna retorna una lista vacía.
     */
 @GET
 public ComentarioDTO getComentarios(@PathParam("libroId") Long libroId)
 {
     return null;
 }
 
 /**
  * Busca y devuelve la reseña con el ID recibido en la URL, relativa a un
  * libro.
  * @param booksId
  * @param reviewsId
  * @return
  * @throws Exception 
  */
 @GET
 @Path("{comentarioId: \\d+}")
 public ComentarioDTO getComentario (@PathParam("libroId") Long libroId, @PathParam("comentarioId") Long comentarioId) throws BusinessLogicException{
     return null;
 }
 
/**
 * Actualiza una reseña con la informacion que se recibe en el cuerpo de la
 * petición y se regresa el objeto actualizado.
 * @param libroId El ID del libro del cual se guarda la reseña
 * @param comentarioId El ID de la reseña que se va a actualizar
 * @param comentario {@link ReviewDTO} - La reseña que se desea guardar.
 * @return JSON {@link ReviewDTO} - La reseña actualizada.
 * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
 * Error de lógica que se genera cuando ya existe la reseña.
 */    
 @PUT
 @Path("{comentarioId: \\d+}")
 public ComentarioDTO updateComentario(@PathParam("libroId") Long libroId, @PathParam("comentarioId") Long comentarioId, ComentarioDTO comentario) throws BusinessLogicException {
     return null;
 }
 
  /**
     * Borra la reseña con el id asociado recibido en la URL.
     *
     * @param libroId El ID del libro del cual se va a eliminar la reseña.
     * @param comentarioId El ID de la reseña que se va a eliminar.
     */
    @DELETE
    @Path("{reviewsId: \\d+}")
    public void deleteReview(@PathParam("libroId") Long libroId, @PathParam("comentarioId") Long comentarioId) throws BusinessLogicException {
        
    }
}
