/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.BibliotecaEntity;
import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import co.edu.uniandes.csw.bibilioteca.entities.SalaEntity;
import co.edu.uniandes.csw.bibilioteca.entities.VideoEntity;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.BibliotecaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *  Clase que implementa la conexion con la persistencia para la entidad Biblioteca
 * @author David Eduardo Saavedra Hernández
 */
@Stateless
public class BibliotecaLogic 
{
     private static final Logger LOGGER = Logger.getLogger(BibliotecaLogic.class.getName());

    @Inject
    private BibliotecaPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    
    /**
     * Crea una Biblioteca en la persistencia.
     *
     * @param bibliotecaEntity La entidad que representa la biblioteca a
     * persistir.
     * @return La entiddad de la biblioteca luego de persistirla.
     * @throws BusinessLogicException Si la biblioteca a persistir ya existe.
     */
    public BibliotecaEntity createBiblioteca(BibliotecaEntity bibliotecaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la editorial");
        // Verifica la regla de negocio que dice que no puede haber dos editoriales con el mismo nombre
        if (persistence.findByName(bibliotecaEntity.getNombre()) != null) {
            throw new BusinessLogicException("Ya existe una Editorial con el nombre \"" + bibliotecaEntity.getNombre() + "\"");
        }
        // Invoca la persistencia para crear la editorial
        persistence.create(bibliotecaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la editorial");
        return bibliotecaEntity;
    }
    
    /**
     *
     * Obtener todas las Bibliotecas existentes en la base de datos.
     *
     * @return una lista de editoriales.
     */
    public List<BibliotecaEntity> getBibliotecas() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las bibliotecas");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<BibliotecaEntity> editorials = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las Biblioteca");
        return editorials;
    }
    
     /**
     *
     * Obtener una biblioteca por medio de su id.
     *
     * @param bibliotecaId: id de la Biblioteca para ser buscada.
     * @return la biblioteca solicitada por medio de su id.
     */
    public BibliotecaEntity getBiblioteca(Long bibliotecaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la biblioteca con id = {0}", bibliotecaId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        BibliotecaEntity bibliotecaEntity = persistence.find(bibliotecaId);
        if (bibliotecaEntity == null) {
            LOGGER.log(Level.SEVERE, "La biblioteca con el id = {0} no existe", bibliotecaId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la biblioteca con id = {0}", bibliotecaId);
        return bibliotecaEntity;
    }
    
     /**
     *
     * Actualizar una Biblioteca.
     * @param bibliotecaId: id de la biblioteca para buscarla en la base de
     * datos.
     * @param bibliotecaEntity: ebiblioteca con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return la biblioteca con los cambios actualizados en la base de datos.
     */
    public BibliotecaEntity updateBiblioteca(Long bibliotecaId, BibliotecaEntity bibliotecaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la biblioteca con id = {0}", bibliotecaId);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        BibliotecaEntity newEntity = persistence.update(bibliotecaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la biblioteca con id = {0}", bibliotecaEntity.getId());
        return newEntity;
    }
    
     /**
     * Borrar una biblioteca
     *
     * @param bibliotecaId: id de la biblioteca a borrar
     * @throws BusinessLogicException Si la biblioteca a eliminar tiene libros o video o salas. 
     */
    public void deleteBiblioteca(Long bibliotecaId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la biblioteca con id = {0}", bibliotecaId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        List<LibroEntity> books = getBiblioteca(bibliotecaId).getLibros();
        List<SalaEntity> salas = getBiblioteca(bibliotecaId).getSalas();
        List<VideoEntity> videos = getBiblioteca(bibliotecaId).getVideos();
        if (books != null && !books.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar la biblioteca con id = " + bibliotecaId + " porque tiene libros asociados");
        }         
        else if (salas != null && !salas.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar la biblioteca con id = " + bibliotecaId + " porque tiene salas asociados");
        }
         else if (videos!= null && !videos.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar la biblioteca con id = " + bibliotecaId + " porque tiene videos asociados");
        }
        persistence.delete(bibliotecaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la biblioteca con id = {0}", bibliotecaId);
    }
}
