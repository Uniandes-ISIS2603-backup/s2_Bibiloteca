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
import javax.ws.rs.core.MediaType;

@Path("videos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class VideoResource {
     
    @Inject
    private VideoLogic videoLogic;
     
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public VideoDTO createVideo(VideoDTO video) throws BusinessLogicException{
        
        VideoDTO vdto = new VideoDTO(videoLogic.createVideo(video.toEntity()));
        return vdto;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public ArrayList<VideoDTO> getVideos(){
        ArrayList<VideoDTO> listVideo = listEntity2DetailDTO(videoLogic.getVideos());
        return listVideo;
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
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
    
    @GET
    @Path("{videoId: \\d+}/prestamos")
    public Class<PrestamoResource> getPrestamos(@PathParam("videosId") Long videosId){
        if(videoLogic.getVideo(videosId) == null){
            throw new WebApplicationException("El recurso no existe",404);
        }
        return PrestamoResource.class;
    }
    
    @GET
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
