/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.UsuarioEntity;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.UsuarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Nicolás García
 */
@Stateless
public class UsuarioLogic {
         private static final Logger LOGGER = Logger.getLogger(BibliotecaLogic.class.getName());

    @Inject
    private UsuarioPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
    /**
     * Crea un usuario en la persistencia.
     *
     * @param usuarioEntity La entidad que representa  a
     * persistir.
     * @return La entidad del usuario luego de persistirla.
     * @throws BusinessLogicException Si el usuario a persistir ya existe.
     */
    public UsuarioEntity createUsuario(UsuarioEntity usuarioEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del usuario");
        // Verifica la regla de negocio que dice que no puede haber dos usuarios con el mismo nombre
        if (persistence.findNombre(usuarioEntity.getNombre()) != null) {
           throw new BusinessLogicException("Ya existe un usuario con el nombre \"" + usuarioEntity.getNombre() + "\"");
        }
        // Invoca la persistencia para crear la editorial
        persistence.create(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la editorial");
        return usuarioEntity;
    }
    
    /**
     *
     * Obtener todas los usuarios existentes en la base de datos.
     *
     * @return una lista de editoriales.
     */
    public List<UsuarioEntity> getUsuarios() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las bibliotecas");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<UsuarioEntity> usuarios = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las Biblioteca");
        return usuarios;
    }
    
     /**
     *
     * Obtener un usuario por medio de su id.
     *
     * @param usuarioId: id del Usuario para ser buscado.
     * @return el usuario solicitado por medio de su id.
     */
    public UsuarioEntity getUsuario(Long usuarioId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el usuario con id = {0}", usuarioId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        UsuarioEntity usuarioEntity = persistence.find(usuarioId);
        if (usuarioEntity == null) {
            LOGGER.log(Level.SEVERE, "El usuario con el id = {0} no existe", usuarioId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el usuario con id = {0}", usuarioId);
        return usuarioEntity;
    }
    
     /**
     *
     * Actualizar un Usuario.
     * @param usuarioId: id del usuario para buscarla en la base de
     * datos.
     * @param usuarioEntity: usuario con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return el usuario con los cambios actualizados en la base de datos.
     */
    public UsuarioEntity updateUsuario(Long usuarioId, UsuarioEntity usuarioEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el usuario con id = {0}", usuarioId);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        UsuarioEntity newEntity = persistence.update(usuarioEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el usuario con id = {0}", usuarioEntity.getId());
        return newEntity;
    }
}
