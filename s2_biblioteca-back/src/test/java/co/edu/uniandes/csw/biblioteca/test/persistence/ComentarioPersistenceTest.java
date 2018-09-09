/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.test.persistence;

import co.edu.uniandes.csw.bibilioteca.entities.ComentarioEntity;
import co.edu.uniandes.csw.biblioteca.persistence.ComentarioPersistence;
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
 * @author Daniel Montoya
 */
@RunWith(Arquillian.class)
public class ComentarioPersistenceTest {
    
    //Inject no necesita hacer new porque el servidor lo crea por defecto y los guarda, el inject lo utiliza para obtener esa variable ya creada
    @Inject
    private ComentarioPersistence comentarioPersistence;
    
    @PersistenceContext
    private EntityManager em;

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComentarioEntity.class.getPackage())
                .addPackage(ComentarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createComentarioTest()
    {
       PodamFactory factory = new PodamFactoryImpl(); 
       ComentarioEntity nuevaEntity = factory.manufacturePojo(ComentarioEntity.class);
       ComentarioEntity resultado = comentarioPersistence.create(nuevaEntity);
    
       Assert.assertNotNull(resultado);
       
       ComentarioEntity entity = em.find(ComentarioEntity.class,resultado.getId());
       
       Assert.assertEquals(nuevaEntity.getNombreUsuario(), entity.getNombreUsuario());
    }
    
}
