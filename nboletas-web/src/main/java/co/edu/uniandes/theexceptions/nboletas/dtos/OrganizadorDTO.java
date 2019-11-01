/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.dtos;

import co.edu.uniandes.theexceptions.nboletas.entities.OrganizadorEntity;

/**
 *
 * @author df.riveros11
 */
public class OrganizadorDTO {

    private Long id;
    private String imagen;
    private String nombreEmpresa;

    public OrganizadorDTO() {
    }

    public OrganizadorDTO(OrganizadorEntity organizador) {
        if (organizador != null) {
            this.id = organizador.getId();
            this.imagen = organizador.getImagen();
            this.nombreEmpresa = organizador.getNombreEmpresa();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public OrganizadorEntity toEntity() {
        OrganizadorEntity organizador = new OrganizadorEntity();
        organizador.setId(this.id);
        organizador.setImagen(this.imagen);
        organizador.setNombreEmpresa(this.nombreEmpresa);
        
        return organizador;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
