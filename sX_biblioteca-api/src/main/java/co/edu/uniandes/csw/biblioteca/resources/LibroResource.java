/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import co.edu.uniandes.csw.biblioteca.adapters.LibroDTO;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Daniel Montoya
 */
@Path("libros")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class LibroResource {
    
    public LibroDTO createLibro(LibroDTO libro) throws Exception{
        return libro;
    }
    @GET
    @Path("{librosId: \\d+}")
    public LibroDTO getLibro(@PathParam("librosId") Long librosId)
    {
       return null; 
    }
}
