/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.dtos.EspectaculoDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.ejb.EspectaculoLogic;
import co.edu.uniandes.theexceptions.nboletas.entities.EspectaculoEntity;
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

/**
 *
 * @author jf.ramos
 */
@Path("espectaculos")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class EspectaculoResource {

    @Inject
    EspectaculoLogic espectaculoLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(EspectaculoResource.class.getName());

    /**
     * POST http://localhost:8080/nboletas-web/api/espectaculos
     *
     * @param espectaculo correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "EspectaculoDetailDTO", "id": 1, atributo1 : "valor" }
     * @throws BusinessLogicException
     */
    @POST
    public EspectaculoDetailDTO createEspectaculo(EspectaculoDetailDTO espectaculo) throws BusinessLogicException {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        EspectaculoEntity espectaculoEntity = espectaculo.toEntity();
        // Invoca la lógica para crear la Boleta nueva
        EspectaculoEntity nuevoEspectaculo = espectaculoLogic.create(espectaculoEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new EspectaculoDetailDTO(nuevoEspectaculo);
    }

    /**
     * GET para todas las Espectaculos.
     * http://localhost:8080/nboletas-web/api/espectaculos
     *
     * @return la lista de todas las Boletas en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<EspectaculoDetailDTO> getEspectaculos() throws BusinessLogicException {
        return listEntity2DetailDTO(espectaculoLogic.findAll());
    }

    @GET
    @Path("{id: \\d+}")
    public EspectaculoDetailDTO getEspectaculo(@PathParam("id") Long id) throws BusinessLogicException {
        EspectaculoEntity espectaculo = espectaculoLogic.find(id);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe el espectaculo con el id: " + id);
        }
        return new EspectaculoDetailDTO(espectaculo);
    }

    /**
     * PUT http://localhost:8080/nboletas-web/api/espectaculos/1 Ejemplo json {
     * "id": 1, "atirbuto1": "Valor nuevo" }
     *
     * @param id corresponde a la Boleta a actualizar.
     * @param espectaculo corresponde al objeto con los cambios que se van a
     * realizar.
     * @return El espectaculo actualizado.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del Espectaculo a actualizar se retorna un
     * 404 con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public EspectaculoDetailDTO updateEspectaculo(@PathParam("id") Long id, EspectaculoDetailDTO espectaculo) throws BusinessLogicException, UnsupportedOperationException {

        espectaculo.setId(id);
        if (null == espectaculoLogic.find(id)) {
            throw new BusinessLogicException("No existe la boleta con el id: " + id);
        }
        EspectaculoEntity espectaculoActualizado = espectaculoLogic.update(espectaculo.toEntity());
        return (new EspectaculoDetailDTO(espectaculoActualizado));

    }

    /**
     * DELETE http://localhost:8080/nboletas-web/api/espectaculos/{id}
     *
     * @param id corresponde al Espectaculo a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del Espectaculo a actualizar se retorna un
     * 404 con el mensaje.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEspectaculo(@PathParam("id") Long id) throws BusinessLogicException {
        EspectaculoEntity boleta = espectaculoLogic.find(id);
        if (null == boleta) {
            throw new BusinessLogicException("No existe el espectaculo con el id: " + id);
        }
        espectaculoLogic.delete(boleta);
    }

    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EspectaculoEntity a una lista
     * de objetos BoletaDetailDTO (json)
     *
     * @param entityList corresponde a la lista de Boletas de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de Boletas en forma DTO (json)
     */
    private List<EspectaculoDetailDTO> listEntity2DetailDTO(List<EspectaculoEntity> entityList) {
        List<EspectaculoDetailDTO> list = new ArrayList<>();
        for (EspectaculoEntity entity : entityList) {
            list.add(new EspectaculoDetailDTO(entity));
        }
        return list;
    }

}
