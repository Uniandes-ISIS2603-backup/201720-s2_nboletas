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
public class EnvioDetailDTO extends EnvioDTO{
    
     /**
     * Constructor por defecto
     */
    public EnvioDetailDTO(){
    }
    
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public EnvioDetailDTO(EnvioEntity entity){
        super(entity);
    }
    
   
    
     /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    public EnvioEntity toEntity(){
        EnvioEntity envEnt = super.toEntity();
        return envEnt;
    }
    
    
    
    
    
    
}
