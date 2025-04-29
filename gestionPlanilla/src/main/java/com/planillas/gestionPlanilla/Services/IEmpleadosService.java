/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.planillas.gestionPlanilla.Services;

import java.util.List;

import com.planillas.gestionPlanilla.DTO.EmpleadoDTO;
import com.planillas.gestionPlanilla.Models.Empleado;

/**
 *
 * @author danth
 */
public interface IEmpleadosService {
    public List<Empleado> listarEmpleadosId();
}
