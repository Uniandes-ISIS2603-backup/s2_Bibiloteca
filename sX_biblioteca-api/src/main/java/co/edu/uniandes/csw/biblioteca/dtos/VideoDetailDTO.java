package co.edu.uniandes.csw.biblioteca.dtos;

import co.edu.uniandes.csw.bibilioteca.entities.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nicolas Alvarado
 */
public class VideoDetailDTO extends VideoDTO implements Serializable {
    
    private List<ReservaDTO> reservas;
    private List<PrestamoDTO> prestamos;
    
    public VideoDetailDTO(){
        super();
    }
    
    public VideoDetailDTO(VideoEntity ve){
        super(ve);
        if(ve.getReservas() != null){
            reservas = new ArrayList<>();
            for(ReservaEntity e : ve.getReservas()){
                reservas.add(new ReservaDTO(e));
            }
        }
        if(ve.getPrestamos() != null){
            prestamos = new ArrayList<>();
            for(PrestamoEntity e : ve.getPrestamos()){
                prestamos.add(new PrestamoDTO(e));
            }
        }
    }
    
    @Override
    public VideoEntity toEntity(){
        VideoEntity ve = super.toEntity();
        if(reservas != null){
            ArrayList<ReservaEntity> listaReserva = new ArrayList<>();
            for(ReservaDTO e : reservas){
                listaReserva.add(e.toEntity());
            }
            ve.setReservas(listaReserva);
        }
        if(prestamos != null){
            ArrayList<PrestamoEntity> listaPrestamo = new ArrayList<>();
            for(PrestamoDTO e : prestamos){
                listaPrestamo.add(e.toEntity());
            }
            ve.setPrestamos(listaPrestamo);
        }
        return ve;
    }
    
    public List<ReservaDTO> getReservas(){
        return reservas;
    }
    
    public List<PrestamoDTO> getPrestamos(){
        return prestamos;
    }
    
    public void setReservas(List<ReservaDTO> listRE){
        reservas = listRE;
    }
    
    public void setPrestamos(List<PrestamoDTO> listPE){
        prestamos = listPE;
    }
    
}
