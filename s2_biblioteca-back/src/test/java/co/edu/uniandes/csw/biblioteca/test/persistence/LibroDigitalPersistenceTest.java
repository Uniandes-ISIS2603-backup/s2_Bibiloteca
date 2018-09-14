/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.test.persistence;


import co.edu.uniandes.csw.bibilioteca.entities.LibroDigitalEntity;
import co.edu.uniandes.csw.biblioteca.persistence.LibroDigitalPersistence;
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
/**
     * lista que tiene los datos de prueba.
     */
    private List<LibroDigitalEntity> data = new ArrayList<LibroDigitalEntity>();
    
        
    //__________________________________________________________________________
    //Metodos
    //__________________________________________________________________________

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en el payara
     * embebido. El jar contiene las clases de Servicio, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() 
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LibroDigitalEntity.class.getPackage())
                .addPackage(LibroDigitalPersistence.class.getPackage())
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
            entityManager.joinTransaction();
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
        entityManager.createQuery("delete from LibroDigitalEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() 
    {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) 
        {

            LibroDigitalEntity entity = factory.manufacturePojo(LibroDigitalEntity.class);

            entityManager.persist(entity);

            data.add(entity);
        }
    }

    /**
     * Prueba para crear un LibroDigital.
     */
    @Test
    public void createLibroDigitalTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        LibroDigitalEntity newEntity = factory.manufacturePojo(LibroDigitalEntity.class);
        LibroDigitalEntity result = libroDigitalPersistence.create(newEntity);

        Assert.assertNotNull(result);

        LibroDigitalEntity entity = entityManager.find(LibroDigitalEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para consultar la lista de LibrosDigitales.
     */
    @Test
    public void getLibrosDigitalesTest()
    {
        List<LibroDigitalEntity> list = libroDigitalPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (LibroDigitalEntity ent : list)
        {
            boolean found = false;
            for (LibroDigitalEntity entity : data)
            {
                if (ent.getId().equals(entity.getId())) 
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un LibroDigital.
     */
    @Test
    public void getLibroDigitalest() 
    {
        LibroDigitalEntity entity = data.get(0);
        LibroDigitalEntity newEntity = libroDigitalPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Prueba para eliminar un LibroDigital.
     */
    @Test
    public void deleteLibroDigitalTest() 
    {
        LibroDigitalEntity entity = data.get(0);
        libroDigitalPersistence.delete(entity.getId());
        LibroDigitalEntity deleted = entityManager.find(LibroDigitalEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un LibroDigital.
     */
    @Test
    public void updateLibroDigitalTest() 
    {
        LibroDigitalEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        LibroDigitalEntity newEntity = factory.manufacturePojo(LibroDigitalEntity.class);

        newEntity.setId(entity.getId());

        libroDigitalPersistence.update(newEntity);

        LibroDigitalEntity resp = entityManager.find(LibroDigitalEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }

    /**
     * Prueba para consultar un LibroDigital por el nombre del recurso.
     */
    @Test
    public void findEditorialByNameTest()
    {
        LibroDigitalEntity entity = data.get(0);
        LibroDigitalEntity newEntity = libroDigitalPersistence.findByServiceName(entity.getNombre());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());

        newEntity = libroDigitalPersistence.findByServiceName(null);
        Assert.assertNull(newEntity);
    }
    
}
