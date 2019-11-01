/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.dtos;

import co.edu.uniandes.theexceptions.nboletas.entities.ArtistaEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.EspectaculoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jf.ramos
 */
public class ArtistaDetailDTO extends ArtistaDTO {
    
    List <EspectaculoDTO> espectaculos;

    /**
     * Constructor por defecto
     */
    public ArtistaDetailDTO() {
    }

    /**
     * Constructor para transformar un Entity a un DTO
     *
     * @param entity
     */
    public ArtistaDetailDTO(ArtistaEntity entity) {
        super(entity);
        espectaculos=new ArrayList<>();
        if(entity.getEspectaculos()!=null){
        for(EspectaculoEntity espec: entity.getEspectaculos()){
            EspectaculoDTO dto=new EspectaculoDTO(espec);
            espectaculos.add(dto);
        }
        }
    }

    public List<EspectaculoDTO> getEspectaculos() {
        return espectaculos;
    }

    public void setEspectaculos(List<EspectaculoDTO> espectaculos) {
        this.espectaculos = espectaculos;
    }

    /**
     * Transformar un DTO a un Entity
     *
     * @return
     */
    @Override
    public ArtistaEntity toEntity() {
        ArtistaEntity BoletaE = super.toEntity();
        return BoletaE;
    }

}
