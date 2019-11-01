/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.ejb.BoletaLogic;
import co.edu.uniandes.theexceptions.nboletas.dtos.BoletaDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.dtos.FuncionDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.dtos.SillaDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.dtos.UsuarioDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.entities.BoletaEntity;
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
 * Clase que implementa el recurso REST correspondiente a "Boletas".
 *
 * Note que la aplicación (definida en RestConfig.java) define la ruta "/api" y
 * este recurso tiene la ruta "Boletas". Al ejecutar la aplicación, el recurso
 * será accesibe a través de la ruta "/api/nboletas"
 *
 * @author ISIS2603
 *
 */
@Path("boletas")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class BoletaResource {

    @Inject
    BoletaLogic boletaLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * POST http://localhost:8080/nboletas-web/api/boletas
     *
     * @param Boleta correponde a la representación java del objeto json enviado
     * en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * la base de datos y el tipo del objeto java. Ejemplo: { "type":
     * "BoletaDetailDTO", "id": 1, atributo1 : "valor" }
     */
    @POST
    public BoletaDetailDTO createBoleta(BoletaDetailDTO Boleta) {
        BoletaEntity boleta = boletaLogic.create(Boleta.toEntity());
        return new BoletaDetailDTO(boleta);
    }

    /**
     * GET para todas las Boletas.
     * http://localhost:8080/nboletas-web/api/boletas
     *
     * @return la lista de todas las Boletas en objetos json DTO.
     */
    @GET
    public List<BoletaDetailDTO> getBoletas() {
        return BoletaDetailDTO.listBoletaEntity2BoletaDetailDTO(boletaLogic.findAll());
    }

    @GET
    @Path("{id: \\d+}")
    public BoletaDetailDTO getBoleta(@PathParam("id") Long id) throws BusinessLogicException, PersistenceException {
        BoletaEntity boleta = boletaLogic.find(id);
        if (boleta == null) {
            throw new BusinessLogicException("No se encontra la boleta con el id: " + id);
        }
        return new BoletaDetailDTO(boleta);
    }

    /**
     * PUT http://localhost:8080/nboletas-web/api/boletas/1 Ejemplo json { "id":
     * 1, "atirbuto1": "Valor nuevo" }
     *
     * @param id corresponde a la Boleta a actualizar.
     * @param boleta corresponde al objeto con los cambios que se van a
     * realizar.
     * @return La Boleta actualizada.
     * @throws BusinessLogicException
     *
     * En caso de no existir el id de la Boleta a actualizar se retorna un 404
     * con el mensaje.
     */
    @PUT
    @Path("{id: \\d+}")
    public BoletaDetailDTO updateBoleta(@PathParam("id") Long id, BoletaDetailDTO boleta) throws BusinessLogicException {
        boleta.setId(id);
        if (null == boletaLogic.find(id)) {
            throw new BusinessLogicException("No existe la boleta con el id: " + id);
        }
        BoletaEntity boletaActualizada =  boletaLogic.update(boleta.toEntity());
        return (new BoletaDetailDTO(boletaActualizada));
    }

    /**
     * DELETE http://localhost:8080/nboletas-web/api/boletas/{id}
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
    public void deleteBoleta(@PathParam("id") Long id) throws BusinessLogicException {
        BoletaEntity boleta = boletaLogic.find(id);
        if (null == boleta) {
            throw new BusinessLogicException("No existe la boleta con el id: " + id);
        }
            boletaLogic.delete(boleta);
       
    }

    @GET
    @Path("{id: \\d+}/usuarios")
    public UsuarioDetailDTO getUsuario(@PathParam("id") Long id) throws BusinessLogicException {
        BoletaEntity boleta = boletaLogic.find(id);
        if (boleta == null) {
            throw new BusinessLogicException("No se encontra la boleta con el id: " + id);
        }
        return new UsuarioDetailDTO(boleta.getUsuario());
    }

    @GET
    @Path("{id: \\d+}/sillas")
    public SillaDetailDTO getSilla(@PathParam("id") Long id) throws BusinessLogicException {
        BoletaEntity boleta = boletaLogic.find(id);
        if (boleta == null) {
            throw new BusinessLogicException("No se encontra la boleta con el id: " + id);
        }
        return new SillaDetailDTO(boleta.getSilla());
    }

    @GET
    @Path("{id: \\d+}/funciones")
    public FuncionDetailDTO getFuncion(@PathParam("id") Long id) throws BusinessLogicException {
        BoletaEntity boleta = boletaLogic.find(id);
        if (boleta == null) {
            throw new BusinessLogicException("No se encontra la boleta con el id: " + id);
        }
        return new FuncionDetailDTO(boleta.getFuncion());
    }

}
