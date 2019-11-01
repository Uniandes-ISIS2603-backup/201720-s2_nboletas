/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author fc.alvarez10
 */
@Entity
public class SillaEntity extends BaseEntity implements Serializable {

    private String imagen;
    
    @PodamExclude
    @OneToMany(orphanRemoval = true, cascade = CascadeType.REMOVE,fetch = FetchType.LAZY, mappedBy = "silla")
    private List<BoletaEntity> boletas;

    @PodamExclude
    @ManyToOne(fetch = FetchType.LAZY)
    private DivisionDeLugarEntity division;

    private double costo;

    public DivisionDeLugarEntity getDivision() {
        return division;
    }

    public void setDivision(DivisionDeLugarEntity division) {
        this.division = division;
    }

    public void setBoletas(List<BoletaEntity> boletas) {
        this.boletas = boletas;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public List<BoletaEntity> getBoletas() {
        return boletas;
    }

    public double getCosto() {
        return costo;
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
