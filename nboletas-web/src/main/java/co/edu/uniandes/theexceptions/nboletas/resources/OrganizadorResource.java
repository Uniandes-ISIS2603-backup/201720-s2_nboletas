/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.dtos.OrganizadorDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.ejb.OrganizadorLogic;
import co.edu.uniandes.theexceptions.nboletas.entities.OrganizadorEntity;
import co.edu.uniandes.theexceptions.nboletas.exceptions.BusinessLogicException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
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
 * @author df.riveros11
 */
@Path("organizadores")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class OrganizadorResource {

    @Inject
    OrganizadorLogic organizadorLogic;

    /**
     * POST http://localhost:8080/nboletas-web/api/organizadores
     *
     * @param organizador correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "BoletaDetailDTO", "id": 1, atributo1 : "valor" }
     * @throws BusinessLogicException
     */
    @POST
    public OrganizadorDetailDTO createOrganizador(OrganizadorDetailDTO organizador) throws BusinessLogicException, PersistenceException {
        OrganizadorEntity organizadorA = null;
        try {
            organizadorA = organizadorLogic.create(organizador.toEntity());
        } catch (PersistenceException e) {
            throw new PersistenceException("Hubo un error al crear la boleta: " + e.getMessage());
        }
        return new OrganizadorDetailDTO(organizadorA);
    }

    /**
     * GET para todas las Boletas.
     * http://localhost:8080/nboletas-web/api/organizadores
     *
     * @return la lista de todas las Boletas en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<OrganizadorDetailDTO> getOrganizadores() throws BusinessLogicException, PersistenceException {
        OrganizadorDetailDTO entrega = new OrganizadorDetailDTO();
        return entrega.listOrganizadorEntity2OrganizadorDetailDTO(organizadorLogic.findAll());
    }

    @GET
    @Path("{id: \\d+}")
    public OrganizadorDetailDTO getOrganizador(@PathParam("id") Long id) throws BusinessLogicException, PersistenceException {
        OrganizadorEntity organizador = organizadorLogic.find(id);
        if (organizador == null) {
            throw new BusinessLogicException("No existe el organizador con el id: " + id);
        }
        return new OrganizadorDetailDTO(organizador);
    }

    /**
     * PUT http://localhost:8080/nboletas-web/api/organizadores/1 Ejemplo json {
     * "id": 1, "atirbuto1": "Valor nuevo" }
     *
     * @param id corresponde a la Boleta a actualizar.
     * @param organizador corresponde al objeto con los cambios que se van a
     * realizar.
     * @return La Boleta actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Boleta a actualizar se retorna un 404
     * con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public OrganizadorDetailDTO updateOrganizador(@PathParam("id") Long id, OrganizadorDetailDTO organizador) throws BusinessLogicException, PersistenceException {
        organizador.setId(id);
        if (null == organizadorLogic.find(id)) {
            throw new BusinessLogicException("No existe el organizador con el id: " + id);
        }
        OrganizadorEntity organizadorActualizada = null;
        try {
            organizadorActualizada = organizadorLogic.update(organizador.toEntity());
        } catch (PersistenceException e) {
            throw new PersistenceException("hubo un error al actualizar la boleta: " + e.getMessage());
        }
        return (new OrganizadorDetailDTO(organizadorActualizada));
    }

    /**
     * DELETE http://localhost:8080/nboletas-web/api/oranizadores/{id}
     *
     * @param id corresponde a la Boleta a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Boleta a actualizar se retorna un 404
     * con el mensaje.
     *
     * Seguir corrigiendo
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteOrganizador(@PathParam("id") Long id) throws BusinessLogicException, PersistenceException {
        OrganizadorEntity organizador = organizadorLogic.find(id);
        if (null == organizador) {
            throw new BusinessLogicException("No existe el organizador con el id: " + id);
        }
        try {
            organizadorLogic.delete(organizador);
        } catch (PersistenceException e) {
            throw new PersistenceException("Hubo un error al borrar la boleta: " + e.getMessage());
        }
    }
}
