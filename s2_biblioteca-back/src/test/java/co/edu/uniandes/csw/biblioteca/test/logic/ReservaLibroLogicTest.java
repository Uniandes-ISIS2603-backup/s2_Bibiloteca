/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.test.logic;

import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import co.edu.uniandes.csw.bibilioteca.entities.ReservaEntity;
import co.edu.uniandes.csw.biblioteca.ejb.ReservaLibroLogic;
import co.edu.uniandes.csw.biblioteca.ejb.ReservaLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.LibroPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Pruebas de logica de la relacion reserva - libro
 * @author Daniel Preciado
 */
@RunWith(Arquillian.class)
public class ReservaLibroLogicTest {
    

    //__________________________________________________________________________
    //Atributos
    //__________________________________________________________________________
    
    /**
     * productor de datos para las pruebas
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * logica de la reserva
     */
    @Inject
    private ReservaLogic reservaLogic;
    
    /**
     * logica de la relacion
     */
    @Inject
    private ReservaLibroLogic reservaLibroLogic;

    /**
     * entity manager
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * transaccion del usuario
     */
    @Inject
    private UserTransaction utx;

    /**
     * lista para almacenar los datos de prueba
     */
    private List<LibroEntity> data = new ArrayList<LibroEntity>();

    /**
     * lista para almacenar los datos de prueba
     */
    private List<ReservaEntity> reservasData = new ArrayList();

    //__________________________________________________________________________
    //Metodos
    //__________________________________________________________________________
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LibroEntity.class.getPackage())
                .addPackage(ReservaLogic.class.getPackage())
                .addPackage(LibroPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() 
    {
        try 
        {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            try 
            {
                utx.rollback();
            } 
            catch (Exception e1) 
            {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from ReservaEntity").executeUpdate();
        em.createQuery("delete from LibroEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) 
        {
            ReservaEntity reservas = factory.manufacturePojo(ReservaEntity.class);
            em.persist(reservas);
            reservasData.add(reservas);
        }
        for (int i = 0; i < 3; i++) 
        {
            LibroEntity entity = factory.manufacturePojo(LibroEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0)
            {
                reservasData.get(i).setLibro(entity);
                reservasData.get(i).setTipoRecurso("LIBRO");
            }
        }
    }

    /**
     * Prueba para desasociar una reserva existente de un libro existente
     * @throws BusinessLogicException
     */
    @Test
    public void removeReservasTest() throws BusinessLogicException 
    {
        reservaLibroLogic.removeLibro(reservasData.get(0).getIdRecursoReservado());
        ReservaEntity response = reservaLogic.getReserva(reservasData.get(0).getId());
        Assert.assertNull(response.getLibro());
    }
    
}
