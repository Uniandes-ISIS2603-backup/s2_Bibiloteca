/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.test.logic;

import co.edu.uniandes.csw.bibilioteca.entities.ComentarioEntity;
import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import co.edu.uniandes.csw.bibilioteca.entities.ReservaEntity;
import co.edu.uniandes.csw.biblioteca.ejb.ComentarioLogic;
import co.edu.uniandes.csw.biblioteca.ejb.LibroLogic;
import co.edu.uniandes.csw.biblioteca.ejb.LibroReservaLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.ComentarioPersistence;
import co.edu.uniandes.csw.biblioteca.persistence.ReservaPersistence;
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
public class LibroReservaLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private LibroLogic libroLogica;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    @Inject
    private LibroReservaLogic libroReservaLogica;

    //@Inject 
    //private ReservaLogic reserva;
    private List<LibroEntity> data = new ArrayList<>();

    private List<ReservaEntity> dataReservas = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReservaEntity.class.getPackage())
                .addPackage(LibroLogic.class.getPackage())
                .addPackage(LibroReservaLogic.class.getPackage())
                .addPackage(ReservaPersistence.class.getPackage())
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
        em.createQuery("delete from ReservaEntity").executeUpdate();
        em.createQuery("delete from LibroEntity").executeUpdate();
        em.createQuery("delete from BibliotecaEntity").executeUpdate();
    }

    private void insertData() {
        for (int j = 0; j < 3; j++) {
            ReservaEntity reserva = factory.manufacturePojo(ReservaEntity.class);
            em.persist(reserva);
            dataReservas.add(reserva);
        }
        for (int i = 0; i < 3; i++) {
            LibroEntity entity = factory.manufacturePojo(LibroEntity.class);
            em.persist(entity);
            if (i == 0) {
                entity.setReservas(dataReservas);
            }
            data.add(entity);

        }
    }

    @Test
    public void addReservaTest() throws BusinessLogicException {
        LibroEntity libro = data.get(0);
        ReservaEntity reserva = dataReservas.get(0);
        ReservaEntity resultado = libroReservaLogica.addReserva(libro.getId(), reserva.getId());
        Assert.assertNotNull(resultado);
        ReservaEntity reservaEncontrado = em.find(ReservaEntity.class, resultado.getId());
        Assert.assertEquals(reserva.getId(), reservaEncontrado.getId());
    }

    @Test
    public void deleteReservaTest() throws BusinessLogicException {
        LibroEntity libro = data.get(1);
        libro.setReservas(dataReservas);
        libroReservaLogica.deleteReserva(libro.getId(), dataReservas.get(0).getId());
        ReservaEntity reserva = libroReservaLogica.getReserva(libro.getId(), dataReservas.get(0).getId());
        Assert.assertNull(reserva);
    }

}

