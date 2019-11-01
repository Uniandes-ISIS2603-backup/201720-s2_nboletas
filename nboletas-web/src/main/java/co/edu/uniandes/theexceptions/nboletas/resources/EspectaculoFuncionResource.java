/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.dtos.FuncionDetailDTO;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import co.edu.uniandes.theexceptions.nboletas.entities.EspectaculoEntity;
import co.edu.uniandes.theexceptions.nboletas.ejb.EspectaculoLogic;
import co.edu.uniandes.theexceptions.nboletas.entities.FuncionEntity;
import co.edu.uniandes.theexceptions.nboletas.ejb.FuncionLogic;
import co.edu.uniandes.theexceptions.nboletas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.PathParam;

/**
 *
 * @author jf.ramos
 */
@Path("espectaculos/{idEspectaculo: \\d+}/funciones")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class EspectaculoFuncionResource {

    @Inject
    EspectaculoLogic espectaculoLogic;

    @Inject
    FuncionLogic funcionLogic;

    @POST
    public FuncionDetailDTO createEspectaculoFuncion(@PathParam("idEspectaculo") Long idEspectaculo, FuncionDetailDTO funcion) throws BusinessLogicException {
        EspectaculoEntity espectaculo = espectaculoLogic.find(idEspectaculo);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe el espectaculo con el id: " + idEspectaculo);
        }
        FuncionEntity funcionEntity = funcion.toEntity();
        funcionEntity.setEspectaculo(espectaculo);
        FuncionEntity funcionCreada = funcionLogic.create(funcionEntity);
        return new FuncionDetailDTO(funcionCreada);
    }

    /**
     *
     * @param idEspectaculo
     * @return
     * @throws BusinessLogicException
     */
    @GET
    public List<FuncionDetailDTO> getEspectaculoFunciones(@PathParam("idEspectaculo") Long idEspectaculo) throws BusinessLogicException {
        List<FuncionEntity> list = new ArrayList<>();
        EspectaculoEntity espectaculo = espectaculoLogic.find(idEspectaculo);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe el espectaculo con ese id: " + idEspectaculo);
        }
        List<FuncionEntity> funcionesE = espectaculo.getFunciones();
        if (funcionesE != null) {
            for (FuncionEntity funcionEntity : funcionesE) {
                list.add(funcionEntity);
            }
        }
        return listEntity2DetailDTO(list);
    }

    @GET
    @Path("{idFuncion: \\d+}")
    public FuncionDetailDTO getEspectaculoFuncion(@PathParam("idEspectaculo") Long idEspectaculo, @PathParam("idFuncion") Long idFuncion) throws BusinessLogicException {
        EspectaculoEntity espectaculo = espectaculoLogic.find(idEspectaculo);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe la espectaculo con ese id: " + idEspectaculo);
        }
        FuncionEntity funcion = funcionLogic.find(idFuncion);
        if (funcion == null) {
            throw new BusinessLogicException("No existe la silla con ese id: " + idFuncion);
        }
        funcion.setEspectaculo(espectaculo);
        return new FuncionDetailDTO(funcion);
    }

    @PUT
    @Path("{idFuncion: \\d+}")
    public FuncionDetailDTO updateEspectaculoFuncion(@PathParam("idEspectaculo") Long idEspectaculo, @PathParam("idFuncion") Long idFuncion, FuncionDetailDTO funcion) throws BusinessLogicException {
        EspectaculoEntity espectaculo = espectaculoLogic.find(idEspectaculo);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe el espectaculo con ese id: " + idEspectaculo);
        }
        if (null == funcionLogic.find(idFuncion)) {
            throw new BusinessLogicException("No existe la funcion con ese id: " + idFuncion);
        }
        FuncionEntity funcionActualizar = funcion.toEntity();
        funcionActualizar.setEspectaculo(espectaculo);
        funcionActualizar.setId(idFuncion);
        FuncionEntity actual = funcionLogic.update(funcionActualizar);
        return new FuncionDetailDTO(actual);
    }

    @DELETE
    @Path("{idFuncion: \\d+}")
    public void deleteEspectaculoFuncion(@PathParam("idEspectaculo") Long idEspectaculo, @PathParam("idFuncion") Long idFuncion) throws BusinessLogicException {
        EspectaculoEntity espectaculo = espectaculoLogic.find(idEspectaculo);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe el espectaculo con ese id: " + idEspectaculo);
        }
        FuncionEntity funcion = funcionLogic.find(idFuncion);
        if (funcion == null) {
            throw new BusinessLogicException("No existe la funcion con ese id: " + idFuncion);
        }
        funcion.setEspectaculo(espectaculo);
        funcionLogic.delete(funcion);
    }

    private List<FuncionDetailDTO> listEntity2DetailDTO(List<FuncionEntity> entityList) {
        List<FuncionDetailDTO> list = new ArrayList<>();
        for (FuncionEntity entity : entityList) {
            list.add(new FuncionDetailDTO(entity));
        }
        return list;
    }

}
