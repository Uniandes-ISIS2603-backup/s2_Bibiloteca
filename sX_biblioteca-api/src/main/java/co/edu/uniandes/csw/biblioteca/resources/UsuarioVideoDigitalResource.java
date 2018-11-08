/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import co.edu.uniandes.csw.bibilioteca.entities.VideoDigitalEntity;
import co.edu.uniandes.csw.biblioteca.dtos.VideoDigitalDetailDTO;
import co.edu.uniandes.csw.biblioteca.ejb.UsuarioVideoDigitalLogic;
import co.edu.uniandes.csw.biblioteca.ejb.VideoDigitalLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class UsuarioVideoDigitalResource {
      private static final Logger LOGGER = Logger.getLogger(UsuarioVideoDigitalResource.class.getName());

    @Inject
    private UsuarioVideoDigitalLogic usuarioVideoDigitalLogic;

    @Inject
    private VideoDigitalLogic videoDigitalLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Asocia un video digital existente con un usuario existente
     *
     * @param usuariosId El ID del usuario al cual se le va a asociar el video digital
     * @param videosDigitalesId El ID del video digital que se asocia
     * @return JSON {@link VideoDigitalDetailDTO} - El video digital asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el video digital.
     */
    @POST
    @Path("{videodigitalid: \\d+}")
    public VideoDigitalDetailDTO addVideoDigital(@PathParam("usuariosId") Long usuariosId, @PathParam("videodigitalid") Long videodigitalid) {
        LOGGER.log(Level.INFO, "UsuarioVideoDigitalResource addVideoDigital: input: usuariosId {0} , videodigitalid {1}", new Object[]{usuariosId, videodigitalid});
        if (videoDigitalLogic.getVideoDigital(videodigitalid) == null) {
            throw new WebApplicationException("El recurso /videoDigital/" + videodigitalid + " no existe.", 404);
        }
        VideoDigitalDetailDTO detailDTO = new VideoDigitalDetailDTO(usuarioVideoDigitalLogic.addVideoDigital(usuariosId, videodigitalid));
        LOGGER.log(Level.INFO, "UsuarioVideoDigitalResource addVideoDigital: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Busca y devuelve todos los video digitals que existen en un usuario.
     *
     * @param usuariosId El ID del usuario del cual se buscan los video digitals
     * @return JSONArray {@link VideoDigitalDetailDTO} - Los video digitals encontrados en el
     * usuario. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<VideoDigitalDetailDTO> getVideosDigitales(@PathParam("usuariosId") Long usuariosId) {
        LOGGER.log(Level.INFO, "UsuarioVideoDigitalResource getVideosDigitales: input: {0}", usuariosId);
        List<VideoDigitalDetailDTO> lista = videosDigitalesListEntity2DTO(usuarioVideoDigitalLogic.getVideosDigitales(usuariosId));
        LOGGER.log(Level.INFO, "UsuarioVideoDigitalResource getVideosDigitales: output: {0}", lista.toString());
        return lista;
    }

    /**
     * Busca y devuelve el video digital con el ID recibido en la URL, relativo a un
     * usuario.
     *
     * @param usuariosId El ID del usuario del cual se busca el video digital
     * @param videosDigitalesId El ID del video digital que se busca
     * @return {@link VideoDigitalDetailDTO} - El video digital encontrado en el usuario.
     * @throws co.edu.uniandes.csw.videosDigitalestore.exceptions.BusinessLogicException
     * si el video digital no está asociado al usuario
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el video digital.
     */
    @GET
    @Path("{videodigitaldd: \\d+}")
    public VideoDigitalDetailDTO getVideoDigital(@PathParam("usuariosId") Long usuariosId, @PathParam("videodigitalid") Long videodigitalid) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "UsuarioVideoDigitalResource getVideoDigital: input: usuariosId {0} , videodigitalid {1}", new Object[]{usuariosId, videodigitalid});
        if (videoDigitalLogic.getVideoDigital(videodigitalid) == null) {
            throw new WebApplicationException("El recurso /videoDigital/" + videodigitalid + " no existe.", 404);
        }
        VideoDigitalDetailDTO detailDTO = new VideoDigitalDetailDTO(usuarioVideoDigitalLogic.getVideoDigital(usuariosId, videodigitalid));
        LOGGER.log(Level.INFO, "UsuarioVideoDigitalResource getVideoDigital: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la lista de video digitals de un usuario con la lista que se recibe en el
     * cuerpo
     *
     * @param usuariosId El ID del usuario al cual se le va a asociar el video digital
     * @param videosDigitales JSONArray {@link VideoDigitalDetailDTO} - La lista de video digitals que se
     * desea guardar.
     * @return JSONArray {@link VideoDigitalDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el video digital.
     */
    @PUT
    public List<VideoDigitalDetailDTO> replaceVideosDigitales(@PathParam("usuariosId") Long usuariosId, List<VideoDigitalDetailDTO> videosDigitales) {
        LOGGER.log(Level.INFO, "UsuarioVideoDigitalResource replaceVideosDigitales: input: usuariosId {0} , videosDigitales {1}", new Object[]{usuariosId, videosDigitales.toString()});
        for (VideoDigitalDetailDTO videoDigital : videosDigitales) {
            if (videoDigitalLogic.getVideoDigital(videoDigital.getId()) == null) {
                throw new WebApplicationException("El recurso /videoDigital/" + videoDigital.getId() + " no existe.", 404);
            }
        }
        List<VideoDigitalDetailDTO> lista = videosDigitalesListEntity2DTO(usuarioVideoDigitalLogic.replaceVideosDigitales(usuariosId, videosDigitalesListDTO2Entity(videosDigitales)));
        LOGGER.log(Level.INFO, "UsuarioVideoDigitalResource replaceVideosDigitales: output: {0}", lista.toString());
        return lista;
    }

    /**
     * Elimina la conexión entre el video digital y e usuario recibidos en la URL.
     *
     * @param usuariosId El ID del usuario al cual se le va a desasociar el video digital
     * @param videosDigitalesId El ID del video digital que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el video digital.
     */
    @DELETE
    @Path("{videosDigitalesId: \\d+}")
    public void removeVideoDigital(@PathParam("usuariosId") Long usuariosId, @PathParam("videodigitalid") Long videodigitalid) {
        LOGGER.log(Level.INFO, "UsuarioVideoDigitalResource deleteVideoDigital: input: usuariosId {0} , videodigitalid {1}", new Object[]{usuariosId, videodigitalid});
        if (videoDigitalLogic.getVideoDigital(videodigitalid) == null) {
            throw new WebApplicationException("El recurso /videoDigital/" + videodigitalid + " no existe.", 404);
        }
        usuarioVideoDigitalLogic.removeVideoDigital(usuariosId, videodigitalid);
        LOGGER.info("UsuarioVideoDigitalResource deleteVideoDigital: output: void");
    }

    /**
     * Convierte una lista de VideoDigitalEntity a una lista de VideoDigitalDetailDTO.
     *
     * @param entityList Lista de VideoDigitalEntity a convertir.
     * @return Lista de VideoDigitalDetailDTO convertida.
     */
    private List<VideoDigitalDetailDTO> videosDigitalesListEntity2DTO(List<VideoDigitalEntity> entityList) {
        List<VideoDigitalDetailDTO> list = new ArrayList<>();
        for (VideoDigitalEntity entity : entityList) {
            list.add(new VideoDigitalDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de VideoDigitalDetailDTO a una lista de VideoDigitalEntity.
     *
     * @param dtos Lista de VideoDigitalDetailDTO a convertir.
     * @return Lista de VideoDigitalEntity convertida.
     */
    private List<VideoDigitalEntity> videosDigitalesListDTO2Entity(List<VideoDigitalDetailDTO> dtos) {
        List<VideoDigitalEntity> list = new ArrayList<>();
        for (VideoDigitalDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

}
