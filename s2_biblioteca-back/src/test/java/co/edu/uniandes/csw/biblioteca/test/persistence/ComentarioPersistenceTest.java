/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.test.persistence;

import co.edu.uniandes.csw.bibilioteca.entities.ComentarioEntity;
import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import co.edu.uniandes.csw.biblioteca.persistence.ComentarioPersistence;
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
public class ComentarioPersistenceTest {

    //Inject no necesita hacer new porque el servidor lo crea por defecto y los guarda, el inject lo utiliza para obtener esa variable ya creada
    @Inject
    private ComentarioPersistence comentarioPersistence;

    @PersistenceContext
    private EntityManager em;

    private List<ComentarioEntity> data = new ArrayList<>();

    private List<LibroEntity> dataLibro = new ArrayList<>();

    @Inject
    UserTransaction utx;

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComentarioEntity.class.getPackage())
                .addPackage(ComentarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            System.out.println("Entra 2");
            insertData();
            utx.commit();
            System.out.println("Entra 3");
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            LibroEntity entity = factory.manufacturePojo(LibroEntity.class);
            em.persist(entity);
            dataLibro.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            ComentarioEntity entity = factory.manufacturePojo(ComentarioEntity.class);
            if (i == 0) {
                entity.setLibro(dataLibro.get(0));
            }
            em.persist(entity);
            data.add(entity);
        }
    }

    private void clearData() {
        em.createQuery("delete from ComentarioEntity").executeUpdate();
        em.createQuery("delete from LibroEntity").executeUpdate();
    }

    /**
     * Prueba crear un nuevo comentario
     */
    @Test
    public void createComentarioTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioEntity nuevaEntity = factory.manufacturePojo(ComentarioEntity.class);
        ComentarioEntity resultado = comentarioPersistence.create(nuevaEntity);

        Assert.assertNotNull(resultado);

        ComentarioEntity entity = em.find(ComentarioEntity.class, resultado.getId());

        Assert.assertEquals(nuevaEntity.getNombreUsuario(), entity.getNombreUsuario());
        
    }

    /**
     * Prueba la consulta de un comentario
     */
    @Test
    public void getComentarioTest() {
        ComentarioEntity entity = data.get(0);
        ComentarioEntity nuevaEntity = comentarioPersistence.find(dataLibro.get(0).getId(), entity.getId());
        Assert.assertNotNull(nuevaEntity);
        Assert.assertEquals(entity.getNombreUsuario(), nuevaEntity.getNombreUsuario());
    }
    
    /**
     * Prueba eliminar un comentario
     */
    @Test
    public void deleteComentarioTest(){
        ComentarioEntity entity = data.get(0);
        comentarioPersistence.delete(entity.getId());
        ComentarioEntity eliminado = em.find(ComentarioEntity.class,entity.getId());
        Assert.assertNull(eliminado);
    }
    
    /**
     * Prueba actualizar un comentario
     */
    @Test
    public void updateComentarioTest(){
        ComentarioEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);
        newEntity.setId(entity.getId());
        comentarioPersistence.update(newEntity);
        
        ComentarioEntity respuesta = em.find(ComentarioEntity.class,entity.getId());
        
        Assert.assertEquals(respuesta.getNombreUsuario(), newEntity.getNombreUsuario());
        
        
    }
}
