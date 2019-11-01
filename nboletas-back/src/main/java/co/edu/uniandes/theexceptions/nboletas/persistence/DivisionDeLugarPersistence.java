/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.persistence;

import co.edu.uniandes.theexceptions.nboletas.entities.DivisionDeLugarEntity;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
//import javax.persistence.PersistenceException;

/**
 *
 * @author fc.alvarez10
 */
@Stateless
public class DivisionDeLugarPersistence extends AbstractPersistence<DivisionDeLugarEntity> {

    public DivisionDeLugarPersistence() {
        super(DivisionDeLugarEntity.class);
    }
   
    @Override
    public DivisionDeLugarEntity update(DivisionDeLugarEntity entity) throws PersistenceException{
        String query = "UPDATE APP.DIVISIONDELUGARENTITY SET NOMBRE = '";
        query += entity.getNombre()+"'";
        query += ", IMAGEN = '"+entity.getImagen()+"'";     
        if(entity.getLugar()!=null){
            query += ", LUGAR_ID= " + entity.getLugar().getId();
        }
        Query q = em.createNativeQuery(query + " WHERE ID = " + entity.getId());
        q.executeUpdate();
        return entity;
    }
    
 }
