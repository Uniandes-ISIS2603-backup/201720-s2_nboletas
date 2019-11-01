/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jf.ramos
 */
@Entity
public class EspectaculoEntity extends BaseEntity implements Serializable {

    private String nombre;

    private String descripcion;
    
    private String imagen;

    @PodamExclude
    @ManyToMany(fetch = FetchType.LAZY)
    private List<OrganizadorEntity> organizador;

    @PodamExclude
    @ManyToMany(fetch = FetchType.LAZY)
    private List<ArtistaEntity> artista;

    @PodamExclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "espectaculo")
    private List<FuncionEntity> funcion;

    @PodamExclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "espectaculo")
    private List<ComentarioEntity> comentarios;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the organizador
     */
    public List<OrganizadorEntity> getOrganizador() {
        return organizador;
    }

    /**
     * @param organizador the organizador to set
     */
    public void setOrganizador(List<OrganizadorEntity> organizador) {
        this.organizador = organizador;
    }

    /**
     * @return the artista
     */
    public List<ArtistaEntity> getArtista() {
        return artista;
    }

    /**
     * @param artista the artista to set
     */
    public void setArtista(List<ArtistaEntity> artista) {
        this.artista = artista;
    }

    /**
     * @return the funcion
     */
    public List<FuncionEntity> getFunciones() {
        return funcion;
    }

    /**
     * @param funcion the funcion to set
     */
    public void setFunciones(List<FuncionEntity> funcion) {
        this.funcion = funcion;
    }

    /**
     * @return the comentarios
     */
    public List<ComentarioEntity> getComentarios() {
        return comentarios;
    }

    /**
     * @param comentarios the comentarios to set
     */
    public void setComentarios(List<ComentarioEntity> comentarios) {
        this.comentarios = comentarios;
    }
}
