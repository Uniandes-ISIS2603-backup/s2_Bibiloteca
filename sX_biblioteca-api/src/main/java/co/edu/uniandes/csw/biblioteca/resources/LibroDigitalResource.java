/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import co.edu.uniandes.csw.biblioteca.dtos.LibroDigitalDTO;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.*;
import javax.enterprise.context.RequestScoped;
import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Juan Bautista
 */ 
    @Path("librosdigitales")
    @Produces("application/json")
    @Consumes("application/json")
    @RequestScoped
public class LibroDigitalResource {
    
    @POST
    public LibroDigitalDTO createLibroDigital(LibroDigitalDTO pLibroDigital) 
    {
        return pLibroDigital;
    }
    @GET
    @Path("{librosdigitalesid: \\d+}")
    public LibroDigitalDTO getLibroDigital(@PathParam("librosdigitalesid") Long librosdigitalesId)
    {
       return null; 
    }
    @GET
    public Collection<LibroDigitalDTO> getLibrosDigitales()
    {
       return null; 
    }
    
    @PUT
     @Path("{librosdigitalesid: \\d+}")
    public LibroDigitalDTO actualizarLibroDigital(@PathParam("librosdigitalesid") Long pLibrosdigitalesid, LibroDigitalDTO pLibroDigital)
    {
       return pLibroDigital; 
    }
    
    @DELETE
     @Path("{librosdigitalesid: \\d+}")
    public void eliminarLibroDigital(@PathParam("librosdigitalesid") Long librosdigitalesid)
    {
       
    }
    
}