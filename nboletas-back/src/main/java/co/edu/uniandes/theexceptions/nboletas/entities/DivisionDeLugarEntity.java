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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author fc.alvarez10
 */
@Entity
public class DivisionDeLugarEntity extends BaseEntity implements Serializable {

    private String nombre;

    private String imagen;
    
    @PodamExclude
    @OneToMany(orphanRemoval = true, cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "division")
    private List<SillaEntity> sillas;

    @PodamExclude
    @OneToOne(fetch = FetchType.LAZY)
    private LugarEntity lugar;
    
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

    public List<SillaEntity> getSillas() {
        return sillas;
    }

    public void setSillas(List<SillaEntity> sillas) {
        this.sillas = sillas;
    }

    public LugarEntity getLugar() {
        return lugar;
    }

    public void setLugar(LugarEntity lugar) {
        this.lugar = lugar;
    }
}
