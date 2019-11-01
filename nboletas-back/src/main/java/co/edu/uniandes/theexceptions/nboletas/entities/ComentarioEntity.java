/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Calendar;
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
 * @author angeloMarcetty
 */
@Entity
public class ComentarioEntity extends BaseEntity implements Serializable {


    private String comentario;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    @PodamExclude
    @OneToOne(fetch = FetchType.LAZY)
    private BoletaEntity boleta;

    @PodamExclude
    @ManyToOne(fetch = FetchType.LAZY)
    private EspectaculoEntity espectaculo;
    
    public String imagen;

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BoletaEntity getBoleta() {
        return boleta;
    }

    public void setBoleta(BoletaEntity boleta) {
        this.boleta = boleta;
    }

    public EspectaculoEntity getEspectaculo() {
        return espectaculo;
    }

    public void setEspectaculo(EspectaculoEntity espectaculo) {
        this.espectaculo = espectaculo;
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
