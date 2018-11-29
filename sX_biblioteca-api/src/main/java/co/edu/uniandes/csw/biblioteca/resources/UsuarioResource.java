
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import co.edu.uniandes.csw.bibilioteca.entities.UsuarioEntity;
import co.edu.uniandes.csw.biblioteca.dtos.UsuarioDTO;
import co.edu.uniandes.csw.biblioteca.dtos.UsuarioDetailDTO;
import co.edu.uniandes.csw.biblioteca.ejb.UsuarioLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
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
@Path("/usuarios")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsuarioResource {

    @Inject
    private UsuarioLogic usuarioLogica;

    private static final String RUTA = "El recurso /usuarios/";
    private static final String NO_EXISTE = " no existe.";

    /**
     *
     * @param usuario de la clase DTO
     * @return devuelve el usuario creado
     * @throws Exception
     */
    @POST
    public UsuarioDTO createUsuario(UsuarioDTO usuario) throws BusinessLogicException {
        return new UsuarioDTO(usuarioLogica.createUsuario(usuario.toEntity()));

    }

    /**
     *
     * @param usuariosId entra el id por parametro
     * @return devuelve el usuario que se busco por id
     */
    @GET
    @Path("{usuariosId: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("usuariosId") Long usuariosId) {
        UsuarioEntity usuarioEntity = usuarioLogica.getUsuario(usuariosId);
        if (usuarioEntity == null) {
            throw new WebApplicationException(RUTA + usuariosId + NO_EXISTE, 404);
        }
        UsuarioDetailDTO usuarioDetailDTO = new UsuarioDetailDTO(usuarioEntity);
        return usuarioDetailDTO;
    }

    @GET
    public List<UsuarioDetailDTO> getUsuarios() {
        return listEntity2DetailDTO(usuarioLogica.getUsuarios());

    }

    @PUT
    @Path("{usuariosId: \\d+}")
    public UsuarioDetailDTO updateUsuario(@PathParam("usuariosId") Long usuariosId, UsuarioDTO usuario) {
        usuario.setId(usuariosId);
        UsuarioEntity usuarioEntity = usuarioLogica.getUsuario(usuariosId);
        if (usuarioEntity == null) {
            throw new WebApplicationException(RUTA + usuariosId + NO_EXISTE, 404);
        }
        return new UsuarioDetailDTO(usuarioLogica.updateUsuario(usuariosId, usuario.toEntity()));

    }

    @DELETE
    @Path("{usuariosId: \\d+}")
    public void deleteUsuario(@PathParam("usuariosId") Long usuariosId) {
        UsuarioEntity entity = usuarioLogica.getUsuario(usuariosId);
        if (entity == null) {
            throw new WebApplicationException(RUTA + usuariosId + NO_EXISTE, 404);
        }
        usuarioLogica.deleteUsuario(usuariosId);
    }

    @Path("{usuariosId: \\d+}/videosdigitales")
    public Class<UsuarioVideoDigitalResource> getUsuarioVideosDiggitalesResource(@PathParam("usuariosId") Long usuariosId) {
        if (usuarioLogica.getUsuario(usuariosId) == null) {
            throw new WebApplicationException(RUTA + usuariosId + NO_EXISTE, 404);
        }
        return UsuarioVideoDigitalResource.class;
    }

    private List<UsuarioDetailDTO> listEntity2DetailDTO(List<UsuarioEntity> lista) {
        List<UsuarioDetailDTO> resp = new ArrayList<>();
        for (UsuarioEntity entity : lista) {
            resp.add(new UsuarioDetailDTO(entity));
        }
        return resp;
    }
}
