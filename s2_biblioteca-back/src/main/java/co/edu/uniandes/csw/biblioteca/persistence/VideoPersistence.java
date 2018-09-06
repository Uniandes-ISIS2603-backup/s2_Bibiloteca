
package co.edu.uniandes.csw.biblioteca.persistence;

import co.edu.uniandes.csw.bibilioteca.entities.VideoEntity;
import javax.ejb.Stateless;
import javax.persistence.*;

/**
 *
 * @author Nicolas Alvarado
 */

@Stateless
public class VideoPersistence {
    
    @PersistenceContext(unitName="DnsPU")
    protected EntityManager em;
    
    public VideoEntity create(VideoEntity video){
        em.persist(video);
        return video;
    }
    
    public VideoEntity find(Long videoID){
        return em.find(VideoEntity.class, videoID);
    }
    
    public VideoEntity update(VideoEntity video){
        return em.merge(video);
    }
    
    public void delete(Long videoID){
        VideoEntity v = find(videoID);
        em.remove(v);
    }
}
