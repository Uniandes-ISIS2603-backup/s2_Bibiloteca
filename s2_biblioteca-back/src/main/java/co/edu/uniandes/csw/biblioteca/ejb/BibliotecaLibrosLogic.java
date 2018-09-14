/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.BibliotecaEntity;
import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import co.edu.uniandes.csw.biblioteca.persistence.BibliotecaPersistence;
import co.edu.uniandes.csw.biblioteca.persistence.LibroPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
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
    public LibroEntity addBook(Long libroId, Long bibliotecaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un libro a la biblioteca con id = {0}", bibliotecaId);
        BibliotecaEntity bibliotecaEntity = bibliotecaPersistence.find(bibliotecaId);
        LibroEntity libroEntity = libroPersistence.find(libroId);
        libroEntity.setBiblioteca(bibliotecaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un libro a la editorial con id = {0}", bibliotecaId);
        return libroEntity;
    }
}
