/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.persistence;

import co.edu.uniandes.theexceptions.nboletas.entities.EnvioEntity;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

/**
 *
 * @author angeloMarcetty
 */
@Stateless
public class EnvioPersistence extends AbstractPersistence<EnvioEntity> {

    public EnvioPersistence() {
        super(EnvioEntity.class);
    }

    @Override
    public EnvioEntity update(EnvioEntity entity) throws PersistenceException {
        String query = "UPDATE APP.ENVIOENTITY SET ";
        if (!entity.getDireccion().equals("") && !entity.getDireccion().equals(" ")) {
            query += " DIRECCION = '" + entity.getDireccion() + "'";
        }
        if (entity.getBoleta() != null) {
            query += ", BOLETA_ID = " + entity.getBoleta().getId();
        }
        if(entity.getImagen() != null){
            query += ", IMAGEN = '"+ entity.getImagen() + "'";
        }
        
        query += " WHERE ID = " + entity.getId();
        em.createNativeQuery(query).executeUpdate();
        return entity;
    }
}
