/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.dtos.ComentarioDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.ejb.BoletaLogic;
import co.edu.uniandes.theexceptions.nboletas.entities.ComentarioEntity;
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
 * @author am.valero10
 */
@Path("/boletas/{idBoleta: \\d+}/comentarios")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class BoletaComentarioResource {

    @Inject
    BoletaLogic boletaLogic;


    @POST
    public ComentarioDetailDTO createBoletaComentario(@PathParam("idBoleta") Long idBoleta, ComentarioDetailDTO comentario) throws BusinessLogicException, PersistenceException {
        ComentarioEntity comentarioCreado = boletaLogic.createBoletaComentario(idBoleta, comentario.toEntity());
        return new ComentarioDetailDTO(comentarioCreado);
    }

    @GET
    public List<ComentarioDetailDTO> getComentarios(@PathParam("idBoleta") Long idBoleta) throws BusinessLogicException, PersistenceException {
        List<ComentarioEntity> list = boletaLogic.findBoletaComentarios(idBoleta);
        return ComentarioDetailDTO.listComentarioEntity2ComentarioDetailDTO(list);
    }

    @GET
    @Path("{idComentario: \\d+}")
    public ComentarioDetailDTO getComentario(@PathParam("idBoleta") Long idBoleta, @PathParam("idComentario") Long idComentario) throws BusinessLogicException, PersistenceException {
        ComentarioEntity comentario = boletaLogic.findBoletaComentarios(idBoleta, idComentario);
        return new ComentarioDetailDTO(comentario);
    }

    @PUT
    @Path("{idComentario: \\d+}")
    public ComentarioDetailDTO updateBoletaComentario(@PathParam("idBoleta") Long idBoleta, @PathParam("idComentario") Long idComentario, ComentarioDetailDTO comentario) throws BusinessLogicException, PersistenceException {
        ComentarioEntity comentarioA = comentario.toEntity();
        comentarioA.setId(idComentario);
        ComentarioEntity actual = boletaLogic.updateBoletaComentario(idBoleta, comentarioA);
        return new ComentarioDetailDTO(actual);
    }

    @DELETE
    @Path("{idComentario: \\d+}")
    public void deleteBoletaComenario(@PathParam("idBoleta") Long idBoleta, @PathParam("idComentario") Long idComentario) throws BusinessLogicException, PersistenceException {
        boletaLogic.deleteBoletaComentario(idBoleta, idComentario);
    }
}
