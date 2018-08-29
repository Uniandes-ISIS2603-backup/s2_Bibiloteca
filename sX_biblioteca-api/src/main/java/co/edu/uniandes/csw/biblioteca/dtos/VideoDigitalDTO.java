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
    public VideoDigitalDTO(Long pId,String pNombre,String pDirector,String pIdioma,Boolean pSubtitulos)
    {
        id=pId;
        nombre=pNombre;
        director=pDirector;
        idioma=pIdioma;
        subtitulos=pSubtitulos;
    }
    public Long getId()
    {
        return id;
    }
    public String getNombre()
    {
        return nombre;
    }
    public String getDirector()
    {
        return director;
    }
    public String getIdioma()
    {
        return idioma;
    }
    public Boolean getSubtitulos()
    {
        return subtitulos;
    }
    public void setId(Long pId)
    {
        id=pId;
    }
    public void setNombre(String pNombre)
    {
        nombre=pNombre;
    }
     public void setDirector(String pDirector)
    {
        director=pDirector;
    }
      public void setIdioma(String pIdioma)
    {
        idioma=pIdioma;
    }
       public void setSubtitulos(Boolean pSubtitulos)
    {
        subtitulos=pSubtitulos;
    }
}
