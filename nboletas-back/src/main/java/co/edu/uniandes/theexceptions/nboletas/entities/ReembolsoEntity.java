/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author jm.contreras10
 */
@Entity
public class ReembolsoEntity extends BaseEntity implements Serializable {

    /**
     * Valor del reembolso.
     */
    private double valor;
    
    private String imagen;
    
    @NotNull
    @PodamExclude
    @OneToOne(fetch = FetchType.LAZY)
    private BoletaEntity boleta;
    
    @NotNull
    @PodamExclude
    @ManyToOne(fetch = FetchType.LAZY)
    private UsuarioEntity usuario;

    public BoletaEntity getBoleta() {
        return boleta;
    }

    public void setBoleta(BoletaEntity boleta) {
        this.boleta = boleta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
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
