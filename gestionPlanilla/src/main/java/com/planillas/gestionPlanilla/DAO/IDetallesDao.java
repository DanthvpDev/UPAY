/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.planillas.gestionPlanilla.DAO;

import org.springframework.data.repository.CrudRepository;

import com.planillas.gestionPlanilla.Models.Detalle_Planilla;

/**
 *
 * @author danth
 */
public interface IDetallesDao extends CrudRepository<Detalle_Planilla, Long> {

}
