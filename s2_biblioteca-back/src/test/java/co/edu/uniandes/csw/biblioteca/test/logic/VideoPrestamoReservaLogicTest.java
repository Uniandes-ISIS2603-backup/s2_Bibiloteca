package co.edu.uniandes.csw.biblioteca.test.logic;

import co.edu.uniandes.csw.bibilioteca.entities.*;
import co.edu.uniandes.csw.biblioteca.ejb.*;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.*;
import java.util.*;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.*;

/**
 *
 * @author Nicolas Alvarado
 */

@RunWith(Arquillian.class)
public class VideoPrestamoReservaLogicTest {
    
    private PodamFactory pf = new PodamFactoryImpl();
    
    @Inject
    private VideoLogic vl;
    @Inject
    private VideoPrestamoLogic vpl;
    @Inject
    private VideoReservaLogic vrl;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction ut;
    
    private ArrayList<VideoEntity> listVE = new ArrayList<>();
    private ArrayList<PrestamoEntity> listPE = new ArrayList<>();
    private ArrayList<ReservaEntity> listRE = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PrestamoEntity.class.getPackage())
                .addPackage(ReservaEntity.class.getPackage())
                .addPackage(VideoLogic.class.getPackage())
                .addPackage(VideoPrestamoLogic.class.getPackage())
                .addPackage(VideoReservaLogic.class.getPackage())
                .addPackage(ReservaPersistence.class.getPackage())
                .addPackage(PrestamoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Before
    public void configTest(){
        try{
            ut.begin();
            em.createQuery("delete from VideoEntity").executeUpdate();
            em.createQuery("delete from ReservaEntity").executeUpdate();
            em.createQuery("delete from PrestamoEntity").executeUpdate();
            em.createQuery("delete from BibliotecaEntity").executeUpdate();
            insertData();
            ut.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            try{
                ut.rollback();
            }
            catch(Exception f){
                f.printStackTrace();
            }
        }
    }
    
    private void insertData(){
        for(int i = 0; i < 4; i++){
            VideoEntity ve = pf.manufacturePojo(VideoEntity.class);
            BibliotecaEntity be = pf.manufacturePojo(BibliotecaEntity.class);
            em.persist(be);
            ve.setBiblioteca(be);
            em.persist(ve);
            listVE.add(ve);
        }
        for(int i = 0; i < 4; i++){
            PrestamoEntity pe = pf.manufacturePojo(PrestamoEntity.class);
            em.persist(pe);
            listPE.add(pe);
        }
        for(int i = 0; i < 4; i++){
            ReservaEntity re = pf.manufacturePojo(ReservaEntity.class);
            em.persist(re);
            listRE.add(re);
        }
    }
    
    //Test agregar
    @Test
    public void agregarPrestamoTest(){
        VideoEntity ve = listVE.get(0);
        PrestamoEntity pe = listPE.get(0);
        PrestamoEntity pe2 = vpl.addPrestamo(ve.getId(), pe.getId());
        Assert.assertNotNull(pe2);
    }
    
    @Test
    public void agregarReservaTest(){
        VideoEntity ve = listVE.get(0);
        ReservaEntity re = listRE.get(0);
        ReservaEntity re2 = vrl.addReserva(ve.getId(), re.getId());
        Assert.assertNotNull(re2);
    }
    
    //Test delete
    @Test
    public void deletePrestamoTest(){
        VideoEntity ve = listVE.get(1);
        ve.setPrestamos(listPE);
        vpl.removePrestamo(ve.getId(), listPE.get(0).getId());
        PrestamoEntity pe = vpl.getPrestamo(ve.getId(), listPE.get(0).getId());
        Assert.assertNull(pe);
    }
    
    @Test
    public void deleteReservaTest(){
        VideoEntity ve = listVE.get(1);
        ve.setReservas(listRE);
        vrl.removeReserva(ve.getId(), listRE.get(0).getId());
        ReservaEntity re = vrl.getReserva(ve.getId(), listRE.get(0).getId());
        Assert.assertNull(re);
    }
    
    //Test delete video con prestamos y reservas
    @Test(expected = BusinessLogicException.class)
    public void deleteVideoConPrestamo() throws BusinessLogicException{
        VideoEntity ve = listVE.get(2);
        ve.setPrestamos(listPE);
        vl.deleteVideo(ve.getId());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void deleteVideoConReserva() throws BusinessLogicException{
        VideoEntity ve =listVE.get(3);
        ve.setReservas(listRE);
        vl.deleteVideo(ve.getId());
    }
}
