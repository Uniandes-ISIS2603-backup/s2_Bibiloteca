/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.test.persistence;

import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import co.edu.uniandes.csw.biblioteca.persistence.LibroPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Daniel Montoya
 */
@RunWith(Arquillian.class)
public class LibroPersistenceTest {
    
    @Inject
    private LibroPersistence libroPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction ux;
    
    private List<LibroEntity> data = new ArrayList<>();
    
        /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LibroEntity.class.getPackage())
                .addPackage(LibroPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            ux.begin();
            em.joinTransaction();
            clearData();
            insertData();
            ux.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                ux.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from LibroEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            LibroEntity entity = factory.manufacturePojo(LibroEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createLibroTest(){
        PodamFactory factory = new PodamFactoryImpl();
        LibroEntity newEntity = factory.manufacturePojo(LibroEntity.class);
        LibroEntity resultado = libroPersistence.create(newEntity);
        
        Assert.assertNotNull(resultado);
        
        LibroEntity entity = em.find(LibroEntity.class,resultado.getId());
        Assert.assertEquals(newEntity.getIsbn(), entity.getIsbn());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    
    @Test
    public void getLibroTest(){
        LibroEntity entity = data.get(0);
        LibroEntity nuevaEntity = libroPersistence.find(entity.getId());
        Assert.assertNotNull(nuevaEntity);
        Assert.assertEquals(entity.getNombre(), nuevaEntity.getNombre());
        Assert.assertEquals(entity.getIsbn(),nuevaEntity.getIsbn());
    }
    
    @Test
    public void getLibrosTest()
    {
        List<LibroEntity> lista = libroPersistence.findAll();
        Assert.assertEquals(lista.size(), data.size());
        for(LibroEntity i : lista)
        {
            boolean encontrado = false;
            for(LibroEntity j : data)
            {
                if(i.getId().equals(j.getId()))
                    encontrado = true;
            }
            Assert.assertTrue(encontrado);
        }
    }
    
    @Test
    public void deleteLibroTest()
    {
        LibroEntity entity = data.get(0);
        libroPersistence.delete(entity.getId());
        LibroEntity borrado = em.find(LibroEntity.class,entity.getId());
        Assert.assertNull(borrado);
    }
    
    @Test
    public void updateLibroTest()
    {
        LibroEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        LibroEntity nueva = factory.manufacturePojo(LibroEntity.class);
        
        nueva.setId(entity.getId());
        
        libroPersistence.update(nueva);
        
        LibroEntity resp = em.find(LibroEntity.class, entity.getId());
        Assert.assertEquals(resp.getId(), nueva.getId());
        Assert.assertEquals(resp.getIsbn(), nueva.getIsbn());
        Assert.assertEquals(resp.getNombre(), nueva.getNombre());
    }
    
    @Test
    public void findByISBNTest()
    {
        LibroEntity entity = data.get(0);
        LibroEntity nuevaEntity = libroPersistence.findISBN(entity.getIsbn());
        Assert.assertNotNull(nuevaEntity);
        Assert.assertEquals(entity.getIsbn(),nuevaEntity.getIsbn());
        
        nuevaEntity = libroPersistence.findISBN(null);
        Assert.assertNull(nuevaEntity);
        
    }
}
