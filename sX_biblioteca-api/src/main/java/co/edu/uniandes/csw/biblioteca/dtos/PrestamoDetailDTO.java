/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.dtos;

import co.edu.uniandes.csw.bibilioteca.entities.PrestamoEntity;
import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class PrestamoDetailDTO extends PrestamoDTO implements Serializable{
 
     /**
     * Constructor por defecto
     */
    public PrestamoDetailDTO() 
    {
    }
    public PrestamoDetailDTO(PrestamoEntity pPrestamoEntity) 
    {
        super(pPrestamoEntity);
        if (pPrestamoEntity != null) 
            
        {
            if (pPrestamoEntity.getLibro() != null ) 
            {
                libro =  new LibroDTO(pPrestamoEntity.getLibro());
            }
            
            if ( pPrestamoEntity.getSala()!= null ) 
            {
                sala =  new SalaDTO(pPrestamoEntity.getSala());
            }
            
            if (pPrestamoEntity.getVideo() != null ) 
            {
                video =  new VideoDTO(pPrestamoEntity.getVideo());
            }
          
            
        }
    }
    
    /**
     * relacion de un prestamo con el video
     */
    private VideoDTO video;
    
    /**
     * relacion de un prestamo con un libro
     */
    private LibroDTO libro;
    /**
     * relacion de un prestamo con una sala
     */
    private SalaDTO sala;  
    
    
    
    /**
     * Transformar un DTO a un Entity
     * @return El DTO de la editorial para transformar a Entity
     */
    @Override
    public PrestamoEntity toEntity() 
    {
        PrestamoEntity prestamoEntity = super.toEntity();
        
        if(this.libro != null)
        {
            prestamoEntity.setLibro(this.libro.toEntity());
        }
        else
        {
            prestamoEntity.setLibro(null);
        }
        
        if(this.video != null)
        {
            prestamoEntity.setVideo(this.video.toEntity());
        }
        else
        {
            prestamoEntity.setVideo(null);
        }
        
        if(this.sala != null)
        {
            prestamoEntity.setSala (this.sala.toEntity());
        }
        else
        {
            prestamoEntity.setSala(null);
        }
        
      
        
        return prestamoEntity;
    }

    public VideoDTO getVideo() {
        return video;
    }

    public void setVideo(VideoDTO video) {
        this.video = video;
    }

    public LibroDTO getLibro() {
        return libro;
    }

    public void setLibro(LibroDTO libro) {
        this.libro = libro;
    }

    public SalaDTO getSala() {
        return sala;
    }

    public void setSala(SalaDTO sala) {
        this.sala = sala;
    }
}

