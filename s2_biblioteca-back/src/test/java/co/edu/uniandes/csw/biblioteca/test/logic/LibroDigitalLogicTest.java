package co.edu.uniandes.csw.biblioteca.test.logic;

import co.edu.uniandes.csw.bibilioteca.entities.LibroDigitalEntity;
import co.edu.uniandes.csw.biblioteca.ejb.LibroDigitalLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.LibroDigitalPersistence;
import java.util.ArrayList;
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
 * @author Nicolas Alvarado
 */

@RunWith(Arquillian.class)
public class LibroDigitalLogicTest {
    
    private PodamFactory pf = new PodamFactoryImpl();
    
    @Inject
    private LibroDigitalLogic ldl;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction ut;
    
    private ArrayList<LibroDigitalEntity> listLDE = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LibroDigitalEntity.class.getPackage())
                .addPackage(LibroDigitalLogic.class.getPackage())
                .addPackage(LibroDigitalPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Before
    public void configTest(){
        try{
            ut.begin();
            em.createQuery("delete from LibroDigitalEntity").executeUpdate();
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
        for(int i = 0; i < 3; i++){
            LibroDigitalEntity lde = pf.manufacturePojo(LibroDigitalEntity.class);
            em.persist(lde);
            listLDE.add(lde);
        }
    }
    
    @Test
    public void createLibroDigitalTest() throws BusinessLogicException{
        LibroDigitalEntity lde =  pf.manufacturePojo(LibroDigitalEntity.class);
        ldl.createLibroDigital(lde);
        LibroDigitalEntity lde2 = em.find(LibroDigitalEntity.class, lde.getId());
        Assert.assertEquals(lde, lde2);
    }
    
}