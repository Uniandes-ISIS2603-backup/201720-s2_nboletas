/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.ejb;

import co.edu.uniandes.theexceptions.nboletas.entities.BoletaEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.ReembolsoEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.UsuarioEntity;
import co.edu.uniandes.theexceptions.nboletas.exceptions.BusinessLogicException;
import co.edu.uniandes.theexceptions.nboletas.persistence.AbstractPersistence;
import co.edu.uniandes.theexceptions.nboletas.persistence.ReembolsoPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jm.contreras10
 */
@Stateless
public class ReembolsoLogic extends AbstractLogic<ReembolsoEntity> {

    @Inject
    private ReembolsoPersistence persistence;

    @Override
    protected AbstractPersistence<ReembolsoEntity> getPersistence() {
        return persistence;
    }
    
    /**
     * Retorna el usuario que realizo el reembolso.
     * <pre> La boleta fue reembolsada, por lo que debe existir un usuario asociado a ella.
     * @param idReembolso
     * @return
     * @throws BusinessLogicException 
     *  Cuando no existe el reembolso.
     */
    public UsuarioEntity findUsuarioReembolso(long idReembolso) throws BusinessLogicException{
        //Obtencion reembolso
        ReembolsoEntity reembolsoEntity = persistence.find(idReembolso);
        //Verificacion reembolso existe
        if(reembolsoEntity == null){
            throw new BusinessLogicException("Reembolso no encontrado con id: "+idReembolso);
        }
        //Obtencion y retorno del usuario
        return reembolsoEntity.getUsuario();
    }
    
    /**
     * Retorna la boleta a la que se le aplico el reembolso.
     * <pre> La boleta fue reembolsada, por lo que deberia existir siempre una boleta asociada a el.
     * @param idReembolso
     * @return
     * @throws BusinessLogicException 
     *  Cuando no existe el reembolso.
     */
    public BoletaEntity findBoletaReembolso(long idReembolso) throws BusinessLogicException{
        //Obtencion reembolso
        ReembolsoEntity reembolsoEntity = persistence.find(idReembolso);
        //Verificacion reembolso existe
        if(reembolsoEntity == null){
            throw new BusinessLogicException("Reembolso no encontrado con id: "+idReembolso);
        }
        //Obtencion y retorno del usuario
        return reembolsoEntity.getBoleta();
    }
    
    //En este caso no debe existir el create, update y find patra las relaciones, pues estas son agenas al 
    //mismo reembolso.

}
