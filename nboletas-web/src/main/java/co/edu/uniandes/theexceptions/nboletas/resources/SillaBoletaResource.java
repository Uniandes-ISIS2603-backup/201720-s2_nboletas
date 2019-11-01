/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.dtos.BoletaDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.ejb.*;
import co.edu.uniandes.theexceptions.nboletas.entities.BoletaEntity;
import co.edu.uniandes.theexceptions.nboletas.exceptions.BusinessLogicException;
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
@Path("sillas/{sillasid: \\d+}/boletas")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class SillaBoletaResource {

    @Inject
    SillaLogic sillaLogic;

    @POST
    public BoletaDetailDTO createSillaBoleta(@PathParam("sillasid") Long idSilla, BoletaDetailDTO boleta) throws BusinessLogicException {
        BoletaEntity boletaCreada=sillaLogic.createSillaBoleta(idSilla, boleta.toEntity());
        return new BoletaDetailDTO(boletaCreada);
    }

    @GET
    public List<BoletaDetailDTO> getBoletas(@PathParam("sillasid") Long idSilla) throws BusinessLogicException {
        List<BoletaEntity> list=sillaLogic.findSillaBoletas(idSilla);
        return BoletaDetailDTO.listBoletaEntity2BoletaDetailDTO(list);
    }

    @GET
    @Path("{boletasid: \\d+}")
    public BoletaDetailDTO getBoleta(@PathParam("sillasid") Long idSilla, @PathParam("boletasid") Long idBoleta) throws BusinessLogicException {
        BoletaEntity boletaE=sillaLogic.findSillaBoleta(idSilla, idBoleta);
        return new BoletaDetailDTO(boletaE);
    }

    @PUT
    @Path("{boletasid: \\d+}")
    public BoletaDetailDTO updateSillaBoleta(@PathParam("sillasid") Long idSilla, @PathParam("boletasid") Long idBoleta, BoletaDetailDTO boleta) throws BusinessLogicException {
        BoletaEntity boletaE=boleta.toEntity();
        boletaE.setId(idBoleta);
        BoletaEntity actual=sillaLogic.updateSillaBoleta(idSilla, boletaE);
        return new BoletaDetailDTO(actual);
    }

    @DELETE
    @Path("{boletasid: \\d+}")
    public void deleteSillaBoleta(@PathParam("sillasid") Long idSilla, @PathParam("boletasid") Long idBoleta) throws BusinessLogicException {
        sillaLogic.deleteSillaBoleta(idSilla, idBoleta);
    }

}
