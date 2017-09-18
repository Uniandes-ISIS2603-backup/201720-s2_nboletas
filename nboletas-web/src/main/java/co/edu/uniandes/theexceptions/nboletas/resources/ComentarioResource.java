/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.resources;

import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;



/**
 *
 * @author angeloMarcetty
 */
@Path("comentarios")
@Produces("application/json")
@Consumes("application/json")
@Stateless
public class ComentarioResource {
        
        
        
     @Inject
    ComentarioLogic comentarioLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    private static final Logger LOGGER = Logger.getLogger(ComentarioResource.class.getName());

    
    @POST
    public ComentarioDetailDTO createComentario(ComentarioDetailDTO comentario) {
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        ComentarioEntity ComentarioEntity = comentario.toEntity();
        // Invoca la lógica para crear el comentario nuevo
        ComentarioEntity nuevoComentario = comentarioLogic.createComentario(ComentarioEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        return new ComentarioDetailDTO(nuevoComentario);
    }
    
    
    
    @GET
    @Path("{id: \\d+}")
    public ComentarioDetailDTO getComentario(@PathParam("id") Long id) {
        ComentarioEntity entity = comentarioLogic.getComentario(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso comentario: " + id + " no existe.", 404);
        }
        return new ComentarioDetailDTO(entity);
    }
    
    
    @GET
    public List<ComentarioDetailDTO> getComentarios()  {
        return listEntity2DetailDTO(comentarioLogic.getComentarios());
    }
    
    
    
    
    
    @PUT
    @Path("{id: \\d+}")
    public ComentarioDetailDTO updateComentario(@PathParam("id") Long id, ComentarioDetailDTO comentario) {

         comentario.setId(id);
        ComentarioEntity entity = comentarioLogic.getComentario(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso comentario: " + id + " no existe.", 404);
        }
        return new ComentarioDetailDTO(comentarioLogic.updateComentario(id, comentario.toEntity()));    
    }
    
    
    
    
    @DELETE
    @Path("{id: \\d+}")
    public void deleteComentario(@PathParam("id") Long id) {
        ComentarioEntity entity = comentarioLogic.getComentario(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso comentario: " + id + " no existe.", 404);
        }
        //revisar!!
        comentarioLogic.deleteComentario(id); 
    }
    
    
    
    
    
    
    
    /**
     *
     * lista de entidades a DTO.
     *
     */
    private List<ComentarioDetailDTO> listEntity2DetailDTO(List<ComentarioEntity> entityList) {
        List<ComentarioDetailDTO> list = new ArrayList<>();
        for (ComentarioEntity entity : entityList) {
            list.add(new ComentarioDetailDTO(entity));
        }
        return list;
    }
}
