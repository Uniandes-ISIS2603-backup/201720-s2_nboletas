/*
MIT License

Copyright (c) 2017 Universidad de los Andes - ISIS2603

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
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
 * @author ISIS2603
 */
@Entity
public class BoletaEntity extends BaseEntity implements Serializable {

    private double precio;
    private boolean vendida;
    private String imagen;

    @PodamExclude
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "boleta")
    private ReembolsoEntity reembolso;

    @PodamExclude
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "boleta")
    private EnvioEntity envio;

    @PodamExclude
    @ManyToOne(fetch = FetchType.LAZY)
    private UsuarioEntity usuario;

    @PodamExclude
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "boleta")
    private ComentarioEntity comentario;

    @PodamExclude
    @ManyToOne(fetch = FetchType.LAZY)
    private FuncionEntity funcion;

    @PodamExclude
    @ManyToOne(fetch = FetchType.LAZY)
    private SillaEntity silla;

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isVendida() {
        return vendida;
    }

    public void setVendida(boolean vendida) {
        this.vendida = vendida;
    }

    public ReembolsoEntity getReembolso() {
        return reembolso;
    }

    public void setReembolso(ReembolsoEntity reembolso) {
        this.reembolso = reembolso;
    }

    public EnvioEntity getEnvio() {
        return envio;
    }

    public void setEnvio(EnvioEntity envio) {
        this.envio = envio;
    }

    public UsuarioEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public ComentarioEntity getComentario() {
        return comentario;
    }

    public void setComentario(ComentarioEntity comentario) {
        this.comentario = comentario;
    }

    public FuncionEntity getFuncion() {
        return funcion;
    }

    public void setFuncion(FuncionEntity funcion) {
        this.funcion = funcion;
    }

    public SillaEntity getSilla() {
        return silla;
    }

    public void setSilla(SillaEntity silla) {
        this.silla = silla;
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
