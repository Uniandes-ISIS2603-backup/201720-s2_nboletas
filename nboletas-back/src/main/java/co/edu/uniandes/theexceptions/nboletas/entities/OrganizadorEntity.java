package co.edu.uniandes.theexceptions.nboletas.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
public class OrganizadorEntity extends BaseEntity implements Serializable {

    private String nombreEmpresa;
    private String imagen;

    @PodamExclude
    @ManyToMany(mappedBy = "organizador")
    private List<EspectaculoEntity> espectaculos;

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public List<EspectaculoEntity> getEspectaculos() {
        return espectaculos;
    }

    public void setEspectaculos(List<EspectaculoEntity> espectaculos) {
        this.espectaculos = espectaculos;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
