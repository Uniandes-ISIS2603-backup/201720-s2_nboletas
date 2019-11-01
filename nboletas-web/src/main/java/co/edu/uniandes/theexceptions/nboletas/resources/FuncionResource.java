/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.dtos.BoletaDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.dtos.EspectaculoDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.dtos.FuncionDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.dtos.LugarDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.ejb.FuncionLogic;
import co.edu.uniandes.theexceptions.nboletas.ejb.SillaLogic;
import co.edu.uniandes.theexceptions.nboletas.entities.BoletaEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.ComentarioEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.EnvioEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.EspectaculoEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.FuncionEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.LugarEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.ReembolsoEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.SillaEntity;
import co.edu.uniandes.theexceptions.nboletas.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.Date;
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
@Path("funciones")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class FuncionResource {

    @Inject
    private FuncionLogic funcionLogic;

    private static final Logger LOGGER = Logger.getLogger(FuncionResource.class.getName());

    /**
     * GET para todas las Funciones.
     * http://localhost:8080/nboletas-web/api/funciones
     *
     * @return la lista de todas las Funciones en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<FuncionDetailDTO> getFunciones() throws BusinessLogicException {
        return FuncionDetailDTO.listFuncionEntity2DetailDTO(funcionLogic.findAll());
    }

    /**
     * GET para todas las Funciones.
     * http://localhost:8080/nboletas-web/api/funciones/id
     *
     * @return la lista de todas las Funciones en objetos json DTO.
     * @throws WebApplicationException
     *
     * En caso de no existir el id de la Funcion se retorna un 404 not found.
     */
    @GET
    @Path("{id: \\d+}")
    public FuncionDetailDTO getFuncion(@PathParam("id") Long id) throws WebApplicationException {
        FuncionEntity f = funcionLogic.find(id);
        if (f == null) {
            throw new WebApplicationException("No existe funcion con id " + id, 404);
        }
        return new FuncionDetailDTO(f);
    }

    /**
     * GET para el espectaculo de una funcion.
     * http://localhost:8080/nboletas-web/api/funciones/id
     *
     * @return 0 de todas las Funciones en objetos json DTO.
     * @throws WebApplicationException
     *
     * En caso de no existir el id de la Funcion se retorna un 404 not found.
     *
     * En caso de no existir espectaculo para la funcion se retorna un 404 not
     * found.
     */
    @GET
    @Path("{id: \\d+}/espectaculo")
    public EspectaculoDetailDTO getEspectaculo(@PathParam("id") Long id) throws WebApplicationException {
        FuncionEntity f = funcionLogic.find(id);
        if (f == null) {
            throw new WebApplicationException("No existe funcion con id " + id, 404);
        }
        EspectaculoEntity espectaculo = f.getEspectaculo();
        if (espectaculo == null) {
            throw new WebApplicationException("La funcion con id " + id + " no tiene espectaculo asignado", 404);
        }
        return new EspectaculoDetailDTO(espectaculo);
    }

    /**
     * GET para el lugar de una funcion.
     * http://localhost:8080/nboletas-web/api/funciones/id
     *
     * @return la lista de todas las Funciones en objetos json DTO.
     * @throws WebApplicationException
     *
     * En caso de no existir el id de la Funcion se retorna un 404 not found.
     *
     * En caso de no existir lugar para la funcion se retorna un 404 not found.
     */
    @GET
    @Path("{id: \\d+}/lugar")
    public LugarDetailDTO getLugar(@PathParam("id") Long id) throws WebApplicationException {
        FuncionEntity f = funcionLogic.find(id);
        if (f == null) {
            throw new WebApplicationException("No existe funcion con id " + id, 404);
        }
        LugarEntity lugar = f.getLugar();
        if (lugar == null) {
            throw new WebApplicationException("La funcion con id " + id + " no tiene lugar asignado", 404);
        }
        return new LugarDetailDTO(lugar);
    }

    /**
     * POST http://localhost:8080/nboletas-web/api/funciones
     *
     * @param Funcion correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java.
     * @throws BusinessLogicException
     */
    @POST
    public FuncionDetailDTO createFuncion(FuncionDetailDTO funcion) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        FuncionEntity funcionEntity = funcion.toEntity();
        // Invoca la lógica para crear la Funcion nueva
        FuncionEntity nuevaFuncion = funcionLogic.create(funcionEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new FuncionDetailDTO(nuevaFuncion);
    }

    /**
     * PUT http://localhost:8080/nboletas-web/api/funciones/id
     *
     * @param id de la funcion a actualizar.
     * @param funcion datos a actualizar de la funcion.
     * @return La Funcion actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Funcion a actualizar se retorna un 404
     * not found.
     */
    @PUT
    @Path("{id: \\d+}")
    public FuncionDetailDTO updateFuncion(@PathParam("id") Long id, FuncionDetailDTO funcion) throws BusinessLogicException, UnsupportedOperationException {
        if (null == funcionLogic.find(id)) {
            throw new BusinessLogicException("No existe funcion con id: " + id);
        }
        FuncionEntity funcionP = funcion.toEntity();
        funcionP.setId(id);
        FuncionEntity funcionUpdated = funcionLogic.update(funcionP);
        return (new FuncionDetailDTO(funcionUpdated));
    }

    /**
     * DELETE http://localhost:8080/nboletas-web/api/funciones/id
     *
     * @param id corresponde a la Funcion a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Funcion a borrar se retorna un 404 not
     * found.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteFuncion(@PathParam("id") Long id) throws BusinessLogicException {
        FuncionEntity f = funcionLogic.find(id);
        if (null == f) {
            throw new BusinessLogicException("No existe funcion con el id: " + id);
        }
        funcionLogic.delete(f);
    }
}
