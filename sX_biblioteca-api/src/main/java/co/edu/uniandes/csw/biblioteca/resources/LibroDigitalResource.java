package co.edu.uniandes.csw.biblioteca.resources;

/**
 *
 * @author Nicolás Alvarado
 */ 

import co.edu.uniandes.csw.bibilioteca.entities.LibroDigitalEntity;
import co.edu.uniandes.csw.biblioteca.dtos.LibroDigitalDTO;
import co.edu.uniandes.csw.biblioteca.ejb.LibroDigitalLogic;
import co.edu.uniandes.csw.biblioteca.ejb.LibroDigitalUsuarioLogic;
import co.edu.uniandes.csw.biblioteca.ejb.UsuarioLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.*;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("librosdigitales")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class LibroDigitalResource {
    
    @Inject
    private LibroDigitalLogic ldl;
    @Inject
    private UsuarioLogic ul;
    @Inject
    private LibroDigitalUsuarioLogic ldul;
        
    @POST
    public LibroDigitalDTO createLibroDigital(LibroDigitalDTO pLibroDigital) throws BusinessLogicException 
    {
        LibroDigitalDTO lddto = new LibroDigitalDTO(ldl.createLibroDigital(pLibroDigital.toEntity()));
        return lddto;
    }
    
    @GET
    @Path("{librosdigitalesid: \\d+}")
    public LibroDigitalDTO getLibroDigitalById(@PathParam("librosdigitalesid") Long librosdigitalesId)
    {
       return null; 
    }
    
    @GET
    public ArrayList<LibroDigitalDTO> getLibrosDigitales()
    {
       ArrayList<LibroDigitalDTO> listLibros = listLDEntity2DetailDTO(ldl.getLibrosDigitales());
       return listLibros;
    }
    
    @PUT
     @Path("{librosdigitalesid: \\d+}")
    public LibroDigitalDTO actualizarLibroDigital(@PathParam("librosdigitalesid") Long pLibrosdigitalesid, LibroDigitalDTO pLibroDigital)
    {
       return pLibroDigital; 
    }
    
    @DELETE
     @Path("{librosdigitalesid: \\d+}")
    public void eliminarLibroDigital(@PathParam("librosdigitalesid") Long librosdigitalesid)
    {
       
    }
    
    private ArrayList<LibroDigitalDTO> listLDEntity2DetailDTO(List<LibroDigitalEntity> entityList) {
        ArrayList<LibroDigitalDTO> list = new ArrayList<>();
        for (LibroDigitalEntity entity : entityList) {
            list.add(new LibroDigitalDTO(entity));
        }
        return list;
    }
    
}