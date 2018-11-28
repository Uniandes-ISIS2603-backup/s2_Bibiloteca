/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.bibilioteca.entities.BibliotecaEntity;
import co.edu.uniandes.csw.bibilioteca.entities.SalaEntity;
import co.edu.uniandes.csw.biblioteca.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.biblioteca.persistence.BibliotecaPersistence;
import co.edu.uniandes.csw.biblioteca.persistence.SalaPersistence;
import java.util.logging.Logger;
import java.util.List;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Juan Nicolás García
 */
@Stateless
public class SalaLogic {
    private static final Logger LOGGER = Logger.getLogger(UsuarioLogic.class.getName());
    @Inject
    private SalaPersistence salaPersistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.
    @Inject
    private BibliotecaPersistence bibliotecaPersistence;
    /**
     * Crea una sala en la persistencia.
     *
     * @param salaEntity La entidad que representa  a
     * persistir.
     * @return La entidad de la sala luego de persistirla.
     * @throws BusinessLogicException Si la sala a persistir ya existe.
     */
     public SalaEntity createSala(Long bibliotecaId,SalaEntity sala) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia el proceso de crear una sala de la biblioteca con id " + sala.getBiblioteca().getId());
        BibliotecaEntity bibliotecaDeSala = bibliotecaPersistence.find(sala.getBiblioteca().getId());
        LOGGER.log(Level.INFO,"Encuentra la biblioteca" + bibliotecaDeSala);
	boolean existe = false;
        for (SalaEntity salaEx : bibliotecaDeSala.getSalas()) {
            if(salaEx.getUbicacion().equals(sala.getUbicacion()))
		existe = true;
            }
        
        if (existe == false) {
            sala.setBiblioteca(bibliotecaDeSala);
            LOGGER.log(Level.INFO, "La sala fue creado exitosamente");
            return salaPersistence.create(sala);
        } else {
            throw new BusinessLogicException("No puede crear la sala en la biblioteca con id = " + bibliotecaId + " por que ya existe una con la misma ubicación");
        }
    }
        
    
    /**
     *
     * Obtener todas las salas existentes de una biblioteca en la base de datos.
     *
     * @param bibliotecaId
     * @return una lista de salas.
     */
     public List<SalaEntity> getSalasPorBiblioteca(Long bibliotecaId) {
        LOGGER.log(Level.INFO, "Se comienza a buscar todas las salas de una biblioteca con id={0}", bibliotecaId);
        BibliotecaEntity biblio = bibliotecaPersistence.find(bibliotecaId);
        LOGGER.log(Level.INFO, "Termina la consulta de las salas de la biblioteca con id={0}", bibliotecaId);
        return biblio.getSalas();

    }
    
     /**
     *
     * Obtener una sala por medio de su id.
     *
     * @param bibliotecaId
     * @param salaId: id del Usuario para ser buscado.
     * @return la sala solicitada por medio de su id.
     */
     public SalaEntity getSalaBiblioteca(Long bibliotecaId, Long salaId) {
        LOGGER.log(Level.INFO, "Se comienza a buscar todas las salas con id={0}, de una biblioteca con id= " + bibliotecaId, salaId);
        return salaPersistence.find(bibliotecaId, salaId);
    }
    
     /**
     *
     * Actualizar una sala.
     * @param salaId: id de la sala para buscarla en la base de
     * datos.
     * @param salaEntity: sala con los cambios para ser actualizada,
     * por ejemplo la disponibilidad.
     * @return el usuario con los cambios actualizados en la base de datos.
     */
       public SalaEntity updateSala(Long bibliotecaId, SalaEntity sala) {
        LOGGER.log(Level.INFO, "Se comienza la actualizar la sala con id={0} de la biblioteca con id = "+ bibliotecaId,sala.getId());
        BibliotecaEntity biblio = bibliotecaPersistence.find(bibliotecaId);
        sala.setBiblioteca(biblio);
        salaPersistence.update(sala);
        LOGGER.log(Level.INFO, "Se termina la actualizacion de la sala con id = {0}",sala.getId());
        return sala;
    }
    
    public void deleteSala(Long bibliotecaId, Long salaId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO,"Inicia proceso de borrar la sala con id = {0} de la biblioteca con id = " + bibliotecaId, salaId);
        SalaEntity eliminar = getSalaBiblioteca(bibliotecaId, salaId);
        if(eliminar == null)
        {
            throw new BusinessLogicException("La sala con id = "+ salaId + " no esta asociado a la biblioteca con id = " + salaId);
        }
        salaPersistence.delete(eliminar.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar la sala con id = {0} de la biblioteca con id = " + bibliotecaId, salaId);
    }
}
