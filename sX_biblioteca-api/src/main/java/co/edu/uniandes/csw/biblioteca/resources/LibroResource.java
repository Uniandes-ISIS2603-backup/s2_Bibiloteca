/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import co.edu.uniandes.csw.biblioteca.dtos.LibroDTO;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
    
    /**
     * 
     * @param libro libro de la clase DTO
     * @return devuelve el libro creado
     * @throws Exception 
     */
   @POST
    public LibroDTO createLibro(LibroDTO libro) throws BusinessLogicException{
        return libro;
    }
    
    /**
     * 
     * @param librosId entra el id por parametro
     * @return devuelve el libro que se busco por id
     */
    @GET
    @Path("{librosId: \\d+}")
    public LibroDTO getLibro(@PathParam("librosId") Long librosId) throws BusinessLogicException
    {
       return null; 
    }
    
    @GET
    public List<LibroDTO> getLibros()throws BusinessLogicException{
        return null;
    }
    
    @PUT
    @Path("{librosId: \\d+}")
    public LibroDTO updateLibro(@PathParam("librosId") Long librosId, LibroDTO libro) throws BusinessLogicException
    {
       return null; 
    }
    
    @DELETE
    @Path("{librosId: \\d+}")
    public LibroDTO deleteLibro(@PathParam("librosId") Long librosId) throws BusinessLogicException
    {
       return null; 
    }
    
    
}
