package co.edu.uniandes.csw.biblioteca.test.logic;

import co.edu.uniandes.csw.bibilioteca.entities.LibroDigitalEntity;
import co.edu.uniandes.csw.bibilioteca.entities.UsuarioEntity;
import co.edu.uniandes.csw.biblioteca.ejb.LibroDigitalUsuarioLogic;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
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
    
    //@Deployment
    //public static JavaArchive createDeployment(){
        //return ShrinkWrap
    //}
    
}
