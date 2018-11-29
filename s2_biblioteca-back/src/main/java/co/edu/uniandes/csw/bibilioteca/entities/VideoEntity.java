package co.edu.uniandes.csw.bibilioteca.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.eclipse.persistence.jpa.config.Cascade;
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
   private List<ReservaEntity> reservas = new ArrayList<>();
   
   @PodamExclude
   @OneToOne
   private List<PrestamoEntity> prestamos = new ArrayList<>();

   public BibliotecaEntity getBiblioteca() {
     return biblioteca;
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
   
   public List<ReservaEntity> getReservas(){
       return reservas;
   }
   
   public List<PrestamoEntity> getPrestamos(){
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
   
   public void setBiblioteca(BibliotecaEntity pbiblioteca) {
       biblioteca = pbiblioteca;
   }
   
   public void setReservas(List<ReservaEntity> listaRE){
       reservas = listaRE;
   }
   
   public void setPrestamos(List<PrestamoEntity> listPE){
       prestamos = listPE;
   }

    public Integer getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(Integer unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    //El equals del video 
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
        final VideoEntity other = (VideoEntity) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.director, other.director)) {
            return false;
        }
        if (!Objects.equals(this.idioma, other.idioma)) {
            return false;
        }
        if (!Objects.equals(this.unidadesDisponibles, other.unidadesDisponibles)) {
            return false;
        }
        if (!Objects.equals(this.subtitulos, other.subtitulos)) {
            return false;
        }
        if (!Objects.equals(this.biblioteca, other.biblioteca)) {
            return false;
        }
        if (!Objects.equals(this.reservas, other.reservas)) {
            return false;
        }
        if (!Objects.equals(this.prestamos, other.prestamos)) {
            return false;
        }
        return true;
    }
   
    
 }
