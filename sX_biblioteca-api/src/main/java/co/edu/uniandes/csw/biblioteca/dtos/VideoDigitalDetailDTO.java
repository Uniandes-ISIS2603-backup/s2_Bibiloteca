/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.dtos;

import co.edu.uniandes.csw.bibilioteca.entities.UsuarioEntity;
import co.edu.uniandes.csw.bibilioteca.entities.VideoDigitalEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class VideoDigitalDetailDTO extends VideoDigitalDTO implements Serializable {
 
    List<UsuarioDTO> usuarios;
    
    public VideoDigitalDetailDTO(){
        super();
    }   
    
    public VideoDigitalDetailDTO(VideoDigitalEntity videoDigitalEntity){
        super(videoDigitalEntity);
        if(videoDigitalEntity.getUsuarios() != null){
            usuarios = new ArrayList<>();
            for(UsuarioEntity e : videoDigitalEntity.getUsuarios()){
            usuarios.add(new UsuarioDTO(e));
            }
        }
    }
    
    @Override
    public VideoDigitalEntity toEntity(){
        VideoDigitalEntity videoDigitalEntity = super.toEntity();
        if(usuarios != null){
            List<UsuarioEntity> listaUsuarios= new ArrayList<>();
            for(UsuarioDTO e : usuarios){
                listaUsuarios.add(e.toEntity());
            }
            videoDigitalEntity.setUsuarios(listaUsuarios);
        }
                 
        return videoDigitalEntity;
    }
    
    public List<UsuarioDTO> getUsuarios(){
        return usuarios;
    }
    
    public void setUsuario (List<UsuarioDTO> listUsuarios){
        usuarios = listUsuarios;
    }
   
}
