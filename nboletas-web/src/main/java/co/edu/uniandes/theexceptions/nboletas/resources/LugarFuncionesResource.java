/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.dtos.BoletaDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.dtos.FuncionDTO;
import co.edu.uniandes.theexceptions.nboletas.dtos.FuncionDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.dtos.LugarDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.ejb.BoletaLogic;
import co.edu.uniandes.theexceptions.nboletas.ejb.FuncionLogic;
import co.edu.uniandes.theexceptions.nboletas.ejb.LugarLogic;
import co.edu.uniandes.theexceptions.nboletas.entities.BoletaEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.FuncionEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.LugarEntity;
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
 * @author ja.gomez1
 */
@Path("/lugares/{idLugar: \\d+}/funciones")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class LugarFuncionesResource {

    @Inject
    private LugarLogic lugarLogic;

    /**
     * GET para las funciones de un lugar especifico.
     * http://localhost:8080/nboletas-web/api/lugares/idLugar/funciones
     *
     * @return las funciones del lugar en objetos json DTO.
     * @throws BusinessLogicException
     *
     * En caso de no existir el lugar con el id se retorna un 404 not found.
     */
    @GET
    public List<FuncionDetailDTO> getFuncionesLugar(@PathParam("idLugar") Long id) throws BusinessLogicException {
        List<FuncionEntity> getFunciones = lugarLogic.getFuncionesLugar(id);
        return FuncionDetailDTO.listFuncionEntity2DetailDTO(getFunciones);
    }

    /**
     * GET para una funcion especifica de un lugar especifico.
     * http://localhost:8080/nboletas-web/api/lugares/idLugar/funciones/idFuncion
     *
     * @return una funcion especifica del lugar en objeto json DTO.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Funcion se retorna un 404 not found.
     *
     * En caso de no existir el id del Lugar se retorna un 404 not found.
     */
    @GET
    @Path("{idFuncion: \\d+}")
    public FuncionDetailDTO getFuncionLugar(@PathParam("idLugar") Long id, @PathParam("idFuncion") Long idFuncion) throws BusinessLogicException {
        FuncionEntity getFuncion = lugarLogic.getFuncionLugar(id, idFuncion);
        return new FuncionDetailDTO(getFuncion);
    }

    /**
     * POST para crear una relacion lugar funcion.
     * http://localhost:8080/nboletas-web/api/lugares/idLugar/funciones
     *
     * @param Funcion correponde a la representación java del objeto json
     * enviado en el llamado, para agregar la relacion al lugar.
     * @return la funcion que fue creada para la relacion con el lugar en objeto
     * json DTO.
     * @throws BusinessLogicExceptionfu
     *
     * En caso de no existir el id del Lugar se retorna un 404 not found.
     */
    @POST
    public FuncionDetailDTO createFuncionLugar(@PathParam("idLugar") Long id, FuncionDetailDTO funcion) throws BusinessLogicException {
        FuncionEntity createFuncion = lugarLogic.createFuncionLugar(id, funcion.toEntity());
        return new FuncionDetailDTO(createFuncion);
    }

    /**
     * PUT para crear una relacion funcion lugar con uno ya existente en el
     * sistema.
     * http://localhost:8080/nboletas-web/api/lugares/idLugar/funciones/idFuncion
     *
     * @return la funcion que fue creada para la relacion con el lugar en objeto
     * json DTO.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Funcion se retorna un 404 not found.
     *
     * En caso de no existir un Lugar con el id por parametro se returna un 404
     * not found.
     */
    @PUT
    @Path("{idFuncion: \\d+}")
    public FuncionDetailDTO updateFuncionLugar(@PathParam("idLugar") Long id, @PathParam("idFuncion") Long idFuncion) throws BusinessLogicException {
        FuncionEntity updateFuncion = lugarLogic.updateFuncionLugar(id, idFuncion);
        return new FuncionDetailDTO(updateFuncion);
    }

    /**
     * DELETE para eliminar una relacion funcion-lugar ya existente en el
     * sistema.
     * http://localhost:8080/nboletas-web/api/lugares/idLugar/funciones/idFuncion
     *
     * @return la funcion que fue creada para la relacion con el lugar en objeto
     * json DTO.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Funcion se retorna un 404 not found.
     *
     * En caso de no existir el id del Lugar se retorna un 404 not found.
     */
    @DELETE
    @Path("{idFuncion: \\d+}")
    public void deleteFuncionLugar(@PathParam("idLugar") Long id, @PathParam("idFuncion") Long idFuncion) throws BusinessLogicException {
        lugarLogic.deleteFuncionLugar(id, idFuncion);
    }
}
