
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;
import co.edu.uniandes.csw.biblioteca.dtos.UsuarioDTO;
import javax.ws.rs.*;
import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
/**
 *
 * @author Juan Nicolás García
 */
@Path("usarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioResource {
    @POST
    public UsuarioDTO createUsuario(UsuarioDTO usuario)
    {
        return usuario;
    }
    @GET
    @Path("{usuariosId: \\d+}")
    public UsuarioDTO getUsuario(@PathParam("usuariosId") Long usuariosId)
    {
        return null;
    }
    
}
