/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.dtos.BoletaDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.ejb.UsuarioLogic;
import co.edu.uniandes.theexceptions.nboletas.exceptions.BusinessLogicException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
@Path("/usuarios/{idUsuario: \\d+}/boletas")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class UsuarioBoletaResource {

    @Inject
    private UsuarioLogic usuarioLogic;

    /**
     * GET Retorna todos los objetos Boleta en representación Detail.
     * http://localhost:8080/nboletas-web/api/usuarios/idUsuario/boletas
     * @param idUsuario
     * @return Las boletas del usuario en objetos json DetailDTO.
     * @throws BusinessLogicException
     *  En caso de no cumplir con reglas del negocio.
     */
    @GET
    public List<BoletaDetailDTO> getUsuarioBoletas(@PathParam("idUsuario") Long idUsuario) throws BusinessLogicException {
        return BoletaDetailDTO.listBoletaEntity2BoletaDetailDTO(usuarioLogic.findUsuarioBoletas(idUsuario));
    }

    /**
     * GET Retorna un Objeto de tipo Boleta en representación Detail.
     * http://localhost:8080/nboletas-web/api/usuarios/idUsuario/boletas/idBoleta
     * @param idUsuario
     * @param idBoleta
     * @return una boleta especifica del usuario en objeto json DetailDTO.
     * @throws BusinessLogicException
     *  En caso de no cumplir con reglas del negocio.
     */
    @GET
    @Path("{idBoleta: \\d+}")
    public BoletaDetailDTO getBoletaUsuario(@PathParam("idUsuario") Long idUsuario, @PathParam("idBoleta") Long idBoleta) throws BusinessLogicException {
        return new BoletaDetailDTO(usuarioLogic.findUsuarioBoleta(idUsuario, idBoleta));
    }

    /**
     * POST Encargado de "Comprar" una boleta añadiendola al usuario.
     * http://localhost:8080/nboletas-web/api/usuarios/idUsuario/boletas/idBoleta
     * @param idUsuario
     * @param idBoleta
     * @return la boleta que fue creada para la relacion con el usuario en
     * objeto json DetailDTO.
     * @throws BusinessLogicException
     *  En caso de no cumplir con reglas del negocio.
     */
    @POST
    @Path("{idBoleta: \\d+}")
    public BoletaDetailDTO createBoletaUsuario(@PathParam("idUsuario") Long idUsuario,@PathParam("idBoleta")Long idBoleta) throws BusinessLogicException {
        return new BoletaDetailDTO(usuarioLogic.createUsuarioBoleta(idUsuario, idBoleta));       
    }

}
