/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.ejb;

import co.edu.uniandes.theexceptions.nboletas.entities.EnvioEntity;
import co.edu.uniandes.theexceptions.nboletas.persistence.EnvioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author angeloMarcetty
 */
@Stateless
public class EnvioLogic {
    
    private static final Logger LOGGER = Logger.getLogger(EnvioLogic.class.getName());

   @Inject
   private EnvioPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    
    
    public EnvioEntity createEnvio(EnvioEntity entity)  {
        LOGGER.info("Inicia proceso de creación de envio");
        persistence.create(entity);
        LOGGER.info("Termina proceso de creación de envio");
        return entity;
    }
    
    
    
    public List<EnvioEntity> getEnvios() {
        LOGGER.info("Inicia proceso de consultar todos los envios");
        List<EnvioEntity> envios = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los envios");
        return envios;
    }
    
    
    
    public EnvioEntity getEnvio(Long id) {
        EnvioEntity env = persistence.find(id);
        return env;
    }
    
    
    public void deleteEnvio(Long id) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar envio con id={0}", id);
        persistence.delete(id);
         LOGGER.log(Level.INFO, "Termina proceso de borrar envio con id={0}", id);
    }
    
    public EnvioEntity updateEnvio(Long id, EnvioEntity entity)  {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar envio con id={0}", id);
        EnvioEntity newEntity = persistence.update(entity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar envio con id={0}", entity.getId());
        return newEntity;
    }
    
}
