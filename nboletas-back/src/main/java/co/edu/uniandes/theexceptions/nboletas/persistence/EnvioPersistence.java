/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.persistence;

import co.edu.uniandes.theexceptions.nboletas.entities.EnvioEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author angeloMarcetty
 */
@Stateless
public class EnvioPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(EnvioPersistence.class.getName());

    @PersistenceContext(unitName = "nboletasPU")
    protected EntityManager em;    
    
    
    public EnvioEntity create(EnvioEntity entity) {
        LOGGER.info("Creando un envio nuevo");
        em.persist(entity);
        LOGGER.info("envio creado");
        return entity;
    }
    
    
    
    public EnvioEntity update(EnvioEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando envio con id={0}", entity.getId());       
        return em.merge(entity);
    }
    
    
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando envio con id={0}", id);
        EnvioEntity entity = em.find(EnvioEntity.class, id);
       em.remove(entity);
    }
    
    
   public EnvioEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando envio con id={0}", id);   
        return em.find(EnvioEntity.class, id);
    } 
   
   
   
    
   public List<EnvioEntity> findAll() {
        LOGGER.info("Consultando todos los Envios");
        TypedQuery query = em.createQuery("select u from EnvioEntity u", EnvioEntity.class);
        return query.getResultList();
    }
   
   
   
   
   
   
    
}
