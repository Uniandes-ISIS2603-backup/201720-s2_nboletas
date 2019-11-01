/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.dtos;

import co.edu.uniandes.theexceptions.nboletas.entities.BoletaEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.DivisionDeLugarEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.FuncionEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ja.gomez1
 */
public class FuncionDetailDTO extends FuncionDTO {

    private List<BoletaDTO> boletas;

    private LugarDTO lugar;

    private EspectaculoDTO espectaculo;

    public FuncionDetailDTO() {
    }

    public FuncionDetailDTO(FuncionEntity entity) {
        super(entity);

        if (entity.getBoletas() != null) {
            ArrayList<BoletaDTO> b = new ArrayList<>();
            for (BoletaEntity e : entity.getBoletas()) {
                b.add(new BoletaDTO(e));
            }
            this.boletas = b;
        }

        if (entity.getLugar() != null) {
            this.lugar = new LugarDTO(entity.getLugar());
        }

        if (entity.getEspectaculo() != null) {
            this.espectaculo = new EspectaculoDTO(entity.getEspectaculo());
        }
    }

    @Override
    public FuncionEntity toEntity() {
        FuncionEntity f = super.toEntity();

        if (getBoletas() != null) {
            ArrayList<BoletaEntity> b = new ArrayList<>();
            for (BoletaDTO e : getBoletas()) {
                b.add(e.toEntity());
            }
            f.setBoletas(b);
        }

        if (getLugar() != null) {
            f.setLugar(this.lugar.toEntity());
        }

        if (getEspectaculo() != null) {
            f.setEspectaculo(this.espectaculo.toEntity());
        }

        return f;
    }

    /**
     * @return the boletas
     */
    public List<BoletaDTO> getBoletas() {
        return boletas;
    }

    /**
     * @param boletas the boletas to set
     */
    public void setBoletas(List<BoletaDTO> boletas) {
        this.boletas = boletas;
    }

    /**
     * @return the lugar
     */
    public LugarDTO getLugar() {
        return lugar;
    }

    /**
     * @param lugar the lugar to set
     */
    public void setLugar(LugarDTO lugar) {
        this.lugar = lugar;
    }

    /**
     * @return the espectaculo
     */
    public EspectaculoDTO getEspectaculo() {
        return espectaculo;
    }

    /**
     * @param espectaculo the espectaculo to set
     */
    public void setEspectaculo(EspectaculoDTO espectaculo) {
        this.espectaculo = espectaculo;
    }
    
    public static List<FuncionDetailDTO> listFuncionEntity2DetailDTO(List<FuncionEntity> findAll) {
        List<FuncionDetailDTO> f = new ArrayList<>();
        for(FuncionEntity e : findAll) f.add(new FuncionDetailDTO(e));
        return f;
    }
    
    public static List<FuncionEntity> listFuncionDetailDTO2Entity(List<FuncionDetailDTO> findAll) {
        List<FuncionEntity> f = new ArrayList<>();
        for(FuncionDetailDTO e : findAll) f.add(e.toEntity());
        return f;
    }
}
