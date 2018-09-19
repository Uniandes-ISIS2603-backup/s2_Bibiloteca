/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.test.logic;

import co.edu.uniandes.csw.bibilioteca.entities.ComentarioEntity;
import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import co.edu.uniandes.csw.bibilioteca.entities.UsuarioEntity;
import co.edu.uniandes.csw.biblioteca.ejb.ComentarioLogic;
import co.edu.uniandes.csw.biblioteca.ejb.LibroLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.ComentarioPersistence;
import co.edu.uniandes.csw.biblioteca.persistence.LibroPersistence;
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
 * @author Daniel Montoya
 */
@RunWith(Arquillian.class)
public class LibroLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private LibroLogic libroLogica;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<LibroEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LibroEntity.class.getPackage())
                .addPackage(LibroLogic.class.getPackage())
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
        em.createQuery("delete from LibroEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            LibroEntity entity = factory.manufacturePojo(LibroEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }

    @Test
    public void createLibroTest() throws BusinessLogicException {
        LibroEntity nuevoLibroEntity = factory.manufacturePojo(LibroEntity.class);
        LibroEntity resultado = libroLogica.createLibro(nuevoLibroEntity);
        Assert.assertNotNull(resultado);
        LibroEntity libroEntity = em.find(LibroEntity.class, resultado.getId());
        Assert.assertEquals(nuevoLibroEntity.getId(), libroEntity.getId());
        Assert.assertEquals(nuevoLibroEntity.getIdioma(), libroEntity.getIdioma());
    }

    @Test
    public void getLibrosTest() {
        List<LibroEntity> lista = libroLogica.getLibros();
        Assert.assertEquals(data.size(), lista.size());
        for (LibroEntity entity : lista) {
            boolean encontrado = false;
            for (LibroEntity comp : data) {
                if (entity.getId().equals(comp.getId())) {
                    encontrado = true;
                }
            }
            Assert.assertTrue(encontrado);
        }
    }

    @Test
    public void getLibroTest()
    {
        LibroEntity entity = data.get(0);
        LibroEntity resultado = libroLogica.getLibro(entity.getId());
        Assert.assertNotNull(resultado);
        Assert.assertEquals(entity.getId(), resultado.getId());
        Assert.assertEquals(entity.getNombre(),resultado.getNombre());
    }
    
    @Test
    public void updateLibroTest() throws BusinessLogicException
    {
        LibroEntity entity = data.get(0);
        LibroEntity pojo = factory.manufacturePojo(LibroEntity.class);
        pojo.setId(entity.getId());
        libroLogica.updateLibro(pojo, pojo.getId());
        LibroEntity resultado = em.find(LibroEntity.class,entity.getId());
        Assert.assertEquals(pojo.getId(),resultado.getId());
        Assert.assertEquals(pojo.getNombre(),resultado.getNombre());
    }
    
    @Test
    public void deleteLibroTest()
    {
        LibroEntity entity = data.get(0);
        libroLogica.deleteLibro(entity.getId());
        LibroEntity borrado = em.find(LibroEntity.class,entity.getId());
        Assert.assertNull(borrado);
    }
}
