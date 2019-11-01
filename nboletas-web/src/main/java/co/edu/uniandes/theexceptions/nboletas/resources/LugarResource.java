/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.dtos.FuncionDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.dtos.LugarDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.ejb.DivisionDeLugarLogic;
import co.edu.uniandes.theexceptions.nboletas.ejb.FuncionLogic;
import co.edu.uniandes.theexceptions.nboletas.ejb.LugarLogic;
import co.edu.uniandes.theexceptions.nboletas.ejb.SillaLogic;
import co.edu.uniandes.theexceptions.nboletas.entities.DivisionDeLugarEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.FuncionEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.LugarEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.SillaEntity;
import co.edu.uniandes.theexceptions.nboletas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
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
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author ja.gomez1
 */
@Path("lugares")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class LugarResource {

    @Inject
    private LugarLogic lugarLogic;

    @Inject
    private DivisionDeLugarLogic divisionLogic;

    @Inject
    private SillaLogic sillaLogic;

    private static final Logger LOGGER = Logger.getLogger(LugarResource.class.getName());

    /**
     * GET para todos los Lugares.
     * http://localhost:8080/nboletas-web/api/lugares
     *
     * @return la lista de Lugares en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<LugarDetailDTO> getLugares() throws BusinessLogicException {
        return LugarDetailDTO.listLugarEntity2DetailDTO(lugarLogic.findAll());
    }

    /**
     * GET para un lugar especifico.
     * http://localhost:8080/nboletas-web/api/lugares/id
     *
     * @return la el lugar en objeto json DTO.
     * @throws WebApplicationException
     *
     * En caso de no existir el id del lugar buscado se retorna un 404 not
     * found.
     */
    @GET
    @Path("{id: \\d+}")
    public LugarDetailDTO getLugar(@PathParam("id") Long id) throws WebApplicationException {
        LugarEntity l = lugarLogic.find(id);
        if (l == null) {
            throw new WebApplicationException("No existe lugar con el id " + id, 404);
        }
        return new LugarDetailDTO(l);
    }

    /**
     * POST http://localhost:8080/nboletas-web/api/lugares
     *
     * @param lugar correponde a la representación java del objeto json enviado
     * en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java.
     * @throws BusinessLogicException
     */
    @POST
    public LugarDetailDTO createLugar(LugarDetailDTO lugar) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        LugarEntity lugarEntity = lugar.toEntity();
        // Invoca la lógica para crear el Lugar nuevo
        LugarEntity nuevoLugar = lugarLogic.create(lugarEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new LugarDetailDTO(nuevoLugar);
    }

    /**
     * PUT http://localhost:8080/nboletas-web/api/lugares/id
     *
     * @param id del lugar a actualizar.
     * @param lugar datos a actualizar del lugar.
     * @return el Lugar actualizado.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del Lugar a actualizar se retorna un 404 not
     * found.
     */
    @PUT
    @Path("{id: \\d+}")
    public LugarDetailDTO updateLugar(@PathParam("id") Long id, LugarDetailDTO lugar) throws BusinessLogicException, UnsupportedOperationException {
        if (null == lugarLogic.find(id)) {
            throw new BusinessLogicException("No existe lugar con id: " + id);
        }
        LugarEntity lugarP = lugar.toEntity();
        lugarP.setId(id);
        LugarEntity lugarUpdated = lugarLogic.update(lugarP);
        return (new LugarDetailDTO(lugarUpdated));
    }

    /**
     * DELETE http://localhost:8080/nboletas-web/api/lugares/id
     *
     * @param id corresponde al Lugar a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del Lugar a borrar se retorna un 404 not
     * found.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteLugar(@PathParam("id") Long id) throws BusinessLogicException {

        LugarEntity l = lugarLogic.find(id);
        if (null == l) {
            throw new BusinessLogicException("No existe funcion con el id: " + id);
        }
        lugarLogic.delete(l);
    }
}
