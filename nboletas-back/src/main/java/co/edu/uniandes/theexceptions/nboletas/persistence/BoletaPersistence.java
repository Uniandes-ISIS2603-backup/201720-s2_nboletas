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
package co.edu.uniandes.theexceptions.nboletas.persistence;

import co.edu.uniandes.theexceptions.nboletas.entities.BoletaEntity;
import javax.ejb.Stateless;
import javax.persistence.PersistenceException;

/**
 *
 * @author ISIS2603
 */
@Stateless
public class BoletaPersistence extends AbstractPersistence<BoletaEntity> {

    public BoletaPersistence() {
        super(BoletaEntity.class);
    }

    @Override
    public BoletaEntity update(BoletaEntity entity) throws PersistenceException {
        String query = "UPDATE APP.BOLETAENTITY SET VENDIDA = ";
        if (entity.isVendida()) {
            query += "" + 1;
        } else {
            query += "" + 0;
        }
        if (entity.getPrecio() >= 0) {
            query += ", PRECIO = " + entity.getPrecio();
        }
        if (entity.getFuncion() != null) {
            query += ", FUNCION_ID = " + entity.getFuncion().getId();
        }
        if (entity.getSilla() != null) {
            query += ", SILLA_ID = " + entity.getSilla().getId();
        }
        if (entity.getUsuario() != null) {
            query += ", USUARIO_ID = " + entity.getUsuario().getId();
        }
        if (entity.getImagen()!= null) {
            query += ", IMAGEN = '" + entity.getImagen() + "'";
        }
        query += " WHERE ID = " + entity.getId();
        em.createNativeQuery(query).executeUpdate();
        return entity;
    }
}
