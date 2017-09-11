/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.dtos;

import co.edu.uniandes.theexceptions.nboletas.entities.EnvioEntity;

/**
 *
 * @author angeloMarcetty
 */
public class EnvioDTO {
    
    private Long id;
    private String direccion;
    
    
    /**
     * Constructor por defecto
     */
    public EnvioDTO(){
    }
          
    
    //Conviertir Entity a DTO
    public EnvioDTO(EnvioEntity env){
        this.id = env.getId();
        this.direccion = env.getDireccion();
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
     * Convertir DTO a Entity
     * @return Un Entity con los valores del DTO 
     */
    public EnvioEntity toEntity(){
        EnvioEntity entity = new EnvioEntity();
        entity.setId(this.id);
        entity.setDireccion(this.direccion);
        return entity;   
    }
    
    
    
    
}
