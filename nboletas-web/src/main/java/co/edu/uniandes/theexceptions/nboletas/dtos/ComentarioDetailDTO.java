/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.dtos;

import co.edu.uniandes.theexceptions.nboletas.entities.ComentarioEntity;

/**
 *
 * @author angeloMarcetty
 */
public class ComentarioDetailDTO extends ComentarioDTO {
    
    /**
     * Constructor por defecto
     */
    public ComentarioDetailDTO(){
    }
    
    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public ComentarioDetailDTO(ComentarioEntity entity){
        super(entity);
    }
    
    /**
     * Transformar un DTO a un Entity
     *
     * @return 
     */
    public ComentarioEntity toEntity(){
        ComentarioEntity comentEnt = super.toEntity();
        return comentEnt;
    }
    
    
    
}
