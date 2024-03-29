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
public class UsuarioDetailDTO extends UsuarioDTO implements Serializable {

    private List<ComentarioDTO> comentarios;
    private List<PrestamoDTO> prestamos;
    private List<ReservaDTO> reservas;
    private List<VideoDigitalDTO> videosDigitales;

    public UsuarioDetailDTO() {
        super();
    }

    public UsuarioDetailDTO(UsuarioEntity usuario) {
        super(usuario);
        if (usuario != null) {
            videosDigitales = new ArrayList<>();
            for (VideoDigitalEntity entityVideoDigital : usuario.getVideosDigitales()) {
                videosDigitales.add(new VideoDigitalDTO(entityVideoDigital));
            }
            comentarios = new ArrayList<>();
            for (ComentarioEntity comentario : usuario.getComentarios()) {
                comentarios.add(new ComentarioDTO(comentario));
            }

            prestamos = new ArrayList<>();
            for (PrestamoEntity prestamo : usuario.getPrestamos()) {
                prestamos.add(new PrestamoDTO(prestamo));
            }

            reservas = new ArrayList<>();
            for (ReservaEntity reserva : usuario.getReservas()) {
                reservas.add(new ReservaDTO(reserva));
            }
        }
    }

    public List<ComentarioDTO> getComentarios() {
        return comentarios;
    }

    public List<PrestamoDTO> getPrestamos() {
        return prestamos;
    }

    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    public void setComentarios(List<ComentarioDTO> comentarios) {
        this.comentarios = comentarios;
    }

    public void setPrestamos(List<PrestamoDTO> prestamos) {
        this.prestamos = prestamos;
    }

    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }

    public List<VideoDigitalDTO> getVideosDigitales() {
        return videosDigitales;
    }

    public void setVideosDigitales(List<VideoDigitalDTO> videosDigitales) {
        this.videosDigitales = videosDigitales;
    }

    @Override
    public UsuarioEntity toEntity() {
        UsuarioEntity usuarioEntity = super.toEntity();
        if (videosDigitales != null) {
            List<VideoDigitalEntity> videosDigitalesEntity = new ArrayList<>();
            for (VideoDigitalDTO dtoVideoDigital : videosDigitales) {
                videosDigitalesEntity.add(dtoVideoDigital.toEntity());
            }
            usuarioEntity.setVideosDigitales(videosDigitalesEntity);
        }
        if (comentarios != null) {
            List<ComentarioEntity> comentariosEntity = new ArrayList<>();
            for (ComentarioDTO comentarioDto : comentarios) {
                comentariosEntity.add(comentarioDto.toEntity());
            }
            usuarioEntity.setComentarios(comentariosEntity);
        }
        if (prestamos != null) {
            List<PrestamoEntity> prestamosEntity = new ArrayList<>();
            for (PrestamoDTO prestamoDto : prestamos) {
                prestamosEntity.add(prestamoDto.toEntity());
            }
            usuarioEntity.setPrestamos(prestamosEntity);
        }
        if (reservas != null) {
            List<ReservaEntity> reservasEntity = new ArrayList<>();
            for (ReservaDTO reservaDto : reservas) {
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
