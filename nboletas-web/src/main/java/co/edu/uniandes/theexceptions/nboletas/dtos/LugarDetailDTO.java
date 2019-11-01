/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.dtos;

import co.edu.uniandes.theexceptions.nboletas.entities.DivisionDeLugarEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.FuncionEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.LugarEntity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * @author ja.gomez1
 */
public class LugarDetailDTO extends LugarDTO {

    private List<DivisionDeLugarDTO> divisiones;

    private List<FuncionDTO> funciones;

    public LugarDetailDTO() {
    }

    public LugarDetailDTO(LugarEntity entity) {
        super(entity);

        if (entity.getDivisiones() != null) {
            ArrayList<DivisionDeLugarDTO> d = new ArrayList<>();
            for (DivisionDeLugarEntity e : entity.getDivisiones()) {
                d.add(new DivisionDeLugarDTO(e));
            }
            this.divisiones = d;
        }

        if (entity.getFunciones() != null) {
            ArrayList<FuncionDTO> f = new ArrayList<>();
            for (FuncionEntity e : entity.getFunciones()) {
                f.add(new FuncionDTO(e));
            }
            this.funciones = f;
        }
    }

    @Override
    public LugarEntity toEntity() {
        LugarEntity l = super.toEntity();

        if (getDivisiones() != null) {
            ArrayList<DivisionDeLugarEntity> d = new ArrayList<DivisionDeLugarEntity>();
            for (DivisionDeLugarDTO p : getDivisiones()) {
                d.add(p.toEntity());
            }
            l.setDivisiones(d);
        }

        if (getFunciones() != null) {
            ArrayList<FuncionEntity> f = new ArrayList<FuncionEntity>();
            for (FuncionDTO p : getFunciones()) {
                f.add(p.toEntity());
            }
            l.setFunciones(f);
        }
        return l;
    }

    /**
     * @return the divisiones
     */
    public List<DivisionDeLugarDTO> getDivisiones() {
        return divisiones;
    }

    /**
     * @param divisiones the divisiones to set
     */
    public void setDivisiones(List<DivisionDeLugarDTO> divisiones) {
        this.divisiones = divisiones;
    }

    /**
     * @return the funciones
     */
    public List<FuncionDTO> getFunciones() {
        return funciones;
    }

    /**
     * @param funciones the funciones to set
     */
    public void setFunciones(List<FuncionDTO> funciones) {
        this.funciones = funciones;
    }
    
    public static List<LugarDetailDTO> listLugarEntity2DetailDTO(List<LugarEntity> findAll) {
        List<LugarDetailDTO> l = new ArrayList<>();
        for(LugarEntity e : findAll) l.add(new LugarDetailDTO(e));
        return l;
    }
    
    public static List<LugarEntity> listLugarDetailDTO2Entity(List<LugarDetailDTO> findAll) {
        List<LugarEntity> l = new ArrayList<>();
        for(LugarDetailDTO e : findAll) l.add(e.toEntity());
        return l;
    }
}
