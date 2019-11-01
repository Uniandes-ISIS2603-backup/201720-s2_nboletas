/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.dtos.EnvioDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.ejb.BoletaLogic;
import co.edu.uniandes.theexceptions.nboletas.entities.EnvioEntity;
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
@Path("boletas/{idBoleta: \\d+}/envios")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class BoletaEnvioResource {

    @Inject
    BoletaLogic boletaLogic;

    @POST
    public EnvioDetailDTO createBoletaEnvio(@PathParam("idBoleta") Long idBoleta, EnvioDetailDTO envio) throws BusinessLogicException, PersistenceException {
        EnvioEntity envioCreado = boletaLogic.createBoletaEnvio(idBoleta, envio.toEntity());
        return new EnvioDetailDTO(envioCreado);
    }

    @GET
    public List<EnvioDetailDTO> getEnvios(@PathParam("idBoleta") Long idBoleta) throws BusinessLogicException, PersistenceException {
        List<EnvioEntity> envios = boletaLogic.findBoletaEnvios(idBoleta);
        return EnvioDetailDTO.listEnvioEntity2EnvioDetailDTO(envios);
    }

    @GET
    @Path("{idEnvio: \\d+}")
    public EnvioDetailDTO getEnvio(@PathParam("idBoleta") Long idBoleta, @PathParam("idEnvio") Long idEnvio) throws BusinessLogicException, PersistenceException {
        EnvioEntity envio = boletaLogic.findBoletaEnvio(idBoleta, idEnvio);
        return new EnvioDetailDTO(envio);
    }

    @PUT
    @Path("{idEnvio: \\d+}")
    public EnvioDetailDTO updateBoletaEnvio(@PathParam("idBoleta") Long idBoleta, @PathParam("idEnvio") Long idEnvio, EnvioDetailDTO envio) throws BusinessLogicException, PersistenceException {
        EnvioEntity envioE = envio.toEntity();
        envioE.setId(idEnvio);
        EnvioEntity actual = boletaLogic.updateBoletaEnvio(idBoleta, envioE);
        return new EnvioDetailDTO(actual);
    }

    @DELETE
    @Path("{idEnvio: \\d+}")
    public void deleteBoletaEnvio(@PathParam("idBoleta") Long idBoleta, @PathParam("idEnvio") Long idEnvio) throws BusinessLogicException, PersistenceException {
        boletaLogic.deleteBoletaEnvio(idBoleta, idEnvio);
    }

}
