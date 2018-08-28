/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import java.util.logging.Level;
import javax.ws.rs.*;
import co.edu.uniandes.csw.biblioteca.dtos.BibliotecaDTO;
import javax.enterprise.context.RequestScoped;

/**
 * Clase que implementa el recurso "bibliotecas".
 *
 * @author David Saavedra
 * @version 1.0
 */
@Path("bibliotecas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class BibliotecaResource {
    
     @POST
    public BibliotecaDTO createBiblioteca(BibliotecaDTO biblioteca) 
    {
       return biblioteca;
    }
    
    @GET
    @Path("{bibliotecasId: \\d+}")
    public BibliotecaDTO getBiblioteca(@PathParam("bibliotecasId") Long librosId)
    {
       return null; 
    }
}
