/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.persistence;

import co.edu.uniandes.theexceptions.nboletas.entities.ReembolsoEntity;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

/**
 *
 * @author jm.contreras10
 */
@Stateless
public class ReembolsoPersistence extends AbstractPersistence<ReembolsoEntity> {

    public ReembolsoPersistence() {
        super(ReembolsoEntity.class);
    }

    @Override
    public ReembolsoEntity update(ReembolsoEntity entity) throws PersistenceException {
        String query = "UPDATE APP.REEMBOLSOENTITY SET ";
        if (entity.getValor() >= 0) {
            query += "VALOR = " + entity.getValor();
        }
        if (entity.getUsuario() != null) {
            query += ", USUARIO_ID = " + entity.getUsuario().getId();
        }
        if (entity.getBoleta()!= null) {
            query += ", BOLETA_ID = " + entity.getBoleta().getId();
        }
        if (entity.getBoleta()!= null) {
            query += ", IMAGEN = '" + entity.getBoleta().getId() + "'";
        }
        query += " WHERE ID = " + entity.getId();
        em.createNativeQuery(query).executeUpdate();
        return entity;
    }
}
