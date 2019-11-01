/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.ejb;

import co.edu.uniandes.theexceptions.nboletas.entities.EspectaculoEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.OrganizadorEntity;
import co.edu.uniandes.theexceptions.nboletas.exceptions.BusinessLogicException;
import co.edu.uniandes.theexceptions.nboletas.persistence.AbstractPersistence;
import co.edu.uniandes.theexceptions.nboletas.persistence.EspectaculoPersistence;
import co.edu.uniandes.theexceptions.nboletas.persistence.OrganizadorPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

/**
 *
 * @author jf.ramos
 */
@Stateless
public class EspectaculoLogic extends AbstractLogic<EspectaculoEntity> {

    @Inject
    private EspectaculoPersistence persistence;
    
    @Inject
    private OrganizadorPersistence persistenceOrganizador;

    @Override
    protected AbstractPersistence<EspectaculoEntity> getPersistence() {
        return persistence;
    }
   
    @Override
    public void delete(EspectaculoEntity entity){
        try {
            Long id=entity.getId();
            persistence.deleteArtistaEspectaculo(id);
            persistence.deleteComentarioEspectaculo(id);
            persistence.deleteOrganizadorEspectaculo(id);
            persistence.delete(entity);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("La instancia no es una entidad", e);
        } catch (TransactionRequiredException e) {
            throw new TransactionRequiredException("No existe ninguna transaccion");
        }
    }
    /**Recibimos todo el id del organizador que vamos a desasocia
     * @param idOrganizador
     * @param idEspectaculo
     * @throws co.edu.uniandes.theexceptions.nboletas.exceptions.BusinessLogicException*/
    public void deleterEspectaculoOrganizado(Long idOrganizador, Long idEspectaculo) throws BusinessLogicException {
        OrganizadorEntity organizador = persistenceOrganizador.find(idOrganizador);
        if (organizador == null) {
            throw new BusinessLogicException("No existe el organizador con ese id: " + idOrganizador);
        }
        EspectaculoEntity espectaculo = persistence.find(idEspectaculo);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe el espectaculo con ese id: " + idEspectaculo);
        }
        try{
            persistence.deleteEspectaculoTablaIntermediaOrganizador(idOrganizador, idEspectaculo);
        }catch(PersistenceException e){
            throw new PersistenceException("No se puede eliminar el espectaculo con el id: " + idEspectaculo + " relacionado al organizador con el id: " + idOrganizador + " El error es: " + e.getMessage());
        }
    }

    public OrganizadorEntity updateEspectaculoOrganizador(Long idEspectaculo, OrganizadorEntity organizadorE) throws BusinessLogicException {
        EspectaculoEntity espectaculo = persistence.find(idEspectaculo);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe el espectaculo con ese id: " + idEspectaculo);
        }
        long idOrganizador = organizadorE.getId();
        if (null == persistenceOrganizador.find(idOrganizador)) {
            throw new BusinessLogicException("No existe el espectaculo con ese id: " + idOrganizador);
        }
        OrganizadorEntity organizador = null;
        try {
            organizador = persistenceOrganizador.updateOrganizadorEspectaculo(organizadorE, idEspectaculo);
        } catch (PersistenceException e) {
            throw new PersistenceException("No se pudo actualizar el especctaculo con el id: " + idOrganizador + " relacionado con el organizador con el id: " + idOrganizador + " el error es: " + e.getMessage());
        }
        List<EspectaculoEntity> espectaculos = new ArrayList<EspectaculoEntity>();
        espectaculos.add(espectaculo);
        organizador.setEspectaculos(espectaculos);
        return organizador;
    }
}
