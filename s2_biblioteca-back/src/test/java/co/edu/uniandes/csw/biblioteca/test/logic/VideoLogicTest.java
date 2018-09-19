package co.edu.uniandes.csw.biblioteca.test.logic;

import co.edu.uniandes.csw.bibilioteca.entities.*;
import co.edu.uniandes.csw.biblioteca.ejb.*;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.VideoPersistence;
import java.util.*;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.UserTransaction;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.*;

/**
 *
 * @author Nicolas Alvarado
 */

@RunWith(Arquillian.class)
public class VideoLogicTest {
    
    private PodamFactory pf = new PodamFactoryImpl();
    
    @Inject
    private VideoLogic vl;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utrapa;
    
    private List<VideoEntity> listVE = new ArrayList<>();
    //private BibliotecaEntity be;
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(VideoEntity.class.getPackage())
                .addPackage(VideoLogic.class.getPackage())
                .addPackage(VideoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Before
    public void configTest(){
        try{
            utrapa.begin();
            em.createQuery("delete from VideoEntity").executeUpdate();
            em.createQuery("delete from ReservaEntity").executeUpdate();
            em.createQuery("delete from PrestamoEntity").executeUpdate();
            em.createQuery("delete from BibliotecaEntity").executeUpdate();
            insertData();
            utrapa.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            try{
                utrapa.rollback();
            }
            catch(Exception f){
                f.printStackTrace();
            }
        }
    }
    
    private void insertData(){
        for(int i = 0; i < 5; i++){
            VideoEntity ve = pf.manufacturePojo(VideoEntity.class);
            BibliotecaEntity be = pf.manufacturePojo(BibliotecaEntity.class);
            em.persist(be);
            ve.setBiblioteca(be);
            em.persist(ve);
            listVE.add(ve);
        }
    }
    
    @Test
    public void createVideoTest() throws BusinessLogicException{
        VideoEntity ve = pf.manufacturePojo(VideoEntity.class);
        ve.setBiblioteca(listVE.get(0).getBiblioteca());
        vl.createVideo(ve);
        VideoEntity ve2 = em.find(VideoEntity.class, ve.getId());
        Assert.assertEquals(ve, ve2);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createVideoSinNombreTest() throws BusinessLogicException{
        VideoEntity ve = pf.manufacturePojo(VideoEntity.class);
        ve.setNombre(null);
        vl.createVideo(ve);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createVideoConNombreRepetidoTest() throws BusinessLogicException{
        VideoEntity ve = pf.manufacturePojo(VideoEntity.class);
        ve.setNombre(listVE.get(0).getNombre());
        vl.createVideo(ve);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createVideoConBibliotecaNoExistenteTest() throws BusinessLogicException{
        VideoEntity ve = pf.manufacturePojo(VideoEntity.class);
        BibliotecaEntity be = new BibliotecaEntity();
        be.setId(Long.MIN_VALUE);
        ve.setBiblioteca(be);
        vl.createVideo(ve);
    }
    
    @Test
    public void getVideosTest(){
        List<VideoEntity> listRE = vl.getVideos();
        Assert.assertEquals(listRE.size(), listVE.size());
        for(VideoEntity ve : listVE){
            boolean encontro = false;
            for(VideoEntity ve2 : listRE){
                if(ve.equals(ve2)){
                    encontro = true;
                }
            }
            Assert.assertTrue(encontro);
        }
    }
    
    @Test
    public void getVideoTest(){
        VideoEntity ve = listVE.get(0);
        VideoEntity ve2 = vl.getVideo(ve.getId());
        Assert.assertNotNull(ve2);
        Assert.assertEquals(ve, ve2);
    }
    
    @Test
    public void deleteVideoTest() throws BusinessLogicException{
        VideoEntity ve = listVE.get(0);
        vl.deleteVideo(ve.getId());
        VideoEntity del = em.find(VideoEntity.class, ve.getId());
        Assert.assertNull(del);
    }
}
