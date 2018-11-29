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

@Path("videos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class VideoResource {

    private static final String NO_EXISTE = "El video no existe ";
    private static final String NOPRESTAMO = "El prestamo no existe";
    private static final String NORESERVA = "La reserva no existe";
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
    public VideoDTO createVideo(VideoDTO video) throws BusinessLogicException {
        return new VideoDTO(videoLogic.createVideo(video.toEntity()));

    }

    @GET
    //@Produces({MediaType.APPLICATION_JSON})
    public List<VideoDetailDTO> getVideos() {
        return listVEntity2DetailDTO(videoLogic.getVideos());

    }

    @GET
    //@Produces({MediaType.APPLICATION_JSON})
    @Path("{videosId: \\d+}")
    public VideoDetailDTO getVideo(@PathParam("videosId") Long videosId) throws BusinessLogicException {
        VideoEntity ve = videoLogic.getVideo(videosId);
        if (ve == null) {
            throw new WebApplicationException(NO_EXISTE, 404);
        }
        return new VideoDetailDTO(ve);

    }

    @PUT
    @Path("{videosid: \\d+}")
    public VideoDetailDTO updateVideo(@PathParam("videosid") Long videoId, VideoDTO pvd) throws BusinessLogicException {
        pvd.setId(videoId);
        if (videoLogic.getVideo(videoId) == null) {
            throw new WebApplicationException(NO_EXISTE, 404);
        }
        return new VideoDetailDTO(videoLogic.updateVideo(pvd.toEntity(), videoId));

    }

    @DELETE
    @Path("{videosId: \\d+}")
    public void deleteVideo(@PathParam("videosId") Long videosId) throws BusinessLogicException {
        VideoEntity ve = videoLogic.getVideo(videosId);
        if (ve == null) {
            throw new WebApplicationException(NO_EXISTE, 404);
        }
        videoLogic.deleteVideo(videosId);
    }

    @POST
    @Path("{videosId: \\d+}/prestamos/{prestamoId: \\d+}")
    public PrestamoDTO addPrestamo(@PathParam("videosId") Long pVideoId, @PathParam("prestamoId") Long pPrestamoId) {
        PrestamoEntity pe = prestamoLogic.getPrestamo(pPrestamoId);
        if (pe == null) {
            throw new WebApplicationException(NOPRESTAMO, 404);
        }
        return new PrestamoDTO(videoPrestamoL.addPrestamo(pVideoId, pPrestamoId));

    }

    @POST
    @Path("{videosId: \\d+}/reservas/{reservaId: \\d+}")
    public ReservaDTO addReserva(@PathParam("videosId") Long pVideoId, @PathParam("reservaId") Long pReservaId) {
        ReservaEntity re = reservaLogic.getReserva(pReservaId);
        if (re == null) {
            throw new WebApplicationException(NORESERVA, 404);
        }
        return new ReservaDTO(videoReservaL.addReserva(pVideoId, pReservaId));

    }

    @GET
    @Path("{videoId: \\d+}/prestamos")
    public List<PrestamoDTO> getPrestamos(@PathParam("videoId") Long videosId) {
        List<PrestamoDTO> listP = listPEntity2DetailDTO(videoPrestamoL.getPrestamos(videosId));
        if (listP == null || listP.isEmpty()) {
            throw new WebApplicationException(NO_EXISTE, 404);
        }
        return listP;
    }

    @GET
    @Path("{videosId: \\d+}/prestamos/{prestamoId: \\d+}")
    public PrestamoDTO getPrestamo(@PathParam("videosId") Long pVideoId, @PathParam("prestamoId") Long pPrestamoId) {
        PrestamoEntity pe = prestamoLogic.getPrestamo(pPrestamoId);
        if (pe == null) {
            throw new WebApplicationException(NOPRESTAMO, 404);
        }
        return new PrestamoDTO(videoPrestamoL.getPrestamo(pVideoId, pPrestamoId));

    }

    @GET
    @Path("{videoId: \\d+}/reservas")
    public List<ReservaDetailDTO> getReservas(@PathParam("videoId") Long videosId) {
        List<ReservaDetailDTO> listR = listREntity2DetailDTO(videoReservaL.getReservas(videosId));
        if (listR == null || listR.isEmpty()) {
            throw new WebApplicationException(NO_EXISTE, 404);
        }
        return listR;
    }

    @GET
    @Path("{videosId: \\d+}/reservas/{reservaId: \\d+}")
    public ReservaDetailDTO getReserva(@PathParam("videosId") Long pVideoId, @PathParam("reservaId") Long pReservaId) {
        ReservaEntity re = reservaLogic.getReserva(pReservaId);
        if (re == null) {
            throw new WebApplicationException(NORESERVA, 404);
        }
        return new ReservaDetailDTO(videoReservaL.getReserva(pVideoId, pReservaId));

    }

    @DELETE
    @Path("{videosId: \\d+}/prestamos/{prestamoId: \\d+}")
    public void deletePrestamo(@PathParam("videosId") Long pVideoId, @PathParam("prestamoId") Long pPrestamoId) {
        PrestamoEntity pe = prestamoLogic.getPrestamo(pPrestamoId);
        if (pe == null) {
            throw new WebApplicationException(NOPRESTAMO, 404);
        }
        videoPrestamoL.removePrestamo(pVideoId, pPrestamoId);
    }

    @DELETE
    @Path("{videosId: \\d+}/reservas/{reservaId: \\d+}")
    public void deleteReserva(@PathParam("videosId") Long pVideoId, @PathParam("reservaId") Long pReservaId) {
        ReservaEntity re = reservaLogic.getReserva(pReservaId);
        if (re == null) {
            throw new WebApplicationException(NORESERVA, 404);
        }
        videoReservaL.removeReserva(pVideoId, pReservaId);
    }

    private ArrayList<VideoDetailDTO> listVEntity2DetailDTO(List<VideoEntity> entityList) {
        ArrayList<VideoDetailDTO> list = new ArrayList<>();
        entityList.forEach((entity) -> {
            list.add(new VideoDetailDTO(entity));
        });
        return list;
    }

    private ArrayList<PrestamoDTO> listPEntity2DetailDTO(List<PrestamoEntity> entityList) {
        ArrayList<PrestamoDTO> list = new ArrayList<>();
        entityList.forEach((entity) -> {
            list.add(new PrestamoDTO(entity));
        });
        return list;
    }

    private ArrayList<ReservaDetailDTO> listREntity2DetailDTO(List<ReservaEntity> entityList) {
        ArrayList<ReservaDetailDTO> list = new ArrayList<>();
        entityList.forEach((entity) -> {
            list.add(new ReservaDetailDTO(entity));
        });
        return list;
    }
}