/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.dtos;

import co.edu.uniandes.theexceptions.nboletas.entities.ReembolsoEntity;

/**
 *
 * @author jm.contreras10
 */
public class ReembolsoDTO {
    
    private Long id;
    private double valor;

    public ReembolsoDTO() {

    }

    public ReembolsoDTO(ReembolsoEntity entity) {
        if(entity != null){
           this.id = entity.getId();
           this.valor = entity.getValor();  
        }
    }

    public ReembolsoEntity toEntity() {
        ReembolsoEntity entity = new ReembolsoEntity();
        entity.setId(this.id);
        entity.setValor(this.valor);
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
