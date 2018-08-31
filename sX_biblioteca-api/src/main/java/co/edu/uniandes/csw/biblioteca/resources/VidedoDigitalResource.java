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
import javax.ws.rs.*;
import javax.enterprise.context.RequestScoped;

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
public class VidedoDigitalResource {
    
    @POST
    public VideoDigitalDTO createVideoDigital(VideoDigitalDTO pVideoDigital) 
    {
        return pVideoDigital;
    }

       
    @GET
    @Path("{videosdigitalesid: \\d+}")
    public VideoDigitalDTO getVideoDigital(@PathParam("videosdigitalesid") Long videosdigitalesId)
    {
       return null; 
    }
    
    
}
    