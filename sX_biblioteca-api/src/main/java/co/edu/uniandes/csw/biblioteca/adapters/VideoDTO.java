package co.edu.uniandes.csw.biblioteca.adapters;

/**
 *
 * @author Nicolas Alvarado
 */

 public class VideoDTO {

   private String id;
   private String nombre;
   private String director;
   private int unidadesDisponibles;
   private String idioma;
   private boolean subtitulos;

   public VideoDTO(String pID, String pNombre, String pDirector, int pUnidades, String pIdioma, boolean pSubtitulos){
     id = pID;
     nombre = pNombre;
     director = pDirector;
     unidadesDisponibles = pUnidades;
     idioma = pIdioma;
     subtitulos = pSubtitulos;
   }
 }
