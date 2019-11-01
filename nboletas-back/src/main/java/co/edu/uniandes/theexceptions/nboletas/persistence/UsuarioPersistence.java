/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.persistence;

import co.edu.uniandes.theexceptions.nboletas.entities.UsuarioEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

/**
 *
 * @author jm.contreras10
 */
@Stateless
public class UsuarioPersistence extends AbstractPersistence<UsuarioEntity> {

    public UsuarioPersistence() {
        super(UsuarioEntity.class);
    }
    
    /**
     * Busca un usuario por un UserName dado por parametro.
     *
     * @param userName username del usuario a buscar.
     * @return UsuarioEntity encontrado.
     */
    public UsuarioEntity findByUserName(String userName) {
        TypedQuery query = em.createQuery("Select e From UsuarioEntity e where e.userName = :uName", UsuarioEntity.class);
        // Se remplaza el placeholder ":codigo" con el valor del argumento 
        query = query.setParameter("uName", userName);
        // Se invoca el query se obtiene la lista resultado
        List<UsuarioEntity> sameCodigo = query.getResultList();
        if (sameCodigo == null) {
            return null;
        }
        if (sameCodigo.isEmpty()) {
            return null;
        } else {
            return sameCodigo.get(0);
        }
    }
    
    /**
     * Todas las variables son verificadas con sus constraints en la base de datos.
     * @param entity
     * @return
     * @throws PersistenceException 
     */
    @Override
    public UsuarioEntity update(UsuarioEntity entity) throws PersistenceException {
        //TODAS LAS VARIABLES OBLIGATORIAS con respectivo CONSTRAINT verificado en la base de datos.
        String query = "UPDATE APP.USUARIOENTITY SET USERNAME = \'";
        query += entity.getUserName()+"\'";
        query += ", PASSWORD = \'"+entity.getPassword()+"\'";
        query += ", NOMBREUSUARIO = \'"+ entity.getNombreUsuario()+"\'";
        query += ", EMAIL = \'" + entity.getEmail()+"\'";
        query += ", PAIS = \'" + entity.getPais()+"\'";
        query += ", CIUDAD = \'" + entity.getCiudad()+"\'";
        if(entity.getAdmon()){
            query += ", ADMON=1" ;
        }else{
            query += ", ADMON=0";
        }
        if(!entity.getImagen().isEmpty()){
            query += ",IMAGEN=\'"+entity.getImagen()+"\'";
        }
        query += " WHERE ID =" + entity.getId();
        em.createNativeQuery(query).executeUpdate();
        return entity;
    }

    
}
