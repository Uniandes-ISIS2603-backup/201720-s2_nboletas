/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.dtos.UsuarioDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.ejb.UsuarioLogic;
import co.edu.uniandes.theexceptions.nboletas.entities.UsuarioEntity;
import co.edu.uniandes.theexceptions.nboletas.exceptions.BusinessLogicException;
import java.util.List;
import java.util.logging.Level;
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
 * @author jm.contreras10
 */
@Path("usuarios")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class UsuarioResource {

    @Inject
    private UsuarioLogic logic;

    private static final Logger LOGGER = Logger.getLogger(UsuarioResource.class.getName());

    /**
     * GET Retorna la colección de recursos de tipo usuario.
     * http://localhost:8080/nboletas-web/api/usuarios
     *
     * @return la lista de todos los Usuarios en objetos json DTO.
     */
    @GET
    public List<UsuarioDetailDTO> getUsuarios() {
        return UsuarioDetailDTO.listUsuarioEntity2UsuarioDetailDTO(logic.findAll());
    }

    /**
     * GET Retorna al usuario con el id dado.
     * http://localhost:8080/nboletas-web/api/usuarios/id
     *
     * En este metodo hay que ser cuidadoso pues los usuareios deciden que información 
     * desean compartir con los demas.
     * @param id
     * @return la lista de todos los Usuarios en objetos json DTO.
     * @throws BusinessLogicException
     *  En caso de no cumplir con reglas del negocio.
     */
    @GET
    @Path("{id: \\d+}")
    public UsuarioDetailDTO getUsuario(@PathParam("id") Long id) throws BusinessLogicException {
        return new UsuarioDetailDTO(logic.find(id));
    }
    
    @GET
    @Path("{userName}")
    public UsuarioDetailDTO getUsuarioByUserName(@PathParam("userName")String userName) throws BusinessLogicException{
        return new UsuarioDetailDTO(logic.findUsuarioByUserName(userName));
    }

    /**
     * POST Crea un nuevo usuario (Register).
     * http://localhost:8080/nboletas-web/api/usuarios
     *  
     * Metodo equivalente al registro del usuario en el sistema.
     * @param usuario correponde a la representación java del objeto json
     * enviado en el llamado.
     * @return Devuelve el objeto json de entrada que contiene el id creado por
     * @throws BusinessLogicException
     *  En caso de no cumplir con reglas del negocio.
     */
    @POST
    public UsuarioDetailDTO createUsuario(UsuarioDetailDTO usuario) throws BusinessLogicException {
        UsuarioEntity usr = logic.create(usuario.toEntity());
        LOGGER.log(Level.SEVERE, "Id: {0}", usr.getId());
        return new UsuarioDetailDTO(usr);
    }

    /**
     * PUT Actualiza los datos del usuario dado.
     * http://localhost:8080/nboletas-web/api/usuarios/id
     *
     * <pre> En este caso al ser usuario cliente, este metodo solo permite modificar datos propios.
     * @param id del Usuario a actualizar.
     * @param usuario datos a actualizar del Usuario.
     * @return El usuario actualizado.
     * @throws BusinessLogicException
     *  En caso de no cumplir con reglas del negocio.
     */
    @PUT
    @Path("{id: \\d+}")
    public UsuarioDetailDTO updateUsuario(@PathParam("id") Long id, UsuarioDetailDTO usuario) throws WebApplicationException, BusinessLogicException {
        usuario.setId(id);
        return new UsuarioDetailDTO(logic.update(usuario.toEntity()));
    }

    /**
     * DELETE Elimina un objeto Usuario.
     * http://localhost:8080/nboletas-web/api/usuarios/id
     *
     * Elimina una cuenta del sistema Nuestras Boletas.
     * @param id corresponde al Usuario a borrar.
     * @throws BusinessLogicException
     *  En caso de no cumplir con reglas del negocio.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteUsuario(@PathParam("id") Long id) throws BusinessLogicException {
        UsuarioDetailDTO user = getUsuario(id);
        logic.delete(user.toEntity());
    }

}
