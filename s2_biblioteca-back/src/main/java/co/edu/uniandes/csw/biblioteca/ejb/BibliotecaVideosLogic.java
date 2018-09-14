/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.BibliotecaEntity;
import co.edu.uniandes.csw.bibilioteca.entities.VideoEntity;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.BibliotecaPersistence;
import co.edu.uniandes.csw.biblioteca.persistence.VideoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Biblioteca y video.
 * @author estudiante David Eduardo Saavedra Hernandez
 */
@Stateless
public class BibliotecaVideosLogic 
{
    private static final Logger LOGGER = Logger.getLogger(BibliotecaVideosLogic.class.getName());
    
     @Inject
    private VideoPersistence videoPersistence;
     
    @Inject
    private BibliotecaPersistence bibliotecaPersistence;
    
    /**
     * Agregar un video a la biblioteca.
     *
     * @param videoId El id del video a guardar
     * @param editorialsId El id de la biblioteca en la cual se va a guardar el
     * video.
     * @return El video creado.
     */
    public VideoEntity addVideo(Long videoId, Long editorialsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un video a la biblioteca con id = {0}", editorialsId);
        BibliotecaEntity bibliotecaEntity = bibliotecaPersistence.find(editorialsId);
        VideoEntity videoEntity = videoPersistence.find(videoId);
        videoEntity.setBiblioteca(bibliotecaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un video a la biblioteca con id = {0}", editorialsId);
        return videoEntity;
    }
    
    /**
     * Retorna todos loslibros asociados a una biblioteca
     *
     * @param bibliotecaId El ID de la biblioteca buscada
     * @return La lista de videos de la biblioteca
     */
    public List<VideoEntity> getVideos (Long bibliotecaId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los videos asociados a la biblioteca con id = {0}", bibliotecaId);
        return bibliotecaPersistence.find(bibliotecaId).getVideos();
    }
    
    /**
     * Retorna un video asociado a una biblioteca
     *
     * @param bibliotecaId El id de la biblioteca a buscar.
     * @param videoId El id del video a buscar
     * @return El video encontrado dentro de la biblioteca.
     * @throws BusinessLogicException Si el video no se encuentra en la
     * biblioteca
     */
    public VideoEntity getVideo(Long bibliotecaId, Long videoId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el video con id = {0} de la biblioteca con id = " + bibliotecaId, videoId);
        List<VideoEntity> videos = bibliotecaPersistence.find(bibliotecaId).getVideos();
        VideoEntity videoEntity = videoPersistence.find(videoId);
        int index = videos.indexOf(videoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el video con id = {0} de la biblioteca con id = " + bibliotecaId, videoId);
        if (index >= 0) {
            return videos.get(index);
        }
        throw new BusinessLogicException("El video no está asociado a la biblioteca");
    }
    
    /**
     * Remplazar videos de una editorial
     *
     * @param videos Lista de videos que serán los de la editorial.
     * @param bibliotecaId El id de la biblioteca que se quiere actualizar.
     * @return La lista de videos actualizada.
     */
    public List<VideoEntity> replaceVideos(Long bibliotecaId, List<VideoEntity> videos) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la biblioteca con id = {0}", bibliotecaId);
        BibliotecaEntity bibliotecaEntity = bibliotecaPersistence.find(bibliotecaId);
        List<VideoEntity> videoList = videoPersistence.findAll();
        for (VideoEntity video : videoList) {
            if (videos.contains(video)) {
                video.setBiblioteca(bibliotecaEntity);
            } else if (video.getBiblioteca() != null && video.getBiblioteca().equals(bibliotecaEntity)) {
                video.setBiblioteca(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la biblioteca con id = {0}", bibliotecaId);
        return videos;
    }
}
