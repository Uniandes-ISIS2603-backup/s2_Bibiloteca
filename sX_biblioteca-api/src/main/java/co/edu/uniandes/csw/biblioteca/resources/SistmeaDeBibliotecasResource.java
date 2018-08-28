/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import co.edu.uniandes.csw.biblioteca.dtos.SistemaDeBibliotecasDTO;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Juan Bautista
 */ 
    @Path("sistemasdebibliotecas")
    @Produces("application/json")
    @Consumes("application/json")
    @RequestScoped
public class SistmeaDeBibliotecasResource {
    public SistemaDeBibliotecasDTO createSistemaDeBibliotecas(SistemaDeBibliotecasDTO pSistemaDeBibliotecas) throws Exception{
        return pSistemaDeBibliotecas;
    }
    @GET
    @Path("{sistemasdebibliotecas: \\d+}")
    public SistemaDeBibliotecasDTO getSistemaDeBibliotecas(@PathParam("sistemasdebibliotecasId") Long sistemasdebibliotecasId)
    {
       return null; 
    }
}
