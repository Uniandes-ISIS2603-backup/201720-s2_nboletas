/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.persistence;

import co.edu.uniandes.theexceptions.nboletas.entities.ArtistaEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.EspectaculoEntity;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author jf.ramos
 */
@Stateless
public class ArtistaPersistence extends AbstractPersistence<ArtistaEntity> {

    public ArtistaPersistence() {
        super(ArtistaEntity.class);
    }
    
    public List<Long> getArtistaEspectaculo(Long idArtista) {
        String query1="SELECT ESPECTACULOS_ID FROM APP.ESPECTACULOENTITY_ARTISTAENTITY WHERE (ARTISTA_ID = " + idArtista +" )";
        List<Long> listaABorrar= em.createNativeQuery(query1).getResultList();
        return listaABorrar;
    }
}
