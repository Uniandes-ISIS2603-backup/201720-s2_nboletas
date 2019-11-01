/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.ejb;

import co.edu.uniandes.theexceptions.nboletas.entities.BoletaEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.ComentarioEntity;
import co.edu.uniandes.theexceptions.nboletas.exceptions.BusinessLogicException;
import co.edu.uniandes.theexceptions.nboletas.persistence.AbstractPersistence;
import co.edu.uniandes.theexceptions.nboletas.persistence.BoletaPersistence;
import co.edu.uniandes.theexceptions.nboletas.persistence.ComentarioPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

/**
 *
 * @author angeloMarcetty
 */
@Stateless
public class ComentarioLogic extends AbstractLogic<ComentarioEntity> {

    @Inject
    private ComentarioPersistence comentarioPersistence;
    
    @Inject
    private BoletaPersistence boletaPersistence;

    @Override
    protected AbstractPersistence<ComentarioEntity> getPersistence() {
        return comentarioPersistence;
    }
    
    
    public BoletaEntity createBoletaComentario(Long idComentario, BoletaEntity boleta)  throws BusinessLogicException {
        ComentarioEntity c = comentarioPersistence.find(idComentario);
        if(c == null){
        throw new BusinessLogicException("No existe comentario con el id " + idComentario);
        }
        boleta.setComentario(c);
        try{
            boleta = boletaPersistence.create(boleta);
        } catch (PersistenceException e) {
            throw new PersistenceException("Un error al crear el comentario relacionado a la boleta con id:  " + boleta.getId() + " el error es: " + e.getMessage());
        }
        return boleta;            
    }
    
    
    public BoletaEntity updateBoletaComentario(Long idComentario, Long idBoleta) throws BusinessLogicException {
       ComentarioEntity c = comentarioPersistence.find(idComentario);
        if(c == null){
        throw new BusinessLogicException("No existe comentario con el id " + idComentario);
        }
        
        BoletaEntity b = boletaPersistence.find(idBoleta);
        if (b == null) {
            throw new BusinessLogicException("No existe una boleta con el id " + idBoleta);
        }
        
        b.setComentario(c);
        try{
        b = boletaPersistence.update(b);
        } catch (PersistenceException e) {
            throw new PersistenceException("No se pudo actualizar el comentario con el id: " + idComentario + " relacionado a la boleta con el id: " + idBoleta + " el error es: " + e.getMessage());
        }
        return b;      
    }
    
    public void deleteBoletaComentario(Long idComentario, Long idBoleta)throws BusinessLogicException {
        ComentarioEntity c = comentarioPersistence.find(idComentario);
        if(c == null){
        throw new BusinessLogicException("No existe comentario con el id " + idComentario);
        }
        
        BoletaEntity b = boletaPersistence.find(idBoleta);
        if (b == null) {
            throw new BusinessLogicException("No existe una boleta con el id " + idBoleta);
        }
        
        b.setComentario(null);
        try{
        b = boletaPersistence.update(b);
        }catch (PersistenceException e) {
            throw new PersistenceException("No se puede eliminar el comentario con el id: " + idComentario + " relacionado a la boleta con el id: " + idBoleta + " El error es: " + e.getMessage());
        }
    }
    

    public BoletaEntity getBoletaComentario(Long idComentario, Long idBoleta)throws BusinessLogicException {
       
        ComentarioEntity c = comentarioPersistence.find(idComentario);
        if(c == null){
        throw new BusinessLogicException("No existe comentario con el id " + idComentario);
        }
        
        BoletaEntity b = boletaPersistence.find(idBoleta);
        if (b == null) {
            throw new BusinessLogicException("No existe una boleta con el id " + idBoleta);
        }
        
        return b;
    }
    
    
    
    public BoletaEntity finBoletaComentario(Long idComentario)throws BusinessLogicException {

        ComentarioEntity c = comentarioPersistence.find(idComentario);
        if (c == null) {
            throw new BusinessLogicException("No existe comentario con el id " + idComentario);
        }     
        return c.getBoleta();
    }
    
    
    
    
    
    
    
    

}
