/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.dtos;

import co.edu.uniandes.theexceptions.nboletas.entities.DivisionDeLugarEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.SillaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fc.alvarez10
 */
public class DivisionDeLugarDetailDTO extends DivisionDeLugarDTO {

    private LugarDTO lugar;

    private List<SillaDTO> sillas;

    public DivisionDeLugarDetailDTO() {
    }

    public DivisionDeLugarDetailDTO(DivisionDeLugarEntity division) {
        super(division);
        if (division.getLugar() != null) {
            this.lugar = new LugarDTO(division.getLugar());
        }
        if (division.getSillas() != null) {
            this.sillas = new ArrayList<>();
            for (SillaEntity s : division.getSillas()) {
                sillas.add(new SillaDTO(s));
            }
        }
    }

    public LugarDTO getLugar() {
        return lugar;
    }

    public void setLugar(LugarDTO lugar) {
        this.lugar = lugar;
    }

    public List<SillaDTO> getSillas() {
        return sillas;
    }

    public void setSillas(List<SillaDTO> sillas) {
        this.sillas = sillas;
    }

    @Override
    public DivisionDeLugarEntity toEntity() {
        DivisionDeLugarEntity entity = super.toEntity();
        if (getSillas() != null) {
            List<SillaEntity> sillasE = new ArrayList<>();
            for (SillaDTO s : getSillas()) {
                sillasE.add(s.toEntity());
            }
            entity.setSillas(sillasE);
        }

        if (getLugar() != null) {
            entity.setLugar(lugar.toEntity());
        }

        return entity;
    }
    
    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos BoletaEntity a una lista de
     * objetos BoletaDetailDTO (json)
     *
     * @param entityList corresponde a la lista de Boletas de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de Boletas en forma DTO (json)
     */
    public static List<DivisionDeLugarDetailDTO> listBoletaEntity2BoletaDetailDTO(List<DivisionDeLugarEntity> entityList) {
        List<DivisionDeLugarDetailDTO> list = new ArrayList<>();
        for (DivisionDeLugarEntity entity : entityList) {
            list.add(new DivisionDeLugarDetailDTO(entity));
        }
        return list;
    }
    
    public static List<DivisionDeLugarEntity> listDivisionDeLugarDetailDTO2DivisionDeLugarEntity(List<DivisionDeLugarDetailDTO> detailDtoList) {
        List<DivisionDeLugarEntity> list = new ArrayList<>();
        for (DivisionDeLugarDetailDTO detail : detailDtoList) {
            list.add(detail.toEntity());
        }
        return list;
    }
}
