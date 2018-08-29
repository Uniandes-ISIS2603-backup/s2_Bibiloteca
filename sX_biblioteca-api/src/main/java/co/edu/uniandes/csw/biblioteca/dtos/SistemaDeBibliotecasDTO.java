/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.biblioteca.dtos;

/**
 *
 * @author Juan Bautitsa
 */
public class SistemaDeBibliotecasDTO {
    
    private String ciudad;
    public SistemaDeBibliotecasDTO(String pCiudad)
    {
        
        ciudad=pCiudad;
    }
   
    public String getCiudad()
    {
        return ciudad;
    }
    public void setCiudad(String pCiudad)
    {
        ciudad=pCiudad;
    }}
