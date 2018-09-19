package co.edu.uniandes.csw.biblioteca.test.logic;

import co.edu.uniandes.csw.bibilioteca.entities.*;
import co.edu.uniandes.csw.biblioteca.ejb.*;
import co.edu.uniandes.csw.biblioteca.persistence.*;
import java.util.*;
import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
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
    
    //Test agregar
    //Test get
    //Test delete
    //Test delete video con prestamos y reservas
}
