/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.persistence;

import co.edu.uniandes.theexceptions.nboletas.entities.SillaEntity;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author fc.alvarez10
 */
@Stateless
public class SillaPersistence extends AbstractPersistence<SillaEntity> {

    public SillaPersistence() {
        super(SillaEntity.class);
    }
    
    @Override
    public SillaEntity update(SillaEntity entity) throws PersistenceException{
        String query ="";
        if(entity.getCosto()>=0){
            query += "COSTO= " + entity.getCosto() + ",";
        }
        if(entity.getDivision()!=null){
            query += "DIVISION_ID= " + entity.getDivision().getId() + ",";
        }
        if(entity.getImagen()!=null){
            query += "IMAGEN= '" + entity.getImagen() + "',";
        }
        if(!query.equals("")){
            query = query.substring(0, query.length()-1);
        }
        Query q = em.createNativeQuery("UPDATE APP.SILLAENTITY SET " + query + " WHERE ID = " + entity.getId());
        q.executeUpdate();
        return entity;
    }
}
