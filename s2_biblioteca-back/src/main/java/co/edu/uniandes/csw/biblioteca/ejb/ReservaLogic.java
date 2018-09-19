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
    
    
   
    }
    
}
