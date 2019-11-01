/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.dtos;

import co.edu.uniandes.theexceptions.nboletas.entities.ArtistaEntity;

/**
 *
 * @author jf.ramos
 */
public class ArtistaDTO {

    private Long id;
    private String imagen;
    private String nombre;

    /**
     * Constructor por defecto
     */
    public ArtistaDTO() {
    }

    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param artista: Es la entidad que se va a convertir a DTO
     */
    public ArtistaDTO(ArtistaEntity artista) {
        this.id = artista.getId();
        this.nombre = artista.getNombre();
        this.imagen = artista.getImagen();
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
    public ArtistaEntity toEntity() {
        ArtistaEntity entity = new ArtistaEntity();
        entity.setId(this.id);
        entity.setNombre(this.nombre);
        entity.setImagen(this.imagen);
        return entity;
    }

}
