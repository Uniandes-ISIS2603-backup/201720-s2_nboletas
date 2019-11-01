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
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

/**
 *
 * @author fc.alvarez10
 */
@Stateless
public class SillaLogic extends AbstractLogic<SillaEntity> {

    @Inject
    private SillaPersistence persistenceSilla; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    @Inject 
    private BoletaPersistence persistenceBoleta;
    
    @Override
    protected AbstractPersistence<SillaEntity> getPersistence() {
        return persistenceSilla;
    }
    
    public BoletaEntity createSillaBoleta(long idSilla, BoletaEntity boleta) throws BusinessLogicException, PersistenceException{
        SillaEntity silla = persistenceSilla.find(idSilla);
        if(silla==null){
            throw new BusinessLogicException("No existe la silla con el id: " + idSilla);
        }
        boleta.setSilla(silla);
        BoletaEntity boletaCreada=null;
        try{
            boletaCreada= persistenceBoleta.create(boleta);
        }catch(PersistenceException e){
            throw new PersistenceException("Un error al crear la bolta relacionada a la silla con id:  " + idSilla + " el error es: " + e.getMessage());
        }
        return boletaCreada;
    }
    
    public List<BoletaEntity> findSillaBoletas(long idSilla) throws BusinessLogicException, PersistenceException{
        List<BoletaEntity> boletas = new ArrayList<BoletaEntity>();
        SillaEntity silla= persistenceSilla.find(idSilla);
        if(silla==null){
            throw new BusinessLogicException("No se encuentra la silla con el id: " + idSilla);
        }
        try{
            List<BoletaEntity> boletasE=silla.getBoletas();
            for(BoletaEntity b:boletasE){
                boletas.add(b);
            }
        }catch(PersistenceException e){
             throw new PersistenceException(" Sucedio un error en la base de datos, mayor informacion: " + e.getMessage());
        }
        return boletas;
    }
    
    public BoletaEntity findSillaBoleta(long idSilla, long idBoleta) throws BusinessLogicException,PersistenceException{
        SillaEntity silla = persistenceSilla.find(idSilla);
        if(silla==null){
            throw new BusinessLogicException("No existe la silla con ese id: " + idSilla); 
        }
        BoletaEntity boleta =persistenceBoleta.find(idBoleta);
        if(boleta==null){
             throw new BusinessLogicException("No existe la boleta con ese id: " + idBoleta);
        }
        long id=boleta.getSilla().getId();
        if(id!=idBoleta){
             throw new BusinessLogicException("No existe la relación");
        }
        boleta.setSilla(silla);
        return boleta;
    }
    
    public BoletaEntity updateSillaBoleta(long idSilla, BoletaEntity boleta) throws BusinessLogicException, PersistenceException{
        SillaEntity silla= persistenceSilla.find(idSilla);
        if(silla==null){
            throw new BusinessLogicException("No existe la silla con el id: " + idSilla);
        }
        long idBoleta = boleta.getId();
        if(persistenceBoleta.find(idBoleta)==null){
            throw new BusinessLogicException("No existe la boleta con ese id: " + idBoleta);
        }
        boleta.setSilla(silla);
        BoletaEntity boletaF =null;
        try{
            boletaF = persistenceBoleta.update(boleta);
        }catch(PersistenceException e){
            throw new PersistenceException("No se pudo actualizar la boleta con el id: " + idBoleta + " relacionado a la silla con el id: " + idSilla + " el error es: " + e.getMessage());
        }
        return boletaF;
    }
    
    public void deleteSillaBoleta(long idSilla ,long idBoleta) throws BusinessLogicException, PersistenceException{
        SillaEntity silla= persistenceSilla.find(idSilla);
        if(silla==null){
            throw new BusinessLogicException("No existe la silla con el id: " + idSilla);
        }
        BoletaEntity boleta = persistenceBoleta.find(idBoleta);
        if(boleta==null){
            throw new BusinessLogicException("No existe la boleta con ese id: " + idBoleta);
        }
        boleta.setSilla(silla);
        try{
            persistenceBoleta.delete(boleta);
        }catch(PersistenceException e){
            throw new PersistenceException("No se puede eliminar la boleta con el id: " + idBoleta + " relacionado a la silla con el id: " + idSilla + " El error es: " + e.getMessage());
        }
    }
}
