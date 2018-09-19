/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.test.logic;

import co.edu.uniandes.csw.bibilioteca.entities.ComentarioEntity;
import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import co.edu.uniandes.csw.bibilioteca.entities.PrestamoEntity;
import co.edu.uniandes.csw.biblioteca.ejb.ComentarioLogic;
import co.edu.uniandes.csw.biblioteca.ejb.LibroLogic;
import co.edu.uniandes.csw.biblioteca.ejb.LibroPrestamoLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.ComentarioPersistence;
import co.edu.uniandes.csw.biblioteca.persistence.PrestamoPersistence;
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
 * @author Daniel Montoya
 */
@RunWith(Arquillian.class)
public class LibroPrestamoLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private LibroLogic libroLogica;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;
    
    @Inject
    private LibroPrestamoLogic libroPrestamoLogica;
    
    //@Inject 
    //private PrestamoLogic prestamo;
    
    private List<LibroEntity> data = new ArrayList<>();
    
    private List<PrestamoEntity> dataPrestamos = new ArrayList<>();
    
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PrestamoEntity.class.getPackage())
                .addPackage(LibroLogic.class.getPackage())
                .addPackage(PrestamoPersistence.class.getPackage())
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

    private void clearData() {
        em.createQuery("delete from PrestamoEntity").executeUpdate();
        em.createQuery("delete from LibroEntity").executeUpdate();
    }

    private void insertData() {
        for (int i = 0; i < 3; i++) {
            LibroEntity entity = factory.manufacturePojo(LibroEntity.class);
            em.persist(entity);
            data.add(entity);
        }
        for (int j = 0 ; j < 3 ; j++)
        {
            PrestamoEntity prestamo = factory.manufacturePojo(PrestamoEntity.class);
            em.persist(prestamo);
            dataPrestamos.add(prestamo);
            if(j==0)
            {
                data.get(j).setPrestamos(dataPrestamos);
            }
        }
    }
    
    @Test
    public void addPrestamoTest() throws BusinessLogicException
    {
        PrestamoEntity entity = factory.manufacturePojo(PrestamoEntity.class);
        //PrestamoEntity intermedio = 
        PrestamoEntity resultado = libroPrestamoLogica.addPrestamo(data.get(0).getId(), entity.getId());
        Assert.assertNotNull(resultado);
    }

}
