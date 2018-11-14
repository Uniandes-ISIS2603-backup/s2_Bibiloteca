/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.UsuarioEntity;
import co.edu.uniandes.csw.bibilioteca.entities.VideoDigitalEntity;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.UsuarioPersistence;
import co.edu.uniandes.csw.biblioteca.persistence.VideoDigitalPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author juan bautista
 */
@Stateless
public class UsuarioVideoDigitalLogic {
    
    private static final Logger LOGGER = Logger.getLogger(UsuarioVideoDigitalLogic.class.getName());

    @Inject
    private VideoDigitalPersistence videoDigitalPersistence;

    @Inject
    private UsuarioPersistence usuarioPersistence;

    /**
     * Asocia un VideoDigital existente a un Usuario
     *
     * @param usuariosId Identificador de la instancia de Usuario
     * @param videosDigitalesId Identificador de la instancia de VideoDigital
     * @return Instancia de BookEntity que fue asociada a Usuario
     */
    public VideoDigitalEntity addVideoDigital(Long usuariosId, Long videosDigitalesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un video digital al usuario con id = {0}", usuariosId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuariosId);
        VideoDigitalEntity videoDigitalEntity = videoDigitalPersistence.find(videosDigitalesId);
        videoDigitalEntity.getUsuarios().add(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un video digital al usuario con id = {0}", usuariosId);
        return videoDigitalPersistence.find(videosDigitalesId);
    }

    /**
     * Obtiene una colección de instancias de VideoDigitalEntity asociadas a una
     * instancia de Usuario
     *
     * @param usuariosId Identificador de la instancia de Usuario
     * @return Colección de instancias de BookEntity asociadas a la instancia de
     * Usuario
     */
    public List<VideoDigitalEntity> getVideosDigitales(Long usuariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los videos digitales del usuario con id = {0}", usuariosId);
        return usuarioPersistence.find(usuariosId).getVideosDigitales();
    }

    /**
     * Obtiene una instancia de VideoDigitalEntity asociada a una instancia de Usuario
     *
     * @param usuariosId Identificador de la instancia de Usuario
     * @param videosDigitalesId Identificador de la instancia de Book
     * @return La entidad de VideoDigital del usuario
     * @throws BusinessLogicException Si el video digital no está asociado al usuario
     */
    public VideoDigitalEntity getVideoDigital(Long usuariosId, Long videosDigitalesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el video digital con id = {0} del usuario con id = " + usuariosId, videosDigitalesId);
        List<VideoDigitalEntity> videosDigitales = usuarioPersistence.find(usuariosId).getVideosDigitales();
        VideoDigitalEntity videoDigitalEntity = videoDigitalPersistence.find(videosDigitalesId);
        int index = videosDigitales.indexOf(videoDigitalEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el video digital con id = {0} del usuario con id = " + usuariosId, videosDigitalesId);
        if (index >= 0) {
            return videosDigitales.get(index);
        }
        throw new BusinessLogicException("El video digital no está asociado al usuario");
    }

    /**
     * Remplaza las instancias de VideoDigital asociadas a una instancia de Usuario
     *
     * @param usuarioId Identificador de la instancia de Usuario
     * @param videosDigitales Colección de instancias de BookEntity a asociar a instancia
     * de Usuario
     * @return Nueva colección de BookEntity asociada a la instancia de Usuario
     */
    public List<VideoDigitalEntity> replaceVideosDigitales(Long usuarioId, List<VideoDigitalEntity> videosDigitales) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los videos digitales asocidos al usuario con id = {0}", usuarioId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuarioId);
        List<VideoDigitalEntity> videoDigitalList = videoDigitalPersistence.findAll();
        for (VideoDigitalEntity videoDigital : videoDigitalList) {
            if (videosDigitales.contains(videoDigital)) {
                if (!videoDigital.getUsuarios().contains(usuarioEntity)) {
                    videoDigital.getUsuarios().add(usuarioEntity);
                }
            } else {
                videoDigital.getUsuarios().remove(usuarioEntity);
            }
        }
        usuarioEntity.setVideosDigitales(videosDigitales);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los videos digitales asocidos al usuario con id = {0}", usuarioId);
        return usuarioEntity.getVideosDigitales();
    }

    /**
     * Desasocia un VideoDigital existente de un Usuario existente
     *
     * @param usuariosId Identificador de la instancia de Usuario
     * @param videosDigitalesId Identificador de la instancia de VideoDigital
     */
    public void removeVideoDigital(Long usuariosId, Long videosDigitalesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un video digital del usuario con id = {0}", usuariosId);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuariosId);
        VideoDigitalEntity videoDigitalEntity = videoDigitalPersistence.find(videosDigitalesId);
        videoDigitalEntity.getUsuarios().remove(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un video digital del usuario con id = {0}", usuariosId);
    }

}
