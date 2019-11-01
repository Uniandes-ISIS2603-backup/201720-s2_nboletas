/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.netbeans.rest.application.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author df.riveros11
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(co.edu.uniandes.theexceptions.nboletas.mappers.BusinessLogicExceptionMapper.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.mappers.UnsupportedOperationExceptionMapper.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.mappers.WebApplicationExceptionMapper.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.ArtistaEspectaculoResources.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.ArtistaResource.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.BoletaComentarioResource.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.BoletaEnvioResource.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.BoletaReembolsoResource.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.BoletaResource.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.ComentarioResource.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.DivisionDeLugarResource.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.DivisionDeLugarSillaResource.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.EnvioResource.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.EspectaculoArtistaResource.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.EspectaculoComentarioResouce.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.EspectaculoFuncionResource.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.EspectaculoOrganizadorResources.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.EspectaculoResource.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.FuncionBoletasResource.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.FuncionResource.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.LugarDivisionesDeLugarResource.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.LugarFuncionesResource.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.LugarResource.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.OrganizadorEspectaculoResources.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.OrganizadorResource.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.ReembolsoResource.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.SillaBoletaResource.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.SillaResource.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.UsuarioBoletaResource.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.UsuarioReembolsoResource.class);
        resources.add(co.edu.uniandes.theexceptions.nboletas.resources.UsuarioResource.class);
    }

}
