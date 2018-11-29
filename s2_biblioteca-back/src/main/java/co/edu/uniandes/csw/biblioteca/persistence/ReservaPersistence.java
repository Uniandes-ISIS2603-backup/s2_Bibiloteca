/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.persistence;

import co.edu.uniandes.csw.bibilioteca.entities.ReservaEntity;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para una Reserva. Se conecta a través Entity
 * Manager de javax.persistance con la base de datos SQL.
 * @author Daniel Preciado
 */
@Stateless
public class ReservaPersistence {
    

    //__________________________________________________________________________
    //Constantes
    //__________________________________________________________________________
    
    /**
     * constante utilizada para dejar huella y registro
     */
    private static final Logger LOGGER = Logger.getLogger(ReservaPersistence.class.getName());
    
    //__________________________________________________________________________
    //Atributos
    //__________________________________________________________________________
    
    /**
     * 
     */
    @PersistenceContext(unitName = "DnsPU")
    protected EntityManager entityManager;
    
    //__________________________________________________________________________
    //Metodos
    //__________________________________________________________________________
    
     /**
     * Método para persisitir la entidad en la base de datos.
     * crea una nueva reserva en la base de datos
     * @param pReservaEntity objeto reserva que se creará en la base de datos
     * @return devuelve la entidad creada con un id generado por la base de datos.
     */
    public ReservaEntity create(ReservaEntity pReservaEntity) 
    {
        LOGGER.log(Level.INFO, "Creando una reserva nueva");
        entityManager.persist(pReservaEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una reserva nueva");
        return pReservaEntity;
    }

    /**
     * Devuelve todas las reservas de la base de datos.
     * @return una lista con todas las reservas que encuentre en la base de
     * datos.
     */
    public List<ReservaEntity> findAll()    
    {
        LOGGER.log(Level.INFO, "Consultando todas las editoriales");
        TypedQuery query;
        query = entityManager.createQuery("select u from ReservaEntity u", ReservaEntity.class);
        return query.getResultList();
    }

    /**
     * Busca si hay alguna reserva con el id que se envía por parametro
     * @param pReservaId: id correspondiente a la reserva buscada.
     * @return una reserva.
     */
    public ReservaEntity find(Long pReservaId) 
    {
        LOGGER.log(Level.INFO, "Consultando reserva con id={0}", pReservaId);
        return entityManager.find(ReservaEntity.class, pReservaId);
    }

    /**
     * Actualiza una reserva.
     * @param pReservaEntity: la reserva que viene con los nuevos cambios.
     * Por ejemplo la fecha pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una reserva con los cambios aplicados.
     */
    public ReservaEntity update(ReservaEntity pReservaEntity) 
    {
        LOGGER.log(Level.INFO, "Actualizando la reserva con id = {0}", pReservaEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar la reserva con id = {0}", pReservaEntity.getId());
        return entityManager.merge(pReservaEntity);
    }

    /**
     * Borra una reserva de la base de datos recibiendo como parametro el id     *
     * @param pReservaId: id correspondiente a la reserva a borrar.
     */
    public void delete(Long pReservaId)
    {
        LOGGER.log(Level.INFO, "Borrando la reserva con id = {0}", pReservaId);
        ReservaEntity entityToDelete = entityManager.find(ReservaEntity.class, pReservaId);
        entityManager.remove(entityToDelete);
        LOGGER.log(Level.INFO, "Saliendo de borrar la reserva con id = {0}", pReservaId);
    }

    /**
     * Busca si hay alguna reserva con la fecha que se envía por parametro
     * @param pFechaReserva: fecha de la reserva que se está buscando
     * @return null si no existe ninguna reserva con la fecha dada.
     * Si existe alguna devuelve la primera.
     */
    public ReservaEntity findByDate(Date pFechaReserva)
    {
       
        TypedQuery query = entityManager.createQuery("Select e From ReservaEntity e where e.fechaReserva = :pFechaReserva", ReservaEntity.class);
        query = query.setParameter("pFechaReserva", pFechaReserva);
        List<ReservaEntity> sameDate = query.getResultList();
        ReservaEntity result;
        if (sameDate == null) 
        {
            result = null;
        } 
        else if (sameDate.isEmpty()) 
        {
            result = null;
        } 
        else 
        {
            result = sameDate.get(0);
        }
       
        return result;
    }
    
     /**
     * Busca si hay alguna reserva con el id de un recurso que se envía por parametro
     * @param pId: id del recurso de la reserva que se está buscando
     * @param pTipo: tipo del recurso de la reserva que se esta buscando
     * @return null si no existe ninguna reserva con el id dado.
     * Si existe alguna busca la que tenga el mismo id en el tipo de recurso para
     * retornar un dato exacto.
     */
    public ReservaEntity findByIdRecursoReservado(long pId,String pTipo  )
    {
        LOGGER.log(Level.INFO, "Consultando reserva por id del recurso reservado = {0}", pId);
        TypedQuery query = entityManager.createQuery("Select e From ReservaEntity e where e.idRecursoReservado = :pId", ReservaEntity.class);
        query = query.setParameter("pId", pId);
        List<ReservaEntity> sameId = query.getResultList();
        ReservaEntity result = null;
        if(!sameId.isEmpty() || sameId != null) 
        {
            boolean termine = false;
            int cont = 0;
            while(!termine)
            {

                if(sameId.get(cont).getTipoRecurso().equals(pTipo))
                {
                    result = sameId.get(cont);
                    termine = true;
                }
                else if(cont >= sameId.size())
                {
                    termine = true;
                }
                else
                {
                    cont ++;
                }
                
            }
        }

        LOGGER.log(Level.INFO, "Saliendo de consultar reserva por id de recurso = {0} ", pId);
        return result;
    }
    
}
