/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bibilioteca.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Juan Bautista
 */
@Entity
public class VideoDigitalEntity extends BaseEntity implements Serializable {
    private String nombre;
    private String director;
    private String idioma;
    private Boolean subtitulos;
    
    @PodamExclude
    @ManyToMany
    private List<UsuarioEntity> usuarios = new ArrayList<UsuarioEntity>();
    


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Boolean getSubtitulos() {
        return subtitulos;
    }
    public void setSubtitulos(Boolean subtitulos) {
        this.subtitulos = subtitulos;
    }
    public List<UsuarioEntity> getUsuarios(){
       return usuarios;
   }
 public void setUsuarios(List<UsuarioEntity> listUsuarios){
       usuarios = listUsuarios;
   }
}
