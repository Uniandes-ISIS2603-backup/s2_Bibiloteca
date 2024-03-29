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
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la reserva");
        
        // se pregunta a la persistencia si existe una reserva para el recurso dado
        ReservaEntity validacion = persistencia.findByIdRecursoReservado(reservaEntity.getIdRecursoReservado(), reservaEntity.getTipoRecurso());
        
        // Verifica la regla de negocio en la cual dice que no puede haber 
        // dos reservas para el mismo recurso hechas por un mismo usuario
        if (validacion != null) 
        {
            throw new BusinessLogicException("Ya existe una reserva del recurso para el usuario \"" + reservaEntity.getUsuario().getNombre() + "\"" );
        }
        // Invoca la persistencia para crear la editorial
        persistencia.create(reservaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la reserva");
        
        

        return reservaEntity;
    }

    /**
     * Obteniene todas las reservas existentes en la base de datos.
     * @return una lista de reservas.
     */
    public List<ReservaEntity> getReservas()
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las reservas");
        List<ReservaEntity> reservas = persistencia.findAll();
        LOGGER.log(Level.INFO, "Termina el  de consultar todas las reservas");
        return reservas;
    }

    /**
     * Obtener una reserva por medio de su id.
     * @param pReservaID: id de la reserva a ser buscada.
     * @return la reserva solicitada por medio de su id.
     */
    public ReservaEntity getReserva(Long pReservaID) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la reserva con id = {0}", pReservaID);
        // Note que, por medio de la inyección de dependencias se llama al método "find(id)" que se encuentra en la persistencia.
        ReservaEntity reservaEntity = persistencia.find(pReservaID);
        
        if (reservaEntity == null) 
        {
            LOGGER.log(Level.SEVERE, "La editorial con el id = {0} no existe", pReservaID);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la editorial con id = {0}", pReservaID);
        return reservaEntity;
    }
    
    
    /**
     * Actualizar una editorial.
     * @param pReservaId: id de la reserva para buscarla en la base de
     * datos.
     * @param reservaEntity: reserva con los cambios para ser actualizada,
     * por ejemplo el tipo de recurso.
     * @return la reserva con los cambios actualizados en la base de datos.
     */
    public ReservaEntity updateReserva(Long pReservaId, ReservaEntity reservaEntity) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la reserva con id = {0}", pReservaId);
        // Note que, por medio de la inyección de dependencias se llama al método "update(entity)" que se encuentra en la persistencia.
        ReservaEntity newEntity = persistencia.update(reservaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la reserva con id = {0}", reservaEntity.getId());
        return newEntity;
    }

    /**
     * Borra una reserva
     * @param pReservaId: id de la reserva a borrar
     */
    public void deleteReserva(Long pReservaId)
    {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la reserva con id = {0}", pReservaId);
        // Note que, por medio de la inyección de dependencias se llama al método "delete(id)" que se encuentra en la persistencia
        persistencia.delete(pReservaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la reserva con id = {0}", pReservaId);
    }


    
}
