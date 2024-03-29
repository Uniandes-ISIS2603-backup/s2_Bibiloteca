/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bibilioteca.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Daniel Montoya
 */
@Entity
public class LibroEntity extends BaseEntity implements Serializable {

    //Nombre del libro
    private String nombre;

    //Codigo id del libro
    private String isbn;

    //Autor del libro
    private String autor;

    //Editorial del libro
    private String editorial;

    //Edicion del libro
    private String edicion;

    //Idioma del libro
    private String idioma;

    //Unidades que quedan disponibles del libro 
    private Integer unidadesDisponibles;

    //Numero de usuarios en lista de Espera
    private Integer usuariosListaEspera;

    //Calificacion promedio de los review
    private Integer calificacionPromedio;

    @PodamExclude
    @OneToMany(mappedBy = "libro", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ComentarioEntity> comentarios = new ArrayList<>();

    @PodamExclude
    @ManyToOne(cascade = CascadeType.PERSIST)
    private BibliotecaEntity biblioteca;

    @PodamExclude
    @OneToMany(mappedBy = "libro", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<PrestamoEntity> prestamos = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy = "libro", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<ReservaEntity> reservas = new ArrayList<>();

    public BibliotecaEntity getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(BibliotecaEntity biblioteca) {
        this.biblioteca = biblioteca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getEdicion() {
        return edicion;
    }

    public void setEdicion(String edicion) {
        this.edicion = edicion;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(int unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }

    public Integer getUsuariosListaEspera() {
        return usuariosListaEspera;
    }

    public void setUsuariosListaEspera(Integer usuariosListaEspera) {
        this.usuariosListaEspera = usuariosListaEspera;
    }

    public Integer getCalificacionPromedio() {
        return calificacionPromedio;
    }

    public void setCalificacionPromedio(Integer calificacionPromedio) {
        this.calificacionPromedio = calificacionPromedio;
    }

    public List<ComentarioEntity> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }

    public List<PrestamoEntity> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<PrestamoEntity> prestamos) {
        this.prestamos = prestamos;
    }

    public List<ReservaEntity> getReservas() {
        return reservas;
    }

    public void setReservas(List<ReservaEntity> reservas) {
        this.reservas = reservas;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

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
        final LibroEntity other = (LibroEntity) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.isbn, other.isbn)) {
            return false;
        }
        if (!Objects.equals(this.autor, other.autor)) {
            return false;
        }
        if (!Objects.equals(this.editorial, other.editorial)) {
            return false;
        }
        if (!Objects.equals(this.edicion, other.edicion)) {
            return false;
        }
        if (!Objects.equals(this.idioma, other.idioma)) {
            return false;
        }
        if (!Objects.equals(this.unidadesDisponibles, other.unidadesDisponibles)) {
            return false;
        }
        if (!Objects.equals(this.usuariosListaEspera, other.usuariosListaEspera)) {
            return false;
        }
        if (!Objects.equals(this.calificacionPromedio, other.calificacionPromedio)) {
            return false;
        }
        if (!Objects.equals(this.comentarios, other.comentarios)) {
            return false;
        }
        if (!Objects.equals(this.biblioteca, other.biblioteca)) {
            return false;
        }
        if (!Objects.equals(this.prestamos, other.prestamos)) {
            return false;
        }
        if (!Objects.equals(this.reservas, other.reservas)) {
            return false;
        }
        return true;
    }

    
}
