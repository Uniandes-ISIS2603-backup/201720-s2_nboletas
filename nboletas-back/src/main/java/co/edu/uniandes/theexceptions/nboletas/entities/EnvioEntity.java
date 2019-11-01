/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author angeloMarcetty
 */
@Entity
public class EnvioEntity extends BaseEntity implements Serializable {

    private String direccion;

    @PodamExclude
    @OneToOne(fetch = FetchType.LAZY)
    private BoletaEntity boleta;

    public String imagen;
    
    
    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the boleta
     */
    public BoletaEntity getBoleta() {
        return boleta;
    }

    /**
     * @param boleta the boleta to set
     */
    public void setBoleta(BoletaEntity boleta) {
        this.boleta = boleta;
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
