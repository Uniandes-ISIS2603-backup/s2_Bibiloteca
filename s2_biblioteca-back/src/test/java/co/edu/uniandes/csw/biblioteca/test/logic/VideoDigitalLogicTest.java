package co.edu.uniandes.csw.biblioteca.test.logic;



import co.edu.uniandes.csw.bibilioteca.entities.VideoDigitalEntity;
import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import co.edu.uniandes.csw.bibilioteca.entities.UsuarioEntity;
import co.edu.uniandes.csw.biblioteca.ejb.VideoDigitalLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David Eduardo Saavedra Hernández
 */
@RunWith(Arquillian.class)
public class VideoDigitalLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private VideoDigitalLogic videoDigitalLogic;
    
    @PersistenceContext
    private EntityManager eman;
    
    @Inject
    private UserTransaction utx;
    
    private List<VideoDigitalEntity> data = new ArrayList<VideoDigitalEntity>();
        private List<UsuarioEntity> usuariosData = new ArrayList();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(VideoDigitalEntity.class.getPackage())
                .addPackage(VideoDigitalLogic.class.getPackage())
                .addPackage(VideoDigitalPersistence.class.getPackage())
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
        eman.createQuery("delete from UsuarioEntity").executeUpdate();
        eman.createQuery("delete from VideoDigitalEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
       
        for (int i = 0; i < 3; i++) {
            UsuarioEntity usuarios = factory.manufacturePojo(UsuarioEntity.class);
            eman.persist(usuarios);
            usuariosData.add(usuarios);
        }
        for (int i = 0; i < 3; i++) {
            VideoDigitalEntity entity = factory.manufacturePojo(VideoDigitalEntity.class);
            eman.persist(entity);
            data.add(entity);
            if (i == 0) {
                //usuariosData.get(i).setVideoDigital(entity);
            }
        }
    }
    
    /**
     * Prueba para crear una VideoDigital
     *
     * @throws co.edu.uniandes.csw.videoDigital.exceptions.BusinessLogicException
     */
    @Test
    public void createVideoDigitalTest() throws BusinessLogicException 
    {
        VideoDigitalEntity newEntity = factory.manufacturePojo(VideoDigitalEntity.class);
        VideoDigitalEntity result = videoDigitalLogic.createVideoDigital(newEntity);
        Assert.assertNotNull(result);
        VideoDigitalEntity entity = eman.find(VideoDigitalEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    
    /**
     * Prueba para crear un VideoDigital con el mismo nombre de un videoDigital que ya
     * existe.
     *
     * @throws co.edu.uniandes.csw.videoDigital.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createVideoDigitalConMismoNombreTest() throws BusinessLogicException {
        VideoDigitalEntity newEntity = factory.manufacturePojo(VideoDigitalEntity.class);
        newEntity.setNombre(data.get(0).getNombre());
        videoDigitalLogic.createVideoDigital(newEntity);
    }
    
    /**
     * Prueba para consultar la lista de VideoDigitals.
     */
    @Test
    public void getVideosDigitalesTest() {
        List<VideoDigitalEntity> list = videoDigitalLogic.getVideosDigitales();
        Assert.assertEquals(data.size(), list.size());
        for (VideoDigitalEntity entity : list) {
            boolean found = false;
            for (VideoDigitalEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar una VideoDigital.
     */
    @Test
    public void getVideoDigitalTest() {
        VideoDigitalEntity entity = data.get(0);
        VideoDigitalEntity resultEntity = videoDigitalLogic.getVideoDigital(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
    }
    
    /**
     * Prueba para eliminar un VideoDigital
     *
     * @throws co.edu.uniandes.csw.videoDigital.exceptions.BusinessLogicException
     */
    @Test
    public void deleteVideoDigitalTest() throws BusinessLogicException {
        VideoDigitalEntity entity = data.get(1);
        videoDigitalLogic.deleteVideoDigital(entity.getId());
        VideoDigitalEntity deleted = eman.find(VideoDigitalEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}