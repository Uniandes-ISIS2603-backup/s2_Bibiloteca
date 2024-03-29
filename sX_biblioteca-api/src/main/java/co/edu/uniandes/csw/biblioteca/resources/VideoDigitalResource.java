
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import co.edu.uniandes.csw.bibilioteca.entities.VideoDigitalEntity;
import co.edu.uniandes.csw.biblioteca.dtos.VideoDigitalDTO;
import co.edu.uniandes.csw.biblioteca.dtos.VideoDigitalDetailDTO;
import co.edu.uniandes.csw.biblioteca.ejb.VideoDigitalLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.*;
import javax.enterprise.context.RequestScoped;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Juan Bautista
 */
@Path("videosdigitales")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class VideoDigitalResource {

    private static final Logger LOGGER = Logger.getLogger(VideoDigitalResource.class.getName());
    private static final String RUTA = "El recurso /videoDigital/";
    private static final String NO_EXISTE = " no existe.";
    @Inject
    private VideoDigitalLogic videoDigitalLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @POST
    public VideoDigitalDTO createVideoDigital(VideoDigitalDTO pVideoDigital) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "VideoDigitalResource createVideoDigital: input: {0}", pVideoDigital);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        VideoDigitalEntity videoDigitalEntity = pVideoDigital.toEntity();
        // Invoca la lógica para crear la videoDigital nueva
        VideoDigitalEntity nuevoVideoDigitalEntity = videoDigitalLogic.createVideoDigital(videoDigitalEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        VideoDigitalDTO nuevoVideoDigitalDTO = new VideoDigitalDTO(nuevoVideoDigitalEntity);
        LOGGER.log(Level.INFO, "VideoDigitalResource createVideoDigital: output: {0}", nuevoVideoDigitalDTO);
        return nuevoVideoDigitalDTO;
    }

    @GET
    @Path("{videosdigitalesid: \\d+}")
    public VideoDigitalDetailDTO getVideoDigital(@PathParam("videosdigitalesid") Long videodigitalid) {
        LOGGER.log(Level.INFO, "VideoDigitalResource getVideoDigital: input: {0}", videodigitalid);
        VideoDigitalEntity videoDigitalEntity = videoDigitalLogic.getVideoDigital(videodigitalid);
        if (videoDigitalEntity == null) {
            throw new WebApplicationException(RUTA + videodigitalid + NO_EXISTE, 404);
        }
        VideoDigitalDetailDTO detailDTO = new VideoDigitalDetailDTO(videoDigitalEntity);
        LOGGER.log(Level.INFO, "VideoDigitalResource getVideoDigital: output: {0}", detailDTO);
        return detailDTO;
    }

    @GET
    public List<VideoDigitalDetailDTO> getVideosDigitales() {
        LOGGER.info("VideoDigitalResource getVideosDigitales: input: void");
        List<VideoDigitalDetailDTO> listaVideosDigitales = listEntity2DetailDTO(videoDigitalLogic.getVideosDigitales());
        LOGGER.log(Level.INFO, "VideoDigitalResource getVideosDigtales: output: {0}", listaVideosDigitales);
        return listaVideosDigitales;
    }

    @PUT
    @Path("{videosdigitalesid: \\d+}")
    public VideoDigitalDTO actualizarVideoDigital(@PathParam("videosdigitalesid") Long videodigitalid, VideoDigitalDTO pVideoDigital) {
        LOGGER.log(Level.INFO, "VideoDigitalResource updateVideoDigital: input: id:{0} , pVideoDigital: {1}", new Object[]{videodigitalid, pVideoDigital});
        pVideoDigital.setId(videodigitalid);
        if (videoDigitalLogic.getVideoDigital(videodigitalid) == null) {
            throw new WebApplicationException(RUTA + videodigitalid + NO_EXISTE, 404);
        }
        VideoDigitalDetailDTO detailDTO = new VideoDigitalDetailDTO(videoDigitalLogic.updateVideoDigital(videodigitalid, pVideoDigital.toEntity()));
        LOGGER.log(Level.INFO, "VideoDigitalResource updateVideoDigital: output: {0}", detailDTO);
        return detailDTO;
    }

    @DELETE
    @Path("{videosdigitalesid: \\d+}")
    public void eliminarVideoDigital(@PathParam("videosdigitalesid") Long videodigitalid) {
        LOGGER.log(Level.INFO, "VideoDigitalResource deleteVideoDigital: input: {0}", videodigitalid);
        if (videoDigitalLogic.getVideoDigital(videodigitalid) == null) {
            throw new WebApplicationException(RUTA + videodigitalid + NO_EXISTE, 404);
        }
        videoDigitalLogic.deleteVideoDigital(videodigitalid);
        LOGGER.info("VideoDigitalResource deleteVideoDigital: output: void");
    }

    /**
     * Conexión con el servicio de usuarios para un video digital.
     * {@link VideoDigitalUsuarioResource}
     *
     * Este método conecta la ruta de /videoDigital con las rutas de /usuarios
     * que dependen del videoDigital, es una redirección al servicio que maneja
     * el segmento
     *
     * @param videodigitalid El ID del video digital con respecto al cual se
     * accede al servicio.
     * @return El servicio de usuarios para ese video digital en paricular.\
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el video digital.
     */
    @Path("{videosdigitalesid: \\d+}/usuarios")
    public Class<VideoDigitalUsuarioResource> getVideoDigitalUsuarioResource(@PathParam("videosdigitalesid") Long videodigitalid) {
        if (videoDigitalLogic.getVideoDigital(videodigitalid) == null) {
            throw new WebApplicationException(RUTA + videodigitalid + NO_EXISTE, 404);
        }
        return VideoDigitalUsuarioResource.class;
    }

    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EditorialEntity a una lista de
     * objetos EditorialDetailDTO (json)
     *
     * @param entityList corresponde a la lista de editoriales de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de editoriales en forma DTO (json)
     */
    private List<VideoDigitalDetailDTO> listEntity2DetailDTO(List<VideoDigitalEntity> entityList) {
        List<VideoDigitalDetailDTO> list = new ArrayList<>();
        for (VideoDigitalEntity entity : entityList) {
            list.add(new VideoDigitalDetailDTO(entity));
        }
        return list;
    }

}
