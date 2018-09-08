/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;


import java.util.logging.Level;
import javax.ws.rs.*;
import co.edu.uniandes.csw.biblioteca.dtos.PrestamoDTO;
import java.util.Collection;
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
    public PrestamoDTO createPrestamo(PrestamoDTO prestamo) 
    {
       return null;
    }
    
    @GET
    @Path("{prestamoId: \\d+}")
    public PrestamoDTO getPrestamo(@PathParam("bibliotecasId") Long librosId)
    {
       return null; 
    }
    
     @GET
    public Collection<PrestamoDTO> getPrestamos()
    {
       return null; 
    }
    
    @PUT
     @Path("{prestamosId: \\d+}")
    public PrestamoDTO actualizarPrestamo(@PathParam("prestamosId") Long bibliotecasId, PrestamoDTO biblioteca)
    {
       return biblioteca; 
    }
    
    @DELETE
     @Path("{prestamosId: \\d+}")
    public void eliminarPrestamo(@PathParam("prestamosId") Long bibliotecasId)
    {
       
    }
}
