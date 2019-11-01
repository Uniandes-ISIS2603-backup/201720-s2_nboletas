/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.dtos;

import co.edu.uniandes.theexceptions.nboletas.entities.EspectaculoEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.OrganizadorEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jf.ramos
 */
public class EspectaculoDetailDTO extends EspectaculoDTO {

    private List<OrganizadorDTO> organizadores;
    /**
     * Constructor por defecto
     */
    public EspectaculoDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public EspectaculoDetailDTO(EspectaculoEntity entity) {
        super(entity);
        if (entity != null) {
            if (entity.getOrganizador()!= null) {
                organizadores = new ArrayList<>();
                for (OrganizadorEntity organizador : entity.getOrganizador()) {
                    organizadores.add(new OrganizadorDTO(organizador));
                }
            }
        }
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return
     */
    @Override
    public EspectaculoEntity toEntity() {
        EspectaculoEntity entity = super.toEntity();
        if (organizadores != null) {
            List<OrganizadorEntity> organizadoresEntity = new ArrayList<>();
            for (OrganizadorDTO organizador : organizadores) {
                organizadoresEntity.add(organizador.toEntity());
            }
            entity.setOrganizador(organizadoresEntity);
        }
        return entity;
    }

    public static List<EspectaculoDetailDTO> listEspectaculoEntity2EspectaculoDetailDTO(List<EspectaculoEntity> entityList) {
        List<EspectaculoDetailDTO> list = new ArrayList<>();
        for (EspectaculoEntity entity : entityList) {
            list.add(new EspectaculoDetailDTO(entity));
        }
        return list;
    }

    public static List<EspectaculoEntity> listEspectaculoDetailDto2EspectaculoEntity(List<EspectaculoDetailDTO> entityList) {
        List<EspectaculoEntity> list = new ArrayList<>();
        for (EspectaculoDetailDTO entity : entityList) {
            list.add(entity.toEntity());
        }
        return list;
    }
}
