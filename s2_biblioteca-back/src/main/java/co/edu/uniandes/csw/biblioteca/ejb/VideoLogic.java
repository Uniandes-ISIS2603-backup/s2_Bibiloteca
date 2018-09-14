package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.VideoEntity;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.*;
import java.util.*;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Nicolas Alvarado
 */

@Stateless
public class VideoLogic {
    
    @Inject
    private VideoPersistence videoPersis;
    
    @Inject
    private BibliotecaPersistence biblioPersis;
    
    public VideoEntity createVideo(VideoEntity ve) throws BusinessLogicException {
        if(ve.getNombre() == null || ve.getDirector() == null || ve.getIdioma() == null){
            throw new BusinessLogicException("El nombre, director o idioma no pueden ser nulos");
        }
        if(videoPersis.find(ve.getId()) != null || videoPersis.findByName(ve.getNombre()) != null){
            throw new BusinessLogicException("El video ya existe");
        }
        videoPersis.create(ve);
        return ve;
    }
    
    public List<VideoEntity> getVideos(){
        List<VideoEntity> listVE = videoPersis.findAll();
        return listVE;
    }
    
    public VideoEntity getVideo(Long videoID){
        VideoEntity ve = videoPersis.find(videoID);
        return ve;
    }
    
}
