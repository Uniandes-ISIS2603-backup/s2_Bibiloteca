/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.test.logic;

import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import co.edu.uniandes.csw.bibilioteca.entities.PrestamoEntity;
import co.edu.uniandes.csw.bibilioteca.entities.SalaEntity;
import co.edu.uniandes.csw.bibilioteca.entities.VideoEntity;
import co.edu.uniandes.csw.biblioteca.ejb.PrestamoLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.PrestamoPersistence;
import java.time.Clock;
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
 *  Pruebas de logica de la relacion Prestamo
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class PrestamoLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private PrestamoLogic prestamoLogic;

    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<PrestamoEntity> dataPrestamo = new ArrayList<PrestamoEntity>();

    private List<LibroEntity> dataLibro= new ArrayList<LibroEntity>();

    private List<SalaEntity> dataSala= new ArrayList<SalaEntity>();
    
    private List<VideoEntity> dataVideo= new ArrayList<VideoEntity>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PrestamoEntity.class.getPackage())
                .addPackage(PrestamoLogic.class.getPackage())
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

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from PrestamoEntity").executeUpdate();
        em.createQuery("delete from LibroEntity").executeUpdate();
        em.createQuery("delete from SalaEntity").executeUpdate();
        em.createQuery("delete from VideoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PrestamoEntity prestamo = factory.manufacturePojo(PrestamoEntity.class);
            em.persist(prestamo);
            dataPrestamo.add(prestamo);
        }

        for (int i = 0; i < 3; i++) {
            LibroEntity libro = factory.manufacturePojo(LibroEntity.class);
            em.persist(libro);
            dataLibro.add(libro);
        }
        
        for (int i = 0; i < 3; i++) {
            SalaEntity sala = factory.manufacturePojo(SalaEntity.class);
            em.persist(sala);
            dataSala.add(sala);
        }
        
        for (int i = 0; i < 3; i++) {
            VideoEntity video = factory.manufacturePojo(VideoEntity.class);
            em.persist(video);
            dataVideo.add(video);
        }

        
    }
    
     /**
     * Prueba para crear un Prestamo
     *
     * @throws co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException
     */
    @Test
    public void createPrestamoTest() throws BusinessLogicException 
    {
        // Probar un prestamo para Un libro
        PrestamoEntity newEntity = factory.manufacturePojo(PrestamoEntity.class);
        dataLibro.get(0).setUnidadesDisponibles(1);
        newEntity.setLibro(dataLibro.get(0));
        PrestamoEntity result = prestamoLogic.createPrestamo(newEntity);
        Assert.assertNotNull(result);
        System.out.println(newEntity.getFechaDeEntrega()+ " "+ result.getFechaDeEntrega()+ "/n /n /n /n /n /n /n /n /n /n" );
        PrestamoEntity entity = em.find(PrestamoEntity.class, result.getId());
        System.out.println(newEntity.getFechaDeEntrega()+ " "+ entity.getFechaDeEntrega()+ "/n /n /n /n /n /n /n /n /n /n" );
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getLibro(), entity.getLibro());
        
        // Probar un prestamo para un video
        PrestamoEntity newEntity1 = factory.manufacturePojo(PrestamoEntity.class);
        dataVideo.get(0).setUnidadesDis(1);
        newEntity1.setVideo(dataVideo.get(0));
        
        PrestamoEntity result1 = prestamoLogic.createPrestamo(newEntity1);
        Assert.assertNotNull(result1);
        
        PrestamoEntity entity1 = em.find(PrestamoEntity.class, result1.getId());
        Assert.assertEquals(newEntity1.getId(), entity1.getId());
        Assert.assertEquals(newEntity1.getLibro(), entity1.getLibro());
        
         // Probar un prestamo para una Sala
        PrestamoEntity newEntity2 = factory.manufacturePojo(PrestamoEntity.class);
        dataSala.get(0).setDisponibilidad(true);
        newEntity2.setSala(dataSala.get(0));
        PrestamoEntity result2 = prestamoLogic.createPrestamo(newEntity2);
        Assert.assertNotNull(result2);
        
        PrestamoEntity entity2 = em.find(PrestamoEntity.class, result2.getId());
        Assert.assertEquals(newEntity2.getId(), entity2.getId());
        Assert.assertEquals(newEntity2.getLibro(), entity2.getLibro());
    }
    
    /**
     * Prueba para consultar la lista de Prestamos.
     *
     * @throws co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException
     */
    @Test
    public void getPrestamosTest() throws BusinessLogicException {
        List<PrestamoEntity> list = prestamoLogic.getPrestamos();
        Assert.assertEquals(dataPrestamo.size(), list.size());
        for (PrestamoEntity entity : list) {
            boolean found = false;
            for (PrestamoEntity storedEntity : dataPrestamo) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

     /**
     * Prueba para consultar un Prestamo.
     */
    @Test
    public void getPrestamoTest() {
        PrestamoEntity entity = dataPrestamo.get(0);
        PrestamoEntity resultEntity = prestamoLogic.getPrestamo(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
       
    }
    
    /**
     * Prueba para actualizar un Prestamo.
     */
    @Test
    public void updatePrestamoTest() {
        PrestamoEntity entity = dataPrestamo.get(0);
        PrestamoEntity pojoEntity = factory.manufacturePojo(PrestamoEntity.class);

        pojoEntity.setId(entity.getId());

        prestamoLogic.updatePrestamo(pojoEntity.getId(),pojoEntity);

        PrestamoEntity resp = em.find(PrestamoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
      
    }

    /**
     * Prueba para eliminar un Prestamo.
     *
     * @throws co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException
     */
    @Test
    public void deletePrestamoTest() throws BusinessLogicException {
        PrestamoEntity entity = dataPrestamo.get(0);
        prestamoLogic.deletePrestamo( entity.getId());
        PrestamoEntity deleted = em.find(PrestamoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
