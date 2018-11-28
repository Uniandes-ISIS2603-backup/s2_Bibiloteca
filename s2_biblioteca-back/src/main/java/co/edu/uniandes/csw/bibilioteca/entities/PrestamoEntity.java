/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bibilioteca.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author David Saavedra
 */
@Entity
public class PrestamoEntity extends BaseEntity implements Serializable
{
    //--------------------------------------------
    // ATRIBUTOS
    //--------------------------------------------
    
    /**
     * Se refiera a la fecha en la que se presta el libro
     */
    @Temporal(TemporalType.DATE)
    private Date fechaDeSalida;
   
    /**
     * Se refiera a la fecha en la que se debe entregar el libro
     */
    @Temporal(TemporalType.DATE)
    private Date fechaDeEntrega;
    
    /**
     * Se refiere a si el libro fue devuelto o no
     */ 
    private boolean retornado;
     
      
    /**
     * Relación con el usuario.
     */
    @PodamExclude
    @ManyToOne
    private UsuarioEntity usuario;

    /**
     * Relacion con el libro. 
     */
    @PodamExclude
    @ManyToOne
    private LibroEntity libro;
    
     /**
     * Relacion con el video. 
     */
    @PodamExclude
    @ManyToOne
    private VideoEntity video;
    
           
     /**
     * Relacion con la Sala.
     */
    @PodamExclude
    @ManyToOne
    private SalaEntity sala;
    
     
     /**
     * id del recurso prestado
     */
    private Long idRecursoPrestado; 
    
    /**
     * tipo del recurso resservado
     */
    private String tipoRecurso;
    //-------------------------------------------------------------------------
    // MÉTODOS  
    //-------------------------------------------------------------------------
    
    /**
     * obtiene el id del recurso prestado
     * @return 
     */    
    public Long getIdRecursoPrestado() 
    {
        return idRecursoPrestado;
    }
    
    /**
     * asigna el id del recurso asociado al prestamo
     * @param getIdRecursoPrestado
     */
        public void setIdRecursoPrestado(Long getIdRecursoPrestado)
    {
        this.idRecursoPrestado = getIdRecursoPrestado;
    }
    
    /**
     * @return tipoRecurso
     */
    public String getTipoRecurso() 
    {
        return tipoRecurso;
    }

    /**
     * asigna el tipo del recurso que se esta prestando
     * @param tipoRecurso el tipoRecurso a asignar
     * este debe corresponde a uno de los identificadores
     */
    public void setTipoRecurso(String tipoRecurso) 
    {
        /*
        if(tipoRecurso.equals("LIBRO") || tipoRecurso.equals("VIDEO")|| tipoRecurso.equals("SALA")|| tipoRecurso.equals("VIDEODIGITAL")|| tipoRecurso.equals("LIBRODIGITAL"))
        {
            */
            this.tipoRecurso = tipoRecurso;
       
        /*
        else
        {
             this.tipoRecurso = "";
        }
        */
    }
    
    
    
    /**
     * Método que da el usuario del prestamo
     * @return Usuario del prestamo
     */
    public UsuarioEntity getUsuario() {
        return usuario;
    }
    /**
     * Método que modifica el usuario del prestamo
     */
    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
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
     * Método para cambiar si el prestamo ha sido retornado o no
     * @param pRetornado Booleano con el valor para retornado
     */
    public void setRetornado (boolean pRetornado)
    {
        retornado = pRetornado;
    }
    
    /**
     * Devuelve si el prestamo ha sido retornado o no
     * @return  true: si el libro ha sido retornado
     *          false: si el libro no ha sido retornado
     */
    public boolean getRetornado()
    {
        return retornado;
    }
    
    /**
     * Método que da la fecha de entrega del libro
     * @return Libro Fisico
     */
    public LibroEntity getLibro() {
        return libro;
    }
    
    /**
     * Método para cambiar si el libro ha sido prestado. 
     * @param pLibro LibroEntity con el nuevo libro. 
     */
    public void setLibro(LibroEntity pLibro) {
        this.libro = pLibro;
    }

    /**
     * Método que da el video del prestamo
     * @return Video
     */
    public VideoEntity getVideo() {
        return video;
    }
    /**
     * Método para cambiar el video del prestamo. 
     * @param pVideo VideoEntity con el nuevo video. 
     */
    public void setVideo(VideoEntity pVideo) {
        this.video = pVideo;
    }

    
    /**
     * Método que da la sala del prestamo. 
     * @return Sala
     */
    public SalaEntity getSala() {
        return sala;
    }
    
    /**
     * Método para cambiar la sala del prestamo. 
     * @param pSala SalaEntity con el valor sala.
     */
    public void setSala(SalaEntity pSala) {
        this.sala = pSala;
    }
    
}
