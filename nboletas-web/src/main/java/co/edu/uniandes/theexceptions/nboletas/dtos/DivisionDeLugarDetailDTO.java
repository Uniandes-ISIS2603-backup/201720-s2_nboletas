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
    
    public DivisionDeLugarDetailDTO(){
    }
    
    public DivisionDeLugarDetailDTO(DivisionDeLugarEntity division){
        super(division);
        this.lugar = new LugarDTO(division.getLugar());
        this.sillas= new ArrayList<>();
        for (SillaEntity s : division.getSillas()) {
            sillas.add(new SillaDTO(s));
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
    public DivisionDeLugarEntity toEntity(){
        DivisionDeLugarEntity entity=super.toEntity();
        return entity;
    }
}