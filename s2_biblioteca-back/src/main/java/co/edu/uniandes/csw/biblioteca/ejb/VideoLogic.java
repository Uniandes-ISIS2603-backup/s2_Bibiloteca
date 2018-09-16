package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.*;
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
        if(ve.getBiblioteca() == null || biblioPersis.find(ve.getBiblioteca().getId()) == null){
            throw new BusinessLogicException("El video tiene que pertenecer a una biblioteca existente");
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
    
    public VideoEntity getVideoNombre(String pNombre){
        VideoEntity ve = videoPersis.findByName(pNombre);
        return ve;
    }
    
    public VideoEntity updateVideo(VideoEntity ve) throws BusinessLogicException{
        if(ve.getNombre() == null || ve.getDirector() == null || ve.getIdioma() == null){
            throw new BusinessLogicException("El nombre, director o idioma no pueden ser nulos");
        }
        VideoEntity newVE = videoPersis.update(ve);
        return newVE;
    }
    
    public void deleteVideo(Long videoID) throws BusinessLogicException{
        ArrayList<ReservaEntity> listRE = getVideo(videoID).getReservas();
        ArrayList<PrestamoEntity> listPE = getVideo(videoID).getPrestamos();
        if(listRE != null && !listRE.isEmpty()){
            throw new BusinessLogicException("El video no se puede borrar porque tiene reservas");
        }
        if(listPE != null && !listPE.isEmpty()){
            throw new BusinessLogicException("El video no se puede borrar porque tiene reservas");
        }
        videoPersis.delete(videoID);
    }
    
}
