/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import co.edu.uniandes.csw.bibilioteca.entities.UsuarioEntity;
import co.edu.uniandes.csw.biblioteca.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.biblioteca.ejb.UsuarioLogic;
import co.edu.uniandes.csw.biblioteca.ejb.VideoDigitalUsuarioLogic;
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
 * Clase que implementa el recurso "videosdigitales/{id}/usuarios".
 *
 * @author Juan Bautista
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VideoDigitalUsuarioResource {

    private static final Logger LOGGER = Logger.getLogger(VideoDigitalUsuarioResource.class.getName());
    private static final String RUTA = "El recurso /usuarios/";
    private static final String NO_EXISTE = " no existe.";

    @Inject
    private VideoDigitalUsuarioLogic videoDigitalUsuarioLogic;

    @Inject
    private UsuarioLogic usuarioLogic;

    /**
     * Asocia un usuario existente con un videoDigital existente
     *
     * @param usuariosId El ID del usuario que se va a asociar
     * @param videoDigitalid El ID del videoDigital al cual se le va a asociar
     * el usuario
     * @return JSON {@link UsuarioDetailDTO} - El usuario asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el usuario.
     */
    @POST
    @Path("{usuariosId: \\d+}")
    public UsuarioDetailDTO addUsuario(@PathParam("videosdigitalesid") Long videodigitalid, @PathParam("usuariosId") Long usuariosId) {
        LOGGER.log(Level.INFO, "VideoDigitalUsuarioResource addUsuario: input: videodigitalid {0} , usuariosId {1}", new Object[]{videodigitalid, usuariosId});
        if (usuarioLogic.getUsuario(usuariosId) == null) {
            throw new WebApplicationException(RUTA + usuariosId + NO_EXISTE, 404);
        }
        UsuarioDetailDTO detailDTO = new UsuarioDetailDTO(videoDigitalUsuarioLogic.addUsuario(videodigitalid, usuariosId));
        LOGGER.log(Level.INFO, "VideoDigitalUsuarioResource addUsuario: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Busca y devuelve todos los usuarios que existen en un videoDigital.
     *
     * @param videodigitalid El ID del videoDigital del cual se buscan los
     * usuarioes
     * @return JSONArray {@link UsuarioDetailDTO} - Los usuarioes encontrados en
     * el videoDigital. Si no hay ninguno retorna una lista vacía.
     */
    @GET

    public List<UsuarioDetailDTO> getUsuarios(@PathParam("videosdigitalesid") Long videodigitalid) {
        LOGGER.log(Level.INFO, "VideoDigitalUsuarioResource getUsuarios: input: {0}", videodigitalid);
        List<UsuarioDetailDTO> lista = usuariosListEntity2DTO(videoDigitalUsuarioLogic.getUsuarios(videodigitalid));
        LOGGER.log(Level.INFO, "VideoDigitalUsuarioResource getUsuarios: output: {0}", lista);
        return lista;
    }

    /**
     * Busca y devuelve el usuario con el ID recibido en la URL, relativo a un
     * videoDigital.
     *
     * @param usuariosId El ID del usuario que se busca
     * @param videodigitalid El ID del videoDigital del cual se busca el usuario
     * @return {@link UsuarioDetailDTO} - El usuario encontrado en el
     * videoDigital.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el usuario.
     */
    @GET
    @Path("{usuariosId: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("videosdigitalesid") Long videodigitalid, @PathParam("usuariosId") Long usuariosId) {
        LOGGER.log(Level.INFO, "VideoDigitalUsuarioResource getUsuario: input: videosdigitalesid {0} , usuariosId {1}", new Object[]{videodigitalid, usuariosId});
        if (usuarioLogic.getUsuario(usuariosId) == null) {
            throw new WebApplicationException(RUTA + usuariosId + NO_EXISTE, 404);
        }
        UsuarioDetailDTO detailDTO = new UsuarioDetailDTO(videoDigitalUsuarioLogic.getUsuario(videodigitalid, usuariosId));
        LOGGER.log(Level.INFO, "VideoDigitalUsuarioResource getUsuario: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la lista de usuarios de un videoDigital con la lista que se
     * recibe en el cuerpo.
     *
     * @param videodigitalid El ID del videoDigital al cual se le va a asociar
     * la lista de usuarioes
     * @param usuarios JSONArray {@link UsuarioDetailDTO} - La lista de
     * usuarioes que se desea guardar.
     * @return JSONArray {@link UsuarioDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el usuario.
     */
    @PUT
    public List<UsuarioDetailDTO> replaceUsuarios(@PathParam("videosdigitalesid") Long videodigitalid, List<UsuarioDetailDTO> usuarios) {
        LOGGER.log(Level.INFO, "VideoDigitalUsuarioResource replaceUsuarios: input: videodigitalid {0} , usuarios {1}", new Object[]{videodigitalid, usuarios});
        for (UsuarioDetailDTO usuario : usuarios) {
            if (usuarioLogic.getUsuario(usuario.getId()) == null) {
                throw new WebApplicationException(RUTA + usuario.getId() + NO_EXISTE, 404);
            }
        }
        List<UsuarioDetailDTO> lista = usuariosListEntity2DTO(videoDigitalUsuarioLogic.replaceUsuarios(videodigitalid, usuariosListDTO2Entity(usuarios)));
        LOGGER.log(Level.INFO, "VideoDigitalUsuarioResource replaceUsuarios: output:{0}", lista);
        return lista;
    }

    /**
     * Elimina la conexión entre el usuario y el videoDigital recibidos en la
     * URL.
     *
     * @param videodigitalid El ID del videoDigital al cual se le va a
     * desasociar el usuario
     * @param usuariosId El ID del usuario que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper}
     * Error de lógica que se genera cuando no se encuentra el usuario.
     */
    @DELETE
    @Path("{usuariosId: \\d+}")
    public void removeUsuario(@PathParam("videosdigitalesid") Long videodigitalid, @PathParam("usuariosId") Long usuariosId) {
        LOGGER.log(Level.INFO, "VideoDigitalUsuarioResource removeUsuario: input: videodigitalid {0} , usuariosId {1}", new Object[]{videodigitalid, usuariosId});
        if (usuarioLogic.getUsuario(usuariosId) == null) {
            throw new WebApplicationException(RUTA + usuariosId + NO_EXISTE, 404);
        }
        videoDigitalUsuarioLogic.removeUsuario(videodigitalid, usuariosId);
        LOGGER.info("VideoDigitalUsuarioResource removeUsuario: output: void");
    }

    /**
     * Convierte una lista de UsuarioEntity a una lista de UsuarioDetailDTO.
     *
     * @param entityList Lista de UsuarioEntity a convertir.
     * @return Lista de UsuarioDetailDTO convertida.
     */
    private List<UsuarioDetailDTO> usuariosListEntity2DTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de UsuarioDetailDTO a una lista de UsuarioEntity.
     *
     * @param dtos Lista de UsuarioDetailDTO a convertir.
     * @return Lista de UsuarioEntity convertida.
     */
    private List<UsuarioEntity> usuariosListDTO2Entity(List<UsuarioDetailDTO> dtos) {
        List<UsuarioEntity> list = new ArrayList<>();
        for (UsuarioDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

}
