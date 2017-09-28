/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.dtos;

import co.edu.uniandes.theexceptions.nboletas.entities.BoletaEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.ReembolsoEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.UsuarioEntity;

/**
 *
 * @author jm.contreras10
 */
public class ReembolsoDetailDTO extends ReembolsoDTO{
    
    private BoletaDTO boleta;

    private UsuarioDTO usuario;
    
    public ReembolsoDetailDTO(){
        
    }

    public ReembolsoDetailDTO(ReembolsoEntity entity) {
        super(entity);
        BoletaEntity boletaA =entity.getBoleta();
        if (null != boletaA) {
            boleta = new BoletaDTO(boletaA);
        }
        entity.setBoleta(null);
        
        UsuarioEntity usuarioA = entity.getUsuario();
        if (null != usuarioA) {
            usuario = new UsuarioDTO(usuarioA);
        }
        entity.setUsuario(null);
    }

    @Override
    public ReembolsoEntity toEntity() {
        ReembolsoEntity entity = super.toEntity();
        if (usuario != null) {
            entity.setUsuario(usuario.toEntity());
        }
        if (boleta != null) {
            entity.setBoleta(boleta.toEntity());
        }
        return entity;
    }

    public BoletaDTO getBoleta() {
        return boleta;
    }

    public void setBoleta(BoletaDTO boleta) {
        this.boleta = boleta;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
}
