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
package co.edu.uniandes.theexceptions.nboletas.ejb;

import co.edu.uniandes.theexceptions.nboletas.entities.BoletaEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.ComentarioEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.EnvioEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.ReembolsoEntity;
import co.edu.uniandes.theexceptions.nboletas.exceptions.BusinessLogicException;
import co.edu.uniandes.theexceptions.nboletas.persistence.AbstractPersistence;
import co.edu.uniandes.theexceptions.nboletas.persistence.BoletaPersistence;
import co.edu.uniandes.theexceptions.nboletas.persistence.ComentarioPersistence;
import co.edu.uniandes.theexceptions.nboletas.persistence.EnvioPersistence;
import co.edu.uniandes.theexceptions.nboletas.persistence.ReembolsoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;

/**
 *
 * @author ISIS2603
 */
@Stateless
public class BoletaLogic extends AbstractLogic<BoletaEntity> {

    @Inject
    private BoletaPersistence persistenceBoleta;

    @Inject
    private EnvioPersistence persistenceEnvio;

    @Inject
    private ReembolsoPersistence persistenceReembolso;
    
    @Inject
    private ComentarioPersistence persistenceComentario;

    @Override
    protected AbstractPersistence<BoletaEntity> getPersistence() {
        return persistenceBoleta;
    }

    public EnvioEntity createBoletaEnvio(long idBoleta, EnvioEntity envio) throws BusinessLogicException, PersistenceException {
        BoletaEntity boleta = persistenceBoleta.find(idBoleta);
        if (boleta == null) {
            throw new BusinessLogicException("No existe la boleta con el id: " + idBoleta);
        }
        envio.setBoleta(boleta);
        EnvioEntity envioCreado = null;
        try {
            envioCreado = persistenceEnvio.create(envio);
        } catch (PersistenceException e) {
            throw new PersistenceException("Un error al crear el envio relacionado a la boleta con id:  " + idBoleta + " el error es: " + e.getMessage());
        }
        return envioCreado;
    }

    public List<EnvioEntity> findBoletaEnvios(long idBoleta) throws BusinessLogicException, PersistenceException {
        List<EnvioEntity> envios = new ArrayList();
        BoletaEntity boleta = persistenceBoleta.find(idBoleta);
        if (boleta == null) {
            throw new BusinessLogicException("No se encuentra la boleta con el id: " + idBoleta);
        }
        try {
            EnvioEntity envio = boleta.getEnvio();
            envios.add(envio);
        } catch (PersistenceException e) {
            throw new PersistenceException(" Sucedio un error en la base de datos mayor infromacion: " + e.getMessage());
        }
        return envios;
    }

    public EnvioEntity findBoletaEnvio(long idBoleta, long idEnvio) throws BusinessLogicException, PersistenceException {
        BoletaEntity boleta = persistenceBoleta.find(idBoleta);
        if (boleta == null) {
            throw new BusinessLogicException("No existe la boleta con ese id: " + idBoleta);
        }
        EnvioEntity envio = persistenceEnvio.find(idEnvio);
        if (envio == null) {
            throw new BusinessLogicException("No existe el envio con ese id: " + idEnvio);
        }
        long id = envio.getBoleta().getId();
        if (id != idBoleta) {
            throw new BusinessLogicException("No existe la relacion ");
        }
        envio.setBoleta(boleta);
        return envio;
    }

    public EnvioEntity updateBoletaEnvio(long idBoleta, EnvioEntity envio) throws BusinessLogicException, PersistenceException {
        BoletaEntity boleta = persistenceBoleta.find(idBoleta);
        if (boleta == null) {
            throw new BusinessLogicException("No existe la boleta con ese id: " + idBoleta);
        }
        long idEnvio = envio.getId();
        if (null == persistenceEnvio.find(idEnvio)) {
            throw new BusinessLogicException("No existe el envio con ese id: " + idEnvio);
        }
        envio.setBoleta(boleta);
        EnvioEntity envioFinal = null;
        try {
            envioFinal = persistenceEnvio.update(envio);
        } catch (PersistenceException e) {
            throw new PersistenceException("No se pudo actualizar el envio con el id: " + idEnvio + " relacionado a la boleta con el id: " + idBoleta + " el error es: " + e.getMessage());
        }
        return envioFinal;
    }

    public void deleteBoletaEnvio(Long idBoleta, Long idEnvio) throws BusinessLogicException, PersistenceException {
        BoletaEntity boleta = persistenceBoleta.find(idBoleta);
        if (boleta == null) {
            throw new BusinessLogicException("No existe la boleta con ese id: " + idBoleta);
        }
        EnvioEntity envio = persistenceEnvio.find(idEnvio);
        if (envio == null) {
            throw new BusinessLogicException("No existe el envio con ese id: " + idEnvio);
        }
        if(idEnvio.compareTo(boleta.getEnvio().getId()) != 0){
            throw new BusinessLogicException("No existe ninguna relacion entre la boleta con el id :" +idBoleta + " y el envio con id :" +idEnvio);
        }
        envio.setBoleta(boleta);
        try {
            persistenceEnvio.delete(envio);
        } catch (PersistenceException e) {
            throw new PersistenceException("No se puede eliminar el envio con el id: " + idEnvio + " relacionado a la boleta con el id: " + idBoleta + " El error es: " + e.getMessage());
        }
    }

    public ReembolsoEntity createBoletaReembolso(Long idBoleta, ReembolsoEntity reembolso) throws BusinessLogicException, PersistenceException {
        BoletaEntity boleta = persistenceBoleta.find(idBoleta);
        if (boleta == null) {
            throw new BusinessLogicException("No existe la boleta con el id: " + idBoleta);
        }
        reembolso.setBoleta(boleta);
        ReembolsoEntity reembolsoCreado = null;
        try {
            reembolsoCreado = persistenceReembolso.create(reembolso);
        } catch (PersistenceException e) {
            throw new PersistenceException("Un error al crear el envio relacionado a la boleta con id:  " + idBoleta + " el error es: " + e.getMessage());
        }
        return reembolsoCreado;
    }

    public ReembolsoEntity findBoletaReembolso(Long idBoleta, Long idReembolso) throws BusinessLogicException, PersistenceException {
        BoletaEntity boleta = persistenceBoleta.find(idBoleta);
        if (boleta == null) {
            throw new BusinessLogicException("No existe la boleta con ese id: " + idBoleta);
        }
        ReembolsoEntity reembolso = persistenceReembolso.find(idReembolso);
        if (reembolso == null) {
            throw new BusinessLogicException("No existe el reembolso con ese id: " + idReembolso);
        }
        long id = reembolso.getBoleta().getId();
        if (id != idBoleta) {
            throw new BusinessLogicException("No existe la relacion ");
        }
        reembolso.setBoleta(boleta);
        return reembolso;
    }

    public List<ReembolsoEntity> findBoletaReembolsos(Long idBoleta) throws BusinessLogicException, PersistenceException {
        List<ReembolsoEntity> reembolsos = new ArrayList();
        BoletaEntity boleta = persistenceBoleta.find(idBoleta);
        if (boleta == null) {
            throw new BusinessLogicException("No se encuentra la boleta con el id: " + idBoleta);
        }
        try {
            ReembolsoEntity reembolso = boleta.getReembolso();
            reembolsos.add(reembolso);
        } catch (PersistenceException e) {
            throw new PersistenceException(" Sucedio un error en la base de datos mayor informacion: " + e.getMessage());
        }
        return reembolsos;
    }

    public ReembolsoEntity updateBoletaReembolso(Long idBoleta, ReembolsoEntity reembolso) throws BusinessLogicException, PersistenceException {
        BoletaEntity boleta = persistenceBoleta.find(idBoleta);
        if (boleta == null) {
            throw new BusinessLogicException("No existe la boleta con ese id: " + idBoleta);
        }
        long idReembolso = reembolso.getId();
        if (null == persistenceReembolso.find(idReembolso)) {
            throw new BusinessLogicException("No existe el reembolso con ese id: " + idReembolso);
        }
        reembolso.setBoleta(boleta);
        ReembolsoEntity reembolsoFinal = null;
        try {
            reembolsoFinal = persistenceReembolso.update(reembolso);
        } catch (PersistenceException e) {
            throw new PersistenceException("No se pudo actualizar el reembolso con el id: " + idReembolso + " relacionado a la boleta con el id: " + idBoleta + " el error es: " + e.getMessage());
        }
        return reembolsoFinal;
    }

    public void deleteBoletaReembolso(Long idBoleta, Long idReembolso) throws BusinessLogicException,PersistenceException {
        BoletaEntity boleta = persistenceBoleta.find(idBoleta);
        if (boleta == null) {
            throw new BusinessLogicException("No existe la boleta con ese id: " + idBoleta);
        }
        ReembolsoEntity reembolso = persistenceReembolso.find(idReembolso);
        if (reembolso == null) {
            throw new BusinessLogicException("No existe el reembolso con ese id: " + idReembolso);
        }
        if(reembolso.getId().compareTo(boleta.getReembolso().getId()) != 0){
            throw new BusinessLogicException("No existe ninguna relacion entre la boleta con el id :" +idBoleta + " y el reembolso con id :" +reembolso.getId());
        }
        reembolso.setBoleta(boleta);
        try {
            persistenceReembolso.delete(reembolso);
        } catch (PersistenceException e) {
            throw new PersistenceException("No se puede eliminar el reembolso con el id: " + idReembolso + " relacionado a la boleta con el id: " + idBoleta + " El error es: " + e.getMessage());
        }
    }

    public ComentarioEntity createBoletaComentario(Long idBoleta, ComentarioEntity comentario) throws BusinessLogicException, PersistenceException {
        BoletaEntity boleta = persistenceBoleta.find(idBoleta);
        if (boleta == null) {
            throw new BusinessLogicException("No existe la boleta con el id: " + idBoleta);
        }
        comentario.setBoleta(boleta);
        ComentarioEntity comentarioCreado = null;
        try{
            persistenceComentario.create(comentario);
        }catch(PersistenceException e){
            throw new PersistenceException("Un error al crear el comentario relacionado a la boleta con id:  " + idBoleta + " el error es: " + e.getMessage());
        }
       return comentarioCreado;
    }

    public List<ComentarioEntity> findBoletaComentarios(Long idBoleta) throws BusinessLogicException, PersistenceException {
        List<ComentarioEntity> list = new ArrayList<ComentarioEntity>();
        BoletaEntity boleta = persistenceBoleta.find(idBoleta);
        if (boleta == null) {
            throw new BusinessLogicException("No existe la boleta con ese id: " + idBoleta);
        }
        ComentarioEntity comentario = boleta.getComentario();
        if (comentario != null) {
            list.add(boleta.getComentario());
        }
        return list;
    }

    public ComentarioEntity findBoletaComentarios(Long idBoleta, Long idComentario) throws BusinessLogicException, PersistenceException {
        BoletaEntity boleta = persistenceBoleta.find(idBoleta);
        if (boleta == null) {
            throw new BusinessLogicException("No existe la boleta con ese id: " + idBoleta);
        }
        ComentarioEntity comentario = persistenceComentario.find(idComentario);
        if (comentario == null) {
            throw new BusinessLogicException("No existe el comentario con ese id: " + idComentario);
        }
        long id = comentario.getBoleta().getId();
        if (id != idBoleta) {
            throw new BusinessLogicException("No existe la relacion ");
        }
        comentario.setBoleta(boleta);
        return comentario;
    }

    public ComentarioEntity updateBoletaComentario(Long idBoleta, ComentarioEntity comentario) throws BusinessLogicException, PersistenceException{
        BoletaEntity boleta = persistenceBoleta.find(idBoleta);
        if (boleta == null) {
            throw new BusinessLogicException("No existe la boleta con ese id: " + idBoleta);
        }
        long idComentario = comentario.getId();
        if (null == persistenceComentario.find(idComentario)) {
            throw new BusinessLogicException("No existe el comentario con ese id: " + idComentario);
        }
        comentario.setBoleta(boleta);
        comentario.setId(idComentario);
        ComentarioEntity actual = null;
        try {
            actual = persistenceComentario.update(comentario);
        } catch (PersistenceException e) {
            throw new PersistenceException("No se pudo actualizar el comentario con el id: " + idComentario + " relacionado a la boleta con el id: " + idBoleta + " el error es: " + e.getMessage());
        }
        return actual;
    }

    public void deleteBoletaComentario(Long idBoleta, Long idComentario) throws BusinessLogicException, PersistenceException {
       BoletaEntity boleta = persistenceBoleta.find(idBoleta);
        if (boleta == null) {
            throw new BusinessLogicException("No existe la boleta con ese id: " + idBoleta);
        }
        ComentarioEntity comentario = persistenceComentario.find(idComentario);
        if (comentario == null) {
            throw new BusinessLogicException("No existe el comentario con ese id: " + idComentario);
        }
        if(comentario.getId().compareTo(boleta.getComentario().getId()) != 0){
            throw new BusinessLogicException("No existe ninguna relacion entre la boleta con el id :" +idBoleta + " y el comentario con id :" +comentario.getId());
        }
        comentario.setBoleta(boleta);
        try {
            persistenceComentario.delete(comentario);
        } catch (PersistenceException e) {
            throw new PersistenceException("No se puede eliminar el comentario con el id: " + idComentario + " relacionado a la boleta con el id: " + idBoleta + " El error es: " + e.getMessage());
        }
    }

}
