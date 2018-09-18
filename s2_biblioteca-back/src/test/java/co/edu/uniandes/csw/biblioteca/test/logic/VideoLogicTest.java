package co.edu.uniandes.csw.biblioteca.test.logic;

import co.edu.uniandes.csw.bibilioteca.entities.*;
import co.edu.uniandes.csw.biblioteca.ejb.*;
import co.edu.uniandes.csw.biblioteca.persistence.VideoPersistence;
import java.util.*;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
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
            ve.setBiblioteca(be);
            em.persist(ve);
            listVE.add(ve);
        }
    }
}
