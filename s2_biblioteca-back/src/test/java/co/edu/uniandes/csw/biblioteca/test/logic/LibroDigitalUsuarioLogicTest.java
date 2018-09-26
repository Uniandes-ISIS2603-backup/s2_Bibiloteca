package co.edu.uniandes.csw.biblioteca.test.logic;

import co.edu.uniandes.csw.bibilioteca.entities.LibroDigitalEntity;
import co.edu.uniandes.csw.bibilioteca.entities.UsuarioEntity;
import co.edu.uniandes.csw.biblioteca.ejb.LibroDigitalUsuarioLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.UsuarioPersistence;
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
public class LibroDigitalUsuarioLogicTest {
    
    private PodamFactory pf = new PodamFactoryImpl();
    
    @Inject
    private LibroDigitalUsuarioLogic ldul;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction ut;
    
    private ArrayList<LibroDigitalEntity> listLDE = new ArrayList<>();
    private ArrayList<UsuarioEntity> listUE = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(LibroDigitalUsuarioLogic.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Before
    public void configTest(){
        try{
            ut.begin();
            em.createQuery("delete from LibroDigitalEntity").executeUpdate();
            em.createQuery("delete from UsuarioEntity").executeUpdate();
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
        for(int i = 0; i < 3; i++){
            UsuarioEntity ue = pf.manufacturePojo(UsuarioEntity.class);
            em.persist(ue);
            listUE.add(ue);
        }
    }
    
    @Test
    public void agregarUsuarioTest() throws BusinessLogicException{
        LibroDigitalEntity lde = listLDE.get(0);
        UsuarioEntity ue = listUE.get(0);
        UsuarioEntity ue2 = ldul.addUsuario(ue.getId(), lde.getId());
        Assert.assertNotNull(ue2);
    }
    
    @Test
    public void deleteUsuarioTest(){
        LibroDigitalEntity lde = listLDE.get(1);
        lde.setUsuarios(listUE);
        ldul.deleteUsuario(lde.getId(), listUE.get(0).getId());
        UsuarioEntity ue = ldul.getUsuario(lde.getId(), listUE.get(0).getId());
        Assert.assertNull(ue);
    }
    
}
