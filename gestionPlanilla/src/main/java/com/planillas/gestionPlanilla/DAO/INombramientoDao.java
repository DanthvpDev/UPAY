/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.planillas.gestionPlanilla.DAO;

import org.springframework.data.repository.CrudRepository;

import com.planillas.gestionPlanilla.DTO.NombramientoInfoDTO;
import com.planillas.gestionPlanilla.Models.Nombramiento;

/**
 *
 * @author danth
 */
public interface INombramientoDao extends CrudRepository<Nombramiento, Long> {
     public Iterable<NombramientoInfoDTO> findByEstado(String estado);
}
