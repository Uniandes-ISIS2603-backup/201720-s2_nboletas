/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.dtos;

import co.edu.uniandes.theexceptions.nboletas.entities.UsuarioEntity;

/**
 *
 * @author jm.contreras10
 */
public class UsuarioDTO {

    private Long id;
    private String userName;
    private String password;
    private String nombreUsuario;
    private String email;
    private String pais;
    private String ciudad;
    private boolean admon;
    private String imagen;

    public UsuarioDTO() {
    }

    public UsuarioDTO(UsuarioEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.userName = entity.getUserName();
            this.password = entity.getPassword();
            this.nombreUsuario = entity.getNombreUsuario();
            this.email = entity.getEmail();
            this.pais = entity.getPais();
            this.ciudad = entity.getCiudad();
            this.admon = entity.getAdmon();
            this.imagen = entity.getImagen();
        }
    }

    public UsuarioEntity toEntity(){
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(this.id);
        entity.setUserName(this.userName);
        entity.setPassword(this.password);
        entity.setNombreUsuario(this.nombreUsuario);
        entity.setEmail(this.email);
        entity.setPais(this.pais);
        entity.setCiudad(this.ciudad);
        entity.setAdmon(this.admon);
        entity.setImagen(this.imagen);
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public boolean isAdmon() {
        return admon;
    }

    public void setAdmon(boolean admon) {
        this.admon = admon;
    }
}
