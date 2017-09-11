/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.persistence;

import co.edu.uniandes.theexceptions.nboletas.entities.ComentarioEntity;
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
public class ComentarioPersistence {
    
 
    private static final Logger LOGGER = Logger.getLogger(ComentarioPersistence.class.getName());

    @PersistenceContext(unitName = "nboletasPU")
    protected EntityManager em;
    
    
    
    
    /**
     * 
     * @param entity objeto Comentario que se creara en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ComentarioEntity create(ComentarioEntity entity){
        
        LOGGER.info("Creando un comentario nuevo");

         /* Note que hacemos uso de un método propio de EntityManager para persistir el comentario en la base de datos.
        Es similar a "INSERT INTO table_codigo (column1, column2, column3, ...) VALUES (value1, value2, value3, ...);" en SQL.
         */
         em.persist(entity);
         LOGGER.info("Creando un comentario nuevo");
         return entity;
    }
    
    
    
    
    /**
     * 
     * @param entity El Comentario que viene con losnuevos cambios.
     * @return comentario con los cambios aplicados
     */
    public ComentarioEntity update(ComentarioEntity entity){
        LOGGER.log(Level.INFO, "Actualizando Comentario con id={0}", entity.getId());
        
        /* Note que hacemos uso de un método propio del EntityManager llamado merge() que recibe como argumento
        el comentario con los cambios, esto es similar a 
        "UPDATE table_codigo SET column1 = value1, column2 = value2, ... WHERE condition;" en SQL.
         */
        return em.merge(entity);
    }
    
    
    
    /**
     *
     * Borra un comentario de la base de datos recibiendo como argumento el id
     * del comentario
     *
     * @param id: id correspondiente al comentario a borrar.
     */
    public void delete(Long id){
      LOGGER.log(Level.INFO, "Borrando Comentario con id={0}", id);  
      // Se hace uso de mismo método que esta explicado en public ComentarioEntity find(Long id) para obtener el comentario a borrar.
      ComentarioEntity entity = em.find(ComentarioEntity.class, id);
    
    /* Note que una vez obtenido el objeto desde la base de datos llamado "entity", volvemos hacer uso de un método propio del
         EntityManager para eliminar de la base de datos el objeto que encontramos y queremos borrar.
         Es similar a "delete from ComentarioEntity where id=id;" - "DELETE FROM table_codigo WHERE condition;" en SQL.*/
    
         em.remove(entity);
    }
    
    
    
    
    /**
     * Busca si hay algun comentario con el id que se envía de argumento
     *
     * @param id: id correspondiente al comentario buscado.
     * @return un comentario.
     */
    public ComentarioEntity find(Long id){
        LOGGER.log(Level.INFO, "Consultando comentario con id={0}", id);
 
        /* Note que se hace uso del metodo "find" propio del EntityManager, el cual recibe como argumento 
        el tipo de la clase y el objeto que nos hara el filtro en la base de datos en este caso el "id"
        Suponga que es algo similar a "select * from ComentarioEntity where id=id;" - "SELECT * FROM table_codigo WHERE condition;" en SQL.
         */
        
        return em.find(ComentarioEntity.class, id);
        
    }
    
    
    public List<ComentarioEntity> findAll(){
        LOGGER.info("Consultando todos los Comentarios");
        
         // Se crea un query para buscar todos los Comentarios en la base de datos.
        TypedQuery query = em.createQuery("select u from ComentarioEntity u", ComentarioEntity.class);
        // Note que en el query se hace uso del método getResultList() que obtiene una lista de los comentarios.
        return query.getResultList();
        
    }
    
    
    
    
    
    
}
