/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.test.logic;

import co.edu.uniandes.csw.bibilioteca.entities.ReservaEntity;

import co.edu.uniandes.csw.biblioteca.ejb.ReservaLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.ReservaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * clase para probar la logica de reserva
 * @author Daniel Preciado
 */
public class ReservaLogicTest {

    //__________________________________________________________________________
    //Atributos
    //__________________________________________________________________________
    
    /**
     * 
     */
    private PodamFactory factory = new PodamFactoryImpl();

    /**
     * 
     */
    @Inject
    private ReservaLogic reservaLogic;

    /**
     * 
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 
     */
    @Inject
    private UserTransaction utx;

    /**
     * 
     */
    private List<ReservaEntity> data = new ArrayList<ReservaEntity>();
    
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
                .addPackage(ReservaEntity.class.getPackage())
                .addPackage(ReservaLogic.class.getPackage())
                .addPackage(ReservaPersistence.class.getPackage())
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
    private void clearData() 
    {
        entityManager.createQuery("delete from ReservaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() 
    {

        for (int i = 0; i < 3; i++) 
        {
            ReservaEntity entity = factory.manufacturePojo(ReservaEntity.class);
            entityManager.persist(entity);
            data.add(entity);
        }

    }

    /**
     * Prueba para crear una reserva
     * @throws BusinessLogicException
     */
    @Test
    public void createReservaTest() throws BusinessLogicException 
    {
        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        ReservaEntity result = reservaLogic.createReserva(newEntity);
        Assert.assertNotNull(result);
        ReservaEntity entity = entityManager.find(ReservaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getIdRecursoReservado(), entity.getIdRecursoReservado());

    }

    /**
     * Prueba para consultar la lista de reservas.
     */
    @Test
    public void getReservasTest() 
    {
        List<ReservaEntity> list = reservaLogic.getReservas();
        Assert.assertEquals(data.size(), list.size());
        for (ReservaEntity entity : list) 
        {
            boolean found = false;
            for (ReservaEntity storedEntity : data) 
            {
                if (entity.getId().equals(storedEntity.getId())) 
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una reserva.
     */
    @Test
    public void getReservaTest()
    {
        ReservaEntity entity = data.get(0);
        ReservaEntity resultEntity = reservaLogic.getReserva(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
    }

    /**
     * Prueba para actualizar una reserva.
     * @throws BusinessLogicException
     */
    @Test
    public void updateBookTest() throws BusinessLogicException 
    {
        ReservaEntity entity = data.get(0);
        ReservaEntity pojoEntity = factory.manufacturePojo(ReservaEntity.class);
        pojoEntity.setId(entity.getId());
        reservaLogic.updateReserva(pojoEntity.getId(), pojoEntity);
        ReservaEntity resp = entityManager.find(ReservaEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getIdRecursoReservado(), resp.getIdRecursoReservado());

    }


    /**
     * Prueba para eliminar una reserva.
     * @throws BusinessLogicException
     */
    @Test
    public void deleteBookTest() throws BusinessLogicException 
    {
        ReservaEntity entity = data.get(0);
        reservaLogic.deleteReserva(entity.getId());
        ReservaEntity deleted = entityManager.find(ReservaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
