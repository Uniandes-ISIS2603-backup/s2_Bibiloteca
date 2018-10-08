/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.dtos;

import co.edu.uniandes.csw.bibilioteca.entities.ComentarioEntity;
import co.edu.uniandes.csw.bibilioteca.entities.PrestamoEntity;
import co.edu.uniandes.csw.bibilioteca.entities.ReservaEntity;
import co.edu.uniandes.csw.bibilioteca.entities.UsuarioEntity;
import co.edu.uniandes.csw.bibilioteca.entities.VideoDigitalEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Juan Nicolás García
 */
public class UsuarioDetailDTO extends UsuarioDTO implements Serializable{
    ArrayList<ComentarioDTO> comentarios;
    ArrayList<PrestamoDTO> prestamos;
    ArrayList<ReservaDTO> reservas;
    List<VideoDigitalDTO> videosDigitales;
    public UsuarioDetailDTO(){
        super();
    }
    
    public UsuarioDetailDTO(UsuarioEntity usuario){
        super(usuario);
         if (usuario.getVideosDigitales() != null) {
            videosDigitales = new ArrayList<>();
            for (VideoDigitalEntity entityVideoDigital : usuario.getVideosDigitales()) {
                videosDigitales.add(new VideoDigitalDTO(entityVideoDigital));
            }
         }
        if(usuario.getComentarios() != null)
        {
            comentarios = new ArrayList<>();
            for(ComentarioEntity comentario : usuario.getComentarios())
            {
                comentarios.add(new ComentarioDTO(comentario));
            }
        }
	if(usuario.getPrestamos() != null)
        {
            prestamos = new ArrayList<>();
            for(PrestamoEntity prestamo : usuario.getPrestamos())
            {
                prestamos.add(new PrestamoDTO(prestamo));
            }
        }
	if(usuario.getReservas() != null)
        {
            reservas = new ArrayList<>();
            for(ReservaEntity reserva : usuario.getReservas())
            {
                reservas.add(new ReservaDTO(reserva));
            }
        }
         }

    public ArrayList<ComentarioDTO> getComentarios() {
        return comentarios;
    }
    public ArrayList<PrestamoDTO> getPrestamos() {
        return prestamos;
    }
    public ArrayList<ReservaDTO> getReservas() {
        return reservas;
    }
public void setComentarios(ArrayList<ComentarioDTO> comentarios) {
        this.comentarios = comentarios;
    }
    public void setPrestamos(ArrayList<PrestamoDTO> prestamos) {
        this.prestamos = prestamos;
    }
    public void setReservas(ArrayList<ReservaDTO> reservas) {
        this.reservas = reservas;
    }
     public List<VideoDigitalDTO> getVideosDigitales() {
        return videosDigitales;
    }

   
    public void setVideosDigitales(List<VideoDigitalDTO> videosDigitales) {
        this.videosDigitales = videosDigitales;
    }
    @Override
    public UsuarioEntity toEntity()
    {
        UsuarioEntity usuarioEntity = super.toEntity();
      if (videosDigitales != null) {
            List<VideoDigitalEntity> videosDigitalesEntity = new ArrayList<>();
            for (VideoDigitalDTO dtoVideoDigital : videosDigitales) {
                videosDigitalesEntity.add(dtoVideoDigital.toEntity());
            }
            usuarioEntity.setVideosDigitales(videosDigitalesEntity);
        }
        if(comentarios != null)
        {
            ArrayList<ComentarioEntity> comentariosEntity = new ArrayList<>();
            for(ComentarioDTO comentarioDto : comentarios)
            {
                comentariosEntity.add(comentarioDto.toEntity());
            }
            usuarioEntity.setComentarios(comentariosEntity);
        }
	if(prestamos != null)
        {
            ArrayList<PrestamoEntity> prestamosEntity = new ArrayList<>();
            for(PrestamoDTO prestamoDto : prestamos)
            {
                prestamosEntity.add(prestamoDto.toEntity());
            }
            usuarioEntity.setPrestamos(prestamosEntity);
        }
	if(reservas != null)
        {
            ArrayList<ReservaEntity> reservasEntity = new ArrayList<>();
            for(ReservaDTO reservaDto : reservas)
            {
                reservasEntity.add(reservaDto.toEntity());
            }
            usuarioEntity.setReservas(reservasEntity);
        }
        return usuarioEntity;
    }
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
