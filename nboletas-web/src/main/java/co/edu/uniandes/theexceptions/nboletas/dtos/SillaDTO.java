/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.dtos;

import co.edu.uniandes.theexceptions.nboletas.entities.SillaEntity;

/**
 *
 * @author fc.alvarez10
 */
public class SillaDTO {

    private Long id;

    private double costo;
    
    private String imagen;

    public SillaDTO() {

    }

    public SillaDTO(SillaEntity silla) {
        this.id = silla.getId();
        this.costo = silla.getCosto();
        this.imagen = silla.getImagen();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public SillaEntity toEntity() {
        SillaEntity silla = new SillaEntity();
        silla.setId(this.id);
        silla.setCosto(this.costo);
        silla.setImagen(this.getImagen());
        return silla;
    }

    /**
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    
}
