package co.edu.uniandes.csw.biblioteca.dtos;

import co.edu.uniandes.csw.bibilioteca.entities.VideoEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Nicolas Alvarado
 */
public class VideoDTO implements Serializable {

    private Long id;
    private String nombre;
    private String director;
    private Integer unidadesDisponibles;
    private String idioma;
    private Boolean subtitulos;
    
    private BibliotecaDTO biblioteca;

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param videoEntity: Es la entidad que se va a convertir a DTO
     */
    public VideoDTO(VideoEntity videoEntity) {
        if (videoEntity != null) {
            this.id = videoEntity.getId();
            this.nombre = videoEntity.getNombre();
            this.director = videoEntity.getDirector();
            this.unidadesDisponibles = videoEntity.getUnidadesDis();
            this.idioma = videoEntity.getIdioma();
            this.subtitulos = videoEntity.getSubtitulos();
            if(videoEntity.getBiblioteca() != null){
                biblioteca = new BibliotecaDTO(videoEntity.getBiblioteca());
            }
            else{
                biblioteca = null;
            }
        }
    }
    
    public VideoDTO() {

    }

    /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa el libro.
     */

    public VideoEntity toEntity() {
        VideoEntity ve = new VideoEntity();
        ve.setId(this.id);
        ve.setNombre(this.nombre);
        ve.setDirector(this.director);
        ve.setUnidadesDis(this.unidadesDisponibles);
        ve.setIdioma(this.idioma);
        ve.setSubtitulos(this.subtitulos);
        if(biblioteca != null){
            ve.setBiblioteca(biblioteca.toEntity());
        }
        return ve;
    }
    
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDirector() {
        return director;
    }

    public Integer getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public String getIdioma() {
        return idioma;
    }

    public Boolean getSubtitulos() {
        return subtitulos;
    }
    
    public BibliotecaDTO getBiblioteca(){
        return biblioteca;
    }

    public void setId(Long pID) {
        id = pID;
    }

    public void setNombre(String pNombre) {
        nombre = pNombre;
    }

    public void setDirector(String pDirector) {
        director = pDirector;
    }

    public void setUnidadesDisponibles(Integer pUnidades) {
        unidadesDisponibles = pUnidades;
    }

    public void setIdioma(String pIdioma) {
        idioma = pIdioma;
    }

    public void setSubtitulos(Boolean pSubtitulos) {
        subtitulos = pSubtitulos;
    }
    
    public void setBiblioteca(BibliotecaDTO pBiblioteca){
        biblioteca = pBiblioteca;
    }
        @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
