/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.dtos.BoletaDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.dtos.DivisionDeLugarDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.dtos.FuncionDTO;
import co.edu.uniandes.theexceptions.nboletas.dtos.FuncionDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.dtos.LugarDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.ejb.BoletaLogic;
import co.edu.uniandes.theexceptions.nboletas.ejb.DivisionDeLugarLogic;
import co.edu.uniandes.theexceptions.nboletas.ejb.FuncionLogic;
import co.edu.uniandes.theexceptions.nboletas.ejb.LugarLogic;
import co.edu.uniandes.theexceptions.nboletas.entities.BoletaEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.DivisionDeLugarEntity;
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
@Path("/lugares/{idLugar: \\d+}/divisiones")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class LugarDivisionesDeLugarResource {

    @Inject
    private DivisionDeLugarLogic divisionLogic;

    @Inject
    private LugarLogic lugarLogic;

    /**
     * GET para las divisiones de un lugar especifico.
     * http://localhost:8080/nboletas-web/api/lugares/idLugar/divisiones
     *
     * @return las divisiones del lugar en objetos json DTO.
     * @throws BusinessLogicException
     *
     * En caso de no existir el lugar con el id se retorna un 404 not found.
     */
    @GET
    public List<DivisionDeLugarDetailDTO> getDivisionesLugar(@PathParam("idLugar") Long id) throws BusinessLogicException {
        LugarEntity l = lugarLogic.find(id);
        if (l == null) {
            throw new BusinessLogicException("No existe lugar con el id " + id);
        }
        List<DivisionDeLugarDetailDTO> list = listEntity2DetailDTO(l.getDivisiones());
        return list;
    }

    /**
     * GET para una division especifica de un lugar especifico.
     * http://localhost:8080/nboletas-web/api/lugares/idLugar/divisiones/idDivision
     *
     * @return una division especifica del lugar en objeto json DTO.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Division se retorna un 404 not found.
     *
     * En caso de no existir el id del Lugar se retorna un 404 not found.
     */
    @GET
    @Path("{idDivision: \\d+}")
    public DivisionDeLugarDetailDTO getDivisionLugar(@PathParam("idLugar") Long id, @PathParam("idDivision") Long idDivision) throws BusinessLogicException {
        LugarEntity l = lugarLogic.find(id);
        if (l == null) {
            throw new BusinessLogicException("No existe lugar con el id " + id);
        }

        List<DivisionDeLugarEntity> divisiones = l.getDivisiones();
        DivisionDeLugarEntity division = null;
        for (DivisionDeLugarEntity d : divisiones) {
            if (d.getId().equals(idDivision)) {
                division = d;
            }
        }

        if (division == null) {
            throw new BusinessLogicException("No existe division con el id " + idDivision + " relacionada "
                    + "con el lugar de id " + id);
        }
        return new DivisionDeLugarDetailDTO(division);
    }

    /**
     * POST para crear una relacion lugar division.
     * http://localhost:8080/nboletas-web/api/lugares/idLugar/divisiones
     *
     * @param Division correponde a la representación java del objeto json
     * enviado en el llamado, para agregar la relacion al lugar.
     * @return la division que fue creada para la relacion con el lugar en
     * objeto json DTO.
     * @throws BusinessLogicExceptionfu
     *
     * En caso de no existir el id del Lugar se retorna un 404 not found.
     */
    @POST
    public DivisionDeLugarDetailDTO createDivisionLugar(@PathParam("idLugar") Long id, DivisionDeLugarDetailDTO division) throws BusinessLogicException {
        LugarEntity l = lugarLogic.find(id);
        if (l == null) {
            throw new BusinessLogicException("No existe lugar con el id " + id);
        }

        DivisionDeLugarEntity d = division.toEntity();
        d.setLugar(l);
        d = divisionLogic.create(d);
        return new DivisionDeLugarDetailDTO(d);
    }

    /**
     * PUT para crear una relacion division lugar con uno ya existente en el
     * sistema.
     * http://localhost:8080/nboletas-web/api/lugares/idLugar/divisiones/idDivision
     *
     * @return la division que fue creada para la relacion con el lugar en
     * objeto json DTO.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la division se retorna un 404 not found.
     *
     * En caso de no existir un Lugar con el id por parametro se returna un 404
     * not found.
     */
    @PUT
    @Path("{idDivision: \\d+}")
    public DivisionDeLugarDetailDTO updateDivisionLugar(@PathParam("idLugar") Long id, @PathParam("idDivision") Long idDivision) throws BusinessLogicException {
        LugarEntity l = lugarLogic.find(id);
        if (l == null) {
            throw new BusinessLogicException("No existe lugar con el id " + id);
        }

        DivisionDeLugarEntity d = divisionLogic.find(idDivision);
        if (d == null) {
            throw new BusinessLogicException("No existe una division con el id " + idDivision);
        }

        d.setLugar(l);
        d = divisionLogic.update(d);
        return new DivisionDeLugarDetailDTO(d);
    }

    /**
     * DELETE para eliminar una relacion division-lugar ya existente en el
     * sistema.
     * http://localhost:8080/nboletas-web/api/lugares/idLugar/divisiones/idDivision
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
    @Path("{idDivision: \\d+}")
    public void deleteDivisionLugar(@PathParam("idLugar") Long id, @PathParam("idDivision") Long idDivision) throws BusinessLogicException {
        LugarEntity l = lugarLogic.find(id);
        if (l == null) {
            throw new BusinessLogicException("No existe lugar con el id " + id);
        }

        List<DivisionDeLugarEntity> divisiones = l.getDivisiones();
        DivisionDeLugarEntity division = null;
        for (DivisionDeLugarEntity d : divisiones) {
            if (d.getId().equals(idDivision)) {
                division = d;
            }
        }
        if (division == null) {
            throw new BusinessLogicException("No existe una division con el id " + idDivision + " relacionada "
                    + "con el lugar de id " + id);
        }

        division.setLugar(null);
        division = divisionLogic.update(division);
    }

    private List<DivisionDeLugarDetailDTO> listEntity2DetailDTO(List<DivisionDeLugarEntity> entityList) {
        List<DivisionDeLugarDetailDTO> list = new ArrayList<>();
        for (DivisionDeLugarEntity entity : entityList) {
            list.add(new DivisionDeLugarDetailDTO(entity));
        }
        return list;
    }
}
