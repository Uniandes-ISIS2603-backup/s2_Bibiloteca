/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.test.logic;

import co.edu.uniandes.csw.bibilioteca.entities.SalaEntity;
import co.edu.uniandes.csw.biblioteca.ejb.SalaLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
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
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class SalaLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();
    @Inject
    private SalaLogic salaLogica;
    @PersistenceContext
    private EntityManager em;
     @Inject
    private UserTransaction utx;

    private List<SalaEntity> data = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SalaEntity.class.getPackage())
                .addPackage(SalaLogic.class.getPackage())
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
            utx.begin();
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
     private void clearData() {
        em.createQuery("delete from SalaEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            SalaEntity entity = factory.manufacturePojo(SalaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    @Test
    public void createSalaTest() throws BusinessLogicException {
        SalaEntity nuevaSalaEntity = factory.manufacturePojo(SalaEntity.class);
        SalaEntity resultado = salaLogica.createSala(nuevaSalaEntity);
        Assert.assertNotNull(resultado);
        SalaEntity salaEntity = em.find(SalaEntity.class, resultado.getId());
        Assert.assertEquals(nuevaSalaEntity.getId(), salaEntity.getId());
       
    }
    @Test
    public void getSalasTest() {
        List<SalaEntity> lista = salaLogica.getSalas();
        Assert.assertEquals(data.size(), lista.size());
        for (SalaEntity entity : lista) {
            boolean encontrado = false;
            for (SalaEntity comp : data) {
                if (entity.getId().equals(comp.getId())) {
                    encontrado = true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }

    @Test
    public void getSalaTest()
    {
        SalaEntity entity = data.get(0);
        SalaEntity resultado = salaLogica.getSala(entity.getId());
        Assert.assertNotNull(resultado);
        Assert.assertEquals(entity.getId(), resultado.getId());
        Assert.assertEquals(entity.getUbicacion(),resultado.getUbicacion());
    }
     @Test
    public void updateSalaTest() throws BusinessLogicException
    {
        SalaEntity entity = data.get(0);
        SalaEntity pojo = factory.manufacturePojo(SalaEntity.class);
        pojo.setId(entity.getId());
        salaLogica.updateSala(pojo.getId(), pojo);
        SalaEntity resultado = em.find(SalaEntity.class,entity.getId());
        Assert.assertEquals(pojo.getId(),resultado.getId());
        Assert.assertEquals(pojo.getUbicacion(),resultado.getUbicacion());
    }
    
    @Test
    public void deleteSalaTest()
    {
        SalaEntity entity = data.get(0);
        salaLogica.deleteSala(entity.getId());
        SalaEntity borrado = em.find(SalaEntity.class,entity.getId());
        Assert.assertNull(borrado);
    }
}
