package co.edu.uniandes.theexceptions.nboletas.dtos;

import co.edu.uniandes.theexceptions.nboletas.entities.EnvioEntity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author angeloMarcetty
 */
public class EnvioDTO {

   

    private Long id;
    private String direccion;
    private String imagen;

    /**
     * Constructor por defecto
     */
    public EnvioDTO() {
    }

    //Conviertir Entity a DTO
    public EnvioDTO(EnvioEntity env) {

        if (env != null) {
            this.id = env.getId();
            this.direccion = env.getDireccion();
            this.imagen = env.getImagen();
        }
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

    
    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public EnvioEntity toEntity() {
        EnvioEntity entity = new EnvioEntity();
        entity.setId(this.id);
        entity.setDireccion(this.direccion);
        entity.setImagen(this.imagen);
        return entity;
    }

}
