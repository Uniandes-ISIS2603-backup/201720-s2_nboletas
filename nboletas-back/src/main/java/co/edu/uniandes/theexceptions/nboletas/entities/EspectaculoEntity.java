/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.theexceptions.nboletas.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author df.riveros11
 */
@Entity
public class EspectaculoEntity extends BaseEntity implements Serializable {
    
    @PodamExclude
    @ManyToOne(fetch = FetchType.LAZY)
    private OrganizadorEntity organizador;
  
    
}