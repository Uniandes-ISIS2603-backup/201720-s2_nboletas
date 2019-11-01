/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.dtos;

import co.edu.uniandes.theexceptions.nboletas.entities.LugarEntity;

/**
 *
 * @author ja.gomez1
 */
public class LugarDTO {

    private Long id;
    private String ubicacion;
    private String tipo;
    private String direccion;
    private String imagen;
    

    public LugarDTO() {
    }

    public LugarDTO(LugarEntity entity) {
        this.id = entity.getId();
        this.ubicacion = entity.getUbicacion();
        this.tipo = entity.getTipo();
        this.direccion = entity.getDireccion();
        this.imagen = entity.getImagen();
    }

    public LugarEntity toEntity() {
        LugarEntity entity = new LugarEntity();
        entity.setId(this.getId());
        entity.setDireccion(this.getDireccion());
        entity.setTipo(this.getTipo());
        entity.setUbicacion(this.getUbicacion());
        entity.setImagen(this.getImagen());
        return entity;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
}
