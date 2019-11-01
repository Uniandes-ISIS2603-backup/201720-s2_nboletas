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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;

/**
 *
 * @author fc.alvarez10
 */
@Path("divisiones")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class DivisionDeLugarResource {

    @Inject
    DivisionDeLugarLogic divisionDeLugarLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias

    private static final Logger LOGGER = Logger.getLogger(DivisionDeLugarResource.class.getName()); 

    /**
     * POST http://localhost:8080/nboletas-web/api/divisiones
     *
     * @para division correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "DivisionDeLugarDetailDTO", "id": 1, atributo1 : "valor" }
     * @throws BusinessLogicException
     */
    @POST
    public DivisionDeLugarDetailDTO createDivision(DivisionDeLugarDetailDTO division) throws BusinessLogicException {

        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        DivisionDeLugarEntity divisionEntity = division.toEntity();
        // Invoca la lógica para crear la Boleta nueva
        DivisionDeLugarEntity nuevaDivision = divisionDeLugarLogic.create(divisionEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new DivisionDeLugarDetailDTO(nuevaDivision);
    }

    /**
     * GET para todas las Divisiones.
     * http://localhost:8080/nboletas-web/api/divisiones
     *
     * @return la lista de todas las Divisiones en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<DivisionDeLugarDetailDTO> getDivisiones() throws BusinessLogicException {
        return DivisionDeLugarDetailDTO.listBoletaEntity2BoletaDetailDTO(divisionDeLugarLogic.findAll()); 
    }

    @GET
    @Path("{id: \\d+}")
    public DivisionDeLugarDetailDTO getDivision(@PathParam("id") Long id) throws BusinessLogicException {
        DivisionDeLugarEntity division = divisionDeLugarLogic.find(id);
        if (division == null) {
            throw new BusinessLogicException("No existe la division con el id: " + id);
        }
        return new DivisionDeLugarDetailDTO(division);
    }

    /**
     * PUT http://localhost:8080/nboletas-web/api/divisiones/1 Ejemplo json {
     * "id": 1, "atirbuto1": "Valor nuevo" }
     *
     * @param id corresponde a la Boleta a actualizar.
     * @param division corresponde al objeto con los cambios que se van a
     * realizar.
     * @return La division actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Boleta a actualizar se retorna un 404
     * con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public DivisionDeLugarDetailDTO updateDivisionDeLugar(@PathParam("id") Long id, DivisionDeLugarDetailDTO divisionDeLugar) throws BusinessLogicException, UnsupportedOperationException {
        DivisionDeLugarEntity divisionDeLugarActualizar = divisionDeLugar.toEntity();
        if (null == divisionDeLugarLogic.find(id)) {
            throw new BusinessLogicException("No existe la division con el id: " + id);
        }
        divisionDeLugarActualizar.setId(id);
        DivisionDeLugarEntity divisionDeLugarActualizada = divisionDeLugarLogic.update(divisionDeLugarActualizar);
        return (new DivisionDeLugarDetailDTO(divisionDeLugarActualizada));
    }

    /**
     * DELETE http://localhost:8080/nboletas-web/api/divisiones/{id}
     *
     * @param id corresponde a la Division a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la division a actualizar se retorna un 404
     * con el mensaje.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteDivisionDeLugar(@PathParam("id") Long id) throws BusinessLogicException {
        DivisionDeLugarEntity division = divisionDeLugarLogic.find(id);
        if (null == division) {
            throw new BusinessLogicException("No existe la division con el id: " + id);
        }
        divisionDeLugarLogic.delete(division);
    }

    @GET
    @Path("{id: \\d+}/lugar")
    public LugarDetailDTO getLugar(@PathParam("id")Long id) throws BusinessLogicException{
        DivisionDeLugarEntity division=divisionDeLugarLogic.find(id);
        if(division==null){
            throw new BusinessLogicException("No se encuentra la division con el id: "+id);
        }
        return new LugarDetailDTO(division.getLugar());
    }
}
