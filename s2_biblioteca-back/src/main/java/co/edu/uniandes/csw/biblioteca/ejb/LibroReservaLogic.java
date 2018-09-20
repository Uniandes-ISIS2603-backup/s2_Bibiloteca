/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import co.edu.uniandes.csw.bibilioteca.entities.ReservaEntity;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.LibroPersistence;
import co.edu.uniandes.csw.biblioteca.persistence.ReservaPersistence;
import java.util.logging.Logger;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Daniel Montoya
 */
@Stateless
public class LibroReservaLogic {

    private static final Logger LOGGER  = Logger.getLogger(LibroReservaLogic.class.getName());

    @Inject
    private LibroPersistence libroPersistencia;

    @Inject
    private ReservaPersistence reservaPersistencia;

    public ReservaEntity addReserva(Long libroId, Long reservaId) throws BusinessLogicException {
        LibroEntity libro = libroPersistencia.find(libroId);
        ReservaEntity reserva = reservaPersistencia.find(reservaId);

        if (libro == null) {
            throw new BusinessLogicException("El libro con id = " + libroId + " que quiere hacer la reserva no existe");
        }
        if (reserva == null) {
            throw new BusinessLogicException("La reserva con id = " + reservaId + " no existe");
        }
        List<ReservaEntity> reservas = libro.getReservas();
        reservas.add(reserva);
        libro.setReservas(reservas);
        return libro.getReservas().get(reservas.size() - 1);
    }

    public List<ReservaEntity> getReservas(Long libroId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Iniciando consulta de reservas de un libro con id = {0}", libroId);
        LibroEntity libro = libroPersistencia.find(libroId);
        if (libro != null) {
            return libro.getReservas();
        } else {
            throw new BusinessLogicException("El libro no existe.");
        }
    }

    public ReservaEntity getReserva(Long libroId, Long reservaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Iniciando la consulta de la reserva con id = {0} del libro con id = " + libroId, reservaId);
        List<ReservaEntity> reservas = getReservas(libroId);
        ReservaEntity reservaEntity = reservaPersistencia.find(reservaId);
        int i = reservas.indexOf(reservaEntity);
        LOGGER.log(Level.INFO, "Termino la consulta de la reservas con id = {0} del libro con id = " + libroId, reservaId);
        if (i >= 0) {
            return reservas.get(i);
        }
        return null;
    }

    public void deleteReserva(Long libroId, Long reservaId) {
        LOGGER.log(Level.INFO, "Inicia el proceso de eliminacion de la reserva con id = {0} del libro con id = " + libroId, reservaId);
        LibroEntity libro = libroPersistencia.find(libroId);
        ReservaEntity reservaEliminar = reservaPersistencia.find(reservaId);
        libro.getReservas().remove(reservaEliminar);
        LOGGER.log(Level.INFO, "Reserva con id = {0} del libro con id = " + libroId + " fue eliminado", reservaId);
    }
}
