/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

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
@Path("artistas/{idArtista: \\d+}/espectaculos")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class ArtistaEspectaculoResources {

    @Inject
    ArtistaLogic artistaLogic;

    @Inject
    EspectaculoLogic espectaculoLogic;

    @POST
    public EspectaculoDetailDTO createArtistaEspectaculo(@PathParam("idArtista") Long idArtista, EspectaculoDetailDTO espectaculo) throws BusinessLogicException {
        ArtistaEntity artista = artistaLogic.find(idArtista);
        if (artista == null) {
            throw new BusinessLogicException("No existe el artista con el id: " + idArtista);
        }
        EspectaculoEntity espectaculoE = espectaculo.toEntity();
        List <ArtistaEntity> lista = new ArrayList<>();
        lista.add(artista);
        espectaculoE.setArtista(lista);
        List<EspectaculoEntity> espectaculos = artista.getEspectaculos();
        espectaculos.add(espectaculoE);
        artista.setEspectaculos(espectaculos);
        EspectaculoEntity espectaculoCreado = espectaculoLogic.create(espectaculoE);
        return new EspectaculoDetailDTO(espectaculoCreado);
    }

    @GET
    public List<EspectaculoDetailDTO> getEspectaculos(@PathParam("idArtista") Long idArtista) throws BusinessLogicException {
        List<EspectaculoEntity> list = new ArrayList<>();
        ArtistaEntity artista = artistaLogic.find(idArtista);
        if (artista == null) {
            throw new BusinessLogicException("No existe el artista con ese id: " + idArtista);
        }
        List<EspectaculoEntity> espectaculos = artista.getEspectaculos();
        espectaculos.size();
        if (espectaculos != null) {
            for (EspectaculoEntity espectaculo : espectaculos) {
                list.add(espectaculo);
            }
        }
        return listEntity2DetailDTO(list);
    }

    @GET
    @Path("{idEspectaculo: \\d+}")
    public EspectaculoDetailDTO getEspectaculo(@PathParam("idArtista") Long idArtista, @PathParam("idEspectaculo") Long idEspectaculo) throws BusinessLogicException {
        ArtistaEntity artista = artistaLogic.find(idArtista);
        if (artista == null) {
            throw new BusinessLogicException("No existe el artista con ese id: " + idArtista);
        }
        EspectaculoEntity espectaculo = espectaculoLogic.find(idEspectaculo);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe el espectaculo con ese id: " + idEspectaculo);
        }
        List<EspectaculoEntity> espectaculosA= artista.getEspectaculos();
        if(!espectaculosA.contains(espectaculo)){
            throw new BusinessLogicException("El artista no está asociado al espectaculo con id: " + idEspectaculo);
        }
        return new EspectaculoDetailDTO(espectaculo);
    }

    @PUT
    @Path("{idEspectaculo: \\d+}")
    public EspectaculoDetailDTO updateArtistaEspectaculo(@PathParam("idArtista") Long idArtista, @PathParam("idEspectaculo") Long idEspectaculo, EspectaculoDetailDTO espectaculo) throws BusinessLogicException {
        ArtistaEntity artista = artistaLogic.find(idArtista);
        if (artista == null) {
            throw new BusinessLogicException("No existe el artista con ese id: " + idArtista);
        }
        if (null == espectaculoLogic.find(idEspectaculo)) {
            throw new BusinessLogicException("No existe el espectaculo con ese id: " + idEspectaculo);
        }
        EspectaculoEntity espectaculoActualizar = espectaculo.toEntity();
        List<ArtistaEntity> artistas = new ArrayList<>();
        artistas.add(artista);
        espectaculoActualizar.setArtista(artistas);
        espectaculoActualizar.setId(idEspectaculo);
        EspectaculoEntity actual = espectaculoLogic.update(espectaculoActualizar);
        return new EspectaculoDetailDTO(actual);
    }

    @DELETE
    @Path("{idEspectaculo: \\d+}")
    public void deleteArtistaEspectaculo(@PathParam("idArtista") Long idArtista, @PathParam("idEspectaculo") Long idEspectaculo) throws BusinessLogicException {
        ArtistaEntity artista = artistaLogic.find(idArtista);
        if (artista == null) {
            throw new BusinessLogicException("No existe el artista con ese id: " + idArtista);
        }
        EspectaculoEntity espectaculo = espectaculoLogic.find(idEspectaculo);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe el espectaculo con ese id: " + idEspectaculo);
        }
        List<ArtistaEntity> artistas = new ArrayList<>();
        artistas.add(artista);
        espectaculo.setArtista(artistas);
        espectaculoLogic.delete(espectaculo);
    }

    private List<EspectaculoDetailDTO> listEntity2DetailDTO(List<EspectaculoEntity> entityList) {
        List<EspectaculoDetailDTO> list = new ArrayList<>();
        for (EspectaculoEntity entity : entityList) {
            list.add(new EspectaculoDetailDTO(entity));
        }
        return list;
    }
}
