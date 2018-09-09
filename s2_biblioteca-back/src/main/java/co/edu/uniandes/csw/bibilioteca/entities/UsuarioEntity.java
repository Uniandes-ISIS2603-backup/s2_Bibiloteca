/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bibilioteca.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
public class UsuarioEntity extends BaseEntity implements Serializable{
    //Nombre del usuario
    private String nombre;
    //Email del usuario
    private String email;
    //Telefono del usuario
    private String telefono;
    //Multa del usuario 
    private Integer multa;
    
    public String getNombre() {
        return nombre;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefono() {
        return telefono;
    }
    public Integer getMulta() {
        return multa;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
     public void setEmail(String email) {
        this.email = email;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public void setMulta(Integer multa)
    {
        this.multa = multa;
    }
    
    
    
}
