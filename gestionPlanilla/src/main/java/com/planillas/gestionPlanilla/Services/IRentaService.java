/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.planillas.gestionPlanilla.Services;

import java.util.List;

import com.planillas.gestionPlanilla.DTO.RentaDTO;

/**
 *
 * @author danth
 */
public interface IRentaService {
    public List<RentaDTO> obtenerTopesRenta(String estado);
    public double calcularMontoRenta(double salario, List<RentaDTO> tramos);
}
