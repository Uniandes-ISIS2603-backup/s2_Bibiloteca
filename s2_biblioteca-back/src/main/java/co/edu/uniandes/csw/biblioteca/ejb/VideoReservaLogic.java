package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.*;
import co.edu.uniandes.csw.biblioteca.persistence.*;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Nicolas Alvarado
 */

@Stateless
public class VideoReservaLogic {
    
    @Inject
    private VideoPersistence videoPersis;
    
    @Inject
    private ReservaPersistence reservaPersis;
    
    public ReservaEntity addReserva(Long videoID, Long reservaID){
        ReservaEntity re = reservaPersis.find(reservaID);
        VideoEntity ve = videoPersis.find(videoID);
        ve.getReservas().add(re);
        return re;
    }
    
    public ArrayList<ReservaEntity> getReservas(Long videoID){
        return videoPersis.find(videoID).getReservas();
    }
    
    public ReservaEntity getReserva(Long videoID, Long reservaID){
        ArrayList<ReservaEntity> listRE = getReservas(videoID);
        ReservaEntity re = reservaPersis.find(reservaID);
        int i = listRE.indexOf(re);
        if(i >= 0){
            return listRE.get(i);
        }
        return null;
    }
    
    public void removeReserva(Long videoID, Long reservaID){
        VideoEntity ve = videoPersis.find(videoID);
        ReservaEntity re = reservaPersis.find(reservaID);
        ve.getReservas().remove(re);
    }
}
