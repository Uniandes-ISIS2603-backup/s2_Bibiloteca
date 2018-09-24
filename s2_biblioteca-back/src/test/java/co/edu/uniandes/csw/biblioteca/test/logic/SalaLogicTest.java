/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.test.logic;

import co.edu.uniandes.csw.bibilioteca.entities.BibliotecaEntity;
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
    private List<BibliotecaEntity> dataBiblioteca = new ArrayList<>();
    
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
        em.createQuery("delete from BibliotecaEntity").executeUpdate();
    }

    private void insertData() {
        for(int i = 0 ; i<3 ; i++)
        {
            BibliotecaEntity biblio = factory.manufacturePojo(BibliotecaEntity.class);
            em.persist(biblio);
            dataBiblioteca.add(biblio);
        }
        
        for(int k = 0 ; k < 3 ; k++)
        {
            SalaEntity sala = factory.manufacturePojo(SalaEntity.class);
            sala.setBiblioteca(dataBiblioteca.get(1));
            em.persist(sala);
            data.add(sala);
        }
    }
     @Test
    public void createSalaTest() throws BusinessLogicException
    {
        SalaEntity nuevaEntity = factory.manufacturePojo(SalaEntity.class);
        nuevaEntity.setBiblioteca(dataBiblioteca.get(1));
        SalaEntity resultado = salaLogica.createSala(dataBiblioteca.get(1).getId(), nuevaEntity);
        Assert.assertNotNull(resultado);
        SalaEntity entity = em.find(SalaEntity.class, resultado.getId());
        Assert.assertEquals(nuevaEntity.getId(), entity.getId());
    }
    @Test
    public void getSalasPorBibliotecaTest()
    {
        List<SalaEntity> lista = salaLogica.getSalasPorBiblioteca(dataBiblioteca.get(1).getId());
        Assert.assertEquals(data.size(), lista.size());
        for(SalaEntity entity : lista)
        {
            boolean encontrado = false; 
            for(SalaEntity comp : data)
            {
                if(entity.getId().equals(comp.getId()))
                {
                 encontrado = true;   
                }
            }
            Assert.assertTrue(encontrado);
        }
        
    }

          @Test
    public void getSalaBibliotecaTest()
    {
        SalaEntity entity = data.get(0);
        SalaEntity resultado = salaLogica.getSalaBiblioteca(dataBiblioteca.get(1).getId(), entity.getId());
        Assert.assertNotNull(resultado);
        Assert.assertEquals(entity.getId(), resultado.getId());
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
    public void deleteSalaTest() throws BusinessLogicException {
        SalaEntity entity = data.get(0);
        salaLogica.deleteSala(dataBiblioteca.get(1).getId(), entity.getId());
        SalaEntity deleted = em.find(SalaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
