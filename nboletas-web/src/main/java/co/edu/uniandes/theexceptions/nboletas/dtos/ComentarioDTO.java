/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.dtos;

import co.edu.uniandes.theexceptions.nboletas.entities.ComentarioEntity;
import java.util.Date;

/**
 *
 * @author angeloMarcetty
 */
public class ComentarioDTO {
    
    private Long id;
    private String comentario;
    private Date fecha;
    
    
    /**
     * Constructor por defecto
     */
    public ComentarioDTO(){
    }
           
    
    //Conviertir Entity a DTO
    public ComentarioDTO(ComentarioEntity coment){
        this.id = coment.getId();
        this.comentario = coment.getComentario();
        this.fecha = coment.getFecha();
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
     * @return the comentario
     */
    public String getComentario() {
        return comentario;
    }

    /**
     * @param comentario the comentario to set
     */
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
    
    /**
     * Convertir DTO a Entity
     * @return Un Entity con los valores del DTO 
     */
    public ComentarioEntity toEntity(){
        ComentarioEntity entity = new ComentarioEntity();
        entity.setId(this.id);
        entity.setComentario(this.comentario);
        entity.setFecha(this.fecha);
        return entity;
    }
    
    
    
    
}
