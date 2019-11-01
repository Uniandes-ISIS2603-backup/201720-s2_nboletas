/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.dtos.ArtistaDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.dtos.EspectaculoDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.ejb.ArtistaLogic;
import co.edu.uniandes.theexceptions.nboletas.ejb.EspectaculoLogic;
import co.edu.uniandes.theexceptions.nboletas.entities.ArtistaEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.EspectaculoEntity;
import co.edu.uniandes.theexceptions.nboletas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author jf.ramos
 */
@Path("espectaculos/{idEspectaculo: \\d+}/artistas")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class EspectaculoArtistaResource {

    @Inject
    ArtistaLogic artistaLogic;

    @Inject
    EspectaculoLogic espectaculoLogic;
    
    
    @POST
    public ArtistaDetailDTO createEspectaculoArtista(@PathParam("idEspectaculo") Long idEspectaculo, ArtistaDetailDTO artista) throws BusinessLogicException {
        EspectaculoEntity espectaculo = espectaculoLogic.find(idEspectaculo);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe el espectaculo con el id: " + idEspectaculo);
        }
        ArtistaEntity artistaE = artistaLogic.find(artista.getId());
        List <EspectaculoEntity> lista= new ArrayList<>();
        lista.add(espectaculo);
        List<ArtistaEntity> artistas = espectaculo.getArtista();
        artistaE.setEspectaculos(lista);
        artistas.add(artistaE);
        espectaculo.setArtista(artistas);
        ArtistaEntity artistaCreado = artistaLogic.create(artistaE);
        return new ArtistaDetailDTO(artistaCreado);
    }

    @GET
    public List<ArtistaDetailDTO> getArtistas(@PathParam("idEspectaculo") Long idEspectaculo) throws BusinessLogicException {
        List<ArtistaEntity> list = new ArrayList<>();
        EspectaculoEntity espectaculo = espectaculoLogic.find(idEspectaculo);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe el espectaculo con ese id: " + idEspectaculo);
        }
        List<ArtistaEntity> artistas = espectaculo.getArtista();
        if (artistas != null) {
            for (ArtistaEntity artista : artistas) {
                list.add(artista);
            }
        }
        return listEntity2DetailDTO(list);
    }

    @GET
    @Path("{idArtista: \\d+}")
    public ArtistaDetailDTO getArtista(@PathParam("idEspectaculo") Long idEspectaculo, @PathParam("idArtista") Long idArtista) throws BusinessLogicException {
        EspectaculoEntity espectaculo = espectaculoLogic.find(idEspectaculo);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe el espectaculo con ese id: " + idEspectaculo);
        }
        ArtistaEntity artista = artistaLogic.find(idArtista);
        if (artista == null) {
            throw new BusinessLogicException("No existe el artista con ese id: " + idArtista);
        }
        List<ArtistaEntity> artistasE=espectaculo.getArtista();
        if(!artistasE.contains(artista)){
            throw new BusinessLogicException("El espectaculo no está asociado al artista con id: " + idArtista);
        }
        return new ArtistaDetailDTO(artista);
    }

    @PUT
    @Path("{idArtista: \\d+}")
    public ArtistaDetailDTO updateEspectaculoArtista(@PathParam("idEspectaculo") Long idEspectaculo, @PathParam("idArtista") Long idArtista, ArtistaDetailDTO artista) throws BusinessLogicException {
        EspectaculoEntity espectaculo = espectaculoLogic.find(idEspectaculo);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe el espectaculo con ese id: " + idEspectaculo);
        }
        if (null == artistaLogic.find(idArtista)) {
            throw new BusinessLogicException("No existe el artista con ese id: " + idArtista);
        }
        ArtistaEntity artistaActualizar = artista.toEntity();
        List<EspectaculoEntity> espectaculos = new ArrayList<>();
        espectaculos.add(espectaculo);
        artistaActualizar.setEspectaculos(espectaculos);
        artistaActualizar.setId(idArtista);
        ArtistaEntity actual = artistaLogic.update(artistaActualizar);
        return new ArtistaDetailDTO(actual);
    }

    @DELETE
    @Path("{idArtista: \\d+}")
    public void deleteEspectaculoArtista(@PathParam("idEspectaculo") Long idEspectaculo, @PathParam("idArtista") Long idArtista) throws BusinessLogicException {
        EspectaculoEntity espectaculo = espectaculoLogic.find(idEspectaculo);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe el espectaculo con ese id: " + idEspectaculo);
        }
        ArtistaEntity artista = artistaLogic.find(idArtista);
        if (artista == null) {
            throw new BusinessLogicException("No existe el artista con ese id: " + idArtista);
        }
        List<EspectaculoEntity> espectaculos = new ArrayList<>();
        espectaculos.add(espectaculo);
        artista.setEspectaculos(espectaculos);
        artistaLogic.delete(artista);
    }

    private List<ArtistaDetailDTO> listEntity2DetailDTO(List<ArtistaEntity> entityList) {
        List<ArtistaDetailDTO> list = new ArrayList<>();
        for (ArtistaEntity entity : entityList) {
            list.add(new ArtistaDetailDTO(entity));
        }
        return list;
    }
}
