/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.LibroPersistence;
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
public class LibroLogic {
    
    private static final Logger LOGGER = Logger.getLogger(LibroLogic.class.getName());
    
    @Inject 
    private LibroPersistence persistencia;
    
    public LibroEntity createLibro(LibroEntity libro) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Comienza la creacion del libro");
        if(persistencia.find(libro.getId())!=null || persistencia.findISBN(libro.getIsbn()) != null)
        {
            throw new BusinessLogicException("El libro con el id "+libro.getId()+" ya existe");
        }
        else
        {
            persistencia.create(libro);
            LOGGER.log(Level.INFO,"El libro fue creado");
            return libro;
        }
    }
    
    public List<LibroEntity> getLibros()
    {
        LOGGER.log(Level.INFO, "Inicia el proceso de consultar todos los libros");
        List<LibroEntity> libros = persistencia.findAll();
        LOGGER.log(Level.INFO, "Termina el proceso de consulta de libros");
        return libros;
    }
    
    public LibroEntity getLibro(Long libroId)
    {
        LOGGER.log(Level.INFO, "Comienza el proceso de consulta de un libro con id = {0}",libroId);
        LibroEntity libro = persistencia.find(libroId);
        if(libro == null)
        {
            LOGGER.log(Level.INFO, "El libro con id ={0} no existe",libroId);
        }
        LOGGER.log(Level.INFO, "Termina el proceso de consulta del libro con id={0}",libroId);
        return libro;
    }
    
    public LibroEntity updateLibro(LibroEntity libro , Long libroId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Comienza el proceso de actualizar el libro con id = {0}",libroId);
        if(libro.getIsbn() == null || libro.getIsbn().isEmpty())
        {
            throw new BusinessLogicException("El ISBN es invalido");
        }
        if(persistencia.find(libroId)==null)
        {
            throw new BusinessLogicException("El libro con id = "+ libroId + " no esxiste.");
        }
        LibroEntity libroActualizado = persistencia.update(libro);
        LOGGER.log(Level.INFO, "Termina el proceso de actualizacion del libro con id = {0}",libroActualizado.getId());
        return libroActualizado;
    }
    
    public void deleteLibro(Long libroId)
    {
        LOGGER.log(Level.INFO,"Comienza el proceso de eliminar el libro con id = {0}",libroId);
        persistencia.delete(libroId);
        LOGGER.log(Level.INFO, "El proceso de borrado del libro con id = {0} ha terminado",libroId);
    }
}
