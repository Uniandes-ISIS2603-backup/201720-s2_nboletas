/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.dtos.EspectaculoDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.dtos.OrganizadorDTO;
import co.edu.uniandes.theexceptions.nboletas.dtos.OrganizadorDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.ejb.EspectaculoLogic;
import co.edu.uniandes.theexceptions.nboletas.ejb.OrganizadorLogic;
import co.edu.uniandes.theexceptions.nboletas.entities.EspectaculoEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.OrganizadorEntity;
import co.edu.uniandes.theexceptions.nboletas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import java.util.List;
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
@Path("espectaculos/{idEspectaculo: \\d+}/organizadores")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class EspectaculoOrganizadorResources {

    @Inject
    OrganizadorLogic organizadorLogic;

    @Inject
    EspectaculoLogic espectaculoLogic;

    @POST
    public OrganizadorDetailDTO createEspectaculoOrganizador(@PathParam("idEspectaculo") Long idEspectaculo, OrganizadorDTO organizador) throws BusinessLogicException {
        EspectaculoEntity espectaculo = espectaculoLogic.find(idEspectaculo);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe el espectaculo con el id: " + idEspectaculo);
        }
        OrganizadorEntity organizadorE = organizadorLogic.find(organizador.getId());
        List <EspectaculoEntity> lista= new ArrayList<>();
        lista.add(espectaculo);
        organizadorE.setEspectaculos(lista);
        List<OrganizadorEntity> organizadores = espectaculo.getOrganizador();
        organizadores.add(organizadorE);
        espectaculo.setOrganizador(organizadores);
        OrganizadorEntity organizadorCreado = organizadorLogic.create(organizadorE);
        return new OrganizadorDetailDTO(organizadorCreado);
    }

    @GET
    public List<OrganizadorDTO> getOrganizadores(@PathParam("idEspectaculo") Long idEspectaculo) throws BusinessLogicException {
        List<OrganizadorEntity> list = new ArrayList<>();
        EspectaculoEntity espectaculo = espectaculoLogic.find(idEspectaculo);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe el espectaculo con ese id: " + idEspectaculo);
        }
        List<OrganizadorEntity> organizadores = espectaculo.getOrganizador();
        if (organizadores != null) {
            for (OrganizadorEntity organizador : organizadores) {
                list.add(organizador);
            }
        }
        return listEntity2DetailDTO(list);
    }

    @GET
    @Path("{idOrganizador: \\d+}")
    public OrganizadorDTO getOrganizador(@PathParam("idEspectaculo") Long idEspectaculo, @PathParam("idOrganizador") Long idOrganizador) throws BusinessLogicException {
        EspectaculoEntity espectaculo = espectaculoLogic.find(idEspectaculo);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe el espectaculo con ese id: " + idEspectaculo);
        }
        OrganizadorEntity organizador = organizadorLogic.find(idOrganizador);
        if (organizador == null) {
            throw new BusinessLogicException("No existe el organizador con ese id: " + idOrganizador);
        }
        List<OrganizadorEntity> organizadoresE=espectaculo.getOrganizador();
        if(!organizadoresE.contains(organizador)){
            throw new BusinessLogicException("El espectaculo no está asociado al organizador con id: " + idOrganizador);
        }
        return new OrganizadorDetailDTO(organizador);
    }

    @PUT
    @Path("{idOrganizador: \\d+}")
    public OrganizadorDetailDTO updateEspectaculoOrganizador(@PathParam("idEspectaculo") Long idEspectaculo, @PathParam("idOrganizador") Long idOrganizador, OrganizadorDetailDTO organizador) throws BusinessLogicException {
        OrganizadorEntity organizadorE = organizador.toEntity();
        organizadorE.setId(idOrganizador);
        OrganizadorEntity actual = espectaculoLogic.updateEspectaculoOrganizador(idEspectaculo, organizadorE);
        return new OrganizadorDetailDTO(actual);
    }

    
    /** Aca solo llamos a la logica y listo
     * @param idOrganizador
     * @param idEspectaculo
     * @throws co.edu.uniandes.theexceptions.nboletas.exceptions.BusinessLogicException **/
    @DELETE
    @Path("{idOrganizador: \\d+}")
    public void deleteOrganizadorEspectaculo(@PathParam("idOrganizador") Long idOrganizador, @PathParam("idEspectaculo") Long idEspectaculo) throws BusinessLogicException {
        espectaculoLogic.deleterEspectaculoOrganizado(idOrganizador, idEspectaculo);
    }

    private List<OrganizadorDTO> listEntity2DetailDTO(List<OrganizadorEntity> entityList) {
        List<OrganizadorDTO> list = new ArrayList<>();
        for (OrganizadorEntity entity : entityList) {
            list.add(new OrganizadorDetailDTO(entity));
        }
        return list;
    }
}
