/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.dtos.ReembolsoDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.ejb.BoletaLogic;
import co.edu.uniandes.theexceptions.nboletas.entities.ReembolsoEntity;
import co.edu.uniandes.theexceptions.nboletas.exceptions.BusinessLogicException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
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
 * @author df.riveros11
 */
@Path("boletas/{idBoleta: \\d+}/reembolsos")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class BoletaReembolsoResource {

    @Inject
    BoletaLogic boletaLogic;

    @POST
    public ReembolsoDetailDTO createBoletaReembolso(@PathParam("idBoleta") Long idBoleta, ReembolsoDetailDTO reembolso) throws BusinessLogicException, PersistenceException {
        ReembolsoEntity reembolsoCreado = boletaLogic.createBoletaReembolso(idBoleta, reembolso.toEntity());
        return new ReembolsoDetailDTO(reembolsoCreado);
    }

    @GET
    public List<ReembolsoDetailDTO> getReembolsos(@PathParam("idBoleta") Long idBoleta) throws BusinessLogicException, PersistenceException {
        List<ReembolsoEntity> list = boletaLogic.findBoletaReembolsos(idBoleta);
        return ReembolsoDetailDTO.listReembolsoEntity2ReembolsoDetailDTO(list);
    }

    @GET
    @Path("{idReembolso: \\d+}")
    public ReembolsoDetailDTO getReembolso(@PathParam("idBoleta") Long idBoleta, @PathParam("idReembolso") Long idReembolso) throws BusinessLogicException, PersistenceException {
        ReembolsoEntity reembolso = boletaLogic.findBoletaReembolso(idBoleta, idReembolso);
        return new ReembolsoDetailDTO(reembolso);
    }

    @PUT
    @Path("{idReembolso: \\d+}")
    public ReembolsoDetailDTO updateBoletaReembolso(@PathParam("idBoleta") Long idBoleta, @PathParam("idReembolso") Long idReembolso, ReembolsoDetailDTO reembolso) throws BusinessLogicException, PersistenceException {
        ReembolsoEntity reembolsoE = reembolso.toEntity();
        reembolsoE.setId(idReembolso);
        ReembolsoEntity actual = boletaLogic.updateBoletaReembolso(idBoleta, reembolsoE);
        return new ReembolsoDetailDTO(actual);
    }

    @DELETE
    @Path("{idReembolso: \\d+}")
    public void deleteBoletReembolso(@PathParam("idBoleta") Long idBoleta, @PathParam("idReembolso") Long idReembolso) throws BusinessLogicException, PersistenceException {
        boletaLogic.deleteBoletaReembolso(idBoleta, idReembolso);
    }
}
