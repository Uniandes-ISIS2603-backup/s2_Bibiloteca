/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.resources;

import co.edu.uniandes.csw.biblioteca.dtos.LibroDTO;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;


/**
 *
 * @author Daniel Montoya
 */
@Path("libros")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ComentarioResource {
     @Inject private ComentarioLogic comentarioLogic;
     
     
}
