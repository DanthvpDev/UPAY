/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.planillas.gestionPlanilla.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planillas.gestionPlanilla.DAO.IAjustesSalarialesDAO;
import com.planillas.gestionPlanilla.Models.Ajuste_Salarial;

/**
 *
 * @author danth
 */
@Service
public class AjustesService implements IAjustesService {
    
    @Autowired
    private IAjustesSalarialesDAO ajustesSalariales;

    @Override
    public List<Ajuste_Salarial> obtenerAjustesSalariales() {
        return (List<Ajuste_Salarial>) ajustesSalariales.findAllByEstado("ACT");
    }

    @Override
    public double calcularPorcentaje(double porcentaje, double salarioBase) {
        return (porcentaje / 100) * salarioBase;
    } 

}
