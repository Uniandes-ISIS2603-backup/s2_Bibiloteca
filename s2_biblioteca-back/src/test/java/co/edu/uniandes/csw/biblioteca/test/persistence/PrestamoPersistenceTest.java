/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.test.persistence;

import co.edu.uniandes.csw.bibilioteca.entities.PrestamoEntity;
import co.edu.uniandes.csw.biblioteca.persistence.PrestamoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *  Purebas de presistencia de Prestamo
 * 
 * @author David Eduardo Saavedra Hernandez
 */
@RunWith(Arquillian.class)
public class PrestamoPersistenceTest 
{
    
    @Inject
    private PrestamoPersistence prestamoPersistence;
     
    @PersistenceContext
    private EntityManager em;
       
    @Inject
    UserTransaction utx;
    
    private List<PrestamoEntity> data = new ArrayList<PrestamoEntity>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PrestamoEntity.class.getPackage())
                .addPackage(PrestamoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from PrestamoEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            PrestamoEntity entity = factory.manufacturePojo(PrestamoEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }
    
     /**
     * Prueba para crear un Prestamo.
     */
    @Test
    public void createPrestamoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PrestamoEntity newEntity = factory.manufacturePojo(PrestamoEntity.class);
        PrestamoEntity result = prestamoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        PrestamoEntity entity = em.find(PrestamoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    /**
     * Prueba para consultar la lista de Prestamos.
     */
    @Test
    public void getPrestamosTest() {
        List<PrestamoEntity> list = prestamoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PrestamoEntity ent : list) {
            boolean found = false;
            for (PrestamoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un Prestamo.
     */
    @Test
    public void getPrestamoTest() {
        PrestamoEntity entity = data.get(0);
        PrestamoEntity newEntity = prestamoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }
    
    /**
     * Prueba para eliminar una Editorial.
     */
    @Test
    public void deletePrestamoTest() {
        PrestamoEntity entity = data.get(0);
        prestamoPersistence.delete(entity.getId());
        PrestamoEntity deleted = em.find(PrestamoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar un Prestamo. 
     */
    @Test
    public void updatePrestamoTest() {
        PrestamoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PrestamoEntity newEntity = factory.manufacturePojo(PrestamoEntity.class);

        newEntity.setId(entity.getId());

        prestamoPersistence.update(newEntity);

        PrestamoEntity resp = em.find(PrestamoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

}
