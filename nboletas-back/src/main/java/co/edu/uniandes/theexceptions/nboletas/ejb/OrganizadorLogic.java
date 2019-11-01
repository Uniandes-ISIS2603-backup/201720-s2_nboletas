/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.ejb;

import co.edu.uniandes.theexceptions.nboletas.entities.EspectaculoEntity;
import co.edu.uniandes.theexceptions.nboletas.exceptions.BusinessLogicException;
import co.edu.uniandes.theexceptions.nboletas.entities.OrganizadorEntity;
import co.edu.uniandes.theexceptions.nboletas.persistence.AbstractPersistence;
import co.edu.uniandes.theexceptions.nboletas.persistence.EspectaculoPersistence;
import java.util.List;
import co.edu.uniandes.theexceptions.nboletas.persistence.OrganizadorPersistence;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

/**
 *
 * @author df.riveros11
 */
@Stateless
public class OrganizadorLogic extends AbstractLogic<OrganizadorEntity> {

    @Inject
    private OrganizadorPersistence persistenceOrganizador;

    @Inject
    private EspectaculoPersistence persistenceEspectaculo;

    @Override
    protected AbstractPersistence<OrganizadorEntity> getPersistence() {
        return persistenceOrganizador;
    }

    public EspectaculoEntity createOrganizadorEspectaculo(long idOrganizador, EspectaculoEntity espectaculo) throws BusinessLogicException {
        OrganizadorEntity organizador = persistenceOrganizador.find(idOrganizador);
        if (organizador == null) {
            throw new BusinessLogicException("No existe el organizador con el id: " + idOrganizador);
        }
        List<OrganizadorEntity> organizadores = new ArrayList<OrganizadorEntity>();
        organizadores.add(organizador);
        espectaculo.setOrganizador(organizadores);
        EspectaculoEntity espectaculoE = null;
        try {
            espectaculoE = persistenceEspectaculo.create(espectaculo);
        } catch (PersistenceException e) {
            throw new PersistenceException("Un error al crear el envio relacionado al organizador con id:  " + idOrganizador + " el error es: " + e.getMessage());
        }
        return espectaculoE;
    }

    public List<EspectaculoEntity> findOrganizadoresEspectaculos(Long idOrganizador) throws BusinessLogicException {
        List<EspectaculoEntity> list = new ArrayList<EspectaculoEntity>();
        OrganizadorEntity organizador = persistenceOrganizador.find(idOrganizador);
        if (organizador == null) {
            throw new BusinessLogicException("No existe el organizador con ese id: " + idOrganizador);
        }
        List<EspectaculoEntity> espectaculos = organizador.getEspectaculos();
        if (espectaculos != null) {
            list = espectaculos;
        }
        return list;
    }

    public EspectaculoEntity findOrganizadorEspectaculo(Long idOrganizador, Long idEspectaculo) throws BusinessLogicException {
        OrganizadorEntity organizador = persistenceOrganizador.find(idOrganizador);
        if (organizador == null) {
            throw new BusinessLogicException("No existe el organizador con ese id: " + idOrganizador);
        }
        EspectaculoEntity espectaculo = persistenceEspectaculo.find(idEspectaculo);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe el espectaculo con ese id: " + idEspectaculo);
        }
        List<OrganizadorEntity> organizadores = espectaculo.getOrganizador();
        for (OrganizadorEntity organizadore : organizadores) {
            if (organizadore.getId().equals(idOrganizador)) {
                return espectaculo;
            }
        }
        throw new BusinessLogicException("No se encontnro relacion entre el espectaulo con el id: " + idEspectaculo + " y el organizador: " + idOrganizador);
    }

    public EspectaculoEntity updateOrganizadorEspectaculo(Long idOrganizador, EspectaculoEntity espectaculoE) throws BusinessLogicException {
        OrganizadorEntity organizador = persistenceOrganizador.find(idOrganizador);
        if (organizador == null) {
            throw new BusinessLogicException("No existe el organizador con ese id: " + idOrganizador);
        }
        long idEspectaculo = espectaculoE.getId();
        if (null == persistenceEspectaculo.find(idEspectaculo)) {
            throw new BusinessLogicException("No existe el espectaculo con ese id: " + idEspectaculo);
        }
        OrganizadorEntity organizadorActual = null;
        try {
            organizadorActual = persistenceOrganizador.updateOrganizadorEspectaculo(organizador, idEspectaculo);
        } catch (PersistenceException e) {
            throw new PersistenceException("No se pudo actualizar el especctaculo con el id: " + idEspectaculo + " relacionado con el organizador con el id: " + idOrganizador + " el error es: " + e.getMessage());
        }
        List<OrganizadorEntity> organizadores = new ArrayList<OrganizadorEntity>();
        organizadores.add(organizadorActual);
        espectaculoE.setOrganizador(organizadores);
        return espectaculoE;
    }

    public void deleteOrganizadorEspectaculo(Long idOrganizador, Long idEspectaculo) throws BusinessLogicException {
        OrganizadorEntity organizador = persistenceOrganizador.find(idOrganizador);
        if (organizador == null) {
            throw new BusinessLogicException("No existe el organizador con ese id: " + idOrganizador);
        }
        EspectaculoEntity espectaculo = persistenceEspectaculo.find(idEspectaculo);
        if (espectaculo == null) {
            throw new BusinessLogicException("No existe el espectaculo con ese id: " + idEspectaculo);
        }
        List<EspectaculoEntity> espetaculos = organizador.getEspectaculos();
        boolean noHayRelacion = true;
        for (EspectaculoEntity espetaculo : espetaculos) {
            if(espetaculo.getId().compareTo(idEspectaculo) == 0){
                noHayRelacion = false;
            }
        }
        if(noHayRelacion){
            throw new BusinessLogicException("No existe relacion entre el organizador con el id: " + idOrganizador + " y el el espectaculo con el id: " + idEspectaculo);
        }
        try{
            persistenceOrganizador.deleteOrganizadorEspectaculo(idOrganizador, idEspectaculo);
        }catch(PersistenceException e){
            throw new PersistenceException("No se puede eliminar el espectaculo con el id: " + idEspectaculo + " relacionado al organizador con el id: " + idOrganizador + " El error es: " + e.getMessage());
        }
    }

    public void deleteOrganizadorEspectaculos(Long idOrganizador, List<EspectaculoEntity> listEspectaculoDetailDto2EspectaculoEntity) throws BusinessLogicException {
        OrganizadorEntity organizador = persistenceOrganizador.find(idOrganizador);
        if (organizador == null) {
            throw new BusinessLogicException("No existe el organizador con ese id: " + idOrganizador);
        }
        for (EspectaculoEntity espectaculoEntity : listEspectaculoDetailDto2EspectaculoEntity) {
            EspectaculoEntity espectaculo = persistenceEspectaculo.find(espectaculoEntity.getId());
            if (espectaculo == null) {
                throw new BusinessLogicException("No existe el espectaculo con ese id: " + espectaculoEntity.getId());
            }
        }
        organizador.setEspectaculos(listEspectaculoDetailDto2EspectaculoEntity);
        try{
            persistenceOrganizador.update(organizador);
        }catch(PersistenceException e){
            throw new PersistenceException("No se puede actualizar  el organizador con el id: " + idOrganizador + " El error es: " + e.getMessage());
        }
    }

}
