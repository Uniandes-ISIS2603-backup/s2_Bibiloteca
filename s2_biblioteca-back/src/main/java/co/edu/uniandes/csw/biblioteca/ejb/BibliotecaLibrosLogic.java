/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.BibliotecaEntity;
import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.BibliotecaPersistence;
import co.edu.uniandes.csw.biblioteca.persistence.LibroPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de Biblioteca y libro.
 * @author estudiante David Eduardo Saavedra Hernandez
 */
@Stateless
public class BibliotecaLibrosLogic 
{
    private static final Logger LOGGER = Logger.getLogger(BibliotecaLibrosLogic.class.getName());
    
    @Inject
    private LibroPersistence libroPersistence;
    
    @Inject
    private BibliotecaPersistence bibliotecaPersistence;
    
    /**
     * Agregar un libro a la biblioteca
     *
     * @param libroId El id libro a guardar
     * @param bibliotecaId El id de la biblioteca en la cual se va a guardar el
     * libro.
     * @return El libro creado.
     */
    public LibroEntity addLibro(Long libroId, Long bibliotecaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un libro a la biblioteca con id = {0}", bibliotecaId);
        BibliotecaEntity bibliotecaEntity = bibliotecaPersistence.find(bibliotecaId);
        LibroEntity libroEntity = libroPersistence.find(libroId);
        libroEntity.setBiblioteca(bibliotecaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un libro a la biblioteca con id = {0}", bibliotecaId);
        return libroEntity;
    }
    
    /**
     * Retorna todos los libros asociados a una biblioteca
     *
     * @param libroId El ID de la editorial buscada
     * @return La lista de libros de la biblioteca
     */
    public List<LibroEntity> getLibros(Long libroId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los libros asociados a la biblioteca con id = {0}", libroId);
        return bibliotecaPersistence.find(libroId).getLibros();
    }
    
    /**
     * Retorna un libro asociado a una biblioteca
     *
     * @param bibliotecaId El id de la biblioteca a buscar.
     * @param libroId El id del libro a buscar
     * @return El libro encontrado dentro de la biblioteca.
     * @throws BusinessLogicException Si el libro no se encuentra en la
     * biblioteca
     */
    public LibroEntity getLibro(Long bibliotecaId, Long libroId) throws BusinessLogicException 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el libro con id = {0} de la biblioteca con id = " + bibliotecaId, libroId);
        List<LibroEntity> books = bibliotecaPersistence.find(bibliotecaId).getLibros();
        LibroEntity libroEntity = libroPersistence.find(libroId);
        int index = books.indexOf(libroEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el libro con id = {0} de la biblioteca con id = " + bibliotecaId, libroId);
        if (index >= 0) {
            return books.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado a la biblioteca");
    }
    
    /**
     * Remplazar libros de una biblioteca
     *
     * @param pLibros Lista de libros que serán los de la biblioteca.
     * @param bibliotecaId El id de la biblioteca que se quiere actualizar.
     * @return La lista de libros actualizada.
     */
    public List<LibroEntity> replaceBooks(Long bibliotecaId, List<LibroEntity> pLibros) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la biblioteca con id = {0}", bibliotecaId);
        BibliotecaEntity bibliotecaEntity = bibliotecaPersistence.find(bibliotecaId);
        List<LibroEntity> libroList = libroPersistence.findAll();
        for (LibroEntity libro : libroList) {
            if (pLibros.contains(libro)) {
                libro.setBiblioteca(bibliotecaEntity);
            } else if (libro.getBiblioteca() != null && libro.getBiblioteca().equals(bibliotecaEntity)) {
                libro.setBiblioteca(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la biblioteca con id = {0}", bibliotecaId);
        return pLibros;
    }
}
