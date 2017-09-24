/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.dtos;

import co.edu.uniandes.theexceptions.nboletas.entities.EspectaculoEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.OrganizadorEntity;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author df.riveros11
 */
public class OrganizadorDetailDTO extends OrganizadorDTO{
    
    private List<EspectaculoDTO> espectaculos;

    public OrganizadorDetailDTO() {
        super();
    }

    public OrganizadorDetailDTO(OrganizadorEntity entity) {
        super(entity);
        if (entity.getEspectaculos() != null) {
            espectaculos = new ArrayList<>();
            for (EspectaculoEntity espectaculo : entity.getEspectaculos()) {
                espectaculos.add(new EspectaculoDTO(espectaculo));
            }
        }
    }

    @Override
    public OrganizadorEntity toEntity() {
        OrganizadorEntity entity = super.toEntity();
        if (espectaculos != null) {
            List<EspectaculoEntity> espectaculosEntity = new ArrayList<>();
            for (EspectaculoDTO espectaculo : espectaculos) {
                espectaculosEntity.add(espectaculo.toEntity());
            }
            entity.setEspectaculos(espectaculosEntity);
        }
        return entity;
    }
}
