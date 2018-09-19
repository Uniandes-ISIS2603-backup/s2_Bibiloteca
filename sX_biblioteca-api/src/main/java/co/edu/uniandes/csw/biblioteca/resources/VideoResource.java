package co.edu.uniandes.csw.biblioteca.resources;

/**
 *
 * @author Nicolas Alvarado
 */

import co.edu.uniandes.csw.bibilioteca.entities.VideoEntity;
import co.edu.uniandes.csw.biblioteca.dtos.VideoDTO;
import co.edu.uniandes.csw.biblioteca.ejb.VideoLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import java.util.*;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;

@Path("videos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class VideoResource {
     
    @Inject
    private VideoLogic videoLogic;
     
    @POST
    public VideoDTO createVideo(VideoDTO video) throws BusinessLogicException{
        VideoDTO vdto = new VideoDTO(videoLogic.createVideo(video.toEntity()));
        return vdto;
    }

    @GET
    public ArrayList<VideoDTO> getVideos(){
        ArrayList<VideoDTO> listVideo = listEntity2DetailDTO(videoLogic.getVideos());
        return listVideo;
    }
    
    @GET
    @Path("{videosId: \\d+}")
    public VideoDTO getVideo(@PathParam("videosId") Long videosId){
        VideoEntity ve = videoLogic.getVideo(videosId);
        if(ve == null){
            throw new WebApplicationException("El video no existe ",404);
        }
        VideoDTO vd = new VideoDTO(ve);
        return vd;
    }
    
    @PUT
    @Path("{videosid: \\d+}")
    public VideoDTO updateVideo(@PathParam("videoId") Long videoId, VideoDTO pvd) throws BusinessLogicException{
        pvd.setId(videoId);
        if(videoLogic.getVideo(videoId) == null){
            throw new WebApplicationException("El video no existe",404);
        }
        VideoDTO vd = new VideoDTO(videoLogic.updateVideo(pvd.toEntity()));
        return vd;
    }
    
    @DELETE
    @Path("{videosId: \\d+}")
    public void deleteVideo(@PathParam("videosId") Long videosId) throws BusinessLogicException{
        VideoEntity ve = videoLogic.getVideo(videosId);
        if(ve == null){
            throw new WebApplicationException("El video no existe",404);
        }
        videoLogic.deleteVideo(videosId);
    }
    
    @Path("{videoId: \\d+}/prestamos")
    public Class<PrestamoResource> getPrestamos(@PathParam("videosId") Long videosId){
        if(videoLogic.getVideo(videosId) == null){
            throw new WebApplicationException("El recurso no existe",404);
        }
        return PrestamoResource.class;
    }
    
    @Path("{videoId: \\d+}/reservas")
    public Class<ReservaResource> getReservas(@PathParam("videosId") Long videosId){
        if(videoLogic.getVideo(videosId) == null){
            throw new WebApplicationException("El recurso no existe",404);
        }
        return ReservaResource.class;
    }
     
    private ArrayList<VideoDTO> listEntity2DetailDTO(List<VideoEntity> entityList) {
        ArrayList<VideoDTO> list = new ArrayList<>();
        for (VideoEntity entity : entityList) {
            list.add(new VideoDTO(entity));
        }
        return list;
    }
}
