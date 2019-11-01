/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.dtos.EnvioDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.ejb.EnvioLogic;
import co.edu.uniandes.theexceptions.nboletas.entities.EnvioEntity;
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
 * @author angeloMarcetty
 */
@Path("envios")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class EnvioResource {

    @Inject
    EnvioLogic envioLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(EnvioResource.class.getName());

    /**
     * POST http://localhost:8080/nboletas-web/api/envios
     *
     * @para envio correponde a la representación java del objeto json enviado
     * en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "EnvioDetailDTO", "id": 1, atributo1 : "valor" }
     * @throws BusinessLogicException
     */
    @POST
    public EnvioDetailDTO createEnvio(EnvioDetailDTO envio) throws BusinessLogicException {
        EnvioEntity EnvioEntity = envio.toEntity();
        EnvioEntity nuevoEnvio = envioLogic.create(EnvioEntity);
        return new EnvioDetailDTO(nuevoEnvio);
    }

    @GET
    @Path("{id: \\d+}")
    public EnvioDetailDTO getEnvio(@PathParam("id") Long id) {
        EnvioEntity entity = envioLogic.find(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso envio: " + id + " no existe.", 404);
        }
        return new EnvioDetailDTO(entity);
    }

    /**
     * GET para todas los Envios. http://localhost:8080/nboletas-web/api/envios
     *
     * @return la lista de todas los Envios en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<EnvioDetailDTO> getEnvios() throws BusinessLogicException {
        return listEntity2DetailDTO(envioLogic.findAll());
    }

    /**
     * PUT http://localhost:8080/nboletas-web/api/envios/1 Ejemplo json { "id":
     * 1, "atirbuto1": "Valor nuevo" }
     *
     * @param id corresponde al Envio a actualizar.
     * @param envio corresponde al objeto con los cambios que se van a realizar.
     * @return El envio actualizado.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del envio a actualizar se retorna un 404 con
     * el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public EnvioDetailDTO updateEnvio(@PathParam("id") Long id, EnvioDetailDTO envio) throws BusinessLogicException {

        envio.setId(id);
        EnvioEntity entity = envioLogic.find(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso envio: " + id + " no existe.", 404);
        }
        return new EnvioDetailDTO(envioLogic.update(envio.toEntity()));
    }

    /**
     * DELETE http://localhost:8080/nboletas-web/api/envios/{id}
     *
     * @param id corresponde al envio a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del envio a borrar se retorna un 404 con el
     * mensaje.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEnvio(@PathParam("id") Long id) {
        EnvioEntity entity = envioLogic.find(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso envio: " + id + " no existe.", 404);
        }
        //revisar!!
        envioLogic.delete(entity);
    }

    /**
     *
     * lista de entidades a DTO.
     *
     */
    private List<EnvioDetailDTO> listEntity2DetailDTO(List<EnvioEntity> entityList) {
        List<EnvioDetailDTO> list = new ArrayList<>();
        for (EnvioEntity entity : entityList) {
            list.add(new EnvioDetailDTO(entity));
        }
        return list;
    }

}
