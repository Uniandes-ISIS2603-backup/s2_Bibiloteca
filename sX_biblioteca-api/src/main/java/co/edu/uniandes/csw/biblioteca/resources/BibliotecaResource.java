/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import java.util.logging.Level;
import javax.ws.rs.*;
import co.edu.uniandes.csw.biblioteca.dtos.BibliotecaDTO;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import java.util.Collection;
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
    public BibliotecaDTO createBiblioteca(BibliotecaDTO biblioteca) throws BusinessLogicException 
    {
       return biblioteca;
    }
    
    @GET
    @Path("{bibliotecasId: \\d+}")
    public BibliotecaDTO getBiblioteca(@PathParam("bibliotecasId") Long biliotecasId) throws BusinessLogicException 
    {
       return null; 
    }
        
    @GET
    public Collection<BibliotecaDTO> getBibliotecas() throws BusinessLogicException 
    {
       return null; 
    }
    
    @PUT
     @Path("{bibliotecasId: \\d+}")
    public BibliotecaDTO actualizarBiblioteca(@PathParam("bibliotecasId") Long bibliotecasId, BibliotecaDTO biblioteca) throws BusinessLogicException 
    {
       return biblioteca; 
    }
    
    @DELETE
     @Path("{bibliotecasId: \\d+}")
    public void eliminarBiblioteca(@PathParam("bibliotecasId") Long bibliotecasId) throws BusinessLogicException 
    {
       
    }
    
    
        

}
