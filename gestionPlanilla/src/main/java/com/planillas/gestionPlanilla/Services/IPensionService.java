/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.planillas.gestionPlanilla.Services;

import java.util.List;

import com.planillas.gestionPlanilla.DTO.PensionDTO;

/**
 *
 * @author danth
 */
public interface IPensionService {
    public List<PensionDTO> obtenerPensionesActivas(String estado);
    public double calcularTotalPensiones(List<PensionDTO> pensiones, double salarioNeto);
}
