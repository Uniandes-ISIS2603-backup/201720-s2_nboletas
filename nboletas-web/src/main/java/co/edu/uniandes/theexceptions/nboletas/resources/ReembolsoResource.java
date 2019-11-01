/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.dtos.ReembolsoDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.ejb.ReembolsoLogic;
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
 * @author jm.contreras10
 */
@Path("reembolsos")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class ReembolsoResource {

    @Inject
    private ReembolsoLogic logic;

    
    @POST
    public ReembolsoDetailDTO createReembolso(ReembolsoDetailDTO Reembolso) {
        ReembolsoEntity reembolso = logic.create(Reembolso.toEntity());
        return new ReembolsoDetailDTO(reembolso);
    }
    
    /**
     * GET Retorna una colección de objetos Reembolso en representación Detail.
     * http://localhost:8080/nboletas-web/api/reembolsos
     *
     * @return la lista de todos los reembolsos en objetos json detail DTO.
     * @throws BusinessLogicException
     *  Cuando se incumplen las reglas del negocio.
     */
    @GET
    public List<ReembolsoDetailDTO> getReembolsos() throws BusinessLogicException{
        return ReembolsoDetailDTO.listReembolsoEntity2ReembolsoDetailDTO(logic.findAll());
    }

    /**
     * GET Retorna todos los objetos Reembolso en representación Detail.
     * http://localhost:8080/nboletas-web/api/reembolsos/id
     *
     * @param idReembolso
     * @return la lista de todos los Reembolsos en objetos json detail DTO.
     * @throws BusinessLogicException
     *  Cuando se incumplen las reglas del negocio.
     */
    @GET
    @Path("{idReembolso: \\d+}")
    public ReembolsoDetailDTO getReembolso(@PathParam("idReembolso") Long idReembolso) throws BusinessLogicException {
        ReembolsoEntity reembolso = logic.find(idReembolso);
        if (reembolso == null) {
            throw new BusinessLogicException("No se encontra el reembolso con el id: " + idReembolso);
        }
        return new ReembolsoDetailDTO(reembolso);
    }

    /**
     * PUT Es el encargado de actualizar objetos Reembolso.
     * http://localhost:8080/nboletas-web/api/reembolsos/id
     *
     * @param idReembolso
     * @param reembolso
     * @return El Reembolso actualizado.
     * @throws BusinessLogicException
     *  Cuando se incumplen las reglas del negocio.
     */
    @PUT
    @Path("{idReembolso: \\d+}")
    public ReembolsoDetailDTO updateReembolso(@PathParam("idReembolso") Long idReembolso, ReembolsoDetailDTO reembolso) throws BusinessLogicException, PersistenceException {
        reembolso.setId(idReembolso);
        if (null == logic.find(idReembolso)) {
            throw new BusinessLogicException("No existe el reembolso con el id: " + idReembolso);
        }
        ReembolsoEntity reembolsoActualizado = logic.update(reembolso.toEntity());
        return (new ReembolsoDetailDTO(reembolsoActualizado));
    }

    /**
     * DELETE Elimina un objeto Reembolso.
     * http://localhost:8080/nboletas-web/api/reembolso/id
     *
     * @param idReembolso
     * @throws BusinessLogicException
     *  Cuando se incumplen las reglas del negocio.
     */
    @DELETE
    @Path("{idReembolso: \\d+}")
    public void deleteReembolso(@PathParam("idReembolso") Long idReembolso) throws BusinessLogicException, PersistenceException {
        logic.delete(logic.find(idReembolso));
    }


}
