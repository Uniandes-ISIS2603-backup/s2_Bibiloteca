/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.dtos;

/**
 *
 * @author Juan Bautitsa
 */
public class VideoDigitalDTO {
    private Long id;
    private String nombre;
    private String director;
    private String idioma;
    private Boolean subtitulos;

    public VideoDigitalDTO(Long id, String nombre, String director, String idioma, Boolean subtitulos) {
        this.id = id;
        this.nombre = nombre;
        this.director = director;
        this.idioma = idioma;
        this.subtitulos = subtitulos;
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