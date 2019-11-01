/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.dtos.SillaDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.ejb.*;
import co.edu.uniandes.theexceptions.nboletas.entities.SillaEntity;
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
 * @author fc.alvarez10
 */
@Path("divisiones/{divisionesid: \\d+}/sillas")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class DivisionDeLugarSillaResource {

    @Inject
    DivisionDeLugarLogic divisionLogic;

    @POST
    public SillaDetailDTO createDivisionSilla(@PathParam("divisionesid") Long idDivision, SillaDetailDTO silla) throws BusinessLogicException {
        SillaEntity sillaCreada=divisionLogic.createDivisionSilla(idDivision, silla.toEntity());
        return new SillaDetailDTO(sillaCreada);
    }

    @GET
    public List<SillaDetailDTO> getSillas(@PathParam("divisionesid") Long idDivision) throws BusinessLogicException {
        List<SillaEntity> list=divisionLogic.findDivisionSillas(idDivision);
        return SillaDetailDTO.listSillaEntity2SillaDetailDTO(list);
    }

    @GET
    @Path("{sillasid: \\d+}")
    public SillaDetailDTO getSilla(@PathParam("divisionesid") Long idDivision, @PathParam("sillasid") Long idSilla) throws BusinessLogicException {
        SillaEntity silla= divisionLogic.findDivisionSilla(idDivision, idSilla);
        return new SillaDetailDTO(silla);
    }

    @PUT
    @Path("{sillasid: \\d+}")
    public SillaDetailDTO updateDivisionSilla(@PathParam("divisionesid") Long idDivision, @PathParam("sillasid") Long idSilla, SillaDetailDTO silla) throws BusinessLogicException {
        SillaEntity sillaE=silla.toEntity();
        sillaE.setId(idSilla);
        SillaEntity actual=divisionLogic.updateDivisionSilla(idDivision, sillaE);
        return new SillaDetailDTO(actual);
    }

    @DELETE
    @Path("{sillasid: \\d+}")
    public void deleteDivisionSilla(@PathParam("divisionesid") Long idDivision, @PathParam("sillasid") Long idSilla) throws BusinessLogicException {
        divisionLogic.deleteDivisionSilla(idDivision, idSilla);
    }

    
}
