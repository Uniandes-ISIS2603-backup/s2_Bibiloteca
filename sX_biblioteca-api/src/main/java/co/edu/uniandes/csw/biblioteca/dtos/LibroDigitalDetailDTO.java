package co.edu.uniandes.csw.biblioteca.dtos;

import co.edu.uniandes.csw.bibilioteca.entities.LibroDigitalEntity;
import co.edu.uniandes.csw.bibilioteca.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nicolas Alvarado
 */
public class LibroDigitalDetailDTO extends LibroDigitalDTO implements Serializable {
    
    List<UsuarioDTO> usuarios;
    
    public LibroDigitalDetailDTO(){
        super();
    }
    
    public LibroDigitalDetailDTO(LibroDigitalEntity lde){
        super();
        if(lde.getUsuarios() != null){
            usuarios = new ArrayList<>();
            for(UsuarioEntity ue : lde.getUsuarios()){
                usuarios.add(new UsuarioDTO(ue));
            }
        }
    }
    
    @Override
    public LibroDigitalEntity toEntity(){
        LibroDigitalEntity lde = super.toEntity();
        if(usuarios != null){
            ArrayList<UsuarioEntity> listUE = new ArrayList<>();
            for(UsuarioDTO ud : usuarios){
                listUE.add(ud.toEntity());
            }
            lde.setUsuarios(listUE);
        }
        return lde;
    }
    
    public List<UsuarioDTO> getUsuarios(){
        return usuarios;
    }
    
    public void setUsuarios(List<UsuarioDTO> listUD){
        usuarios = listUD;
    }
    
}
