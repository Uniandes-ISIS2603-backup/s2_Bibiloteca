package co.edu.uniandes.csw.bibilioteca.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Nicolas Alvarado
 */

@Entity
 public class VideoEntity extends BaseEntity implements Serializable {

   @Id
   private Long id;
   private String nombre;
   private String director;
   private Integer unidadesDisponibles;
   private String idioma;
   private Boolean subtitulos;

   public VideoEntity(Long pID, String pNombre, String pDirector, Integer pUnidades, String pIdioma, Boolean pSubtitulos){

   }

   public Long getId(){
     return id;
   }

   public String getNombre(){
     return nombre;
   }

   public String getDirector(){
     return director;
   }

   public Integer getUnidadesDis(){
     return unidadesDisponibles;
   }

   public String getIdioma(){
     return idioma;
   }

   public Boolean getSubtitulos(){
     return subtitulos;
   }

   public void setId(Long pID){
     id = pID;
   }

   public void setNombre(String pNombre){
     nombre = pNombre;
   }

   public void setDirector(String pDirector){
     director = pDirector;
   }

   public void setUnidadesDis(Integer pUnidades){
     unidadesDisponibles = pUnidades;
   }

   public void setIdioma(String pIdioma){
     idioma = pIdioma;
   }

   public void setSubtitulos(Boolean pSubtitulos){
     subtitulos = pSubtitulos;
   }
 }
