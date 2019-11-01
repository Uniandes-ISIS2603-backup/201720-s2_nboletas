/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.ejb;

import co.edu.uniandes.theexceptions.nboletas.entities.BoletaEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.FuncionEntity;
import co.edu.uniandes.theexceptions.nboletas.exceptions.BusinessLogicException;
import co.edu.uniandes.theexceptions.nboletas.persistence.AbstractPersistence;
import co.edu.uniandes.theexceptions.nboletas.persistence.BoletaPersistence;
import co.edu.uniandes.theexceptions.nboletas.persistence.FuncionPersistence;
import java.util.List;
import javax.ejb.Stateful;
import javax.inject.Inject;

/**
 *
 * @author ja.gomez1
 */
@Stateful
public class FuncionLogic extends AbstractLogic<FuncionEntity> {

    @Inject
    private FuncionPersistence funcionPersistence;
    
    @Inject
    private BoletaPersistence boletaPersistence;

    @Override
    protected AbstractPersistence<FuncionEntity> getPersistence() {
        return funcionPersistence;
    }

    public BoletaEntity createBoletaFuncion(Long id, BoletaEntity boleta) throws BusinessLogicException {
        FuncionEntity f = funcionPersistence.find(id);
        if (f == null) {
            throw new BusinessLogicException("No existe funcion con el id " + id);
        }
        boleta.setFuncion(f);
        boleta = boletaPersistence.create(boleta);
        return boleta;
    }

    public BoletaEntity updateBoletaFuncion(Long id, Long idBoleta) throws BusinessLogicException {
        FuncionEntity f = funcionPersistence.find(id);
        if (f == null) {
            throw new BusinessLogicException("No existe funcion con el id " + id);
        }

        BoletaEntity b = boletaPersistence.find(idBoleta);
        if (b == null) {
            throw new BusinessLogicException("No existe una boleta con el id " + idBoleta);
        }

        b.setFuncion(f);
        b = boletaPersistence.update(b);
        return b;
    }

    public void deleteBoletaFuncion(Long id, Long idBoleta) throws BusinessLogicException {
        FuncionEntity f = funcionPersistence.find(id);
        if (f == null) {
            throw new BusinessLogicException("No existe funcion con el id " + id);
        }

        List<BoletaEntity> boletas = f.getBoletas();
        BoletaEntity boleta = null;
        for (BoletaEntity b : boletas) {
            if (b.getId().equals(idBoleta)) {
                boleta = b;
            }
        }
        if (boleta == null) {
            throw new BusinessLogicException("No existe una boleta con el id " + idBoleta + " relacionada "
                    + "con la funcion de id " + id);
        }

        boleta.setFuncion(null);
        boletaPersistence.delete(boleta);
    }

    public BoletaEntity getBoletaFuncion(Long id, Long idBoleta) throws BusinessLogicException {
        FuncionEntity f = funcionPersistence.find(id);
        if (f == null) {
            throw new BusinessLogicException("No existe funcion con el id " + id);
        }

        List<BoletaEntity> boletas = f.getBoletas();
        BoletaEntity boleta = null;
        for (BoletaEntity b : boletas) {
            if (b.getId().equals(idBoleta)) {
                boleta = b;
            }
        }

        if (boleta == null) {
            throw new BusinessLogicException("No existe boleta con el id " + idBoleta + " relacionada "
                    + "con la funcion de id " + id);
        }
        return boleta;
    }

    public List<BoletaEntity> getBoletasFuncion(Long id) throws BusinessLogicException {
        FuncionEntity f = funcionPersistence.find(id);
        if (f == null) {
            throw new BusinessLogicException("No existe funcion con el id " + id);
        }
        return f.getBoletas();
    }
}