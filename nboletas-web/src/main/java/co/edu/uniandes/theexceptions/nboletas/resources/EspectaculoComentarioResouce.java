/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.dtos.ComentarioDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.ejb.ComentarioLogic;
import co.edu.uniandes.theexceptions.nboletas.ejb.EspectaculoLogic;
import co.edu.uniandes.theexceptions.nboletas.entities.ComentarioEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.EspectaculoEntity;
import co.edu.uniandes.theexceptions.nboletas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author jf.ramos
 */
@Path("espectaculos/{idEspectaculo: \\d+}/comentarios")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class EspectaculoComentarioResouce {

    @Inject
    EspectaculoLogic espectaculoLogic;

    @Inject
    ComentarioLogic comentarioLogic;

    @POST
    public ComentarioDetailDTO createEspectaculoComentario(@PathParam("idEspectaculo") Long idEspectaculo, ComentarioDetailDTO comentario) throws BusinessLogicException {
        EspectaculoEntity espectaculo = espectaculoLogic.find(idEspectaculo);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe el espectaculo con el id: " + idEspectaculo);
        }
        ComentarioEntity comentarioEntity = comentario.toEntity();
        comentarioEntity.setEspectaculo(espectaculo);
        ComentarioEntity comentarioCreado = comentarioLogic.create(comentarioEntity);
        return new ComentarioDetailDTO(comentarioCreado);
    }

    @GET
    public List<ComentarioDetailDTO> getEspectaculoComentarios(@PathParam("idEspectaculo") Long idEspectaculo) throws BusinessLogicException {
        List<ComentarioEntity> list = new ArrayList<>();
        EspectaculoEntity espectaculo = espectaculoLogic.find(idEspectaculo);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe el espectaculo con ese id: " + idEspectaculo);
        }
        List<ComentarioEntity> comentarios = espectaculo.getComentarios();
        if (comentarios != null) {
            for (ComentarioEntity comentarioEntity : comentarios) {
                list.add(comentarioEntity);
            }
        }
        return listEntity2DetailDTO(list);
    }

    @GET
    @Path("{idComentario: \\d+}")
    public ComentarioDetailDTO getEspectaculoComentario(@PathParam("idEspectaculo") Long idEspectaculo, @PathParam("idComentario") Long idComentario) throws BusinessLogicException {
        EspectaculoEntity espectaculo = espectaculoLogic.find(idEspectaculo);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe el espectaculo con ese id: " + idEspectaculo);
        }
        ComentarioEntity comentario = comentarioLogic.find(idComentario);
        if (comentario == null) {
            throw new BusinessLogicException("No existe el comentario con ese id: " + idComentario);
        }
        comentario.setEspectaculo(espectaculo);
        return new ComentarioDetailDTO(comentario);
    }

    @PUT
    @Path("{idComentario: \\d+}")
    public ComentarioDetailDTO updateEspectaculoComentario(@PathParam("idEspectaculo") Long idEspectaculo, @PathParam("idComentario") Long idComentario, ComentarioDetailDTO comentario) throws BusinessLogicException {
        EspectaculoEntity espectaculo = espectaculoLogic.find(idEspectaculo);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe el espectaculo con ese id: " + idEspectaculo);
        }
        if (null == comentarioLogic.find(idComentario)) {
            throw new BusinessLogicException("No existe el comentario con ese id: " + idComentario);
        }
        ComentarioEntity comentarioActualizar = comentario.toEntity();
        comentarioActualizar.setEspectaculo(espectaculo);
        comentarioActualizar.setId(idComentario);
        ComentarioEntity actual = comentarioLogic.update(comentarioActualizar);
        return new ComentarioDetailDTO(actual);
    }

    @DELETE
    @Path("{idComentario: \\d+}")
    public void deleteEspectaculoComentario(@PathParam("idEspectaculo") Long idEspectaculo, @PathParam("idComentario") Long idComentario) throws BusinessLogicException {
        EspectaculoEntity espectaculo = espectaculoLogic.find(idEspectaculo);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe el espectaculo con ese id: " + idEspectaculo);
        }
        ComentarioEntity comentario = comentarioLogic.find(idComentario);
        if (comentario == null) {
            throw new BusinessLogicException("No existe el comentario con ese id: " + idComentario);
        }
        comentario.setEspectaculo(espectaculo);
        comentarioLogic.delete(comentario);
    }

    private List<ComentarioDetailDTO> listEntity2DetailDTO(List<ComentarioEntity> entityList) {
        List<ComentarioDetailDTO> list = new ArrayList<>();
        for (ComentarioEntity entity : entityList) {
            list.add(new ComentarioDetailDTO(entity));
        }
        return list;
    }
}
