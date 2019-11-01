/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.persistence;

import co.edu.uniandes.theexceptions.nboletas.entities.ComentarioEntity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;

/**
 *
 * @author angeloMarcetty
 */
@Stateless
public class ComentarioPersistence extends AbstractPersistence<ComentarioEntity> {

    public ComentarioPersistence() {
        super(ComentarioEntity.class);
    }
    
    @Override
    public ComentarioEntity update(ComentarioEntity entity)throws IllegalArgumentException, TransactionRequiredException {
        String setStatement = "";
//        if(entity.getComentario() != null) setStatement += "COMENTARIO = '" + entity.getComentario() + "', ";
        
        setStatement += "COMENTARIO = '" + entity.getComentario() + "', ";
        
        if(entity.getFecha() !=null){
           Date fecha1 = entity.getFecha();
           String newstring = new SimpleDateFormat("yyyy-MM-dd").format(entity.getFecha());         
            
            setStatement += "FECHA = '" + newstring + "', ";
        }
        
        if(entity.getImagen() != null){
            setStatement += "IMAGEN = '"+ entity.getImagen() + "', ";
        }
        
        if(!setStatement.equals("")) setStatement = setStatement.substring(0, setStatement.length() - 2);
        
        Query q = em.createNativeQuery("UPDATE COMENTARIOENTITY SET " + setStatement + " WHERE ID = " + entity.getId());
        q.executeUpdate();
        return entity;
    }
    
    
    
    
    
    
    
    
    
}
