/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.dtos;

import co.edu.uniandes.theexceptions.nboletas.entities.BoletaEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.ReembolsoEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.UsuarioEntity;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author jm.contreras10
 */
public class UsuarioDetailDTO extends UsuarioDTO {

    private List<BoletaDTO> boletasCompradas;

    private List<ReembolsoDTO> reembolsos;

    public UsuarioDetailDTO() {

    }

    public UsuarioDetailDTO(UsuarioEntity entity) {
        super(entity);
        if (entity != null) {
            List<BoletaDTO> bols = null;
            List<ReembolsoDTO> reem = new LinkedList<>();

            if (entity.getBoletas() != null) {
                bols = new LinkedList<>();
                for (BoletaEntity b : entity.getBoletas()) {
                    bols.add(new BoletaDTO(b));
                }
            }
            if (entity.getReembolsos() != null) {
                for (ReembolsoEntity r : entity.getReembolsos()) {
                    reem.add(new ReembolsoDTO(r));
                }
            }

            boletasCompradas = bols;
            reembolsos = reem;
        }
    }

    @Override
    public UsuarioEntity toEntity() {
        UsuarioEntity entity = super.toEntity();

        List<BoletaEntity> bols = new LinkedList<>();
        List<ReembolsoEntity> reems = new LinkedList<>();

        if (this.boletasCompradas != null) {
            for (BoletaDTO b : this.boletasCompradas) {
                bols.add(b.toEntity());
            }
        }

        if (this.reembolsos != null) {
            for (ReembolsoDTO r : this.reembolsos) {
                reems.add(r.toEntity());
            }
        }

        entity.setBoletas(bols);
        entity.setReembolsos(reems);

        return entity;
    }

    public List<BoletaDTO> getBoletasCompradas() {
        return boletasCompradas;
    }

    public void setBoletasCompradas(List<BoletaDTO> boletasCompradas) {
        this.boletasCompradas = boletasCompradas;
    }

    public List<ReembolsoDTO> getReembolsos() {
        return reembolsos;
    }

    public void setReembolsos(List<ReembolsoDTO> reembolsos) {
        this.reembolsos = reembolsos;
    }
    
    /**
     * Transforma una lista de UsuarioEntity a una lista de UsuarioDetailDTO.
     * @param entityList
     * @return 
     */
    public static List<UsuarioDetailDTO> listUsuarioEntity2UsuarioDetailDTO(List<UsuarioEntity> entityList) {
        List<UsuarioDetailDTO> list = new LinkedList<>();
        for (UsuarioEntity entity : entityList) {
            list.add(new UsuarioDetailDTO(entity));
        }
        return list;
    }

    /**
     * Transforma una lista de UsuarioDetailDTO a una lista de UsuarioEntity.
     * @param detailDtoList
     * @return 
     */
    public static List<UsuarioEntity> listUsuarioDetailDTO2UsuarioEntity(List<UsuarioDetailDTO> detailDtoList) {
        List<UsuarioEntity> list = new LinkedList<>();
        for (UsuarioDetailDTO detail : detailDtoList) {
            list.add(detail.toEntity());
        }
        return list;
    }
}
