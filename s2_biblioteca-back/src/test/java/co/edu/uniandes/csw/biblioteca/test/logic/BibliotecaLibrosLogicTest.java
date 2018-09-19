/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.test.logic;

import co.edu.uniandes.csw.bibilioteca.entities.BibliotecaEntity;
import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import co.edu.uniandes.csw.biblioteca.ejb.BibliotecaLibrosLogic;
import co.edu.uniandes.csw.biblioteca.ejb.BibliotecaLogic;
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
 *  Pruebas de logica de la relacion Biblioteca - Salas
 * 
 * @author David Eduardo Saavedra Hernández. 
 */
@RunWith(Arquillian.class)
public class BibliotecaLibrosLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
   
    @Inject
    private BibliotecaLogic bibliotecaLogic;
    @Inject
    private BibliotecaLibrosLogic bibliotecaLibrosLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;

    private List<BibliotecaEntity> data = new ArrayList<BibliotecaEntity>();

    private List<LibroEntity> librosData = new ArrayList();

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
        em.createQuery("delete from LibroEntity").executeUpdate();
        em.createQuery("delete from BibliotecaEntity").executeUpdate();
        
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            LibroEntity libros = factory.manufacturePojo(LibroEntity.class);
            em.persist(libros);
            librosData.add(libros);
        }
        for (int i = 0; i < 3; i++) {
            BibliotecaEntity entity = factory.manufacturePojo(BibliotecaEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                librosData.get(i).setBiblioteca(entity);
            }
        }
    }
    
    /**
     * Prueba para asociar una sala existente a un Editorial.
     */
    @Test
    public void addLibrosTest() {
        BibliotecaEntity entity = data.get(0);
        LibroEntity libroEntity = librosData.get(1);
        LibroEntity response = bibliotecaLibrosLogic.addLibro(libroEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(libroEntity.getId(), response.getId());
    }
    
    /**
     * Prueba para obtener una colección de instancias de libros asociadas a una
     * instancia Biblioteca.
     */
    @Test
    public void getLibrosTest() {
        List<LibroEntity> list = bibliotecaLibrosLogic.getLibros(data.get(0).getId());
        System.out.println("Holi \n \n \n \n \n \n"+data.get(0).getId() );
        Assert.assertEquals(1, list.size());
    }
    
    /**
     * Prueba para obtener una instancia de Libros asociada a una instancia
     * Biblioteca.
     *
     * @throws co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException
     */
    @Test
    public void getLibroTest() throws BusinessLogicException {
        BibliotecaEntity entity = data.get(0);
        LibroEntity libroEntity = librosData.get(0);
        LibroEntity response = bibliotecaLibrosLogic.getLibro(entity.getId(), libroEntity.getId());

        Assert.assertEquals(libroEntity.getId(), response.getId());
        Assert.assertEquals(libroEntity.getAutor(), response.getAutor());
        Assert.assertEquals(libroEntity.getCalificacionPromedio(), response.getCalificacionPromedio());
        Assert.assertEquals(libroEntity.getIsbn(), response.getIsbn());
        Assert.assertEquals(libroEntity.getEdicion(), response.getEdicion());
        Assert.assertEquals(libroEntity.getEditorial(), response.getEditorial());
        Assert.assertEquals(libroEntity.getIdioma(), response.getIdioma());
        Assert.assertEquals(libroEntity.getUnidadesDisponibles(), response.getUnidadesDisponibles());
    }
    
    /**
     * Prueba para obtener una instancia de Libros asociada a una instancia
     * Biblioteca que no le pertenece.
     *
     * @throws co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void getLibroNoAsociadoTest() throws BusinessLogicException {
        BibliotecaEntity entity = data.get(0);
        LibroEntity bookEntity = librosData.get(1);
        bibliotecaLibrosLogic.getLibro(entity.getId(), bookEntity.getId());
    }
    
    /**
     * Prueba para remplazar las instancias de libros asociadas a una instancia
     * de Biblioteca.
     */
    @Test
    public void replaceLibrosTest() {
        BibliotecaEntity entity = data.get(0);
        List<LibroEntity> list = librosData.subList(1, 3);
        bibliotecaLibrosLogic.replaceBooks(entity.getId(), list);

        entity = bibliotecaLogic.getBiblioteca(entity.getId());
        Assert.assertFalse(entity.getLibros().contains(librosData.get(0)));
        Assert.assertTrue(entity.getLibros().contains(librosData.get(1)));
        Assert.assertTrue(entity.getLibros().contains(librosData.get(2)));
    }

}
