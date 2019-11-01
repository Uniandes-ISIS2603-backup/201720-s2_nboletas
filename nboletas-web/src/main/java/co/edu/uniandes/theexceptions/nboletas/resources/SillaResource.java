/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.dtos.*;
import co.edu.uniandes.theexceptions.nboletas.ejb.*;
import co.edu.uniandes.theexceptions.nboletas.entities.*;
import co.edu.uniandes.theexceptions.nboletas.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;

/**
 *
 * @author fc.alvarez10
 */
@Path("sillas")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class SillaResource {

    @Inject
    SillaLogic sillaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(SillaResource.class.getName());

    /**
     * POST http://localhost:8080/nboletas-web/api/sillas
     *
     * @param silla correponde a la representación java del objeto json enviado
     * en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "BoletaDetailDTO", "id": 1, atributo1 : "valor" }
     * @throws BusinessLogicException
     */
    @POST
    public SillaDetailDTO createSilla(SillaDetailDTO silla) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        SillaEntity sillaEntity = silla.toEntity();
        // Invoca la lógica para crear la Boleta nueva
        SillaEntity nuevoSilla = sillaLogic.create(sillaEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new SillaDetailDTO(nuevoSilla);
    }

    /**
     * GET para todas las Sillas. http://localhost:8080/nboletas-web/api/sillas
     *
     * @return la lista de todas las Sillas en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<SillaDetailDTO> getSillas() throws BusinessLogicException {
        return SillaDetailDTO.listSillaEntity2SillaDetailDTO(sillaLogic.findAll());
    }

    @GET
    @Path("{id: \\d+}")
    public SillaDetailDTO getSilla(@PathParam("id") Long id) throws BusinessLogicException {
        SillaEntity silla = sillaLogic.find(id);
        if (silla == null) {
            throw new BusinessLogicException("No existe la silla con el id: " + id);
        }
        return new SillaDetailDTO(silla);
    }

    /**
     * PUT http://localhost:8080/nboletas-web/api/sillas/1 Ejemplo json { "id":
     * 1, "atirbuto1": "Valor nuevo" }
     *
     * @param id corresponde a la Boleta a actualizar.
     * @param silla corresponde al objeto con los cambios que se van a realizar.
     * @return La Boleta actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Boleta a actualizar se retorna un 404
     * con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public SillaDetailDTO updateSilla(@PathParam("id") Long id, SillaDetailDTO silla) throws BusinessLogicException, UnsupportedOperationException {
        SillaEntity sillaActualizar = silla.toEntity();
        if (null == sillaLogic.find(id)) {
            throw new BusinessLogicException("No existe la silla con el id: " + id);
        }
        sillaActualizar.setId(id);
        SillaEntity sillaActualizada = sillaLogic.update(sillaActualizar);
        return (new SillaDetailDTO(sillaActualizada));
    }

    /**
     * DELETE http://localhost:8080/nboletas-web/api/sillas/{id}
     *
     * @param id corresponde a la Boleta a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Boleta a actualizar se retorna un 404
     * con el mensaje.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteSilla(@PathParam("id") Long id) throws BusinessLogicException {
        SillaEntity silla = sillaLogic.find(id);
        if (null == silla) {
            throw new BusinessLogicException("No existe la silla con el id: " + id);
        }
        sillaLogic.delete(silla);
    }
    
    @GET
    @Path("{id: \\d+}/division")
    public DivisionDeLugarDetailDTO getDivision(@PathParam("id") Long id)throws BusinessLogicException{
        SillaEntity silla = sillaLogic.find(id);
        if(silla == null){
            throw new BusinessLogicException("No se encuentra la silla con el id: "+ id);
        }
        return new DivisionDeLugarDetailDTO(silla.getDivision());
    }
}
