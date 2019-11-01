/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.dtos.EspectaculoDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.ejb.OrganizadorLogic;
import co.edu.uniandes.theexceptions.nboletas.entities.EspectaculoEntity;
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
 * @author df.riveros11
 */
@Path("organizadores/{idOrganizador: \\d+}/espectaculos")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class OrganizadorEspectaculoResources {

    @Inject
    OrganizadorLogic organizadorLogic;

    @POST
    public EspectaculoDetailDTO createOrganizadorEspectaculo(@PathParam("idOrganizador") Long idOrganizador, EspectaculoDetailDTO espectaculo) throws BusinessLogicException {
        EspectaculoEntity espectaculoCreado = organizadorLogic.createOrganizadorEspectaculo(idOrganizador, espectaculo.toEntity());
        return new EspectaculoDetailDTO(espectaculoCreado);
    }

    @GET
    public List<EspectaculoDetailDTO> getEspectaculos(@PathParam("idOrganizador") Long idOrganizador) throws BusinessLogicException {
        List<EspectaculoEntity> list = organizadorLogic.findOrganizadoresEspectaculos(idOrganizador);
        return EspectaculoDetailDTO.listEspectaculoEntity2EspectaculoDetailDTO(list);
    }

    @GET
    @Path("{idEspectaculo: \\d+}")
    public EspectaculoDetailDTO getEspectaculo(@PathParam("idOrganizador") Long idOrganizador, @PathParam("idEspectaculo") Long idEspectaculo) throws BusinessLogicException {
        EspectaculoEntity espectaculo = organizadorLogic.findOrganizadorEspectaculo(idOrganizador, idEspectaculo);
        return new EspectaculoDetailDTO(espectaculo);
    }

    @PUT
    @Path("{idEspectaculo: \\d+}")
    public EspectaculoDetailDTO updateOrganizadorEspectaculo(@PathParam("idOrganizador") Long idOrganizador, @PathParam("idEspectaculo") Long idEspectaculo, EspectaculoDetailDTO espectaculo) throws BusinessLogicException {
        EspectaculoEntity espectaculoE = espectaculo.toEntity();
        espectaculoE.setId(idEspectaculo);
        EspectaculoEntity actual = organizadorLogic.updateOrganizadorEspectaculo(idOrganizador, espectaculoE);
        return new EspectaculoDetailDTO(actual);
    }

    @DELETE
    public void deleteOrganizadorEspectaculos(@PathParam("idOrganizador") Long idOrganizador, List<EspectaculoDetailDTO> espectaculos) throws BusinessLogicException {
        organizadorLogic.deleteOrganizadorEspectaculos(idOrganizador, EspectaculoDetailDTO.listEspectaculoDetailDto2EspectaculoEntity(espectaculos));
    }
    
    @DELETE
    @Path("{idEspectaculo: \\d+}")
    public void deleteOrganizadorEspectaculo(@PathParam("idOrganizador") Long idOrganizador, @PathParam("idEspectaculo") Long idEspectaculo) throws BusinessLogicException {
        organizadorLogic.deleteOrganizadorEspectaculo(idOrganizador, idEspectaculo);
    }
}
