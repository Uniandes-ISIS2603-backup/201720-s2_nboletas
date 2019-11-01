/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.ejb;

import co.edu.uniandes.theexceptions.nboletas.entities.DivisionDeLugarEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.FuncionEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.LugarEntity;
import co.edu.uniandes.theexceptions.nboletas.exceptions.BusinessLogicException;
import co.edu.uniandes.theexceptions.nboletas.persistence.AbstractPersistence;
import co.edu.uniandes.theexceptions.nboletas.persistence.DivisionDeLugarPersistence;
import co.edu.uniandes.theexceptions.nboletas.persistence.FuncionPersistence;
import co.edu.uniandes.theexceptions.nboletas.persistence.LugarPersistence;
import java.util.List;
import javax.ejb.Stateful;
import javax.inject.Inject;

/**
 *
 * @author ja.gomez1
 */
@Stateful
public class LugarLogic extends AbstractLogic<LugarEntity> {

    @Inject
    private LugarPersistence lugarPersistence;
    
    @Inject
    private FuncionPersistence funcionPersistence;
    
    @Inject
    private DivisionDeLugarPersistence divisionPersistence;

    @Override
    protected AbstractPersistence<LugarEntity> getPersistence() {
        return lugarPersistence;
    }

    public FuncionEntity getFuncionLugar(Long id, Long idFuncion) throws BusinessLogicException {
        LugarEntity l = lugarPersistence.find(id);
        if (l == null) {
            throw new BusinessLogicException("No existe lugar con el id " + id);
        }
        List<FuncionEntity> list = l.getFunciones();
        FuncionEntity funcion = null;
        for(FuncionEntity entity : list) if(entity.getId().equals(idFuncion)) funcion = entity;
        
        if (funcion == null) {
            throw new BusinessLogicException("No existe funcion con el id " + idFuncion + " relacionada con"
                    + " el lugar con id " + id);
        }
        return funcion;
    }

    public List<FuncionEntity> getFuncionesLugar(Long id) throws BusinessLogicException {
        LugarEntity l = lugarPersistence.find(id);
        if (l == null) {
            throw new BusinessLogicException("No existe lugar con el id " + id);
        }
        return l.getFunciones();
    }

    public FuncionEntity createFuncionLugar(Long id, FuncionEntity f) throws BusinessLogicException {
        LugarEntity l = lugarPersistence.find(id);
        if (l == null) {
            throw new BusinessLogicException("No existe lugar con el id " + id);
        }
        
        f.setLugar(l);
        f = funcionPersistence.create(f);
        return f;
    }
    
    public FuncionEntity updateFuncionLugar(Long id, Long idFuncion) throws BusinessLogicException {
        LugarEntity l = lugarPersistence.find(id);
        if (l == null) {
            throw new BusinessLogicException("No existe lugar con el id " + id);
        }

        FuncionEntity f = funcionPersistence.find(idFuncion);
        if (f == null) {
            throw new BusinessLogicException("No existe una funcion con el id " + idFuncion);
        }

        f.setLugar(l);
        f = funcionPersistence.update(f);
        return f;
    }
    
    public void deleteFuncionLugar(Long id, Long idFuncion) throws BusinessLogicException {
        LugarEntity l = lugarPersistence.find(id);
        if (l == null) {
            throw new BusinessLogicException("No existe lugar con el id " + id);
        }

        List<FuncionEntity> funciones = l.getFunciones();
        FuncionEntity funcion = null;
        for (FuncionEntity f : funciones) {
            if (f.getId().equals(idFuncion)) {
                funcion = f;
            }
        }
        if (funcion == null) {
            throw new BusinessLogicException("No existe una funcion con el id " + idFuncion + " relacionada "
                    + "con el lugar de id " + id);
        }

        funcion.setLugar(null);
        funcion = funcionPersistence.update(funcion);
    } 

    public List<DivisionDeLugarEntity> getDivisionesLugar(Long id) throws BusinessLogicException {
        LugarEntity l = lugarPersistence.find(id);
        if (l == null) {
            throw new BusinessLogicException("No existe lugar con el id " + id);
        }
        return l.getDivisiones();
    }

    public DivisionDeLugarEntity getDivisionLugar(Long id, Long idDivision) throws BusinessLogicException {
        LugarEntity l = lugarPersistence.find(id);
        if (l == null) {
            throw new BusinessLogicException("No existe lugar con el id " + id);
        }

        List<DivisionDeLugarEntity> divisiones = l.getDivisiones();
        DivisionDeLugarEntity division = null;
        for (DivisionDeLugarEntity d : divisiones) {
            if (d.getId().equals(idDivision)) {
                division = d;
            }
        }

        if (division == null) {
            throw new BusinessLogicException("No existe division con el id " + idDivision + " relacionada "
                    + "con el lugar de id " + id);
        }
        return  division;
    }

    public DivisionDeLugarEntity createDivisionLugar(Long id, DivisionDeLugarEntity d) throws BusinessLogicException {
        LugarEntity l = lugarPersistence.find(id);
        if (l == null) {
            throw new BusinessLogicException("No existe lugar con el id " + id);
        }
        d.setLugar(l);
        d = divisionPersistence.create(d);
        return d;
    }

    public DivisionDeLugarEntity updateDivisionLugar(Long id, Long idDivision) throws BusinessLogicException {
        LugarEntity l = lugarPersistence.find(id);
        if (l == null) {
            throw new BusinessLogicException("No existe lugar con el id " + id);
        }

        DivisionDeLugarEntity d = divisionPersistence.find(idDivision);
        if (d == null) {
            throw new BusinessLogicException("No existe una division con el id " + idDivision);
        }

        d.setLugar(l);
        d = divisionPersistence.update(d);
        return d;
    }

    public void deleteDivisionLugar(Long id, Long idDivision) throws BusinessLogicException {
        LugarEntity l = lugarPersistence.find(id);
        if (l == null) {
            throw new BusinessLogicException("No existe lugar con el id " + id);
        }

        List<DivisionDeLugarEntity> divisiones = l.getDivisiones();
        DivisionDeLugarEntity division = null;
        for (DivisionDeLugarEntity d : divisiones) {
            if (d.getId().equals(idDivision)) {
                division = d;
            }
        }
        if (division == null) {
            throw new BusinessLogicException("No existe una division con el id " + idDivision + " relacionada "
                    + "con el lugar de id " + id);
        }

        division.setLugar(null);
        division = divisionPersistence.update(division);
    }
}
