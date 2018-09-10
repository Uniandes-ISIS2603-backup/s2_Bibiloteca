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
 *
 * @author Juan Nicolás García
 */
@Stateless
public class SalaPersistence {
        private static final Logger LOGGER = Logger.getLogger(SalaPersistence.class.getName());
    @PersistenceContext(unitName = "DnsPU")
    protected EntityManager em;
}
