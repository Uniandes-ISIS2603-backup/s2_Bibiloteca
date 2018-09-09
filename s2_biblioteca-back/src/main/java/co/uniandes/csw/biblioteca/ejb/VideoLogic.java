package co.uniandes.csw.biblioteca.ejb;

import co.edu.uniandes.csw.biblioteca.persistence.*;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Nicolas Alvarado
 */

@Stateless
public class VideoLogic {
    
    @Inject
    private VideoPersistence videoPersis;
    
}
