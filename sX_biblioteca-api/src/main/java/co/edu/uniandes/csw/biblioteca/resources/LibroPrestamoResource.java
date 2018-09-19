/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import co.edu.uniandes.csw.biblioteca.dtos.PrestamoDetailDTO;
import co.edu.uniandes.csw.biblioteca.ejb.LibroPrestamoLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Daniel Montoya
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LibroPrestamoResource {
     
    @Inject
    private LibroPrestamoLogic libroPrestamo;
    
    @Inject
    private PrestamoLogic prestamoLogic;
    
    @POST
    @Path("{prestamosId: \\d+}")
    public PrestamoDetailDTO addPrestamo(@PathParam("librosId") Long librosId, @PathParam("prestamosId") Long prestamosId) throws BusinessLogicException
    {
        if(prestamoLogic.getPrestamo(prestamosId)==null)
        {
            throw new WebApplicationException("El recurso /prestamos/" + prestamosId + " no existe.", 404);
        }
        PrestamoDetailDTO resp = new PrestamoDetailDTO(libroPrestamo.addPrestamo(librosId, prestamosId));
        return resp;
    }
    
    @GET
    public List<PrestamoDetailDTO> getPrestamos(@PathParam("librosId") Long librosId) throws BusinessLogicException
    {
        List<PrestamoDetailDTO> lista = prestamoListEntity2DTO(libroPrestamo.getPrestamos(librosId));
        return lista; 
    }
    
    @GET
    @Path("{prestamosId: \\d+}")
    public PrestamoDetailDTO getPrestamo(@PathParam("librosId") Long librosId, @PathParam("prestamosId") Long prestamosId) throws BusinessLogicException
    {
        if(prestamoLogic.getPrestamo(prestamosId)==null)
        {
            throw new WebApplicationException("El recurso /prestamos/" + prestamosId + " no existe.", 404);
        }
        PrestamoDetailDTO prestamo = new PrestamoDetailDTO(libroPrestamo.getPrestamo(librosId, prestamosId));
        return prestamo;
    }
    
    @DELETE
    @Path("{prestamosId: \\d+}")
    public void deletePrestamo(@PathParam("librosId") Long librosId, @PathParam("prestamosId") Long prestamosId)
    {
        if(prestamoLogic.getPrestamo(prestamosId)==null)
        {
            throw new WebApplicationException("El recurso /prestamos/" + prestamosId + " no existe.", 404);
        }
        libroPrestamo.deletePrestamo(librosId, prestamosId);
    }
    
    
    private List<PrestamoDetailDTO> prestamoListEntity2DTO(List<PrestamoEntity> lista)
    {
        List<PrestamoDetailDTO> resp = new ArrayList<>();
        for(PrestamoEntity entity : lista)
        {
            resp.add(new PrestamoDetailDTO(entity));
            
        }
        return resp;
    }
    private List<PrestamoEntity> prestamoListDTO2Entity(List<PrestamoDetailDTO> lista)
    {
        List<PrestamoEntity> resp = new ArrayList<>();
        for(PrestamoDetailDTO prestamo : lista)
        {
            resp.add(prestamo.toEntity());
        }
        return resp;
    }   
}
