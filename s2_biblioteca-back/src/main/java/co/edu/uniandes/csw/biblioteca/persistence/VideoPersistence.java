
package co.edu.uniandes.csw.biblioteca.persistence;

import co.edu.uniandes.csw.bibilioteca.entities.VideoEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;

/**
 *
 * @author Nicolas Alvarado
 */

@Stateless
public class VideoPersistence {
    
    @PersistenceContext(unitName = "DnsPU")
    protected EntityManager em;
    
    public VideoEntity create(VideoEntity video){
        em.persist(video);
        return video;
    }
    
    public VideoEntity find(Long videoID){
        return em.find(VideoEntity.class, videoID);
    }
    
    public List<VideoEntity> findAll(){
        TypedQuery querty = em.createQuery("select u from VideoEntity u", VideoEntity.class);
        return querty.getResultList();
    }
    
    public VideoEntity findByName(String pNombre){
        TypedQuery querty = em.createQuery("Select e From VideoEntity e where e.nombre = :name", VideoEntity.class);
        querty = querty.setParameter("name", pNombre);
        List<VideoEntity> results = querty.getResultList();
        VideoEntity re;
        if(results == null || results.isEmpty()){
            re = null;
        }
        else{
            re = results.get(0);
        }
        return re;
    }
    
    public VideoEntity update(VideoEntity video){
        return em.merge(video);
    }
    
    public void delete(Long videoID){
        VideoEntity v = find(videoID);
        em.remove(v);
    }
}
