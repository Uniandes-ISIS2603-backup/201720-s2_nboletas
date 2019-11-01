/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.ejb;

import co.edu.uniandes.theexceptions.nboletas.persistence.AbstractPersistence;
import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.TransactionRequiredException;

/**
 *
 * @author df.riveros11
 * @param <T> Entity
 */
public abstract class AbstractLogic<T> {

    protected abstract AbstractPersistence<T> getPersistence();

    /**
     * Crear un objeto tipo T
     *
     * @param entity
     * @return
     */
    public T create(T entity) {
        T create = null;
        try {
            create = getPersistence().create(entity);
        } catch (EntityExistsException e) {
            throw new EntityExistsException("La entidadde tipo " + entity.getClass().getSimpleName() + " ya existe", e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("La instancia no es una entidad", e);
        } catch (TransactionRequiredException e) {
            throw new TransactionRequiredException("No existe ninguna transaccion");
        }
        return create;
    }

    /**
     * Actualiza un obeto de tipo T
     *
     * @param entity
     * @return
     */
    public T update(T entity) {
        T update = null;
        try {
            update = getPersistence().update(entity);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("La instancia no es una entidad", e);
        } catch (TransactionRequiredException e) {
            throw new TransactionRequiredException("No existe ninguna transaccion");
        }
        return update;
    }

    /**
     * Elimina un objeto T
     *
     * @param entity
     */
    public void delete(T entity) {
        try {
            getPersistence().delete(entity);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("La instancia no es una entidad", e);
        } catch (TransactionRequiredException e) {
            throw new TransactionRequiredException("No existe ninguna transaccion");
        }
    }

    /**
     * Encuentra un tipo T al mandar un identificador
     *
     * @param id
     * @return
     */
    public T find(Object id){
        T find = null;
        try {
            find = getPersistence().find(id);
        }  catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("La instancia no es una entidad", e);
        }
        return find;
    }

    /**
     * Devuelve todo los objetos
     *
     * @return list con todos los objetos
     */
    public List<T> findAll() {
        List<T> findAll = null;
        try {
           findAll = getPersistence().findAll();
        } catch (IllegalStateException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        return findAll;
    }

}
