/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.dtos;

import co.edu.uniandes.theexceptions.nboletas.entities.DivisionDeLugarEntity;

/**
 *
 * @author fc.alvarez10
 */
public class DivisionDeLugarDTO {

    private Long id;

    private String name;
    
    private String imagen;

    

    public DivisionDeLugarDTO() {
    }

    public DivisionDeLugarDTO(DivisionDeLugarEntity division) {
        this.id = division.getId();
        this.name = division.getNombre();
        this.imagen= division.getImagen();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public DivisionDeLugarEntity toEntity() {
        DivisionDeLugarEntity division = new DivisionDeLugarEntity();
        division.setId(this.id);
        division.setNombre(this.name);
        division.setImagen(this.imagen);
        return division;
    }
}
