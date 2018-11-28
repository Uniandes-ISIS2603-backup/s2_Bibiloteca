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
    
      /**
     * identificador del recurso que se quiere prestar
     */
    private Long idRecursoPrestado;
    
    /**
     * tipo del recurso prestado
     */
    private String tipoRecurso;
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
            this.idRecursoPrestado = prestamoEntity.getIdRecursoPrestado();
            this.tipoRecurso = prestamoEntity.getTipoRecurso();
            if (prestamoEntity.getUsuario()!= null) {
                this.usuario = new UsuarioDTO(prestamoEntity.getUsuario());
            } else {
                this.usuario = null;
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
         prestamoEntity.setIdRecursoPrestado(this.idRecursoPrestado);
        prestamoEntity.setTipoRecurso(this.tipoRecurso);
        if(this.usuario != null)
        {
            prestamoEntity.setUsuario(this.usuario.toEntity());
        }
        else
        {
            prestamoEntity.setUsuario(null);
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
    
   
    public Long getIdDRecursoReservado() {
        return idRecursoPrestado;
    }
    
  
    public void setIdDRecursoReservado(Long pIdDRecursoReservado) {
        this.idRecursoPrestado = pIdDRecursoReservado;
    }
 /**
     * obtiene el tipo del recurso que se esta reservando 
     * @return el tipoRecurso
     */
    public String getTipoRecurso() 
    {
        return tipoRecurso;
    }

    /**
     * asigna el tipo del recurso que se esta reservando
     * @param tipoRecurso el tipoRecurso a asignar
     * este debe corresponde a uno de los identificadores
     */
    public void setTipoRecurso(String tipoRecurso) 
    {
        if(tipoRecurso.equals("LIBRO") || tipoRecurso.equals("VIDEO")|| tipoRecurso.equals("SALA"))
        {
            this.tipoRecurso = tipoRecurso;
        }
        else
        {
             this.tipoRecurso = "";
        }
        
    }
    
   
}
