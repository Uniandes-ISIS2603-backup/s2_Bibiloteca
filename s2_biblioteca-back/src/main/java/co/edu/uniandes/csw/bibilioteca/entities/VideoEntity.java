package co.edu.uniandes.csw.bibilioteca.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Nicolas Alvarado
 */

@Entity
 public class VideoEntity extends BaseEntity implements Serializable {

   private String nombre;
   private String director;
   private Integer unidadesDisponibles;
   private String idioma;
   private Boolean subtitulos;
   
   @PodamExclude
   @ManyToOne(cascade = CascadeType.PERSIST)
   private BibliotecaEntity biblioteca;
   
   @PodamExclude
   @OneToMany(mappedBy = "video", cascade = CascadeType.PERSIST, orphanRemoval = true)
   private ArrayList<ReservaEntity> reservas = new ArrayList<>();
   
   @PodamExclude
   @OneToMany(mappedBy = "video", cascade = CascadeType.PERSIST, orphanRemoval = true)
   private ArrayList<PrestamoEntity> prestamos = new ArrayList<>();

    public BibliotecaEntity getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(BibliotecaEntity biblioteca) {
        this.biblioteca = biblioteca;
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
   
   public ArrayList<ReservaEntity> getReservas(){
       return reservas;
   }
   
   public ArrayList<PrestamoEntity> getPrestamos(){
       return prestamos;
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
   
   public void setReservas(ArrayList<ReservaEntity> listaRE){
       reservas = listaRE;
   }
   
   public void setPrestamos(ArrayList<PrestamoEntity> listPE){
       prestamos = listPE;
   }
   
 }
