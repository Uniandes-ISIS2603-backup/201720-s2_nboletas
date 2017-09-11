/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import co.edu.uniandes.theexceptions.nboletas.dtos.EnvioDetailDTO;
import co.edu.uniandes.theexceptions.nboletas.ejb.EnvioLogic;
import co.edu.uniandes.theexceptions.nboletas.entities.EnvioEntity;
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

    
    @POST
    public EnvioDetailDTO createEnvio(EnvioDetailDTO envio) {
        EnvioEntity EnvioEntity = envio.toEntity();
        EnvioEntity nuevoEnvio = envioLogic.createEnvio(EnvioEntity);
        return new EnvioDetailDTO(nuevoEnvio);
    }
    
    
    
    @GET
    @Path("{id: \\d+}")
    public EnvioDetailDTO getEnvio(@PathParam("id") Long id) {
        EnvioEntity entity = envioLogic.getEnvio(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso envio: " + id + " no existe.", 404);
        }
        return new EnvioDetailDTO(entity);
    }
    
    
    @GET
    public List<EnvioDetailDTO> getEnvios()  {
        return listEntity2DetailDTO(envioLogic.getEnvios());
    }
    
    
    
    
    
    @PUT
    @Path("{id: \\d+}")
    public EnvioDetailDTO updateEnvio(@PathParam("id") Long id, EnvioDetailDTO envio) {

        envio.setId(id);
        EnvioEntity entity = envioLogic.getEnvio(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso envio: " + id + " no existe.", 404);
        }
        return new EnvioDetailDTO(envioLogic.updateEnvio(id, envio.toEntity()));    
    }
    
    
    
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEnvio(@PathParam("id") Long id) {
        EnvioEntity entity = envioLogic.getEnvio(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso envio: " + id + " no existe.", 404);
        }
        //revisar!!
        envioLogic.deleteEnvio(id); 
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
