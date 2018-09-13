/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.test.persistence;


import co.edu.uniandes.csw.biblioteca.persistence.LibroDigitalPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;

/**
 * prueba de persistencia de LibroDigital
 * @author Daniel Preciado
 */
@RunWith(Arquillian.class)
public class LibroDigitalPersistenceTest {
    
    //__________________________________________________________________________
    //Atributos
    //__________________________________________________________________________
    
     /**
     * Inyección de la dependencia a la clase LibroDigitalPersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private LibroDigitalPersistence libroDigitalPersistence;

    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Variable para marcar las transacciones del entityManager anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    /**
     * lista que tiene los datos de prueba.
     */
    //private List<LibroDigitalEntity> data = new ArrayList<LibroDigitalEntity>();
    
}
