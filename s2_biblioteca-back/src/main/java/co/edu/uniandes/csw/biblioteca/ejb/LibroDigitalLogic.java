package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.LibroDigitalEntity;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.LibroDigitalPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Nicolas Alvarado
 */

@Stateless
public class LibroDigitalLogic {
    
    @Inject
    private LibroDigitalPersistence ldp;
    
    public LibroDigitalEntity createLibroDigital(LibroDigitalEntity lde) throws BusinessLogicException{
        if(lde.getNombre() == null || lde.getIsbn() == null || lde.getAutor() == null || lde.getEditorial() == null || lde.getEdicion() == null || lde.getIdioma() == null){
            throw new BusinessLogicException("El nombre, isbn, autor, editorial, edicion o idioma no pueden ser nulos.");
        }
        if(ldp.find(lde.getId()) != null || ldp.findByServiceName(lde.getNombre()) != null){
            throw new BusinessLogicException("El libro ya existe");
        }
        ldp.create(lde);
        return lde;
    }
    
    public List<LibroDigitalEntity> getLibrosDigitales(){
        List<LibroDigitalEntity> listVDE;
        listVDE = ldp.findAll();
        return listVDE;
    }
    
    public LibroDigitalEntity getLibroDigital(Long ldId){
        LibroDigitalEntity lde;
        lde = ldp.find(ldId);
        return lde;
    }
    
    public LibroDigitalEntity getLibroDigitalNombre(String ldn){
        LibroDigitalEntity lde;
        lde = ldp.findByServiceName(ldn);
        return lde;
    }
    
    public LibroDigitalEntity updateLibroDigital(LibroDigitalEntity lde) throws BusinessLogicException{
        if(lde.getNombre() == null || lde.getIsbn() == null || lde.getAutor() == null || lde.getEditorial() == null || lde.getEdicion() == null || lde.getIdioma() == null){
            throw new BusinessLogicException("El nombre, isbn, autor, editorial, edicion o idioma no pueden ser nulos.");
        }
        if(ldp.find(lde.getId()) != null){
            throw new BusinessLogicException("El libro ya existe");
        }
        LibroDigitalEntity lde2;
        lde2 = ldp.update(lde);
        return lde2;
    }
    
    public void deleteVideoDigital(Long ldId){
        ldp.delete(ldId);
    }
    
}
