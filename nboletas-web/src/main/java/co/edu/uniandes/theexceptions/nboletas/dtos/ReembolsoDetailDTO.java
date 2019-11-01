/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.dtos;

import co.edu.uniandes.theexceptions.nboletas.entities.BoletaEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.ReembolsoEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jm.contreras10
 */
public class ReembolsoDetailDTO extends ReembolsoDTO {

    private BoletaDTO boleta;

    private UsuarioDTO usuario;

    public ReembolsoDetailDTO() {
        super();
    }

    public ReembolsoDetailDTO(ReembolsoEntity entity) {
        super(entity);
        if (entity != null) {
            BoletaEntity boletaA = entity.getBoleta();
            if (boletaA != null) {
                boleta = new BoletaDTO(entity.getBoleta());
            }
            UsuarioEntity usuarioA = entity.getUsuario();
            if (usuarioA != null) {
                usuario = new UsuarioDTO(entity.getUsuario());
            }
        }
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

    /**
     * Transforma una lista de ReembolsoEntity a una lista de
     * ReembolsoDetailDTO.
     *
     * @param entityList
     * @return
     */
    public static List<ReembolsoDetailDTO> listReembolsoEntity2ReembolsoDetailDTO(List<ReembolsoEntity> entityList) {
        List<ReembolsoDetailDTO> list = new ArrayList<>();
        for (ReembolsoEntity entity : entityList) {
            list.add(new ReembolsoDetailDTO(entity));
        }
        return list;
    }

    /**
     * Transforma una lista de ReembolsoEntity a una lista de
     * ReembolsoDetailDTO.
     *
     * @param detailList
     * @return
     */
    public static List<ReembolsoEntity> listReembolsoDetailDTO2ReembolsoEntity(List<ReembolsoDetailDTO> detailList) {
        List<ReembolsoEntity> list = new ArrayList<>();
        for (ReembolsoDetailDTO detail : detailList) {
            list.add(detail.toEntity());
        }
        return list;
    }
}
