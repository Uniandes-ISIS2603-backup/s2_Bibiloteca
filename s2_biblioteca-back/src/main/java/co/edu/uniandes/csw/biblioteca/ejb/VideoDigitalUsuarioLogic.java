/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.ejb;
import co.edu.uniandes.csw.bibilioteca.entities.VideoDigitalEntity;
import co.edu.uniandes.csw.bibilioteca.entities.UsuarioEntity;
import co.edu.uniandes.csw.biblioteca.persistence.UsuarioPersistence;
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
public class VideoDigitalUsuarioLogic {

    private static final Logger LOGGER = Logger.getLogger(VideoDigitalUsuarioLogic.class.getName());

    @Inject
    private VideoDigitalPersistence videoDigitalPersistence;

    @Inject
    private UsuarioPersistence usuarioPersistence;

    /**
     * Asocia un Usuario existente a un VideoDigital
     *
     * @param videodigitalid Identificador de la instancia de VideoDigital
     * @param usuariosId Identificador de la instancia de Usuario
     * @return Instancia de UsuarioEntity que fue asociada a VideoDigital
     */
    public UsuarioEntity addUsuario(Long videodigitalid, Long usuariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle un usuario al videoDigital con id = {0}", videodigitalid);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuariosId);
        VideoDigitalEntity videoDigitalEntity = videoDigitalPersistence.find(videodigitalid);
        videoDigitalEntity.getUsuarios().add(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle un usuario al videoDigital con id = {0}", videodigitalid);
        return usuarioPersistence.find(usuariosId);
    }

    /**
     * Obtiene una colección de instancias de UsuarioEntity asociadas a una
     * instancia de VideoDigital
     *
     * @param videodigitalid Identificador de la instancia de VideoDigital
     * @return Colección de instancias de UsuarioEntity asociadas a la instancia
     * de VideoDigital
     */
    public List<UsuarioEntity> getUsuarios(Long videodigitalid) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los usuarios del videoDigital con id = {0}", videodigitalid);
        return videoDigitalPersistence.find(videodigitalid).getUsuarios();
    }

    /**
     * Obtiene una instancia de UsuarioEntity asociada a una instancia de VideoDigital
     *
     * @param videodigitalidIdentificador de la instancia de VideoDigital
     * @param usuariosId Identificador de la instancia de Usuario
     * @return La entidad del Usuario asociada al videoDigital
     */
    public UsuarioEntity getUsuario(Long videodigitalid, Long usuariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un usuario del videoDigital con id = {0}", videodigitalid);
        List<UsuarioEntity> usuarios = videoDigitalPersistence.find(videodigitalid).getUsuarios();
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuariosId);
        int index = usuarios.indexOf(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar un usuario del videoDigital con id = {0}", videodigitalid);
        if (index >= 0) {
            return usuarios.get(index);
        }
        return null;
    }

    /**
     * Remplaza las instancias de Usuario asociadas a una instancia de VideoDigital
     *
     * @param videodigitalid Identificador de la instancia de VideoDigital
     * @param list Colección de instancias de UsuarioEntity a asociar a instancia
     * de VideoDigital
     * @return Nueva colección de UsuarioEntity asociada a la instancia de VideoDigital
     */
    public List<UsuarioEntity> replaceUsuarios(Long videodigitalid, List<UsuarioEntity> list) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los usuarios del videoDigital con id = {0}", videodigitalid);
        VideoDigitalEntity videoDigitalEntity = videoDigitalPersistence.find(videodigitalid);
        videoDigitalEntity.setUsuarios(list);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar los usuarios del videoDigital con id = {0}", videodigitalid);
        return videoDigitalPersistence.find(videodigitalid).getUsuarios();
    }

    /**
     * Desasocia un Usuario existente de un VideoDigital existente
     *
     * @param videodigitalid Identificador de la instancia de VideoDigital
     * @param usuariosId Identificador de la instancia de Usuario
     */
    public void removeUsuario(Long videodigitalid, Long usuariosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar un usuario del videoDigital con id = {0}", videodigitalid);
        UsuarioEntity usuarioEntity = usuarioPersistence.find(usuariosId);
        VideoDigitalEntity videoDigitalEntity = videoDigitalPersistence.find(videodigitalid);
        videoDigitalEntity.getUsuarios().remove(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de borrar un usuario del videoDigital con id = {0}", videodigitalid);
    }
}