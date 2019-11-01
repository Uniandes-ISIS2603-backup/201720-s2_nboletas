/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.ejb;

import co.edu.uniandes.theexceptions.nboletas.entities.BoletaEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.ReembolsoEntity;
import co.edu.uniandes.theexceptions.nboletas.entities.UsuarioEntity;
import co.edu.uniandes.theexceptions.nboletas.exceptions.BusinessLogicException;
import co.edu.uniandes.theexceptions.nboletas.persistence.AbstractPersistence;
import co.edu.uniandes.theexceptions.nboletas.persistence.BoletaPersistence;
import co.edu.uniandes.theexceptions.nboletas.persistence.ReembolsoPersistence;
import co.edu.uniandes.theexceptions.nboletas.persistence.UsuarioPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.TransactionRequiredException;

/**
 *
 * @author jm.contreras10
 */
@Stateless
public class UsuarioLogic extends AbstractLogic<UsuarioEntity> {

    @Inject
    private UsuarioPersistence persistence;
    
    @Inject
    private ReembolsoPersistence reembolsoPersistence;
    
    @Inject
    private BoletaPersistence boletaPersistence;

    
    @Override
    protected AbstractPersistence<UsuarioEntity> getPersistence() {
        return persistence;
    }

    /**
     * Retorna un usuario dado su userName.
     * @param userName
     * @return
     * @throws BusinessLogicException 
     *  Cuando el usuario con el userName dado no existe.
     */
    public UsuarioEntity findUsuarioByUserName(String userName) throws BusinessLogicException{
        //Obtencion de usuario.
        UsuarioEntity usuario = persistence.findByUserName(userName);
        //Verificacion usuario existe.
        if(usuario == null){
            throw new BusinessLogicException("No encontrado el usuario con nombre de usuario: " + userName);
        }
        return usuario;
    }
    
    @Override
    public void delete(UsuarioEntity entity) {
        try {
            //Obteniendo boleta completa
            entity = persistence.find(entity.getId());
            //Desconectando todas las boletas compradas por el usuario
            for (BoletaEntity bol : entity.getBoletas()) {
                bol.setUsuario(null);
                bol.setVendida(false);
                boletaPersistence.update(bol);
            }
            //Eliminando reeembolsos del usuario
            for (ReembolsoEntity reem : entity.getReembolsos()) {
                reembolsoPersistence.delete(reem);
            }
            persistence.delete(entity);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("La instancia no es una entidad", e);
        } catch (TransactionRequiredException e) {
            throw new TransactionRequiredException("No existe ninguna transaccion");
        }
    }
    
    /**
     * Retorna una lista de rembolsos del usuario dado.
     * @param idUsuario
     * @return
     * @throws BusinessLogicException
     *  Cuando no existe el Usuario con idDado.
     */
    public List<ReembolsoEntity> findUsuarioReembolsos(long idUsuario) throws BusinessLogicException{
        //Revision existencia del usuario
        UsuarioEntity usuarioEntity = persistence.find(idUsuario);
        if (usuarioEntity == null)
            throw new BusinessLogicException("No se encuentra el usuario con id: " + idUsuario);
        return usuarioEntity.getReembolsos();
    }
    
    /**
     * Retorna un reembolso dado que haya realizado un usuario dado.
     * @param idUsuario
     * @param idReembolso
     * <pre> El reembolso ha sido creado relacionado con un usuario (No existen reembolsos sin usuario)
     * @return
     * @throws BusinessLogicException 
     *  Cuando no existe el Usuario.
     *  Cuando no existe el reembolso.
     *  Cuando el usuario no ha realizado el reembolso.
     */
    public ReembolsoEntity findUsuarioReembolso(long idUsuario,long idReembolso) throws BusinessLogicException{
        //Revision existencia del usuario
        UsuarioEntity usuarioEntity = persistence.find(idUsuario);
        if (usuarioEntity == null) {
            throw new BusinessLogicException("No se encuentra el usuario con id: " + idUsuario);
        }
        //Revision existencia del Reembolso
        ReembolsoEntity reembolso = reembolsoPersistence.find(idReembolso);
        if (reembolso == null) {
            throw new BusinessLogicException("No se encuentra el reembolso con id: " + idReembolso);
        }
        //Obtencion usuario del reembolso
        UsuarioEntity usuarioEnReembolso = reembolso.getUsuario();
        //Revicion: UsuarioEn Reembolso no es nulo
        if(usuarioEnReembolso == null){
            throw new BusinessLogicException("El usuario con id: " + idUsuario
            + ", no ha realizado el reembolso co id: " + idReembolso);
        }
        //Revicion emparentacion usuario-reembolso
        if(reembolso.getUsuario().getId()==idUsuario){
            return reembolso;
        }else{
            throw new BusinessLogicException("El usuario con id: " + idUsuario
            + ", no ha realizado el reembolso co id: " + idReembolso);
        }
    }
    
    /**
     * Crea un reembolso nuevo, eliminando la boleta que el usuario compro de sus compradas.
     * @param idUsuario
     * @param reembolso
     * @return
     * @throws BusinessLogicException
     *  Cuando no se encuentra el usuario.
     *  Cuando no se encuentra la boleta.
     *  Cuando la boleta no ha sido comprada.
     *  Cuando la boleta no ha sido comprada por el usuario.
     */
    public ReembolsoEntity createUsuarioReembolso(long idUsuario,ReembolsoEntity reembolso) throws BusinessLogicException,
            PersistenceException{
        //Revision existencia del usuario
        UsuarioEntity usuarioEntity = persistence.find(idUsuario);
        if (usuarioEntity == null){
            throw new BusinessLogicException("No se encuentra el usuario con id: " + idUsuario);
        }
        //Obtencion Boleta
        BoletaEntity boletaEnReembolso = reembolso.getBoleta();
        //Revision existencia de la Boleta a reembolsar
        BoletaEntity boletaEntity = boletaPersistence.find(boletaEnReembolso.getId());
        if (boletaEntity == null){
            throw new BusinessLogicException("No se encuentra la boleta con id: " + boletaEnReembolso.getId());
        }
        //Obteniendo usuarioEnBoleta
        UsuarioEntity usuarioEnBoleta = boletaEntity.getUsuario();
        //Revision: La boleta tiene usuario
        if(usuarioEnBoleta == null){
            throw new BusinessLogicException("La boleta existe pero no ha sido comprada.");
        }
        //Revisiom Usuario si posee la boleta
        if (usuarioEnBoleta.getId()!= idUsuario){
            throw new BusinessLogicException("El usuario con id: " + idUsuario
                    +", no ha comprado la boleta con id: " + boletaEntity.getId());
        }
        //Estableciendo boleta como "reembolsada"
        boletaEntity.setVendida(false);
        //Estableciendo usuario a boleta
        boletaEntity.setUsuario(null);
        //Eliminando boleta
        List<BoletaEntity> boletas = usuarioEntity.getBoletas();
        boletas.remove(boletaEntity);
        usuarioEntity.setBoletas(boletas);
        //Añadiendo reembolso a usuario
        List<ReembolsoEntity> reembolsos = usuarioEntity.getReembolsos();
        reembolsos.add(reembolso);
        usuarioEntity.setReembolsos(reembolsos);
        //finalizado
        reembolsoPersistence.create(reembolso);
        persistence.update(usuarioEntity);
        return reembolso;
    }
    
    //Los metodos de UPDATE y DELETE para usuarioReembolsos, no es aceptado para el usuarioCliente común
    //Estos metodos solo estan disponibles para el usuarioAdministrador, quien si puede manipular los
    //reembolsos de los demas ususarios y los propios.
    
    /**
     * Busca todas las boletas que el usuario con id dado ha comprado.
     * @param idUsuario
     * @return
     * @throws BusinessLogicException 
     *  Cuando no se encuentra el usuario.
     */
    public List<BoletaEntity> findUsuarioBoletas(long idUsuario) throws BusinessLogicException{
        //Revision existencia del usuario
        UsuarioEntity usuarioEntity = persistence.find(idUsuario);
        if (usuarioEntity == null){
            throw new BusinessLogicException("No se encuentra el usuario con id: " + idUsuario);
        }
        return usuarioEntity.getBoletas();     
    }
    
    /**
     * Busca una boleta comprada por un usuario.
     * @param idUsuario
     * @param idBoleta
     * @return
     * @throws BusinessLogicException
     *  Cuandono se encuentra el usuario.
     *  Cuando no se encuentra la boleta.
     *  Cuando la boleta no ha sido comprada.
     *  Cuando la boleta no ha sido comprada por el usuario.
     * @throws PersistenceException
     *  Cuando la informacion a crear no es unica o incmple alguna restriccion de persistencia.
     */
    public BoletaEntity findUsuarioBoleta(long idUsuario,long idBoleta) throws BusinessLogicException{
        //Revision existencia del usuario
        UsuarioEntity usuarioEntity = persistence.find(idUsuario);
        if (usuarioEntity == null) {
            throw new BusinessLogicException("No se encuentra el usuario con id: " + idUsuario);
        }
        //Revision existencia de la Boleta
        BoletaEntity boleta = boletaPersistence.find(idBoleta);
        if (boleta == null) {
            throw new BusinessLogicException("No se encuentra la boleta con id: " + idBoleta);
        }
        //Obtencion usuario en la boleta
        UsuarioEntity usuarioEnBoleta = boleta.getUsuario();
        //Revicion: UsuarioEn Reembolso no es nulo
        if(usuarioEnBoleta == null){
            throw new BusinessLogicException("La boleta con id :"
                    +  idBoleta+ " no ha sido comprada.");
        }
        //Revicion emparentacion usuario-reembolso
        if(boleta.getUsuario().getId()==idUsuario){
            return boleta;
        }else{
            throw new BusinessLogicException("El usuario con id: " + idUsuario
            + ", no ha realizado la compra de la boleta con id: " + idBoleta);
        }
    }
    
    /**
     * Crea una boleta con id dado, para un usuario que la compró
     * <pre> La boleta ya existe, pues no se pueden comprar boletas inexistentes.
     * @param idUsuario
     * @param idBoleta
     * @return
     * @throws BusinessLogicException 
     *  Cuando no se encuentra el usuario.
     *  Cuando no se encuentra la boleta.
     *  Cuando la boleta ya fue comprada.
     * @throws PersistenceException
     *  Cuando la informacion a crear no es unica o incmple alguna restriccion de persistencia.
     */
    public BoletaEntity createUsuarioBoleta(long idUsuario,long idBoleta) throws BusinessLogicException,
            PersistenceException{
        //Obtencion usuario
        UsuarioEntity usuarioEntity = persistence.find(idUsuario);
        //Verificacion usuario existe
        if(usuarioEntity == null){
            throw new BusinessLogicException("Usuario no encontrado con id: "+idUsuario);
        }
        //Obtencion de la Boleta
        BoletaEntity boletaEntity = boletaPersistence.find(idBoleta);
        //Verificacion Boleta existe
        if(boletaEntity == null){
            throw new BusinessLogicException("Boleta no encontrada con id: "+idBoleta);
        }
        //Obtencion usuario que ha comprado la boleta
        UsuarioEntity usuarioEnBoleta = boletaEntity.getUsuario();
        //Verificacion alguien no ha comprado la boleta antes
        if(usuarioEnBoleta!=null){
            throw new BusinessLogicException("Alguien ya ha comprado la boleta con id: "+idBoleta);
        }
        //Obtencion boletas del usuario
        List<BoletaEntity> usuarioBoletas = usuarioEntity.getBoletas();
        //Poniendo la nueva boleta
        boletaEntity.setUsuario(usuarioEntity);
        boletaEntity.setVendida(true);
        usuarioBoletas.add(boletaEntity);
        //Finalizando
        usuarioEntity.setBoletas(usuarioBoletas);
        boletaPersistence.update(boletaEntity);
        persistence.update(usuarioEntity);
        return boletaEntity;
    }
    
    //El metodo UPDATE para usuarioBoletas, no es aceptado para el usuarioCliente común
    //Este metodo solo esta disponible para el usuarioAdministrador, quien si puede manipular las
    //boletas de los demas ususarios y las propias.
    
    //En el caso del metodo DELETE, se toma como un caso particular de la adicion de un nuevo reembolso
    //(para un usuarioCliente), pues se hace el mismo proceso, en caso de eliminacion de una boleta sin reembolsar
    //es tenido en cuenta para el usuarioAdministrador.

}
