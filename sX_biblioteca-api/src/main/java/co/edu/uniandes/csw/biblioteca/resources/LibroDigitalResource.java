package co.edu.uniandes.csw.biblioteca.resources;

/**
 *
 * @author Nicolás Alvarado
 */ 

import co.edu.uniandes.csw.bibilioteca.entities.LibroDigitalEntity;
import co.edu.uniandes.csw.bibilioteca.entities.UsuarioEntity;
import co.edu.uniandes.csw.biblioteca.dtos.LibroDigitalDTO;
import co.edu.uniandes.csw.biblioteca.dtos.LibroDigitalDetailDTO;
import co.edu.uniandes.csw.biblioteca.dtos.UsuarioDTO;
import co.edu.uniandes.csw.biblioteca.dtos.UsuarioDetailDTO;
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

@Path("librosDigitales")
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
    @Path("{librosDigitalesId: \\d+}")
    public LibroDigitalDetailDTO getLibroDigitalById(@PathParam("librosDigitalesId") Long librosdigitalesId)
    {
       LibroDigitalEntity lde = ldl.getLibroDigital(librosdigitalesId);
       if(lde == null){
           throw new WebApplicationException("En libro no existe",404);
       }
       LibroDigitalDetailDTO lddto = new LibroDigitalDetailDTO(lde);
       return lddto;
    }
    
    @GET
    public ArrayList<LibroDigitalDetailDTO> getLibrosDigitales()
    {
       ArrayList<LibroDigitalDetailDTO> listLibros = listLDEntity2DetailDTO(ldl.getLibrosDigitales());
       return listLibros;
    }
    
    @PUT
    @Path("{librosDigitalesId: \\d+}")
    public LibroDigitalDTO actualizarLibroDigital(@PathParam("librosDigitalesId") Long pLibrosdigitalesid, LibroDigitalDTO pLibroDigital) throws BusinessLogicException
    {
       pLibroDigital.setId(pLibrosdigitalesid);
       if(ldl.getLibroDigital(pLibrosdigitalesid) == null){
           throw new WebApplicationException("El libro no existe",404);
       }
       LibroDigitalDTO lddto = new LibroDigitalDTO(ldl.updateLibroDigital(pLibroDigital.toEntity()));
       return lddto;
    }
    
    @DELETE
     @Path("{librosDigitalesId: \\d+}")
    public void eliminarLibroDigital(@PathParam("librosDigitalesId") Long librosdigitalesid)
    {
       LibroDigitalEntity lde = ldl.getLibroDigital(librosdigitalesid);
       if(lde == null){
           throw new WebApplicationException("El libro no existe",404);
       }
       ldl.deleteVideoDigital(librosdigitalesid);
    }
    
    @POST
    @Path("{librosDigitalesId: \\d+}/{usuariosId: \\d+}")
    public UsuarioDTO addUsuario(@PathParam("librosDigitalesId") Long libroId, @PathParam("usuariosId") Long usuarioId) throws BusinessLogicException{
        UsuarioEntity ue = ul.getUsuario(usuarioId);
        if(ue == null){
            throw new WebApplicationException("El usuario no existe",404);
        }
        UsuarioDTO udto = new UsuarioDTO(ldul.addUsuario(libroId, usuarioId));
        return udto;
    }
    
    @GET
    @Path("{libroDigitalId: \\d+}/usuarios")
    public List<UsuarioDetailDTO> getUsuarios(@PathParam("libroDigitalId") Long libroId){
        List<UsuarioDetailDTO> listUDTO = listUEntity2DetailDTO(ldul.getUsuarios(libroId));
        if(listUDTO == null){
            throw new WebApplicationException("El libro no existe",404);
        }
        return listUDTO;
    }
    
    @GET
    @Path("{librosDigitalesId: \\d+}/{usuariosId: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("librosDigitalesId") Long libroId, @PathParam("usuariosId") Long usuarioId){
        UsuarioEntity ue = ul.getUsuario(usuarioId);
        if(ue == null){
            throw new WebApplicationException("El usuario no existe",404);
        }
        UsuarioDetailDTO udto = new UsuarioDetailDTO(ldul.getUsuario(libroId, usuarioId));
        return udto;
    }
    
    @DELETE
    @Path("{librosDigitalesId: \\d+}/{usuariosId: \\d+}")
    public void deleteUsuario(@PathParam("librosDigitalesId") Long libroId, @PathParam("usuariosId") Long usuarioId){
        UsuarioEntity ue = ul.getUsuario(usuarioId);
        if(ue == null){
            throw new WebApplicationException("El usuario no existe",404);
        }
        ldul.deleteUsuario(libroId, usuarioId);
    }
    
    private ArrayList<LibroDigitalDetailDTO> listLDEntity2DetailDTO(List<LibroDigitalEntity> entityList) {
        ArrayList<LibroDigitalDetailDTO> list = new ArrayList<>();
        for (LibroDigitalEntity entity : entityList) {
            list.add(new LibroDigitalDetailDTO(entity));
        }
        return list;
    }
    
    private ArrayList<UsuarioDetailDTO> listUEntity2DetailDTO(List<UsuarioEntity> entityList) {
        ArrayList<UsuarioDetailDTO> list = new ArrayList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }
    
}