package co.edu.uniandes.csw.biblioteca.resources;

/**
 *
 * @author Nicolas Alvarado
 */

import co.edu.uniandes.csw.bibilioteca.entities.PrestamoEntity;
import co.edu.uniandes.csw.bibilioteca.entities.ReservaEntity;
import co.edu.uniandes.csw.bibilioteca.entities.VideoEntity;
import co.edu.uniandes.csw.biblioteca.dtos.PrestamoDTO;
import co.edu.uniandes.csw.biblioteca.dtos.ReservaDTO;
import co.edu.uniandes.csw.biblioteca.dtos.ReservaDetailDTO;
import co.edu.uniandes.csw.biblioteca.dtos.VideoDTO;
import co.edu.uniandes.csw.biblioteca.dtos.VideoDetailDTO;
import co.edu.uniandes.csw.biblioteca.ejb.PrestamoLogic;
import co.edu.uniandes.csw.biblioteca.ejb.ReservaLogic;
import co.edu.uniandes.csw.biblioteca.ejb.VideoLogic;
import co.edu.uniandes.csw.biblioteca.ejb.VideoPrestamoLogic;
import co.edu.uniandes.csw.biblioteca.ejb.VideoReservaLogic;
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
    @Inject
    private PrestamoLogic prestamoLogic;
    @Inject
    private VideoPrestamoLogic videoPrestamoL;
    @Inject
    private ReservaLogic reservaLogic;
    @Inject
    private VideoReservaLogic videoReservaL;
     
    @POST
    //@Consumes({MediaType.APPLICATION_JSON})
    public VideoDTO createVideo(VideoDTO video) throws BusinessLogicException{
        VideoDTO vdto = new VideoDTO(videoLogic.createVideo(video.toEntity()));
        return vdto;
    }

    @GET
    //@Produces({MediaType.APPLICATION_JSON})
    public ArrayList<VideoDetailDTO> getVideos(){
        ArrayList<VideoDetailDTO> listVideo = listVEntity2DetailDTO(videoLogic.getVideos());
        return listVideo;
    }
    
    @GET
    //@Produces({MediaType.APPLICATION_JSON})
    @Path("{videosId: \\d+}")
    public VideoDetailDTO getVideo(@PathParam("videosId") Long videosId) throws BusinessLogicException{
        VideoEntity ve = videoLogic.getVideo(videosId);
        if(ve == null){
            throw new WebApplicationException("El video no existe ",404);
        }
        VideoDetailDTO vd = new VideoDetailDTO(ve);
        return vd;
    }
    
    @PUT
    @Path("{videosid: \\d+}")
    public VideoDetailDTO updateVideo(@PathParam("videosid") Long videoId, VideoDTO pvd) throws BusinessLogicException{
        pvd.setId(videoId);
        if(videoLogic.getVideo(videoId) == null){
            throw new WebApplicationException("El video no existe",404);
        }
        VideoDetailDTO vd = new VideoDetailDTO(videoLogic.updateVideo(pvd.toEntity(),videoId));
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
    
    @POST
    @Path("{videosId: \\d+}/prestamos/{prestamoId: \\d+}")
    public PrestamoDTO addPrestamo(@PathParam("videosId") Long pVideoId, @PathParam("prestamoId") Long pPrestamoId){
        PrestamoEntity pe = prestamoLogic.getPrestamo(pPrestamoId);
        if(pe == null){
            throw new WebApplicationException("El prestamo no existe",404);
        }
        PrestamoDTO pdto = new PrestamoDTO(videoPrestamoL.addPrestamo(pVideoId, pPrestamoId));
        return pdto;
    }
    
    @POST
    @Path("{videosId: \\d+}/reservas/{reservaId: \\d+}")
    public ReservaDTO addReserva(@PathParam("videosId") Long pVideoId, @PathParam("reservaId") Long pReservaId){
        ReservaEntity re = reservaLogic.getReserva(pReservaId);
        if(re == null){
            throw new WebApplicationException("La reserva no existe",404);
        }
        ReservaDTO rdto = new ReservaDTO(videoReservaL.addReserva(pVideoId, pReservaId));
        return rdto;
    }
    
    @GET
    @Path("{videoId: \\d+}/prestamos")
    public List<PrestamoDTO> getPrestamos(@PathParam("videoId") Long videosId){
        List<PrestamoDTO> listP = listPEntity2DetailDTO(videoPrestamoL.getPrestamos(videosId));
        if(listP == null){
            throw new WebApplicationException("El video no existe",404);
        }
        return listP;
    }
    
    @GET
    @Path("{videosId: \\d+}/prestamos/{prestamoId: \\d+}")
    public PrestamoDTO getPrestamo(@PathParam("videosId") Long pVideoId, @PathParam("prestamoId") Long pPrestamoId){
        PrestamoEntity pe = prestamoLogic.getPrestamo(pPrestamoId);
        if(pe == null){
            throw new WebApplicationException("El prestamo no existe",404);
        }
        PrestamoDTO pdto = new PrestamoDTO(videoPrestamoL.getPrestamo(pVideoId, pPrestamoId));
        return pdto;
    }
    
    @GET
    @Path("{videoId: \\d+}/reservas")
    public List<ReservaDetailDTO> getReservas(@PathParam("videoId") Long videosId){
        List<ReservaDetailDTO> listR = listREntity2DetailDTO(videoReservaL.getReservas(videosId));
        if(listR == null){
            throw new WebApplicationException("El video no existe",404);
        }
        return listR;
    }
    
    @GET
    @Path("{videosId: \\d+}/reservas/{reservaId: \\d+}")
    public ReservaDetailDTO getReserva(@PathParam("videosId") Long pVideoId, @PathParam("reservaId") Long pReservaId){
        ReservaEntity re = reservaLogic.getReserva(pReservaId);
        if(re == null){
            throw new WebApplicationException("La reserva no existe",404);
        }
        ReservaDetailDTO rdto = new ReservaDetailDTO(videoReservaL.getReserva(pVideoId, pReservaId));
        return rdto;
    }
    
    @DELETE
    @Path("{videosId: \\d+}/prestamos/{prestamoId: \\d+}")
    public void deletePrestamo(@PathParam("videosId") Long pVideoId, @PathParam("prestamoId") Long pPrestamoId){
        PrestamoEntity pe = prestamoLogic.getPrestamo(pPrestamoId);
        if(pe == null){
            throw new WebApplicationException("El prestamo no existe",404);
        }
        videoPrestamoL.removePrestamo(pVideoId, pPrestamoId);
    }
    
    @DELETE
    @Path("{videosId: \\d+}/reservas/{reservaId: \\d+}")
    public void deleteReserva(@PathParam("videosId") Long pVideoId, @PathParam("reservaId") Long pReservaId){
        ReservaEntity re = reservaLogic.getReserva(pReservaId);
        if(re == null){
            throw new WebApplicationException("La reserva no existe",404);
        }
        videoReservaL.removeReserva(pVideoId, pReservaId);
    }
    
    private ArrayList<VideoDTO> listVEntity2DTO(List<VideoEntity> entityList) {
        ArrayList<VideoDTO> list = new ArrayList<>();
        for (VideoEntity entity : entityList) {
            list.add(new VideoDTO(entity));
        }
        return list;
    }
    
        private ArrayList<VideoDetailDTO> listVEntity2DetailDTO(List<VideoEntity> entityList) {
        ArrayList<VideoDetailDTO> list = new ArrayList<>();
        for (VideoEntity entity : entityList) {
            list.add(new VideoDetailDTO(entity));
        }
        return list;
    }
    
    private ArrayList<PrestamoDTO> listPEntity2DetailDTO(List<PrestamoEntity> entityList) {
        ArrayList<PrestamoDTO> list = new ArrayList<>();
        for (PrestamoEntity entity : entityList) {
            list.add(new PrestamoDTO(entity));
        }
        return list;
    }
        
    private ArrayList<ReservaDetailDTO> listREntity2DetailDTO(List<ReservaEntity> entityList) {
        ArrayList<ReservaDetailDTO> list = new ArrayList<>();
        for (ReservaEntity entity : entityList) {
            list.add(new ReservaDetailDTO(entity));
        }
        return list;
    }
}
