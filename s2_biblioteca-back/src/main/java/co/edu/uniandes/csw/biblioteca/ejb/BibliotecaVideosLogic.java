/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.BibliotecaEntity;
import co.edu.uniandes.csw.bibilioteca.entities.VideoEntity;
import co.edu.uniandes.csw.biblioteca.persistence.BibliotecaPersistence;
import co.edu.uniandes.csw.biblioteca.persistence.VideoPersistence;
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
    public VideoEntity addBook(Long videoId, Long editorialsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un video a la biblioteca con id = {0}", editorialsId);
        BibliotecaEntity bibliotecaEntity = bibliotecaPersistence.find(editorialsId);
        VideoEntity videoEntity = videoPersistence.find(videoId);
        videoEntity.setBiblioteca(bibliotecaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un video a la biblioteca con id = {0}", editorialsId);
        return videoEntity;
    }

}
