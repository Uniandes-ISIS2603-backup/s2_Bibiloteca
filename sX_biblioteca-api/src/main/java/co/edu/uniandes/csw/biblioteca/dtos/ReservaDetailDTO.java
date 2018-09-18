/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.dtos;

import co.edu.uniandes.csw.bibilioteca.entities.ReservaEntity;
import java.io.Serializable;


/**
 * Clase que extiende de {@link ReservaDTO} para manejar las relaciones entre las
 * ReservaDTO y otros DTOs. Para conocer el contenido de la reserva vaya a la
 * documentacion de {@link ReservaDTO}
 * @author Daniel Preciado
 */
public class ReservaDetailDTO extends ReservaDTO implements Serializable {
    
    //__________________________________________________________________________
    //Atributos
    //__________________________________________________________________________
    
    /**
     * relacion de  una reserva con el video
     */
    private VideoDTO video;
    
    /**
     * relacion de una reserva con un libro
     */
    private LibroDTO libro;
    
    /**
     * relacion de una reserva con una sala
     */
    private SalaDTO sala;
    
    /**
     * relacion de una reserva con el video digital
     */
     private VideoDigitalDTO videoDigital;
     
    /**
     * Constructor por defecto
     */
    public ReservaDetailDTO() 
    {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     * @param pReservaEntity La entidad de la reserva para transformar a DTO.
     */
    public ReservaDetailDTO(ReservaEntity pReservaEntity) 
    {
        super(pReservaEntity);
        if (pReservaEntity != null) 
            
        {
            if (pReservaEntity.getLibro() != null ) 
            {
                libro =  new LibroDTO(pReservaEntity.getLibro());
            }
            
            if ( pReservaEntity.getSala()!= null ) 
            {
                sala =  new SalaDTO(pReservaEntity.getSala());
            }
            
            if (pReservaEntity.getVideo() != null ) 
            {
                video =  new VideoDTO(pReservaEntity.getVideo());
            }
            
            if (pReservaEntity.getVideoDigital() != null ) 
            {
                videoDigital =  new VideoDigitalDTO(pReservaEntity.getVideoDigital());
            }
            
        }
    }

    /**
     * Transformar un DTO a un Entity
     * @return El DTO de la editorial para transformar a Entity
     */
    @Override
    public ReservaEntity toEntity() 
    {
        ReservaEntity reservaEntity = super.toEntity();
        
        if(this.libro != null)
        {
            reservaEntity.setLibro(this.libro.toEntity());
        }
        else
        {
            reservaEntity.setLibro(null);
        }
        
        if(this.video != null)
        {
            reservaEntity.setVideo(this.video.toEntity());
        }
        else
        {
            reservaEntity.setVideo(null);
        }
        
        if(this.sala != null)
        {
            reservaEntity.setSala (this.sala.toEntity());
        }
        else
        {
            reservaEntity.setSala(null);
        }
        
        if(this.videoDigital != null)
        {
            reservaEntity.setVideoDigital(this.videoDigital.toEntity());
        }
        else
        {
            reservaEntity.setVideoDigital(null);
        }
        
        return reservaEntity;
    }

    
    
}
