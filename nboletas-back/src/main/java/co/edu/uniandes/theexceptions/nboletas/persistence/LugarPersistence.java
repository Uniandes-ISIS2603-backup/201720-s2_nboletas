/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.persistence;

import co.edu.uniandes.theexceptions.nboletas.entities.LugarEntity;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

/**
 *
 * @author ja.gomez1
 */
@Stateless
public class LugarPersistence extends AbstractPersistence<LugarEntity> {

    public LugarPersistence() {
        super(LugarEntity.class);
    }

    @Override
    public LugarEntity update(LugarEntity entity) throws IllegalArgumentException, TransactionRequiredException {
        String setStatement = "";
        if(entity.getDireccion()!= null) setStatement += "DIRECCION = '" + entity.getDireccion() + "', ";
        if(entity.getTipo()!= null) setStatement += "TIPO = '" + entity.getTipo() + "', ";
        if(entity.getUbicacion()!= null) setStatement += "UBICACION = '" + entity.getUbicacion() + "', ";
        if(entity.getImagen()!= null) setStatement += "IMAGEN = '" + entity.getImagen()+ "', ";
        if(!setStatement.equals("")) setStatement = setStatement.substring(0, setStatement.length() - 2);
        
        Query q = em.createNativeQuery("UPDATE LUGARENTITY SET " + setStatement + " WHERE ID = " + entity.getId());
        q.executeUpdate();
        return entity;
    }
    
    
}
