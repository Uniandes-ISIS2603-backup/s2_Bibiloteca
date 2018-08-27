/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.adapters;

/**
 *
 * @author Juan Nicolás García
 */
public class UsuarioDTO {
    
    //Nombre del usuario.
    private String nombre;
    
    //Email del usuario.
    private String email;
    
    //Telefono del usuario.
    private String telefono;
    
    //Multa asignada al usuario.
    private String multa;
    
    public UsuarioDTO(String pNombre, String pEmail, String pTelefono, String pMulta)
    {
        nombre = pMulta;
        email = pEmail;
        telefono = pTelefono;
        multa = pMulta;
    }
    
    public String getNombre()
    {
        return nombre;
    }
    public String getEmail()
    {
        return email;
    }
    public String getTelefono()
    {
        return telefono;
    }
    public String getMulta()
    {
        return multa;
    }
    public void setNombre(String pNombre)
    {
       nombre = pNombre;
    }
    public void setEmail(String pEmail)
    {
       email = pEmail;
    }
    public void setTelefono(String pTelefono)
    {
       telefono = pTelefono;
    }
    public void setMulta(String pMulta)
    {
       multa = pMulta;
    }
}
