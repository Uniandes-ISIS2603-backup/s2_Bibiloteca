package co.edu.uniandes.csw.biblioteca.test.persistence;

import co.edu.uniandes.csw.bibilioteca.entities.VideoEntity;
import co.edu.uniandes.csw.biblioteca.persistence.VideoPersistence;
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
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Nicolas Alvarado
 */

@RunWith(Arquillian.class)
public class VideoPersistenceTest {
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private VideoPersistence vp;
    @Inject
    UserTransaction ut;
    
    private List<VideoEntity> listVE = new ArrayList<VideoEntity>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(VideoEntity.class.getPackage())
                .addPackage(VideoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Before
    public void configTest() {
        try {
            ut.begin();
            em.joinTransaction();
            em.createQuery("delete from VideoEntity").executeUpdate();
            insertData();
            ut.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                ut.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    private void insertData() {
    PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

        VideoEntity et = factory.manufacturePojo(VideoEntity.class);

        em.persist(et);

        listVE.add(et);
        }
    }
    
    @Test
    public void createEditorialTest() {
    PodamFactory factory = new PodamFactoryImpl();
    VideoEntity newVE = factory.manufacturePojo(VideoEntity.class);
    VideoEntity result = vp.create(newVE);

    Assert.assertNotNull(result);

    VideoEntity entity = em.find(VideoEntity.class, result.getId());

    Assert.assertEquals(newVE.getNombre(), entity.getNombre());
    }
}
