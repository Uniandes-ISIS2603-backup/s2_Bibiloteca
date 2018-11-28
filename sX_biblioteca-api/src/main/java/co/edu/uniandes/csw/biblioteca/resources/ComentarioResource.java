/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import co.edu.uniandes.csw.bibilioteca.entities.ComentarioEntity;
import co.edu.uniandes.csw.biblioteca.dtos.ComentarioDTO;
import co.edu.uniandes.csw.biblioteca.dtos.LibroDTO;
import co.edu.uniandes.csw.biblioteca.ejb.ComentarioLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
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
 *
 * @author Daniel Montoya
 */
//@Path("comentarios")
@Produces("application/json")
@Consumes("application/json")
public class ComentarioResource {
    
    @Inject
    private ComentarioLogic comentarioLogic;
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
 public ComentarioDTO createComentario(@PathParam("librosId") Long libroId , ComentarioDTO comentario,@PathParam("usuariosId") Long usuarioId) throws BusinessLogicException
 {
     ComentarioDTO nuevoComentario = new ComentarioDTO(comentarioLogic.createComentario(libroId, comentario.toEntity(), usuarioId));
     return nuevoComentario;
 }
 
     /**
     * Busca y devuelve todas las reseñas que existen en un libro.
     *
     * @param libroId El ID del libro del cual se buscan las reseñas
     * @return JSONArray {@link ComentarioDTO} - Las reseñas encontradas en el
     * libro. Si no hay ninguna retorna una lista vacía.
     */
 @GET
 public List<ComentarioDTO> getComentarios(@PathParam("librosId") Long libroId)
 {
     List<ComentarioDTO> listaDTO = listEntity2DTO(comentarioLogic.getComentariosPorLibro(libroId));
     return listaDTO;
 }
 
 /**
  * Busca y devuelve la reseña con el ID recibido en la URL, relativa a un
  * libro.
  * @param libroId
  * @param comentarioId
  * @return
  * @throws Exception 
  */
 @GET
 @Path("{comentarioId: \\d+}")
 public ComentarioDTO getComentarioLibro (@PathParam("librosId") Long libroId, @PathParam("comentarioId") Long comentarioId){
     ComentarioEntity entity = comentarioLogic.getComentarioLibro(libroId, comentarioId);
     if(entity==null)
     {
         throw new WebApplicationException("Comentario con id: "+comentarioId+" no existe",404);
     }
     ComentarioDTO comentarioDTO = new ComentarioDTO(entity);
     return comentarioDTO;
 }
 
  /**
  * Busca y devuelve la reseña con el ID recibido en la URL, relativa a un
  * libro.
  * @param usuariosId
  * @param comentarioId
  * @return
  * @throws Exception 
  */
 /*
 @GET
 @Path("{comentarioId: \\d+}")
 public ComentarioDTO getComentarioUsuario (@PathParam("usuariosId") Long usuarioId, @PathParam("comentarioId") Long comentarioId){
    
     ComentarioEntity entity = comentarioLogic.getComentarioUsuario(usuarioId, comentarioId);
     if(entity==null)
     {
         throw new WebApplicationException("Comentario con id: "+comentarioId+" no existe",404);
     }
     
     ComentarioDTO comentarioDTO = new ComentarioDTO(entity);
     return comentarioDTO;
 }
*/
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
 public ComentarioDTO updateComentario(@PathParam("librosId") Long libroId, @PathParam("comentarioId") Long comentarioId ,ComentarioDTO comentario) throws BusinessLogicException  {
     if(!comentarioId.equals(comentario.getId()))
     {
         throw new BusinessLogicException("Los ids del Review no coinciden.");
     }
     ComentarioEntity entity = comentarioLogic.getComentarioLibro(libroId, comentarioId);
     if(entity == null)
     {
         throw new WebApplicationException("El recurso /libros/" + libroId + "/comentarios/" + comentarioId + " no existe.", 404);
     }
     ComentarioDTO comentarioDTO = new ComentarioDTO(comentarioLogic.updateComentario(libroId, comentario.toEntity(), comentario.getUsuario().getId()));
     return comentarioDTO;
    
 }
 
  /**
     * Borra la reseña con el id asociado recibido en la URL.
     *
     * @param libroId El ID del libro del cual se va a eliminar la reseña.
     * @param comentarioId El ID de la reseña que se va a eliminar.
     */
 @DELETE
 @Path("{comentarioId: \\d+}")
    public void deleteComentario(@PathParam("librosId") Long libroId, @PathParam("comentarioId") Long comentarioId) throws BusinessLogicException  {
        ComentarioEntity comentario = comentarioLogic.getComentarioLibro(libroId, comentarioId);
        if(comentario == null)
        {
            throw new WebApplicationException("El recurso /libros/" + libroId + "/comentarios/" + comentarioId + " no existe.", 404);
        }
        comentarioLogic.deleteComentario(libroId, comentarioId);
    }

    private List<ComentarioDTO> listEntity2DTO(List<ComentarioEntity> listaEntity)
    {
        List<ComentarioDTO> resp = new ArrayList<>();
        for(ComentarioEntity entity : listaEntity)
        {
            resp.add(new ComentarioDTO(entity));
        }
        return resp;
    }
}
