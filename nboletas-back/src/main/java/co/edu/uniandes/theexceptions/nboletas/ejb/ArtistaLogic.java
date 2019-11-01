/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.ejb;

import co.edu.uniandes.theexceptions.nboletas.entities.ArtistaEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.EspectaculoEntity;
import co.edu.uniandes.theexceptions.nboletas.persistence.AbstractPersistence;
import co.edu.uniandes.theexceptions.nboletas.persistence.ArtistaPersistence;
import co.edu.uniandes.theexceptions.nboletas.persistence.EspectaculoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TransactionRequiredException;

/**
 *
 * @author jf.ramos
 */
@Stateless
public class ArtistaLogic extends AbstractLogic<ArtistaEntity> {

    @Inject
    private ArtistaPersistence persistence;
    
    @Inject
    private EspectaculoPersistence persistence2;

    @Override
    protected AbstractPersistence<ArtistaEntity> getPersistence() {
        return persistence;
    }
    
    @Override
    public void delete(ArtistaEntity entity){
        try {
            Long id=entity.getId();
            List<Long> lista=persistence.getArtistaEspectaculo(id);
            for(Long id1:lista){
                persistence2.deleteArtistaEspectaculo(id1);
                persistence2.deleteComentarioEspectaculo(id1);
                persistence2.deleteOrganizadorEspectaculo(id1);
                persistence2.delete(persistence2.find(id1));
            }
            super.delete(entity);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("La instancia no es una entidad", e);
        } catch (TransactionRequiredException e) {
            throw new TransactionRequiredException("No existe ninguna transaccion");
        }
    }

}
