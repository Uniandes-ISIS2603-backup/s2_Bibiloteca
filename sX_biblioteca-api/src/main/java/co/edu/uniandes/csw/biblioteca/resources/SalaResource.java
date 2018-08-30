/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;
import co.edu.uniandes.csw.biblioteca.adapters.SalaDTO;
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
public class SalaResource {
     @POST
    public SalaDTO createSala(SalaDTO sala)
    {
        return sala;
    }
    @GET
    @Path("{salasId: \\d+}")
    public SalaDTO getSala(@PathParam("salasId") Long salasId)
    {
        return null;
    }
}