/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.dtos.ReembolsoDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.ejb.UsuarioLogic;
import co.edu.uniandes.theexceptions.nboletas.entities.ReembolsoEntity;
import co.edu.uniandes.theexceptions.nboletas.exceptions.BusinessLogicException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author jm.contreras10
 */
@Path("/usuarios/{idUsuario: \\d+}/reembolsos")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class UsuarioReembolsoResource {

    @Inject
    private UsuarioLogic usuarioLogic;

    /**
     * GET Retorna todos los objetos reembolsos en representación Detail.
     * http://localhost:8080/nboletas-web/api/usuarios/idUsuario/reembolsos
     *
     * @param idUsuario
     * @return los reembolsos del usuario en objetos json DTO.
     * @throws BusinessLogicException
     *  En caso de no cumplir con reglas del negocio.
     */
    @GET
    public List<ReembolsoDetailDTO> getUsuarioReembolsos(@PathParam("idUsuario") Long idUsuario) throws BusinessLogicException {
        List<ReembolsoEntity> reembolsosEntity = usuarioLogic.findUsuarioReembolsos(idUsuario);
        List<ReembolsoDetailDTO> reembolsos = ReembolsoDetailDTO.listReembolsoEntity2ReembolsoDetailDTO(reembolsosEntity);
        return reembolsos;
    }

    /**
     * GET Retorna un Objeto de tipo Reembolso en representación Detail.
     * http://localhost:8080/nboletas-web/api/usuarios/idUsuario/reembolsos/idReembolso
     *
     * @param idUsuario
     * @param idReembolso
     * @return un reembolso especifico del usuario en objeto json DTO.
     * @throws BusinessLogicException
     *  En caso de no cumplir con reglas del negocio.
     */
    @GET
    @Path("{idReembolso: \\d+}")
    public ReembolsoDetailDTO getUsuarioReembolso(@PathParam("idUsuario") Long idUsuario, @PathParam("idReembolso") Long idReembolso) throws BusinessLogicException {
        return new ReembolsoDetailDTO(usuarioLogic.findUsuarioReembolso(idUsuario, idReembolso));
    }

    /**
     * POST Crea un objeto de tipo reembolso ya relacionado con el usuario de id
     * dado.
     * http://localhost:8080/nboletas-web/api/usuarios/idUsuario/reembolsos
     *
     * @param idUsuario
     * @param reembolso correponde a la representación java del objeto json
     * enviado en el llamado, para agregar la relacion al usuario.
     * @return el reembolso que fue creado para la relacion con el usuario en
     * objeto json DTO.
     * @throws BusinessLogicException
     *  En caso de no cumplir con reglas del negocio.
     *
     * En caso de no existir el id del usuario se retorna un 404 not found.
     */
    @POST
    public ReembolsoDetailDTO createUsuarioReembolso(@PathParam("idUsuario") Long idUsuario, ReembolsoDetailDTO reembolso) throws BusinessLogicException, PersistenceException {
        return new ReembolsoDetailDTO(usuarioLogic.createUsuarioReembolso(idUsuario, reembolso.toEntity()));
    }
    
    //El reembolso no deberia tener un metodo de actualizacion, ya que el cliente no deberia poder modificar este.
    //El reembolso no deberia tener un metodo de eliminadion, ya que el cliente no deberia poder modificar este.
}
