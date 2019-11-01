/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author df.riveros11
 */
@Entity
public class FuncionEntity extends BaseEntity implements Serializable {

    @Temporal(TemporalType.DATE)
    private Date fecha;

    private String hora;
    
    private String imagen;

    @PodamExclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "funcion")
    private List<BoletaEntity> boletas;

    @PodamExclude
    @ManyToOne(fetch = FetchType.LAZY)
    private EspectaculoEntity espectaculo;

    @PodamExclude
    @ManyToOne(fetch = FetchType.LAZY)
    private LugarEntity lugar;

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the boletas
     */
    public List<BoletaEntity> getBoletas() {
        return boletas;
    }

    /**
     * @param boletas the boletas to set
     */
    public void setBoletas(List<BoletaEntity> boletas) {
        this.boletas = boletas;
    }

    /**
     * @return the espectaculo
     */
    public EspectaculoEntity getEspectaculo() {
        return espectaculo;
    }

    /**
     * @param espectaculo the espectaculo to set
     */
    public void setEspectaculo(EspectaculoEntity espectaculo) {
        this.espectaculo = espectaculo;
    }

    /**
     * @return the lugar
     */
    public LugarEntity getLugar() {
        return lugar;
    }

    /**
     * @param lugar the lugar to set
     */
    public void setLugar(LugarEntity lugar) {
        this.lugar = lugar;
    }

    /**
     * @return the hora
     */
    public String getHora() {
        return hora;
    }

    /**
     * @param hora the hora to set
     */
    public void setHora(String hora) {
        this.hora = hora;
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
