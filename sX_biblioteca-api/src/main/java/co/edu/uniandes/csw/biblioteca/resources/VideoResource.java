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
     
    private ArrayList<VideoDTO> listEntity2DetailDTO(List<VideoEntity> entityList) {
        ArrayList<VideoDTO> list = new ArrayList<>();
        for (VideoEntity entity : entityList) {
            list.add(new VideoDTO(entity));
        }
        return list;
    }
}
