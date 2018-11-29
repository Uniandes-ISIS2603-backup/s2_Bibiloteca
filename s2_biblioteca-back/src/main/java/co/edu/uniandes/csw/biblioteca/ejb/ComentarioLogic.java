/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.ComentarioEntity;
import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import co.edu.uniandes.csw.bibilioteca.entities.PrestamoEntity;
import co.edu.uniandes.csw.bibilioteca.entities.UsuarioEntity;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.ComentarioPersistence;
import co.edu.uniandes.csw.biblioteca.persistence.LibroPersistence;
import co.edu.uniandes.csw.biblioteca.persistence.UsuarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Daniel Montoya
 */
@Stateless
public class ComentarioLogic {

    private static final Logger LOGGER = Logger.getLogger(ComentarioLogic.class.getName());

    @Inject
    private ComentarioPersistence persistencia;

    @Inject
    private LibroPersistence libroPersistencia;

    @Inject
    private UsuarioPersistence usuarioPersistencia;

    public ComentarioEntity createComentario(Long libroId, ComentarioEntity comentario, Long usuarioId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia el proceso de crear un comentario del libro con id = " + comentario.getLibro().getId());
        LibroEntity libroParaComentario = libroPersistencia.find(comentario.getLibro().getId());
        UsuarioEntity usuarioCreador = usuarioPersistencia.find(comentario.getUsuario().getId());
        boolean devuelto = false;
        for (PrestamoEntity prestamo : usuarioCreador.getPrestamos()) {
            if (prestamo.getLibro().getId().equals(libroId) && prestamo.getRetornado()) {
                devuelto = true;
            }
        }
        if (devuelto) {
            comentario.setLibro(libroParaComentario);
            comentario.setUsuario(usuarioCreador);
            comentario.setNombreUsuario(usuarioCreador.getNombre());
            LOGGER.log(Level.INFO, "El comentario fue creado exitosamente");
            return persistencia.create(comentario);
        } else {
            throw new BusinessLogicException("No puede crear el comentario del libro con id = " + libroId + " por que no lo ha devuelto");
        }
    }

    public List<ComentarioEntity> getComentariosPorLibro(Long libroId) {
        LOGGER.log(Level.INFO, "Se comienza a buscar todos los comentarios sobre un libro con id={0}", libroId);
        LibroEntity libro = libroPersistencia.find(libroId);
        LOGGER.log(Level.INFO, "Termina la consulta de los comentarios de un libro on id={0}", libroId);
        return libro.getComentarios();

    }

    public List<ComentarioEntity> getComentariosPorUsuario(Long usuarioId) {
        LOGGER.log(Level.INFO, "Se comienza a buscar los comentarios de un usuario con id={0}", usuarioId);
        UsuarioEntity usuario = usuarioPersistencia.find(usuarioId);
        LOGGER.log(Level.INFO, "Termina la consulta de los comentarios de un usuario on id={0}", usuarioId);
        return usuario.getComentarios();
    }

    public ComentarioEntity getComentarioLibro(Long libroId, Long comentarioId) {
        LOGGER.log(Level.INFO, "Se comienza a buscar todos los comentarios con id={0}, de un libro con id= " + libroId, comentarioId);
        return persistencia.find(libroId, comentarioId);
    }

    public ComentarioEntity getComentarioUsuario(Long usuarioId, Long comentarioId) {
        LOGGER.log(Level.INFO, "Se comienza a buscar todos los comentarios con id={0}, de un usuario con id= " + usuarioId, comentarioId);
        return persistencia.findUsuario(usuarioId, comentarioId);
    }

    /**
     * Actualiza la informacion de una instancia Comentario
     * @param libroId id del libro el cual sera padre del comentario actualizado
     * @param comentario Instancia del comentario con los nuevos datos
     * @param usuarioId id del usuario el cual sera padre del comentario actualizado
     * @return Instancia de ComentarioEntity con los datos actualizados
     */
    public ComentarioEntity updateComentario(Long libroId, ComentarioEntity comentario,Long usuarioId) {
        LOGGER.log(Level.INFO, "Se comienza la actualizar el comentario con id={0} del libro con id = "+ libroId + "del usuario con id = " + usuarioId,comentario.getId());
        LibroEntity libro = libroPersistencia.find(libroId);
        UsuarioEntity usuario = usuarioPersistencia.find(usuarioId);
        comentario.setLibro(libro);
        comentario.setUsuario(usuario);
        comentario.setNombreUsuario(usuario.getNombre());
        persistencia.update(comentario);
        LOGGER.log(Level.INFO, "Se termina la actualizacion del comentario con id = {0}",comentario.getId());
        return comentario;
    }
    
    public void deleteComentario(Long libroId, Long comentarioId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO,"Inicia proceso de borrar el review con id = {0} del libro con id = " + libroId, comentarioId);
        ComentarioEntity eliminar = getComentarioLibro(libroId, comentarioId);
        if(eliminar == null)
        {
            throw new BusinessLogicException("El comentario con id = "+ comentarioId + " no esta asociado a el libro con id = " + comentarioId);
        }
        persistencia.delete(eliminar.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el review con id = {0} del libro con id = " + libroId, comentarioId);
    }
}
