/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.test.persistence;

import co.edu.uniandes.csw.bibilioteca.entities.ComentarioEntity;
import co.edu.uniandes.csw.biblioteca.persistence.ComentarioPersistence;
import javax.inject.Inject;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author estudiante
 */
public class ComentarioPersistenceTest {
    
    //Inject no necesita hacer new porque el servidor lo crea por defecto y los guarda, el inject lo utiliza para obtener esa variable ya creada
    @Inject
    private ComentarioPersistence comentarioPersistence;
    
    @Test
    public void createComentarioTest()
    {
       PodamFactory factory = new PodamFactoryImpl(); 
       ComentarioEntity nuevaEntity = factory.manufacturePojo(ComentarioEntity.class);
       ComentarioEntity resultado = comentarioPersistence.create(nuevaEntity);
    }
}
