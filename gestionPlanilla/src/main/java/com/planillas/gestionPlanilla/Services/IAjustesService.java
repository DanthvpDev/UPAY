/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.planillas.gestionPlanilla.Services;

import java.util.List;

import com.planillas.gestionPlanilla.Models.Ajuste_Salarial;

/**
 *
 * @author danth
 */
public interface IAjustesService {
    public List<Ajuste_Salarial> obtenerAjustesSalariales();
    public double calcularPorcentaje(double valor, double salarioBase);
    
}
