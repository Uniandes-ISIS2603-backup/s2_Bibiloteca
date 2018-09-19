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
        
        // se pregunta a la persistencia si existe una reserva para el recurso dado
        ReservaEntity validacion = persistencia.findByIdRecursoReservado(reservaEntity.getIdRecursoReservado(), reservaEntity.getTipoRecurso());
        
        // Verifica la regla de negocio en la cual dice que no puede haber 
        // dos reservas para el mismo recurso hechas por un mismo usuario
        if (validacion != null && validacion.getUsuario().getId().equals(reservaEntity.getUsuario().getId())) 
        {
            throw new BusinessLogicException("Ya existe una reserva del recurso para el usuario \"" + reservaEntity.getUsuario().getNombre() + "\"" );
        }
        else
        {
            // Invoca la persistencia para crear la editorial
            persistencia.create(reservaEntity);
            LOGGER.log(Level.INFO, "Termina proceso de creación de la reserva");
        
        }

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

    public ReservaEntity getReserva(Long reservaId)
    {
        ReservaEntity entity = persistencia.find(reservaId);
        return entity;
    }


    
}
