/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.test.persistence;

import co.edu.uniandes.csw.bibilioteca.entities.BibliotecaEntity;
import co.edu.uniandes.csw.bibilioteca.entities.SalaEntity;
import co.edu.uniandes.csw.biblioteca.persistence.SalaPersistence;
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
 * @author Juan Nicolás García
 */
@RunWith(Arquillian.class)
public class SalaPersistenceTest {
      @Inject
    private SalaPersistence salaPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction ux;
    
    private List<SalaEntity> data = new ArrayList<>();
    private List<BibliotecaEntity> dataBiblioteca = new ArrayList<>();
    
        /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SalaEntity.class.getPackage())
                .addPackage(SalaPersistence.class.getPackage())
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
        em.createQuery("delete from SalaEntity").executeUpdate();
        em.createQuery("delete from BibliotecaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            BibliotecaEntity entity = factory.manufacturePojo(BibliotecaEntity.class);
            em.persist(entity);
            dataBiblioteca.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            SalaEntity entity = factory.manufacturePojo(SalaEntity.class);
            if (i == 0) {
                entity.setBiblioteca(dataBiblioteca.get(0));
            }
            em.persist(entity);
            data.add(entity);
        }
    }
    
 /**
     * Prueba crear una nueva sala
     */
    @Test
    public void createSalaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        SalaEntity nuevaEntity = factory.manufacturePojo(SalaEntity.class);
        SalaEntity resultado = salaPersistence.create(nuevaEntity);

        Assert.assertNotNull(resultado);

       SalaEntity entity = em.find(SalaEntity.class, resultado.getId());

        Assert.assertEquals(nuevaEntity.getUbicacion(), entity.getUbicacion());
        
    }
    
    /**
     * Prueba la consulta de una sala
     */
    @Test
    public void getSalaTest() {
        SalaEntity entity = data.get(0);
        SalaEntity nuevaEntity = salaPersistence.find(dataBiblioteca.get(0).getId(), entity.getId());
        Assert.assertNotNull(nuevaEntity);
        Assert.assertEquals(entity.getUbicacion(), nuevaEntity.getUbicacion());
    }
    
    @Test
    public void getSalasTest()
    {
        List<SalaEntity> lista = salaPersistence.findAll();
        Assert.assertEquals(lista.size(), data.size());
        for(SalaEntity i : lista)
        {
            boolean encontrado = false;
            for(SalaEntity j : data)
            {
                if(i.getId().equals(j.getId()))
                    encontrado = true;
            }
            Assert.assertTrue(encontrado);
        }
    }
    
    /**
     * Prueba eliminar una sala
     */
    @Test
    public void deleteSalaTest(){
        SalaEntity entity = data.get(0);
        salaPersistence.delete(entity.getId());
        SalaEntity eliminado = em.find(SalaEntity.class,entity.getId());
        Assert.assertNull(eliminado);
    }
    
    /**
     * Prueba actualizar una sala
     */
    @Test
    public void updateSalaTest(){
        SalaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        SalaEntity newEntity = factory.manufacturePojo(SalaEntity.class);
        newEntity.setId(entity.getId());
        salaPersistence.update(newEntity);
        
        SalaEntity respuesta = em.find(SalaEntity.class,entity.getId());
        
        Assert.assertEquals(respuesta.getUbicacion(), newEntity.getUbicacion());
        
        
    }
}
