/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.dtos;

import co.edu.uniandes.csw.bibilioteca.entities.UsuarioEntity;
import java.io.Serializable;

/**
 *
 * @author Juan Nicolás García
 */
public class UsuarioDTO implements Serializable {

    private Long id;

    //Nombre del usuario.
    private String nombre;

    //Email del usuario.
    private String email;

    //Telefono del usuario.
    private String telefono;

    //Multa asignada al usuario.
    private Integer multa;

    public UsuarioDTO() {

    }

    public UsuarioDTO(UsuarioEntity usuario) {
        if (usuario != null) {
            this.id = usuario.getId();
            this.nombre = usuario.getNombre();
            this.email = usuario.getEmail();
            this.telefono = usuario.getTelefono();
            this.multa = usuario.getMulta();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public void setNombre(String pNombre) {
        nombre = pNombre;
    }

    public void setEmail(String pEmail) {
        email = pEmail;
    }

    public void setTelefono(String pTelefono) {
        telefono = pTelefono;
    }

    public void setMulta(Integer pMulta) {
        multa = pMulta;
    }

    public UsuarioEntity toEntity() {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(this.id);
        usuario.setNombre(this.nombre);
        usuario.setEmail(this.email);
        usuario.setMulta(this.multa);
        usuario.setTelefono(this.telefono);
        return usuario;
    }
}
