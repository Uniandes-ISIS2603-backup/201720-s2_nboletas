/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.dtos.ArtistaDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.ejb.ArtistaLogic;
import co.edu.uniandes.theexceptions.nboletas.entities.ArtistaEntity;
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
 * @author jf.ramos
 */
@Path("artistas")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class ArtistaResource {

    @Inject
    ArtistaLogic artistaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * POST http://localhost:8080/nboletas-web/api/artistas
     *
     * @param Artista correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "ArtistaDetailDTO", "id": 1, atributo1 : "valor" }
     * @throws BusinessLogicException
     */
    @POST
    public ArtistaDetailDTO createArtista(ArtistaDetailDTO Artista) throws BusinessLogicException {
        ArtistaEntity artista = Artista.toEntity();
        return new ArtistaDetailDTO(artistaLogic.create(artista));
    }

    /**
     * GET para todas los Artistas.
     * http://localhost:8080/nboletas-web/api/artistas
     *
     * @return la lista de todos los Artistas en objetos json DTO.
     * @throws BusinessLogicException
     */
    @GET
    public List<ArtistaDetailDTO> getArtistas() throws BusinessLogicException {
        return listEntity2DetailDTO(artistaLogic.findAll());
    }

    @GET
    @Path("{id: \\d+}")
    public ArtistaDetailDTO getArtista(@PathParam("id") Long id) throws BusinessLogicException {
        ArtistaEntity boleta = artistaLogic.find(id);
        if (boleta == null) {
            throw new BusinessLogicException("No existe el artista con el id: " + id);
        }
        return new ArtistaDetailDTO(boleta);
    }

    /**
     * PUT http://localhost:8080/nboletas-web/api/artistas/1 Ejemplo json {
     * "id": 1, "atributo1": "Valor nuevo" }
     *
     * @param id corresponde a la Boleta a actualizar.
     * @param artista corresponde al objeto con los cambios que se van a
     * realizar.
     * @return El artista actualizado.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del Artista a actualizar se retorna un 404
     * con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public ArtistaDetailDTO updateArtista(@PathParam("id") Long id, ArtistaDetailDTO artista) throws BusinessLogicException {
        artista.setId(id);
        if (null == artistaLogic.find(id)) {
            throw new BusinessLogicException("No existe el artista con el id: " + id);
        }
        ArtistaEntity artistaActualizado = artistaLogic.update(artista.toEntity());
        return (new ArtistaDetailDTO(artistaActualizado));
    }

    /**
     * DELETE http://localhost:8080/nboletas-web/api/artistas/{id}
     *
     * @param id corresponde al Artista a borrar.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id del Artista a actualizar se retorna un 404
     * con el mensaje.
     *
     * Seguir corrigiendo
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteArtista(@PathParam("id") Long id) throws BusinessLogicException {
        ArtistaEntity boleta = artistaLogic.find(id);
        if (null == boleta) {
            throw new BusinessLogicException("No existe el artista con el id: " + id);
        }
        artistaLogic.delete(boleta);
    }

    /**
     *
     * lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos ArtistaEntity a una lista de
     * objetos ArtistaDetailDTO (json)
     *
     * @param entityList corresponde a la lista de Artistas de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de Artistas en forma DTO (json)
     */
    private List<ArtistaDetailDTO> listEntity2DetailDTO(List<ArtistaEntity> entityList) {
        List<ArtistaDetailDTO> list = new ArrayList<>();
        for (ArtistaEntity entity : entityList) {
            list.add(new ArtistaDetailDTO(entity));
        }
        return list;
    }
}
