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

    @PodamExclude
    @OneToOne(fetch = FetchType.LAZY)
    private BoletaEntity boleta;

    @PodamExclude
    @ManyToOne(fetch = FetchType.LAZY)
    private UsuarioEntity usuario;

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public BoletaEntity getBoleta() {
        return boleta;
    }

    public void setBoleta(BoletaEntity boleta) {
        this.boleta = boleta;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    
}
