/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.test.persistence;

import co.edu.uniandes.csw.bibilioteca.entities.VideoDigitalEntity;
import co.edu.uniandes.csw.biblioteca.persistence.VideoDigitalPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
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

    
}
