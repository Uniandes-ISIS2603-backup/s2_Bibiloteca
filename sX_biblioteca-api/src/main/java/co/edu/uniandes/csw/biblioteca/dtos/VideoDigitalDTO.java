/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.dtos;
import co.edu.uniandes.csw.bibilioteca.entities.VideoDigitalEntity;
import java.io.Serializable;
/**
 *
 * @author Juan Bautitsa
 */
public class VideoDigitalDTO implements Serializable {
    
    private Long id;
    private String nombre;
    private String director;
    private String idioma;
    private Boolean subtitulos;
    
    public VideoDigitalDTO()
    {
    }

    public VideoDigitalDTO(VideoDigitalEntity pVideoDigital) {
        
        this.id = pVideoDigital.getId();
        this.nombre = pVideoDigital.getNombre();
        this.director = pVideoDigital.getDirector();
        this.idioma = pVideoDigital.getIdioma();
        this.subtitulos = pVideoDigital.getSubtitulos();
    }
    public VideoDigitalEntity toEntity()
    {
        VideoDigitalEntity videoDigitalEntity = new VideoDigitalEntity();
        videoDigitalEntity.setId(this.id);
        videoDigitalEntity.setNombre(this.nombre);
        videoDigitalEntity.setDirector(this.director);
        videoDigitalEntity.setIdioma(this.idioma);
        videoDigitalEntity.setSubtitulos(this.subtitulos);       
        return videoDigitalEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

}