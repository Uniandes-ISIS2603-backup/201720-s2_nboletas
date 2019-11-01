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
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ja.gomez1
 */
@Entity
public class LugarEntity extends BaseEntity implements Serializable {

    private String direccion;
    private String tipo;
    private String ubicacion;
    private String imagen;

    @PodamExclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lugar")
    private List<FuncionEntity> funciones;

    @PodamExclude
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "lugar")
    private List<DivisionDeLugarEntity> divisiones;

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
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @param ubicacion the ubicacion to set
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * @return the funciones
     */
    public List<FuncionEntity> getFunciones() {
        return funciones;
    }

    /**
     * @param funciones the funciones to set
     */
    public void setFunciones(List<FuncionEntity> funciones) {
        this.funciones = funciones;
    }

    /**
     * @return the divisiones
     */
    public List<DivisionDeLugarEntity> getDivisiones() {
        return divisiones;
    }

    /**
     * @param divisiones the divisiones to set
     */
    public void setDivisiones(List<DivisionDeLugarEntity> divisiones) {
        this.divisiones = divisiones;
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
