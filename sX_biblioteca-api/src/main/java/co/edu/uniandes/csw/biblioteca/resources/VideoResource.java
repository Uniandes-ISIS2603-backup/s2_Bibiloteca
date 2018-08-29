package co.edu.uniandes.csw.biblioteca.resources;

/**
 *
 * @author Nicolas Alvarado
 */

 import co.edu.uniandes.csw.biblioteca.dtos.VideoDTO;
 import javax.enterprise.context.RequestScoped;
 import javax.inject.Inject;
 import javax.ws.rs.*;

 @Path("videos")
 @Produces("application/json")
 @Consumes("application/json")
 @RequestScoped

 public class VideoResource {
   @POST
   public VideoDTO createVideo(VideoDTO video){
     return null;
   }

   @GET
   @Path("{videosId: \\d+}")
   public VideoDTO getVideo(@PathParam("videosId") Long videosId){
     return null;
   }
 }
