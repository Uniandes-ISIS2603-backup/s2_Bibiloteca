/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import co.edu.uniandes.csw.bibilioteca.entities.ReservaEntity;
import co.edu.uniandes.csw.biblioteca.dtos.ReservaDetailDTO;
import co.edu.uniandes.csw.biblioteca.ejb.LibroReservaLogic;
import co.edu.uniandes.csw.biblioteca.ejb.ReservaLogic;
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
public class LibroReservaResource {
    
    @Inject
    private LibroReservaLogic libroReserva;
    
    @Inject
    private ReservaLogic reservaLogic;
    
    @POST
    @Path("{reservasId: \\d+}")
    public ReservaDetailDTO addReserva(@PathParam("librosId") Long librosId, @PathParam("reservasId") Long reservasId) throws BusinessLogicException
    {
        if(reservaLogic.getReserva(reservasId)==null)
        {
            throw new WebApplicationException("El recurso /reservas/" + reservasId + " no existe.", 404);
        }
        ReservaDetailDTO resp = new ReservaDetailDTO(libroReserva.addReserva(librosId, reservasId));
        return resp;
    }
    
    @GET
    public List<ReservaDetailDTO> getReservas(@PathParam("librosId") Long librosId) throws BusinessLogicException
    {
        List<ReservaDetailDTO> lista = reservaListEntity2DTO(libroReserva.getReservas(librosId));
        return lista; 
    }
    
    @GET
    @Path("{reservasId: \\d+}")
    public ReservaDetailDTO getReserva(@PathParam("librosId") Long librosId, @PathParam("reservasId") Long reservasId) throws BusinessLogicException
    {
        if(reservaLogic.getReserva(reservasId)==null)
        {
            throw new WebApplicationException("El recurso /reservas/" + reservasId + " no existe.", 404);
        }
        ReservaDetailDTO reserva = new ReservaDetailDTO(libroReserva.getReserva(librosId, reservasId));
        return reserva;
    }
    
    @DELETE
    @Path("{reservasId: \\d+}")
    public void deleteReserva(@PathParam("librosId") Long librosId, @PathParam("reservasId") Long reservasId)
    {
        if(reservaLogic.getReserva(reservasId)==null)
        {
            throw new WebApplicationException("El recurso /reservas/" + reservasId + " no existe.", 404);
        }
        libroReserva.deleteReserva(librosId, reservasId);
    }
    
    
    private List<ReservaDetailDTO> reservaListEntity2DTO(List<ReservaEntity> lista)
    {
        List<ReservaDetailDTO> resp = new ArrayList<>();
        for(ReservaEntity entity : lista)
        {
            resp.add(new ReservaDetailDTO(entity));
            
        }
        return resp;
    }
    private List<ReservaEntity> reservaListDTO2Entity(List<ReservaDetailDTO> lista)
    {
        List<ReservaEntity> resp = new ArrayList<>();
        for(ReservaDetailDTO reserva : lista)
        {
            resp.add(reserva.toEntity());
        }
        return resp;
    }
}
