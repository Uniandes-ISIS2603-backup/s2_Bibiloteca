package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.*;
import co.edu.uniandes.csw.biblioteca.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Nicolas Alvarado
 */

@Stateless
public class VideoPrestamoLogic {
    
    @Inject
    private VideoPersistence videoPersis;
    
    @Inject
    private PrestamoPersistence prestamoPersis;
    
    public PrestamoEntity addPrestamo(Long videoID, Long prestamoID){
        PrestamoEntity pe = prestamoPersis.find(prestamoID);
        VideoEntity ve = videoPersis.find(videoID);
        ve.getPrestamos().add(pe);
        return pe;
    }
    
    public List<PrestamoEntity> getPrestamos(Long videoID){
        return videoPersis.find(videoID).getPrestamos();
    }
    
    public PrestamoEntity getPrestamo(Long videoID, Long prestamoID){
        List<PrestamoEntity> listPE = getPrestamos(videoID);
        PrestamoEntity pe = prestamoPersis.find(prestamoID);
        int i = listPE.indexOf(pe);
        if(i >= 0){
            return listPE.get(i);
        }
        return null;
    }
    
    public void removePrestamo(Long videoID, Long prestamoID){
        VideoEntity ve = videoPersis.find(videoID);
        PrestamoEntity pe = prestamoPersis.find(prestamoID);
        ve.getPrestamos().remove(pe);
    }
    
}
