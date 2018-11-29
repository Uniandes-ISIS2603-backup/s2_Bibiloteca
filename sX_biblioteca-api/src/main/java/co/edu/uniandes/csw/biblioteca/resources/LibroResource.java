/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import co.edu.uniandes.csw.biblioteca.dtos.LibroDTO;
import co.edu.uniandes.csw.biblioteca.dtos.LibroDetailDTO;
import co.edu.uniandes.csw.biblioteca.ejb.LibroLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Daniel Montoya
 */
@Path("libros")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class LibroResource {

    private static final String RUTA = "El recurso /libros/";
    private static final String NO_EXISTE = " no existe.";
    @Inject
    private LibroLogic libroLogica;

    /**
     *
     * @param libro libro de la clase DTO
     * @return devuelve el libro creado
     * @throws Exception
     */
    @POST
    public LibroDTO createLibro(LibroDTO libro) throws BusinessLogicException {
        return new LibroDTO(libroLogica.createLibro(libro.toEntity()));

    }

    /**
     *
     * @param librosId entra el id por parametro
     * @return devuelve el libro que se busco por id
     */
    @GET
    @Path("{librosId: \\d+}")
    public LibroDetailDTO getLibro(@PathParam("librosId") Long librosId) {
        LibroEntity libroEntity = libroLogica.getLibro(librosId);
        if (libroEntity == null) {
            throw new WebApplicationException(RUTA + librosId + NO_EXISTE, 404);
        }
        return new LibroDetailDTO(libroEntity);

    }

    @GET
    public List<LibroDetailDTO> getLibros() {
        return listEntity2DetailDTO(libroLogica.getLibros());

    }

    @PUT
    @Path("{librosId: \\d+}")
    public LibroDetailDTO updateLibro(@PathParam("librosId") Long librosId, LibroDTO libro) throws BusinessLogicException {
        libro.setId(librosId);
        LibroEntity libroEntity = libroLogica.getLibro(librosId);
        if (libroEntity == null) {
            throw new WebApplicationException(RUTA + librosId + NO_EXISTE, 404);
        }
        return new LibroDetailDTO(libroLogica.updateLibro(libro.toEntity(), librosId));

    }

    @DELETE
    @Path("{librosId: \\d+}")
    public void deleteLibro(@PathParam("librosId") Long librosId) {
        LibroEntity entity = libroLogica.getLibro(librosId);
        if (entity == null) {
            throw new WebApplicationException(RUTA + librosId + NO_EXISTE, 404);
        }
        libroLogica.deleteLibro(librosId);
    }

    private List<LibroDetailDTO> listEntity2DetailDTO(List<LibroEntity> lista) {
        List<LibroDetailDTO> resp = new ArrayList<>();
        for (LibroEntity entity : lista) {
            resp.add(new LibroDetailDTO(entity));
        }
        return resp;
    }

}
