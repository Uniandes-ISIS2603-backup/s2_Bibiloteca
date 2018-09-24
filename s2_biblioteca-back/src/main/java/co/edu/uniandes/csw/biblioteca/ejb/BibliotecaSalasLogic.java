/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.BibliotecaEntity;
import co.edu.uniandes.csw.bibilioteca.entities.SalaEntity;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.BibliotecaPersistence;
import co.edu.uniandes.csw.biblioteca.persistence.SalaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la relación entre
 * la entidad de biblitoeca y Salas.
 * @author David Eduardo Saavedra Hernandez
 */
@Stateless
public class BibliotecaSalasLogic 
{
    private static final Logger LOGGER = Logger.getLogger(BibliotecaSalasLogic.class.getName());
    
    @Inject
    private SalaPersistence salaPersistence;

    @Inject
    private BibliotecaPersistence bibliotecaPersistence;
    
    /**
     * Agregar una sala a la biblioteca
     *
     * @param salaId El id de la sala a guardar
     * @param bibliotecaId El id de la biblioteca en la cual se va a guardar la
     * sala.
     * @return La sala creada.
     */
    public SalaEntity addSala(Long salaId, Long bibliotecaId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una sala a la biblioteca con id = {0}", bibliotecaId);
        BibliotecaEntity bibliotecaEntity = bibliotecaPersistence.find(bibliotecaId);
        SalaEntity salaEntity = salaPersistence.find(bibliotecaId, salaId);
        salaEntity.setBiblioteca(bibliotecaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle una sala a la biblioteca con id = {0}", bibliotecaId);
        return salaEntity;
    }
    
    /**
     * Retorna todos las salas asociados a una editorial
     *
     * @param bibliotecaId El ID de la biblioteca buscada
     * @return La lista de libros de la biblioteca
     */
    public List<SalaEntity> getSalas(Long bibliotecaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar las salas asociados a la biblioteca con id = {0}", bibliotecaId);
        return bibliotecaPersistence.find(bibliotecaId).getSalas();
    }
    
    
    /**
     * Retorna una sala asociado a una biblioteca
     *
     * @param bibliotecaId El id de la biblioteca a buscar.
     * @param salaId El id de la sala a buscar
     * @return La sala encontrada dentro de la editorial.
     * @throws BusinessLogicException Si la sala no se encuentra en la
     * biblioteca
     */
    public SalaEntity getSala(Long bibliotecaId, Long salaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la sala con id = {0} de la biblioteca con id = " + bibliotecaId, salaId);
        List<SalaEntity> salas = bibliotecaPersistence.find(bibliotecaId).getSalas();
        SalaEntity salaEntity = salaPersistence.find(bibliotecaId, salaId);
        int index = salas.indexOf(salaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar la sala con id = {0} de la biblioteca con id = " + bibliotecaId, salaId);
        if (index >= 0) {
            return salas.get(index);
        }
        throw new BusinessLogicException("El libro no está asociado a la editorial");
    }
    
     /**
     * Remplazar las salas de una biblioteca
     *
     * @param pSalas Lista de salas que serán los de la biblioteca.
     * @param bibliotecaId El id de la biblioteca que se quiere actualizar.
     * @return La lista de salas actualizada.
     */
    public List<SalaEntity> replaceSalas(Long bibliotecaId, List<SalaEntity> pSalas) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la biblioteca con id = {0}", bibliotecaId);
        BibliotecaEntity bibliotecaEntity = bibliotecaPersistence.find(bibliotecaId);
        List<SalaEntity> salaList = salaPersistence.findAll();
        for (SalaEntity sala : salaList) {
            if (pSalas.contains(sala)) {
                sala.setBiblioteca(bibliotecaEntity);
            } else if (sala.getBiblioteca()!= null && sala.getBiblioteca().equals(bibliotecaEntity)) {
                sala.setBiblioteca(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la biblioteca con id = {0}", bibliotecaId);
        return pSalas;
    }
}
