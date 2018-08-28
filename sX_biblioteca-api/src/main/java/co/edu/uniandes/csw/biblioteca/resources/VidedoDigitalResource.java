/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import co.edu.uniandes.csw.biblioteca.dtos.VideoDigitalDTO;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Juan Bautista
 */ 
    @Path("videosdigitales")
    @Produces("application/json")
    @Consumes("application/json")
    @RequestScoped
public class VidedoDigitalResource {
    public VideoDigitalDTO createVideoDigital(VideoDigitalDTO pVideoDigital) throws Exception{
        return pVideoDigital;
    }
    @GET
    @Path("{videosdigitales: \\d+}")
    public VideoDigitalDTO getVideoDigital(@PathParam("videosdigitalesId") Long videosdigitalesId)
    {
       return null; 
    }
}
