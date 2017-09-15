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
import java.util.List;
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
    private boolean venida;
    
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
    @ManyToOne(fetch = FetchType.LAZY)
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

    public boolean isVenida() {
        return venida;
    }

    public void setVenida(boolean venida) {
        this.venida = venida;
    }

    
    
}
