/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;


import java.util.logging.Level;
import javax.ws.rs.*;
import co.edu.uniandes.csw.biblioteca.dtos.PrestamoDTO;
import javax.enterprise.context.RequestScoped;
/**
 * Clase que implementa el recurso "prestamo".
 *
 * @author David Saavedra
 * @version 1.0
 */
@Path("prestamos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PrestamoResource 
{
     @POST
    public PrestamoDTO createBiblioteca(PrestamoDTO prestamo) 
    {
       return prestamo;
    }
    
    @GET
    @Path("{bibliotecasId: \\d+}")
    public PrestamoDTO getBiblioteca(@PathParam("bibliotecasId") Long librosId)
    {
       return null; 
    }
}
