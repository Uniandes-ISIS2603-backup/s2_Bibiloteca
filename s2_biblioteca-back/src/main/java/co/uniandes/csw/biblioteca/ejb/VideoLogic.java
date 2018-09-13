package co.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.VideoEntity;
import co.edu.uniandes.csw.biblioteca.persistence.*;
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
    
    public VideoEntity createVideo(VideoEntity ve){
        return null;
    }
    
}
