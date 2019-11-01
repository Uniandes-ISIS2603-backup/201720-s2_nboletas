/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.persistence;

import co.edu.uniandes.theexceptions.nboletas.entities.FuncionEntity;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityExistsException;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TransactionRequiredException;

/**
 *
 * @author ja.gomez1
 */
@Stateless
public class FuncionPersistence extends AbstractPersistence<FuncionEntity> {
    
    private static final Logger LOGGER = Logger.getLogger(FuncionPersistence.class.getName());

    public FuncionPersistence() {
        super(FuncionEntity.class);
    }

    @Override
    public FuncionEntity update(FuncionEntity entity) throws IllegalArgumentException, TransactionRequiredException {
        String setStatement = "";
        
        if(entity.getHora() != null) {
            boolean valid = true;
            String h = entity.getHora();
            String[] params = h.split(" ");
            if(params.length != 2) valid = false;
            if(!(params[1].compareTo("pm") == 0 || params[1].compareTo("am") == 0)) valid = false;
            if(params[0].split(":").length != 2 || params[0].length() > 5 || params[0].length() < 4) valid = false;
            
            if(!valid) throw new IllegalArgumentException("La hora ingresada no es valida");
        }
        
        if(entity.getFecha() != null) setStatement += "FECHA = '" + (new SimpleDateFormat("MM/dd/yyyy")).format(entity.getFecha()) + "', ";
        if(entity.getEspectaculo()!= null) setStatement += "ESPECTACULO_ID = " + entity.getEspectaculo().getId() + ", ";
        if(entity.getLugar()!= null) setStatement += "LUGAR_ID = " + entity.getLugar().getId() + ", ";
        if(entity.getHora()!= null) setStatement += "HORA = '" + entity.getHora() + "', ";
        if(entity.getImagen() != null) setStatement += "IMAGEN = '" + entity.getImagen() + "', ";
        if(!setStatement.equals("")) setStatement = setStatement.substring(0, setStatement.length() - 2);
        
        Query q = em.createNativeQuery("UPDATE FUNCIONENTITY SET " + setStatement + " WHERE ID = " + entity.getId());
        q.executeUpdate();
        return entity;
    }

    @Override
    public FuncionEntity create(FuncionEntity entity) throws EntityExistsException, IllegalArgumentException, TransactionRequiredException {
         if(entity.getHora() != null) {
            String h = entity.getHora();
            String[] params = h.split(" ");
            if(params.length != 2) throw new IllegalArgumentException("La hora ingresada no es valida");
            if(!(params[1].compareTo("pm") == 0 || params[1].compareTo("am") == 0)) throw new IllegalArgumentException("La hora ingresada no es valida");
            if(params[0].split(":").length != 2 || params[0].length() > 5 || params[0].length() < 4) throw new IllegalArgumentException("La hora ingresada no es valida");
        }
        
        em.persist(entity);
        return entity;
    }
}
