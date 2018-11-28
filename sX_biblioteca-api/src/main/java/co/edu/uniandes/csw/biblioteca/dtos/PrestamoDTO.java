/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.dtos;


import co.edu.uniandes.csw.bibilioteca.entities.PrestamoEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author estudiante
 */
public class PrestamoDTO implements Serializable
{
    //--------------------------------------------
    // ATRIBUTOS
    //--------------------------------------------
    
    /**
     * Se refiera a la fecha en la que se presta el libro
     */
    private Date fechaDeSalida;
   
    /**
     * Se refiera a la fecha en la que se debe entregar el libro
     */
    private Date fechaDeEntrega;
    
    /**
     * Se refiere a si el libro fue devuelto o no
     */ 
    private Boolean retornado;
     
    /**
    * Se refiere al identificador de cada prestamo
    */
    private Long id;
    
    /**
    * Relación a una usuario dado que esta tiene cardinalidad 1.
    * 
    */
    private UsuarioDTO usuario;
    
    // Las siguientes relaciones son exclusivas sólo se puede tener relación con una de las siguientes. 
    /**
     * Relacion con el libro. 
     */
     private LibroDTO libro;

    
    
     /**
     * Relacion con el video. 
     */
    private VideoDTO video;
    
     /**
     * Relacion con la Sala.
     */
    private SalaDTO sala;
    //----------------------------------------------
    // CONSTRUCTOR
    //----------------------------------------------
     /**
     * Constructor por defecto.
     */
    public PrestamoDTO()
    {
        
    }
    
     /**
     * Constructor a partir de la entidad
     * Dado que prestamo sólo tiene un recurso entonces se hace la validadción para cada recurso y el que no sea nulo es el que tiene el prestamo
     * @param prestamoEntity La entidad de la biblioteca
     */
    public PrestamoDTO(PrestamoEntity prestamoEntity) {
        if (prestamoEntity != null) 
        {
            this.fechaDeEntrega = prestamoEntity.getFechaDeEntrega();
            this.fechaDeSalida = prestamoEntity.getFechaDeSalida();
            this.retornado = prestamoEntity.getRetornado();
            this.id = prestamoEntity.getId();
            if (prestamoEntity.getUsuario()!= null) {
                this.usuario = new UsuarioDTO(prestamoEntity.getUsuario());
            } else {
                this.usuario = null;
            }
            if( prestamoEntity.getLibro() != null)
            {
                this.libro = new LibroDTO(prestamoEntity.getLibro());
            }
            else if ( prestamoEntity.getVideo() != null)
            {
                this.video = new VideoDTO(prestamoEntity.getVideo());    
            }
            else if ( prestamoEntity.getSala() != null)
            {
                this.sala = new SalaDTO(prestamoEntity.getSala());    
            }
            else
            {
                this.sala = null;
                this.libro = null;
                this.video = null;
                
            }
        }
    }
    
    //----------------------------------------------
    // METODOS
    //----------------------------------------------
    
    /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad del libro asociado.
     */
    public PrestamoEntity toEntity() {
        PrestamoEntity prestamoEntity = new PrestamoEntity();
        prestamoEntity.setId(this.id);
        prestamoEntity.setFechaDeEntrega(this.fechaDeEntrega);
        prestamoEntity.setFechaDeSalida(this.fechaDeSalida);
        prestamoEntity.setRetornado(this.retornado);
        if (this.usuario != null) {
            prestamoEntity.setUsuario(this.usuario.toEntity());
        }
        if( prestamoEntity.getLibro() != null)
        {
            prestamoEntity.setLibro(this.libro.toEntity());
        }
        else if ( prestamoEntity.getVideo() != null)
        {
            prestamoEntity.setVideo(this.video.toEntity());    
        }
        
        else if ( prestamoEntity.getSala() != null)
        {
            prestamoEntity.setSala(this.sala.toEntity());   
        }
        
        return prestamoEntity;
    }
    /**
     * Devuelve si el prestamo está retornado.
     *
     * @return the id
     */
    public Boolean getRetornado() {
        return retornado;
    }
    
    /**
     * Modifica el estado del préstamo. Retornado o no retornado
     *
     * @param pRetornado retornado para poner
     */
    public void setRetornado(Boolean pRetornado) {
        this.retornado = pRetornado;
    }
    
    /**
     * Devuelve el usuario del prestamo. 
     *
     * @return El usuario
     */
    public UsuarioDTO getUsuario() {
        return usuario;
    }
    
    /**
     * Modifica elusuario del prestamo
     *
     * @param pUsuario the id to set
     */
    public void setUsuario(UsuarioDTO pUsuario) {
        this.usuario = pUsuario;
    }
    
    /**
     * Devuelve el ID del prestamo
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el ID del prestamo
     *
     * @param pId the id to set
     */
    public void setId(Long pId) {
        this.id = pId;
    }
    
    /**
     * Método para cambiar la fecha de salida
     * @param pFechaDeSalida Nueva fecha de salida
     */
    public void setFechaDeSalida(Date pFechaDeSalida)
    {
        this.fechaDeSalida= pFechaDeSalida;
    }
    
    /**
     * Método que da la fecha de salida del libro
     * @return Fecha De salida
     */
    public Date getFechaDeSalida()
    {
        return fechaDeSalida;
    }
    
    /**
     * Método para cambiar la fecha de entrega
     * @param pFechaDeEntrega Nueva fecha de entrega
     */
    public void setFechaDeEntrega(Date pFechaDeEntrega)
    {
        this.fechaDeEntrega= pFechaDeEntrega;
    }
    
    /**
     * Método que da la fecha de entrega del libro
     * @return Fecha De entrega
     */
    public Date getFechaDeEntrega()
    {
        return fechaDeEntrega;
    }

    /**
     * Método para cambiar si el libro ha sido retornado o no
     * @param pRetornado Booleano con el valor para retornado
     */
    public void setRetornado (boolean pRetornado)
    {
        retornado = pRetornado;
    }
    
    /**
     * Devuelve si el libro ha sido retornado o no
     * @return  true: si el libro ha sido retornado
     *          false: si el libro no ha sido retornado
     */
    public boolean darRetornado()
    {
        return retornado;
    }
    
    /**
     * Método que da la fecha de entrega del libro
     * @return Libro Fisico
     */
    public LibroDTO getLibro() {
        return libro;
    }
    
    /**
     * Método para cambiar si el libro ha sido prestado. 
     * @param pLibro LibroDTO con el nuevo libro. 
     */
    public void setLibro(LibroDTO pLibro) {
        this.libro = pLibro;
    }

    /**
     * Método que da el video del prestamo
     * @return Video
     */
    public VideoDTO getVideo() {
        return video;
    }

    /**
     * Método para cambiar el video del prestamo. 
     * @param pVideo VideoDTO con el nuevo video. 
     */
    public void setVideo(VideoDTO pVideo) {
        this.video = pVideo;
    }

  
    /**
     * Método que da la sala del prestamo. 
     * @return Sala
     */
    public SalaDTO getSala() {
        return sala;
    }

    /**
     * Método para cambiar la sala del prestamo. 
     * @param pSala SalaEntity con el valor sala.
     */
    public void setSala(SalaDTO pSala) {
        this.sala = pSala;
    }
    
   
}
