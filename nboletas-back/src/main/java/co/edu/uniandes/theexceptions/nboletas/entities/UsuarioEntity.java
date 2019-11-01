/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jm.contreras10
 */
@Entity
public class UsuarioEntity extends BaseEntity implements Serializable {

    /**
     * User-Name del usuario.
     */
    @NotNull
    @Column(name = "userName", unique = true)
    private String userName;

    /**
     * Contraseña del usuario.
     */
    @NotNull
    private String password;

    /**
     * Nombre completo del usuario.
     */
    @NotNull
    private String nombreUsuario;

    /**
     * E-mail del usuario.
     */
    @NotNull
    private String email;
    
    /**
     * Imagen del usuario.
     */
    private String imagen;

    /**
     * Pais del usuario.
     */
    @NotNull
    private String pais;

    /**
     * ciudad del usuario.
     */
    @NotNull
    private String ciudad;
    
    /*
     * Indica si el usuario es administrador.
     */
    @NotNull
    private boolean admon;

    @PodamExclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    private List<BoletaEntity> boletas;

    @PodamExclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    private List<ReembolsoEntity> reembolsos;

    public List<BoletaEntity> getBoletas() {
        return boletas;
    }

    public void setBoletas(List<BoletaEntity> boletas) {
        this.boletas = boletas;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    public boolean getAdmon() {
        return admon;
    }

    public void setAdmon(boolean admon) {
        this.admon = admon;
    }

    public List<ReembolsoEntity> getReembolsos() {
        return reembolsos;
    }

    public void setReembolsos(List<ReembolsoEntity> reembolsos) {
        this.reembolsos = reembolsos;
    }

}
