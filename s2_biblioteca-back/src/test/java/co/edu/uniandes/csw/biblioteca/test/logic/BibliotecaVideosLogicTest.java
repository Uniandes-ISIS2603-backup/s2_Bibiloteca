/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.test.logic;

import co.edu.uniandes.csw.bibilioteca.entities.BibliotecaEntity;
import co.edu.uniandes.csw.bibilioteca.entities.VideoEntity;
import co.edu.uniandes.csw.biblioteca.ejb.BibliotecaLogic;
import co.edu.uniandes.csw.biblioteca.ejb.BibliotecaVideosLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.BibliotecaPersistence;
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
public class BibliotecaVideosLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private BibliotecaLogic bibliotecaLogic;
    @Inject
    private BibliotecaVideosLogic editorialBooksLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<BibliotecaEntity> data = new ArrayList<BibliotecaEntity>();

    private List<VideoEntity> videosData = new ArrayList();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BibliotecaEntity.class.getPackage())
                .addPackage(BibliotecaLogic.class.getPackage())
                .addPackage(BibliotecaPersistence.class.getPackage())
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

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from VideoEntity").executeUpdate();
        em.createQuery("delete from BibliotecaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            VideoEntity videos = factory.manufacturePojo(VideoEntity.class);
            em.persist(videos);
            videosData.add(videos);
        }
        for (int i = 0; i < 3; i++) {
            BibliotecaEntity entity = factory.manufacturePojo(BibliotecaEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                videosData.get(i).setBiblioteca(entity);
            }
        }
    }
    
    /**
     * Prueba para asociar un Video existente a una biblioteca.
     */
    @Test
    public void addVideoTest() {
        BibliotecaEntity entity = data.get(0);
        VideoEntity videoEntity = videosData.get(1);
        VideoEntity response = editorialBooksLogic.addVideo(videoEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(videoEntity.getId(), response.getId());
    }
    
    /**
     * Prueba para obtener una colección de instancias de videos asociadas a una
     * instancia Biblioteca.
     */
    @Test
    public void getVideosTest() {
        List<VideoEntity> list = editorialBooksLogic.getVideos(data.get(0).getId());

        Assert.assertEquals(1, list.size());
    }
    
    /**
     * Prueba para obtener una instancia de videos asociada a una instancia
     * Biblioteca.
     *
     * @throws co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException
     */
    @Test
    public void getVideoTest() throws BusinessLogicException {
        BibliotecaEntity entity = data.get(0);
        VideoEntity videoEntity = videosData.get(0);
        VideoEntity response = editorialBooksLogic.getVideo(entity.getId(), videoEntity.getId());

        Assert.assertEquals(videoEntity.getId(), response.getId());
        Assert.assertEquals(videoEntity.getIdioma(), response.getIdioma());
        Assert.assertEquals(videoEntity.getDirector(), response.getDirector());
        Assert.assertEquals(videoEntity.getNombre(), response.getNombre());
        Assert.assertEquals(videoEntity.getSubtitulos(), response.getSubtitulos());
        Assert.assertEquals(videoEntity.getUnidadesDis(), response.getUnidadesDis());
    }
    
    /**
     * Prueba para obtener una instancia de videos asociada a una instancia
     * Biblioteca que no le pertenece.
     *
     * @throws co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void getVideoNoAsociadoTest() throws BusinessLogicException {
        BibliotecaEntity entity = data.get(0);
        VideoEntity bookEntity = videosData.get(1);
        editorialBooksLogic.getVideo(entity.getId(), bookEntity.getId());
    }
    
     /**
     * Prueba para remplazar las instancias de Videos asociadas a una instancia
     * de Biblioteca.
     */
    @Test
    public void replaceVideosTest() {
        BibliotecaEntity entity = data.get(0);
        List<VideoEntity> list = videosData.subList(1, 3);
        editorialBooksLogic.replaceVideos(entity.getId(), list);

        entity = bibliotecaLogic.getBiblioteca(entity.getId());
        Assert.assertFalse(entity.getVideos().contains(videosData.get(0)));
        Assert.assertTrue(entity.getVideos().contains(videosData.get(1)));
        Assert.assertTrue(entity.getVideos().contains(videosData.get(2)));
    }
}
