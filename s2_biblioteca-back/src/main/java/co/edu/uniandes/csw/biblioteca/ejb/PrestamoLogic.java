/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.ComentarioEntity;
import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import co.edu.uniandes.csw.bibilioteca.entities.PrestamoEntity;
import co.edu.uniandes.csw.bibilioteca.entities.SalaEntity;
import co.edu.uniandes.csw.bibilioteca.entities.VideoEntity;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.ComentarioPersistence;
import co.edu.uniandes.csw.biblioteca.persistence.LibroPersistence;
import co.edu.uniandes.csw.biblioteca.persistence.PrestamoPersistence;
import co.edu.uniandes.csw.biblioteca.persistence.ReservaPersistence;
import co.edu.uniandes.csw.biblioteca.persistence.SalaPersistence;
import co.edu.uniandes.csw.biblioteca.persistence.VideoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author David Eduardo Saavedra Hernández
 */
@Stateless
public class PrestamoLogic 
{
    private static final Logger LOGGER = Logger.getLogger(PrestamoLogic.class.getName());

    @Inject
    private PrestamoPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private LibroPersistence libroPersistence;
    
    @Inject
    private SalaPersistence salaPersistence;
     
    @Inject
    private VideoPersistence videoPersistence;
    
    @Inject
    private ComentarioPersistence comentarioPersistence;
    
    @Inject
    private ReservaPersistence reservaPersistence;
    
    /**
     * Crea un prestamo en la persistencia.
     *
     * @param prestamoEntity La entidad que representa el prestamo a
     * persistir.
     * @return La entiddad del prestamo luego de persistirla.
     * @throws BusinessLogicException Si el libro, video o recurso ya está prestado o si está reservado. 
     */
    public PrestamoEntity createPrestamo(PrestamoEntity prestamoEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del prestamo");
        // Verifica la regla de negocio que dice que no puede haber un prestamo si el libro no está disponible o está reservado. 
          if(prestamoEntity.getTipoRecurso()==null || prestamoEntity.getIdRecursoPrestado()==null){
          throw new BusinessLogicException("El libro \"" + prestamoEntity.getIdRecursoPrestado()+"lo otro"+ prestamoEntity.getTipoRecurso()+ "\"no tiene unidades disponibles ");
       }
          PrestamoEntity validacion = persistence.findByIdRecursoPrestado(prestamoEntity.getIdRecursoPrestado(), prestamoEntity.getTipoRecurso());
         if( validacion == null)
        {
          String tipo = prestamoEntity.getTipoRecurso();
           if(tipo.equals("LIBRO")){
               LibroEntity libro= libroPersistence.find(prestamoEntity.getIdRecursoPrestado());
           
            Integer unidadesDisponibles = libro.getUnidadesDisponibles();
            if ( unidadesDisponibles == 0)
            {
                 throw new BusinessLogicException("El libro \"" + libro.getNombre() + "\"no tiene unidades disponibles ");
            }
            else if( reservaPersistence.findByIdRecursoReservado(libro.getId(), "LIBRO") != null)
            {
                throw new BusinessLogicException("El libro \"" + libro.getNombre() + "\" está reservado ");
            }
            else
            {
                libro.setUnidadesDisponibles(unidadesDisponibles -1);
                libroPersistence.update(libro);
            }
           }     
         else if ( tipo.equals("SALA"))
        {
            
            SalaEntity sala = salaPersistence.find(Long.MIN_VALUE, prestamoEntity.getIdRecursoPrestado());
            Boolean disponible = sala.getDisponibilidad();
            if ( !disponible)
            {
                 throw new BusinessLogicException("La sala con id \"" + sala.getId() + "\"no está disponible ");
            }
            else if( reservaPersistence.findByIdRecursoReservado(sala.getId(), "SALA") != null)
            {
                throw new BusinessLogicException("La sala con id  \"" + sala.getId() + "\" está reservado ");
            }
            else
            {
                sala.setDisponibilidad(false);
                salaPersistence.update(sala);
            }
        }
        else if ( tipo.equals("VIDEO"))
        {
            VideoEntity video = videoPersistence.find(prestamoEntity.getIdRecursoPrestado());
            Integer disponible = video.getUnidadesDis();
            if ( disponible == 0)
            {
                 throw new BusinessLogicException("El video con id \"" + video.getId() + "\"no está disponible  dado que no tiene unidades que prestar");
            }
            else if( reservaPersistence.findByIdRecursoReservado(video.getId(), "VIDEO") != null)
            {
                throw new BusinessLogicException("La sala con nombre  \"" + video.getNombre() + "\" está reservado ");
            }
            else
            {
                video.setUnidadesDis(disponible -1);
                videoPersistence.update(video);
            }
        }
        }
       
        else
        {
             throw new BusinessLogicException("Algo está mal  el elemento ya esta prestado ");
        }
        
        // Invoca la persistencia para crear el prestamo
        persistence.create(prestamoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del prestamo");
        return prestamoEntity;
    }
    
    /**
     *
     * Obtener todas los prestamos existentes en la base de datos.
     *
     * @return una lista de los prestamos.
     */
    public List<PrestamoEntity> getPrestamos() 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas los prestamos");
        // Note que, por medio de la inyección de dependencias se llama al método "findAll()" que se encuentra en la persistencia.
        List<PrestamoEntity> prestamos = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas los prestamos");
        return prestamos;
    }
    
    /**
     *
     * Obtener un prestamo por medio de su id.
     *
     * @param prestamoId: id del prestamo para ser buscado.
     * @return el prestamo solicitado por medio de su id.
     */
    public PrestamoEntity getPrestamo(Long prestamoId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el prestamo con id = {0}", prestamoId);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        PrestamoEntity prestamoEntity = persistence.find(prestamoId);
        if (prestamoEntity == null) 
        {
            LOGGER.log(Level.SEVERE, "El prestamo con el id = {0} no existe", prestamoId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el prestamo con id = {0}", prestamoId);
        return prestamoEntity;
    }
    
    /**
     *
     * Actualizar un prestamo. 
     *
     * @param prestamoId: id del prestamo para buscarla en la base de
     * datos.
     * @param prestamoEntity: prestamo con los cambios para ser actualizada,
     * por ejemplo la fecha de entrega que se alargó.
     * @return El prestamo con los cambios actualizados en la base de datos.
     */
    public PrestamoEntity updatePrestamo(Long prestamoId, PrestamoEntity prestamoEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el prestamo con id = {0}", prestamoId);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        PrestamoEntity newEntity = persistence.update(prestamoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el prestamo con id = {0}", prestamoEntity.getId());
        return newEntity;
    }
    
    /**
     * Borrar un prestamo
     *
     * @param prestamoId: id del prestamo a borrar
     * @throws BusinessLogicException Si lo que tiene el prestamo aún no está devuelto. 
     */
    public void deletePrestamo(Long prestamoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el prestamo con id = {0}", prestamoId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia.
        Boolean retornado = getPrestamo(prestamoId).getRetornado();
        if (!retornado) {
            throw new BusinessLogicException("No se puede borrar el prestamo con id = " + prestamoId + " porque aún no ha sido devuelto");
        }
        persistence.delete(prestamoId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el prestamo con id = {0}", prestamoId);
    }
    
    /**
     * Devolver un prestamo. 
     * 
     * @param prestamoEntity: prestamo a devolver
     * @param comentarioEntity: comentario que puede venir de un libro devuelto. 
     */
    public void devolverPrestamo( PrestamoEntity prestamoEntity, ComentarioEntity comentarioEntity) 
    {
        prestamoEntity.setRetornado(true);
        
        if( prestamoEntity.getLibro() != null)
        {
            LibroEntity libro = prestamoEntity.getLibro();
            Integer unidadesDisponibles = libro.getUnidadesDisponibles();
            libro.setUnidadesDisponibles(unidadesDisponibles +1);
            libroPersistence.update(libro);
            if ( comentarioEntity != null)
            {
                comentarioPersistence.create(comentarioEntity);
            }
                       
                
        }
        else if ( prestamoEntity.getSala() != null)
        {
            SalaEntity sala = prestamoEntity.getSala();
            sala.setDisponibilidad(true);
            salaPersistence.update(sala);
          
        }
        else if ( prestamoEntity.getVideo()!= null)
        {
            VideoEntity video = prestamoEntity.getVideo();
            Integer disponible = video.getUnidadesDis();
            video.setUnidadesDis(disponible +1);
            videoPersistence.update(video);
            
        }
        
        // Finalmente actualizamos el prestamo a la base de datos. 
        persistence.update(prestamoEntity);
    }
    
    
}
