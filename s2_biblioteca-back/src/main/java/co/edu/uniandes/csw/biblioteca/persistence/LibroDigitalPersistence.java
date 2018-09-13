/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.persistence;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Clase que maneja la persistencia para LibroDigital. Se conecta a través Entity
 * Manager de javax.persistance con la base de datos SQL.
 * @author Daniel Preciado
 */
@Stateless
public class LibroDigitalPersistence {
    
    //__________________________________________________________________________
    //Constantes
    //__________________________________________________________________________
    
    /**
     * constante utilizada para dejar huella y registro
     */
    private static final Logger LOGGER = Logger.getLogger(ServicioPersistence.class.getName());
    
    //__________________________________________________________________________
    //Atributos
    //__________________________________________________________________________
    
    /**
     * 
     */
    @PersistenceContext(unitName = "DnsPU")
    protected EntityManager entityManager;
    
}
