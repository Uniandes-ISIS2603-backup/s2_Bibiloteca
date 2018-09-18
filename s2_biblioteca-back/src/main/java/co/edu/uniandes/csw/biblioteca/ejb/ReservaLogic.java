/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.ReservaEntity;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.ReservaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 * Reserva
 * @author Daniel Preciado
 */
@Stateless
public class ReservaLogic {
    
    //__________________________________________________________________________
    //Constantes
    //__________________________________________________________________________

    /**
     *  constante utilizada para dejar huella y registro
     */
    private static final Logger LOGGER = Logger.getLogger(ReservaLogic.class.getName());

    //__________________________________________________________________________
    //Atributos
    //__________________________________________________________________________
    /**
     * Variable para acceder a la persistencia de la aplicación
     * Es una inyección de dependencias.
     */
    @Inject
    private ReservaPersistence persistencia;  

    //__________________________________________________________________________
    //Metodos
    //__________________________________________________________________________
    
    
    /**
     * Crea una reserva en la persistencia.
     * @param reservaEntity La entidad que representa la reserva a
     * persistir.
     * @return La entidad de la reserva luego de persistirla.
     * @throws BusinessLogicException Si la reserva no se persiste por una regla
     * de negocio.
     */
    public ReservaEntity createReserva(ReservaEntity reservaEntity) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la resserva");
        
        // Verifica la regla de negocio que dice que no puede haber dos editoriales con el mismo nombre
        if (persistencia.findByName(reservaEntity.getName()) != null) {
            throw new BusinessLogicException("Ya existe una Editorial con el nombre \"" + reservaEntity.getName() + "\"");
        }
        // Invoca la persistencia para crear la editorial
        persistencia.create(reservaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la editorial");
        return reservaEntity;
    }

    /**
     *
     * Obtener todas las editoriales existentes en la base de datos.
     *
     * @return una lista de editoriales.
     */
    public List<EditorialEntity> getEditorials() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las editoriales");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<EditorialEntity> editorials = persistencia.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las editoriales");
        return editorials;
    }

    /**
     *
     * Obtener una editorial por medio de su id.
     *
     * @param editorialsId: id de la editorial para ser buscada.
     * @return la editorial solicitada por medio de su id.
     */
    public EditorialEntity getEditorial(Long editorialsId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la editorial con id = {0}", editorialsId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        EditorialEntity editorialEntity = persistencia.find(editorialsId);
        if (editorialEntity == null) {
            LOGGER.log(Level.SEVERE, "La editorial con el id = {0} no existe", editorialsId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la editorial con id = {0}", editorialsId);
        return editorialEntity;
    }

    /**
     *
     * Actualizar una editorial.
     *
     * @param editorialsId: id de la editorial para buscarla en la base de
     * datos.
     * @param editorialEntity: editorial con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return la editorial con los cambios actualizados en la base de datos.
     */
    public EditorialEntity updateEditorial(Long editorialsId, EditorialEntity editorialEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la editorial con id = {0}", editorialsId);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        EditorialEntity newEntity = persistencia.update(editorialEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la editorial con id = {0}", editorialEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un editorial
     *
     * @param editorialsId: id de la editorial a borrar
     * @throws BusinessLogicException Si la editorial a eliminar tiene libros.
     */
    public void deleteEditorial(Long editorialsId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la editorial con id = {0}", editorialsId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        List<BookEntity> books = getEditorial(editorialsId).getBooks();
        if (books != null && !books.isEmpty()) {
            throw new BusinessLogicException("No se puede borrar la editorial con id = " + editorialsId + " porque tiene books asociados");
        }
        persistencia.delete(editorialsId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la editorial con id = {0}", editorialsId);
    }
    
}
