/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import co.edu.uniandes.csw.bibilioteca.entities.PrestamoEntity;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.LibroPersistence;
import co.edu.uniandes.csw.biblioteca.persistence.PrestamoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Daniel Montoya
 */
@Stateless
public class LibroPrestamoLogic {

    private static final Logger LOGGER = Logger.getLogger(LibroPrestamoLogic.class.getName());

    @Inject
    private LibroPersistence libroPersistencia;

    @Inject
    private PrestamoPersistence prestamoPersistencia;

    public PrestamoEntity addPrestamo(Long libroId, Long prestamoId) throws BusinessLogicException {
        LibroEntity libro = libroPersistencia.find(libroId);
        PrestamoEntity prestamo = prestamoPersistencia.find(prestamoId);


        if (libro == null) {
            throw new BusinessLogicException("El libro con id = " + libroId + " que quiere hacer el prestamo no existe");
        }
        if (prestamo == null) {
            throw new BusinessLogicException("El prestamo con id = " + prestamoId + " no existe");
        }
        List<PrestamoEntity> prestamos = libro.getPrestamos();


        prestamos.add(prestamo);

        libro.setPrestamos(prestamos);

        return libro.getPrestamos().get(libro.getPrestamos().size() - 1);

    }


    public List<PrestamoEntity> getPrestamos(Long libroId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Iniciando consulta de prestamos de un libro con id = {0}", libroId);
        LibroEntity libro = libroPersistencia.find(libroId);
        if (libro != null) {
            return libro.getPrestamos();
        } else {
            throw new BusinessLogicException("El libro no existe");
        }
    }


    public PrestamoEntity getPrestamo(Long libroId, Long prestamoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Iniciando la consulta del prestamo con id = {0} del libro con id = " + libroId, prestamoId);
        List<PrestamoEntity> prestamos = getPrestamos(libroId);
        PrestamoEntity prestamoEntity = prestamoPersistencia.find(prestamoId);
        int i = prestamos.indexOf(prestamoEntity);
        LOGGER.log(Level.INFO, "Termino la consulta del prestamo con id = {0} del libro con id = " + libroId, prestamoId);
        if (i >= 0) {
            return prestamos.get(i);
        }
        return null;

    }

    public void deletePrestamo(Long libroId, Long prestamoId) {
        LOGGER.log(Level.INFO, "Inicia el proceso de eliminacion del prestamo con id = {0} del libro con id = " + libroId, prestamoId);
        LibroEntity libro = libroPersistencia.find(libroId);
        PrestamoEntity prestamoEliminar = prestamoPersistencia.find(prestamoId);
        libro.getPrestamos().remove(prestamoEliminar);
        LOGGER.log(Level.INFO, "Prestamo con id = {0} del libro con id = " + libroId + " fue eliminado", prestamoId);
    }
}
