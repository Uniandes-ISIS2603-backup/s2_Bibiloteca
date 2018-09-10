/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.test.persistence;

import co.edu.uniandes.csw.bibilioteca.entities.VideoDigitalEntity;
import co.edu.uniandes.csw.biblioteca.persistence.VideoDigitalPersistence;
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
 *
 * @author JuanBautista
 */
@RunWith(Arquillian.class)
public class VideoDigitalPersistenceTest {
    
    
    @Inject 
    private VideoDigitalPersistence videoDigitalPersistence;
    
    @Inject
    UserTransaction utx;

   
    private List<VideoDigitalEntity> data = new ArrayList<VideoDigitalEntity>();
    
    @PersistenceContext
    private EntityManager eman;
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(VideoDigitalEntity.class.getPackage())
                .addPackage(VideoDigitalPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Before
    public void configTest() {
        try {
            utx.begin();
            eman.joinTransaction();
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
        eman.createQuery("delete from VideoDigitalEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            VideoDigitalEntity entity = factory.manufacturePojo(VideoDigitalEntity.class);

            eman.persist(entity);

            data.add(entity);
        }
    }
    
    @Test
    public void createVideoDigitalTest()
    {
       PodamFactory factory = new PodamFactoryImpl(); 
       VideoDigitalEntity newEntity = factory.manufacturePojo(VideoDigitalEntity.class);
       VideoDigitalEntity resultado = videoDigitalPersistence.create(newEntity);
    
       Assert.assertNotNull(resultado);
       
       VideoDigitalEntity entity = eman.find(VideoDigitalEntity.class,resultado.getId());
       
       Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    @Test
    public void getVideosDigitalesTest()
    {
        List<VideoDigitalEntity> list = videoDigitalPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (VideoDigitalEntity ent : list)
        {
            boolean found = false;
            for (VideoDigitalEntity entity : data)
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
     * Prueba para consultar un videoDigital.
     */
    @Test
    public void getVideoDigitalTest() 
    {
        VideoDigitalEntity entity = data.get(0);
        VideoDigitalEntity newEntity = videoDigitalPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Prueba para consultar una videoDigital por el nombre.
     */
    @Test
    public void findNombreTest()
    {
        VideoDigitalEntity entity = data.get(0);
        VideoDigitalEntity newEntity = videoDigitalPersistence.findNombre(entity.getNombre());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());

        newEntity = videoDigitalPersistence.findNombre(null);
        Assert.assertNull(newEntity);
    }




    /**
     * Prueba para eliminar un videoDigital.
     */
    @Test
    public void deleteVideoDigitalTest() 
    {
        VideoDigitalEntity entity = data.get(0);
        videoDigitalPersistence.delete(entity.getId());
        VideoDigitalEntity deleted = eman.find(VideoDigitalEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un videoDigital.
     */
    @Test
    public void updateVideoDigitalTest() 
    {
        VideoDigitalEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        VideoDigitalEntity newEntity = factory.manufacturePojo(VideoDigitalEntity.class);

        newEntity.setId(entity.getId());

        videoDigitalPersistence.update(newEntity);

        VideoDigitalEntity resp = eman.find(VideoDigitalEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
    }
 
    

    
}
