/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.dtos;

import co.edu.uniandes.csw.bibilioteca.entities.ComentarioEntity;
import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import co.edu.uniandes.csw.bibilioteca.entities.PrestamoEntity;
import co.edu.uniandes.csw.bibilioteca.entities.ReservaEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Montoya
 */
public class LibroDetailDTO extends LibroDTO implements Serializable {
    
    private List<ComentarioDTO> comentarios;
    
    private List<PrestamoDTO> prestamos;
    
    private List<ReservaDTO> reservas;
    
    public LibroDetailDTO(){
        super();
    }
    
    public LibroDetailDTO(LibroEntity libro){
        super(libro);
        if(libro.getComentarios() != null)
        {
            comentarios = new ArrayList<>();
            for(ComentarioEntity comentario : libro.getComentarios())
            {
                comentarios.add(new ComentarioDTO(comentario));
            }
        }
        if(libro.getPrestamos() != null)
        {
            prestamos = new ArrayList<>();
            for(PrestamoEntity prestamo : libro.getPrestamos())
            {
                prestamos.add(new PrestamoDTO(prestamo));
            }
        }
        if(libro.getReservas() != null)
        {
            reservas = new ArrayList<>();
            for(ReservaEntity reserva : libro.getReservas())
            {
                reservas.add(new ReservaDTO(reserva));
            }
        }
    }

    public List<ComentarioDTO> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioDTO> comentarios) {
        this.comentarios = comentarios;
    }

    public List<PrestamoDTO> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<PrestamoDTO> prestamos) {
        this.prestamos = prestamos;
    }

    public List<ReservaDTO> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaDTO> reservas) {
        this.reservas = reservas;
    }
    
    @Override
    public LibroEntity toEntity()
    {
        LibroEntity libroEntity = super.toEntity();
        if(comentarios != null)
        {
            List<ComentarioEntity> comentariosEntity = new ArrayList<>();
            for(ComentarioDTO comentarioDto : comentarios)
            {
                comentariosEntity.add(comentarioDto.toEntity());
            }
            libroEntity.setComentarios(comentariosEntity);
        }
        if(prestamos != null)
        {
            List<PrestamoEntity> prestamosEntity = new ArrayList<>();
            for(PrestamoDTO prestamoDTO : prestamos)
            {
                prestamosEntity.add(prestamoDTO.toEntity());
            }
            libroEntity.setPrestamos(prestamosEntity);
        }
        if(reservas != null)
        {
            List<ReservaEntity> reservasEntity = new ArrayList<>();
            for(ReservaDTO reservaDTO : reservas)
            {
                reservasEntity.add(reservaDTO.toEntity());
            }
            libroEntity.setReservas(reservasEntity);
        }
        return libroEntity;
    }
}
