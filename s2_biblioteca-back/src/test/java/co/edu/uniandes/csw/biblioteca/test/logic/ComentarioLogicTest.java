/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.test.logic;

import co.edu.uniandes.csw.bibilioteca.entities.ComentarioEntity;
import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import co.edu.uniandes.csw.bibilioteca.entities.UsuarioEntity;
import co.edu.uniandes.csw.biblioteca.ejb.ComentarioLogic;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
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
public class ComentarioLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ComentarioLogic comentarioLogica;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ComentarioEntity> data = new ArrayList<>();

    private List<LibroEntity> dataLibro = new ArrayList<>();

    private List<UsuarioEntity> dataUsuario = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComentarioEntity.class.getPackage())
                .addPackage(ComentarioLogic.class.getPackage())
                .addPackage(ComentarioPersistence.class.getPackage())
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
        em.createQuery("delete from ComentarioEntity").executeUpdate();
        em.createQuery("delete from LibroEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    private void insertData() {
        for(int i = 0 ; i<3 ; i++)
        {
            LibroEntity libro = factory.manufacturePojo(LibroEntity.class);
            em.persist(libro);
            dataLibro.add(libro);
        }
        
        for(int j = 0 ; j < 3 ; j++)
        {
            UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(usuario);
            dataUsuario.add(usuario);
        }
        
        for(int k = 0 ; k < 3 ; k++)
        {
            ComentarioEntity comentario = factory.manufacturePojo(ComentarioEntity.class);
            comentario.setLibro(dataLibro.get(1));
            comentario.setUsuario(dataUsuario.get(1));
            em.persist(comentario);
            data.add(comentario);
        }
    }
    
    @Test
    public void createComentarioTest() throws BusinessLogicException
    {
        ComentarioEntity nuevaEntity = factory.manufacturePojo(ComentarioEntity.class);
        nuevaEntity.setLibro(dataLibro.get(1));
        nuevaEntity.setUsuario(dataUsuario.get(1));
        ComentarioEntity resultado = comentarioLogica.createComentario(dataLibro.get(1).getId(), nuevaEntity, dataUsuario.get(1).getId());
        Assert.assertNotNull(resultado);
        ComentarioEntity entity = em.find(ComentarioEntity.class, resultado.getId());
        Assert.assertEquals(nuevaEntity.getId(), entity.getId());
    }

    @Test
    public void getComentariosPorLibroTest()
    {
        List<ComentarioEntity> lista = comentarioLogica.getComentariosPorLibro(dataLibro.get(1).getId());
        Assert.assertEquals(data.size(), lista.size());
        for(ComentarioEntity entity : lista)
        {
            boolean encontrado = false; 
            for(ComentarioEntity comp : data)
            {
                if(entity.getId().equals(comp.getId()))
                {
                 encontrado = true;   
                }
            }
            Assert.assertTrue(encontrado);
        }
        
    }
    
    @Test
    public void getComentariosPorUsuarioTest()
    {
        List<ComentarioEntity> lista = comentarioLogica.getComentariosPorUsuario(dataUsuario.get(1).getId());
        Assert.assertEquals(data.size(),lista.size());
        for(ComentarioEntity entity : lista)
        {
            boolean encontrado = false;
            for(ComentarioEntity comp : data)
            {
                if(entity.getId().equals(comp.getId()))
                {
                    encontrado = true;   
                }
            }
            Assert.assertTrue(encontrado);
        }
              
    }
    
    @Test
    public void getComentarioPorLibroTest()
    {
        ComentarioEntity entity = data.get(0);
        ComentarioEntity resultado = comentarioLogica.getComentarioLibro(dataLibro.get(1).getId(), entity.getId());
        Assert.assertNotNull(resultado);
        Assert.assertEquals(entity.getId(), resultado.getId());
    }
    
    @Test
    public void getComentarioPorUsuarioTest()
    {
        ComentarioEntity entity = data.get(0);
        ComentarioEntity resultado = comentarioLogica.getComentarioUsuario(dataUsuario.get(1).getId(), entity.getId());
        Assert.assertNotNull(resultado);
        Assert.assertEquals(entity.getId(), resultado.getId());
    }
    
    @Test
    public void updateComentarioTest()
    {
        ComentarioEntity entity = data.get(0);
        ComentarioEntity pojoEntity = factory.manufacturePojo(ComentarioEntity.class);

        pojoEntity.setId(entity.getId());

        comentarioLogica.updateComentario(dataLibro.get(1).getId(), pojoEntity,dataUsuario.get(1).getId());

        ComentarioEntity resp = em.find(ComentarioEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
    }
    
        @Test
    public void deleteComentarioTest() throws BusinessLogicException {
        ComentarioEntity entity = data.get(0);
        comentarioLogica.deleteComentario(dataLibro.get(1).getId(), entity.getId());
        ComentarioEntity deleted = em.find(ComentarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
