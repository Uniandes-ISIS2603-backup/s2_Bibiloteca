
import co.edu.uniandes.csw.bibilioteca.entities.BibliotecaEntity;
import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author David Eduardo Saavedra Hernández
 */
@RunWith(Arquillian.class)
public class BibliotecalLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private BibliotecaLogic bibliotecaLogic;
    
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
            BibliotecaEntity entityBiblioteca = factory.manufacturePojo(BibliotecaEntity.class);
            em.persist(entityBiblioteca);
            data.add(entityBiblioteca);
            if (i == 0) {
                librosData.get(i).setBiblioteca(entityBiblioteca);
            }
        }
    }
    
    /**
     * Prueba para crear una Biblioteca
     *
     * @throws co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException
     */
    @Test
    public void createBibliotecaTest() throws BusinessLogicException 
    {
        BibliotecaEntity newEntity = factory.manufacturePojo(BibliotecaEntity.class);
        BibliotecaEntity result = bibliotecaLogic.createBiblioteca(newEntity);
        Assert.assertNotNull(result);
        BibliotecaEntity entity = em.find(BibliotecaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    
    /**
     * Prueba para crear un Biblioteca con el mismo nombre de un biblioteca que ya
     * existe.
     *
     * @throws co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createBibliotecaConMismoNombreTest() throws BusinessLogicException {
        BibliotecaEntity newEntity = factory.manufacturePojo(BibliotecaEntity.class);
        newEntity.setNombre(data.get(0).getNombre());
        bibliotecaLogic.createBiblioteca(newEntity);
    }
    
    /**
     * Prueba para consultar la lista de Bibliotecas.
     */
    @Test
    public void getBibliotecasTest() {
        List<BibliotecaEntity> list = bibliotecaLogic.getBibliotecas();
        Assert.assertEquals(data.size(), list.size());
        for (BibliotecaEntity entity : list) {
            boolean found = false;
            for (BibliotecaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar una Biblioteca.
     */
    @Test
    public void getBibliotecaTest() {
        BibliotecaEntity entity = data.get(0);
        BibliotecaEntity resultEntity = bibliotecaLogic.getBiblioteca(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
    }
    
    /**
     * Prueba para eliminar un Biblioteca
     *
     * @throws co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException
     */
    @Test
    public void deleteEditorialTest() throws BusinessLogicException {
        BibliotecaEntity entity = data.get(1);
        bibliotecaLogic.deleteEditorial(entity.getId());
        BibliotecaEntity deleted = em.find(BibliotecaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
