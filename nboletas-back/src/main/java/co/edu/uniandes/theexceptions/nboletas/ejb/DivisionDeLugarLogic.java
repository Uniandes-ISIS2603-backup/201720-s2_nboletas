/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.ejb;

import co.edu.uniandes.theexceptions.nboletas.entities.*;
import co.edu.uniandes.theexceptions.nboletas.exceptions.BusinessLogicException;
import co.edu.uniandes.theexceptions.nboletas.persistence.AbstractPersistence;
import co.edu.uniandes.theexceptions.nboletas.persistence.*;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

/**
 *
 * @author fc.alvarez10
 */
public class DivisionDeLugarLogic extends AbstractLogic<DivisionDeLugarEntity> {

    @Inject
    private DivisionDeLugarPersistence persistenceDivision; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject
    private SillaPersistence persistenceSilla;
    
    @Override
    protected AbstractPersistence<DivisionDeLugarEntity> getPersistence() {
        return persistenceDivision;
    }
    
    public SillaEntity createDivisionSilla(long idDivision, SillaEntity silla) throws BusinessLogicException, PersistenceException {
        DivisionDeLugarEntity division=persistenceDivision.find(idDivision);
        if(division==null){
            throw new BusinessLogicException("No existe la division con el id: " + idDivision);
        }
        silla.setDivision(division);
        SillaEntity sillaCreada=null;
        try{
            sillaCreada = persistenceSilla.create(silla);
        }catch(PersistenceException e){
            throw new PersistenceException("Un error al crear la silla relacionada a la division con id:  " + idDivision + " el error es: " + e.getMessage());
        }
        return sillaCreada;
    }
    
    public List<SillaEntity> findDivisionSillas(long idDivision)throws BusinessLogicException, PersistenceException{
        List<SillaEntity>  sillas = new ArrayList<SillaEntity>();
        DivisionDeLugarEntity division= persistenceDivision.find(idDivision);
        if(division==null){
            throw new BusinessLogicException("No se encuentra la division con el id: " + idDivision);
        }
        try{
            List<SillaEntity> sillasE = division.getSillas();
            for(SillaEntity s:sillasE){
                sillas.add(s);
            }
        }catch(PersistenceException e){
            throw new PersistenceException(" Sucedio un error en la base de datos, mayor informacion: " + e.getMessage());
        }
        return sillas;
    }
    
    public SillaEntity findDivisionSilla(long idDivision,long idSilla)throws BusinessLogicException, PersistenceException{
        DivisionDeLugarEntity division= persistenceDivision.find(idDivision);
        if(division==null){
            throw new BusinessLogicException("No existe la division con el id: " + idDivision);
        }
        SillaEntity silla = persistenceSilla.find(idSilla);
        if(silla==null){
            throw new BusinessLogicException("No existe la silla con ese id: " + idSilla);
        }
        long id = silla.getDivision().getId();
        if(id!=idDivision){
            throw new BusinessLogicException("No existe la relacion ");
        }
        silla.setDivision(division);
        return silla;
    }
    
    public SillaEntity updateDivisionSilla(long idDivision, SillaEntity silla) throws BusinessLogicException, PersistenceException{
        DivisionDeLugarEntity division= persistenceDivision.find(idDivision);
        if(division==null){
            throw new BusinessLogicException("No existe la division con el id: " + idDivision);
        }
        long idsilla = silla.getId();
        if(persistenceSilla.find(idsilla)==null){
            throw new BusinessLogicException("No existe la silla con ese id: " + idsilla);
        }
        silla.setDivision(division);
        SillaEntity sillaF =null;
        try{
            sillaF = persistenceSilla.update(silla);
        }catch(PersistenceException e){
            throw new PersistenceException("No se pudo actualizar la silla con el id: " + idsilla + " relacionado a la division con el id: " + idDivision + " el error es: " + e.getMessage());
        }
        return sillaF;
    }
    
    public void deleteDivisionSilla(long idDivision ,long idSilla) throws BusinessLogicException, PersistenceException{
        DivisionDeLugarEntity division= persistenceDivision.find(idDivision);
        if(division==null){
            throw new BusinessLogicException("No existe la division con el id: " + idDivision);
        }
        SillaEntity silla = persistenceSilla.find(idSilla);
        if(silla==null){
            throw new BusinessLogicException("No existe la silla con ese id: " + idSilla);
        }
        silla.setDivision(division);
        try{
            persistenceSilla.delete(silla);
        }catch(PersistenceException e){
            throw new PersistenceException("No se puede eliminar la silla con el id: " + idSilla + " relacionado a la division con el id: " + idDivision + " El error es: " + e.getMessage());
        }
    }
}
