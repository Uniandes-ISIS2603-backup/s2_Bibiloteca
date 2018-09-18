/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.VideoDigitalEntity;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.VideoDigitalPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class VideoDigitalLogic {

    private static final Logger LOGGER = Logger.getLogger(VideoDigitalLogic.class.getName());

    @Inject
    private VideoDigitalPersistence persistence; 

    /**
     * Crea una videoDigital en la persistencia.
     *
     * @param videoDigitalEntity La entidad que representa el videoDigital a
     * persistir.
     * @return La entiddad de el videoDigital luego de persistirla.
     * @throws BusinessLogicException Si el videoDigital a persistir ya existe.
     */
    public VideoDigitalEntity createVideoDigital(VideoDigitalEntity videoDigitalEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del videoDigital");
        // Verifica la regla de negocio que dice que no puede haber dos videosDigitales con el mismo nombre
        if (persistence.findNombre(videoDigitalEntity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe un VideoDigital con el nombre \"" + videoDigitalEntity.getNombre() + "\"");
        }
        // Invoca la persistencia para crear la videoDigital
        persistence.create(videoDigitalEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del videoDigital");
        return videoDigitalEntity;
    }

    /**
     *
     * Obtener todos los videoDigitales existentes en la base de datos.
     *
     * @return una lista de videosDigitales.
     */
    public List<VideoDigitalEntity> getVideosDigitales() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los videoDigitales");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<VideoDigitalEntity> videosDigitales = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los videosDigitales");
        return videosDigitales;
    }

    /**
     *
     * Obtener un videoDigital por medio de su id.
     *
     * @param videosDigitalesId: id del videoDigital para ser buscado.
     * @return el videoDigital solicitado por medio de su id.
     */
    public VideoDigitalEntity getVideoDigital(Long videosDigitalesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el videoDigital con id = {0}", videosDigitalesId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        VideoDigitalEntity videoDigitalEntity = persistence.find(videosDigitalesId);
        if (videoDigitalEntity == null) {
            LOGGER.log(Level.SEVERE, "El videoDigital con el id = {0} no existe", videosDigitalesId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el videoDigital con id = {0}", videosDigitalesId);
        return videoDigitalEntity;
    }

    /**
     *
     * Actualizar una videoDigital.
     *
     * @param videosDigitalesId: id del videoDigital para buscarlo en la base de
     * datos.
     * @param videoDigitalEntity: videoDigital con los cambios para ser actualizado,
     * por ejemplo el nombre.
     * @return el videoDigital con los cambios actualizados en la base de datos.
     */
    public VideoDigitalEntity updateVideoDigital(Long videosDigitalesId, VideoDigitalEntity videoDigitalEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la videoDigital con id = {0}", videosDigitalesId);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        VideoDigitalEntity newEntity = persistence.update(videoDigitalEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la videoDigital con id = {0}", videoDigitalEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un videoDigital
     *
     * @param videosDigitalesId: id del videoDigital a borrar
     */
    public void deleteVideoDigital(Long videosDigitalesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el videoDigital con id = {0}", videosDigitalesId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        persistence.delete(videosDigitalesId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el videoDigital con id = {0}", videosDigitalesId);
    }
}
    