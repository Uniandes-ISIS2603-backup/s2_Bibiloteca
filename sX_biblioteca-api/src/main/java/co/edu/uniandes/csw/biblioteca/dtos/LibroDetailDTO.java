/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.dtos;

import co.edu.uniandes.csw.bibilioteca.entities.ComentarioEntity;
import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Daniel Montoya
 */
public class LibroDetailDTO extends LibroDTO implements Serializable {
    
    ArrayList<ComentarioDTO> comentarios;
    
    public LibroDetailDTO(){
        super();
    }
    
    public LibroDetailDTO(LibroEntity libro){
        super(libro);
        if(libro.getComentarios() != null)
        {
            comentarios = new ArrayList<>();
            for(ComentarioEntity comentario : libro.getComentarios())
            {
                comentarios.add(new ComentarioDTO(comentario));
            }
        }
    }

    public ArrayList<ComentarioDTO> getComentarios() {
        return comentarios;
    }

    public void setComentarios(ArrayList<ComentarioDTO> comentarios) {
        this.comentarios = comentarios;
    }
    
    @Override
    public LibroEntity toEntity()
    {
        LibroEntity libroEntity = super.toEntity();
        if(comentarios != null)
        {
            ArrayList<ComentarioEntity> comentariosEntity = new ArrayList<>();
            for(ComentarioDTO comentarioDto : comentarios)
            {
                comentariosEntity.add(comentarioDto.toEntity());
            }
            libroEntity.setComentarios(comentariosEntity);
        }
        return libroEntity;
    }
}
