/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bibilioteca.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
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
    private List<UsuarioEntity> usuarios = new ArrayList<>();

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

    public List<UsuarioEntity> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioEntity> listUsuarios) {
        usuarios = listUsuarios;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    //El equals de la clase videoDigital
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final VideoDigitalEntity other = (VideoDigitalEntity) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.director, other.director)) {
            return false;
        }
        if (!Objects.equals(this.idioma, other.idioma)) {
            return false;
        }
        if (!Objects.equals(this.subtitulos, other.subtitulos)) {
            return false;
        }
        if (!Objects.equals(this.usuarios, other.usuarios)) {
            return false;
        }
        return true;
    }

    
}
