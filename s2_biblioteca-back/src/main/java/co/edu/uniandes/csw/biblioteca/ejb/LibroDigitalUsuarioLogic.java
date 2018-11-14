package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.LibroDigitalEntity;
import co.edu.uniandes.csw.bibilioteca.entities.UsuarioEntity;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.LibroDigitalPersistence;
import co.edu.uniandes.csw.biblioteca.persistence.UsuarioPersistence;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Nicolas Alvarado
 */
@Stateless
public class LibroDigitalUsuarioLogic {
    
    @Inject
    private LibroDigitalPersistence ldp;
    
    @Inject
    private UsuarioPersistence up;
    
    public ArrayList<UsuarioEntity> getUsuarios(Long ldId){
        return ldp.find(ldId).getUsuarios();
    }
    
    public UsuarioEntity addUsuario(Long uId, Long ldId) throws BusinessLogicException{
        LibroDigitalEntity lde = ldp.find(ldId);
        UsuarioEntity ue = up.find(uId);
        ArrayList<UsuarioEntity> listUE = getUsuarios(ldId);
        for(UsuarioEntity ue2 : listUE){
            if(ue == ue2){
                throw new BusinessLogicException("El usuario ya esta el la lista");
            }
        }
        lde.getUsuarios().add(ue);
        return ue;
    }
    
    public UsuarioEntity getUsuario(Long ldId,Long uId){
        ArrayList<UsuarioEntity> listUE = getUsuarios(ldId);
        UsuarioEntity ue = up.find(uId);
        int i = listUE.indexOf(ue);
        if(i >= 0){
            return listUE.get(i);
        }
        return null;
    }
    
    public void deleteUsuario(Long ldID, Long uId){
        LibroDigitalEntity lde = ldp.find(ldID);
        UsuarioEntity ue = up.find(uId);
        lde.getUsuarios().remove(ue);
    }
    
}
