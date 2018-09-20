package co.edu.uniandes.csw.biblioteca.dtos;

import co.edu.uniandes.csw.bibilioteca.entities.LibroDigitalEntity;
import co.edu.uniandes.csw.bibilioteca.entities.UsuarioEntity;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Nicolas Alvarado
 */
public class LibroDigitalDetailDTO extends LibroDigitalDTO implements Serializable {
    
    ArrayList<UsuarioDTO> usuarios;
    
    public LibroDigitalDetailDTO(){
        super();
    }
    
    public LibroDigitalDetailDTO(LibroDigitalEntity lde){
        super();
        if(lde.getUsuarios() != null){
            usuarios = new ArrayList<UsuarioDTO>();
            for(UsuarioEntity ue : lde.getUsuarios()){
                usuarios.add(new UsuarioDTO(ue));
            }
        }
    }
    
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
    
    public ArrayList<UsuarioDTO> getUsuarios(){
        return usuarios;
    }
    
    public void setUsuarios(ArrayList<UsuarioDTO> listUD){
        usuarios = listUD;
    }
    
}
