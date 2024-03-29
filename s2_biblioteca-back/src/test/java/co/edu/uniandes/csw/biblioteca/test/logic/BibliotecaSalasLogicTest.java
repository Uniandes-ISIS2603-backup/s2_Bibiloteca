/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.test.logic;

import co.edu.uniandes.csw.bibilioteca.entities.BibliotecaEntity;
import co.edu.uniandes.csw.bibilioteca.entities.SalaEntity;
import co.edu.uniandes.csw.biblioteca.ejb.BibliotecaLogic;
import co.edu.uniandes.csw.biblioteca.ejb.BibliotecaSalasLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.BibliotecaPersistence;
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
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class BibliotecaSalasLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private BibliotecaLogic bibliotecaLogic;
    @Inject
    private BibliotecaSalasLogic bibliotecaSalasLogic;
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<BibliotecaEntity> data = new ArrayList<BibliotecaEntity>();

    private List<SalaEntity> salasData = new ArrayList();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(BibliotecaEntity.class.getPackage())
                .addPackage(BibliotecaLogic.class.getPackage())
                .addPackage(BibliotecaPersistence.class.getPackage())
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
        em.createQuery("delete from SalaEntity").executeUpdate();
        em.createQuery("delete from BibliotecaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            SalaEntity salas = factory.manufacturePojo(SalaEntity.class);
            em.persist(salas);
            salasData.add(salas);
        }
        for (int i = 0; i < 3; i++) {
            BibliotecaEntity entity = factory.manufacturePojo(BibliotecaEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                salasData.get(i).setBiblioteca(entity);
            }
        }
    }
    
    /**
     * Prueba para asociar una sala existente a un Biblioteca.
     */
    @Test
    public void addSalasTest() {
        BibliotecaEntity entity = data.get(0);
        SalaEntity salaEntity = salasData.get(1);
        SalaEntity response = bibliotecaSalasLogic.addSala(salaEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(salaEntity.getId(), response.getId());
    }
    
     /**
     * Prueba para obtener una colección de instancias de slas asociadas a una
     * instancia Biblioteca.
     */
    @Test
    public void getSalasTest() {
        List<SalaEntity> list = bibliotecaSalasLogic.getSalas(data.get(0).getId());

        Assert.assertEquals(1, list.size());
    }
    
    /**
     * Prueba para obtener una instancia de sala asociada a una instancia
     * Biblioteca
     *
     * @throws co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException
     */
    @Test
    public void getSalaTest() throws BusinessLogicException {
        BibliotecaEntity entity = data.get(0);
        SalaEntity salaEntity = salasData.get(0);
        SalaEntity response = bibliotecaSalasLogic.getSala(entity.getId(), salaEntity.getId());

        Assert.assertEquals(salaEntity.getId(), response.getId());
        Assert.assertEquals(salaEntity.getCapacidad(), response.getCapacidad());
        Assert.assertEquals(salaEntity.getDisponibilidad(), response.getDisponibilidad());
        Assert.assertEquals(salaEntity.getUbicacion(), response.getUbicacion());
        
    }
    
     /**
     * Prueba para obtener una instancia de Salas asociada a una instancia
     * Biblioteca que no le pertenece.
     *
     * @throws co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void getLibroNoAsociadoTest() throws BusinessLogicException {
        BibliotecaEntity entity = data.get(0);
        SalaEntity salaEntity = salasData.get(1);
        bibliotecaSalasLogic.getSala(entity.getId(), salaEntity.getId());
    }
    
     /**
     * Prueba para remplazar las instancias de Salas asociadas a una instancia
     * de Biblioteca.
     */
    @Test
    public void replaceSalasTest() {
        BibliotecaEntity entity = data.get(0);
        List<SalaEntity> list = salasData.subList(1, 3);
        bibliotecaSalasLogic.replaceSalas(entity.getId(), list);

        entity = bibliotecaLogic.getBiblioteca(entity.getId());
        Assert.assertFalse(entity.getSalas().contains(salasData.get(0)));
        Assert.assertTrue(entity.getSalas().contains(salasData.get(1)));
        Assert.assertTrue(entity.getSalas().contains(salasData.get(2)));
    }


}
