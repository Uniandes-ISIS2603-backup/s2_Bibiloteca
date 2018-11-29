/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import co.edu.uniandes.csw.bibilioteca.entities.SalaEntity;
import co.edu.uniandes.csw.biblioteca.dtos.SalaDTO;
import co.edu.uniandes.csw.biblioteca.ejb.SalaLogic;
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
@Path("salas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class SalaResource {

    @Inject
    private SalaLogic salaLogic;

    /**
     * Crea una nueva sala con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param bibliotecaId El ID de la biblioteca en la cual se crea la sala.
     * @param sala {@link SalaDTO} - La sala que se desea guardar.
     * @return JSON {@link SalaDTO} - La sala guardada con el atributo id
     * autogenerado.
     * @throws Exception {@link BusinessLogicExceptionMapper} - Error de lógica
     * que se genera cuando ya existe la sala.
     */
    @POST
    public SalaDTO createSala(@PathParam("bibliotecasId") Long bibliotecaId, SalaDTO sala) throws BusinessLogicException {
        return new SalaDTO(salaLogic.createSala(bibliotecaId, sala.toEntity()));

    }

    /**
     * Busca y devuelve la sala con el ID recibido en la URL, relativa a una
     * biblioteca.
     *
     * @param bibliotecaId
     * @param salaId
     * @return
     * @throws Exception
     */
    @GET
    @Path("{salaId: \\d+}")
    public SalaDTO getSalaBiblioteca(@PathParam("librosId") Long bibliotecaId, @PathParam("salaId") Long salaId) {
        SalaEntity entity = salaLogic.getSalaBiblioteca(bibliotecaId, salaId);
        if (entity == null) {
            throw new WebApplicationException("Sala con id: " + salaId + " no existe", 404);
        }
        return new SalaDTO(entity);

    }

    /**
     * Busca y devuelve todas las salas que existen en una biblioteca.
     *
     * @param bibliotecaId El ID de la biblioteca en la cual se buscan las
     * salas.
     * @return JSONArray {@link SalaDTO} - Las salas encontradas en la
     * biblioteca. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<SalaDTO> getSalas(@PathParam("bibliotecasId") Long bibliotecaId) {
        return listEntity2DTO(salaLogic.getSalasPorBiblioteca(bibliotecaId));
    }

    private List<SalaDTO> listEntity2DTO(List<SalaEntity> listaEntity) {
        List<SalaDTO> resp = new ArrayList<>();
        for (SalaEntity entity : listaEntity) {
            resp.add(new SalaDTO(entity));
        }
        return resp;
    }

    /**
     * Actualiza una sala con la informacion que se recibe en el cuerpo de la
     * petición y se regresa el objeto actualizado.
     *
     * @param bibliotecaId El ID de la biblioteca de la cual se guarda la sala.
     * @param salaId El ID de la sala que se va a actualizar
     * @param sala {@link SalaDTO} - La sala que se desea guardar.
     * @return JSON {@link SalaDTO} - La sala actualizada.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la sala.
     */
    @PUT
    @Path("{salaId: \\d+}")
    public SalaDTO updateSala(@PathParam("bibliotecasId") Long bibliotecaId, @PathParam("salaId") Long salaId, SalaDTO sala) throws BusinessLogicException {
        if (!salaId.equals(sala.getId())) {
            throw new BusinessLogicException("Los ids de la sala no coinciden.");
        }
        SalaEntity entity = salaLogic.getSalaBiblioteca(bibliotecaId, salaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /bibliotecas/" + bibliotecaId + "/salas/" + salaId + " no existe.", 404);
        }
        return new SalaDTO(salaLogic.updateSala(bibliotecaId, sala.toEntity()));

    }

    /**
     * Borra la sala con el id asociado recibido en la URL.
     *
     * @param bibliotecaId El ID de la biblioteca de la cual se va a eliminar la
     * sala.
     * @param salaId El ID de la sala que se va a eliminar.
     */
    @DELETE
    @Path("{salaId: \\d+}")
    public void deleteSala(@PathParam("bibliotecasId") Long bibliotecaId, @PathParam("salaId") Long salaId) throws BusinessLogicException {
        SalaEntity sala = salaLogic.getSalaBiblioteca(bibliotecaId, salaId);
        if (sala == null) {
            throw new WebApplicationException("El recurso /bibliotecas/" + bibliotecaId + "/salas/" + salaId + " no existe.", 404);
        }
        salaLogic.deleteSala(bibliotecaId, salaId);
    }
}
