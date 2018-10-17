 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.dtos;

import co.edu.uniandes.csw.bibilioteca.entities.BibliotecaEntity;
import co.edu.uniandes.csw.bibilioteca.entities.LibroEntity;
import co.edu.uniandes.csw.bibilioteca.entities.SalaEntity;
import co.edu.uniandes.csw.bibilioteca.entities.UsuarioEntity;
import co.edu.uniandes.csw.bibilioteca.entities.VideoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author estudiante
 */
public class BibliotecaDetailDTO extends BibliotecaDTO implements Serializable 
{
    //-----------------------------------------------------------------------------
    // ATRIBUTOS
    //-----------------------------------------------------------------------------
    
    /**
     * Atributo que representa los videos fisicos que tiene la bilioteca
     * Relacion cero a muchos
    */
    private List<VideoDTO> videos;
    
    /**
     * Atributo que representa los libros fisicos que tiene la bilioteca
     * Relacion cero a muchos
     */
    private List<LibroDTO> libros;
    
    /**
     * Atributo que representa los libros fisicos que tiene la bilioteca
     * Relacion cero a muchos
     */
    private List<SalaDTO> salas;
    
    /**
     * Atributo que representa los usuarios que tiene la bilioteca
     * Relacion cero a muchos
     */
    private List<UsuarioDTO> usuarios;
    
    //-----------------------------------------------------------------------------
    // ATRIBUTOS
    //----------------------------------------------------------------------------- 
   
    public BibliotecaDetailDTO() {
        super();
    }
    
    //-----------------------------------------------------------------------------
    // CONSTRUCTOR
    //----------------------------------------------------------------------------- 
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param bibliotecaEntity La entidad de la cual se construye el DTO
     */
    public BibliotecaDetailDTO(BibliotecaEntity bibliotecaEntity) {
        super(bibliotecaEntity);
        if (bibliotecaEntity.getLibros()!= null) {
            libros = new ArrayList<>();
            for (LibroEntity entityLibro : bibliotecaEntity.getLibros()) {
                libros.add(new LibroDTO(entityLibro));
            }
        }
        
         if (bibliotecaEntity.getVideos()!= null) {
            videos = new ArrayList<>();
            for (VideoEntity entityVideo : bibliotecaEntity.getVideos()) {
                videos.add(new VideoDTO(entityVideo));
            }
        }
         
         if (bibliotecaEntity.getSalas()!= null) {
            videos = new ArrayList<>();
            for (VideoEntity entityVideo : bibliotecaEntity.getVideos()) {
                videos.add(new VideoDTO(entityVideo));
            }
        }
         
         if (bibliotecaEntity.getUsuarios()!= null) {
            usuarios = new ArrayList<>();
            for (UsuarioEntity entityUsuario : bibliotecaEntity.getUsuarios()) {
               usuarios.add(new UsuarioDTO(entityUsuario));
            }
        }
    }

   
    
    //-----------------------------------------------------------------------------
    // MÉTODOS
    //----------------------------------------------------------------------------- 
    
    /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa el libro.
     */
    @Override
    public BibliotecaEntity toEntity() 
    {
        BibliotecaEntity bibliotecaEntity = super.toEntity();
        if (libros != null) {
            List<LibroEntity> librosEntity = new ArrayList<>();
            for (LibroDTO dtolibro: getLibros()) {
                librosEntity.add(dtolibro.toEntity());
            }
            bibliotecaEntity.setLibros(librosEntity);
        }
         if ( videos != null) {
            List<VideoEntity> videosEntity = new ArrayList<>();
            for (VideoDTO dtoVideo: getVideos()) {
                videosEntity.add(dtoVideo.toEntity());
            }
            bibliotecaEntity.setVideos(videosEntity);
        }
         
          if ( salas != null) {
            List<SalaEntity> salasEntity = new ArrayList<>();
            for (SalaDTO dtoSalas: getSalas()) {
                salasEntity.add(dtoSalas.toEntity());
            }
            bibliotecaEntity.setSalas(salasEntity);
        }
       
        return bibliotecaEntity;
    }
    
     /**
     * Devuelve los libros asociados a esta biblioteca
     *
     * @return Lista de DTOs de Libros
     */
    public List<LibroDTO> getLibros() {
        return libros;
    }
    
     /**
     * Modifica los libros de esta biblioteca.
     *
     * @param pLibros Los nuevos libros
     */
    public void setLibros(List<LibroDTO> pLibros) {
        this.libros = pLibros;
    }
    
     /**
     * Devuelve los videos asociados a esta biblioteca
     *
     * @return Lista de DTOs de Videos
     */
    public List<VideoDTO> getVideos() {
        return videos;
    }
    
    /**
     * Modifica los videos de esta biblioteca.
     *
     * @param pVideos Los nuevos videos
     */
    public void setVideos(List<VideoDTO> pVideos) {
        this.videos = pVideos;
    }
    
      /**
     * Devuelve las salas asociadas a esta biblioteca
     *
     * @return Lista de DTOs de Salas
     */
    public List<SalaDTO> getSalas() {
        return salas;
    }
    
    /**
     * Modifica las salas de esta biblioteca.
     *
     * @param pSalas Las nuevos vsalas
     */
    public void setSalas(List<SalaDTO> pSalas) {
        this.salas = pSalas;
    }
    
    
    /**
     * Devuelve los usuarios de la biblioteca. 
     *
     * @return Lista de DTOs de Usuarios
     */
    public List<UsuarioDTO> getUsuarios() {
        return usuarios;
    }

     /**
     * Modifica los usuarios de la biblioteca. 
     *
     * @param pUsuarios Las nuevos vsalas
     */
    public void setUsuarios(List<UsuarioDTO> pUsuarios) {
        this.usuarios = pUsuarios;
    }
    
}
