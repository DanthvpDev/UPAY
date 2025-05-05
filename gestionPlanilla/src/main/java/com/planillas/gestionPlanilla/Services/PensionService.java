/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.planillas.gestionPlanilla.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planillas.gestionPlanilla.DAO.IPensionDAO;
import com.planillas.gestionPlanilla.DTO.PensionDTO;

/**
 *
 * @author danth
 */

@Service
public class PensionService implements IPensionService {
    
    @Autowired
    private IPensionDAO pensionDAO;

    @Override
    public List<PensionDTO> obtenerPensionesActivas(String estado) {
        return (List<PensionDTO>) pensionDAO.findAllByEstado(estado);
    }

    @Override
    public double calcularTotalPensiones(List<PensionDTO> pensiones, double salarioNeto) {
        double monto = 0;
        double salario = salarioNeto;
        for (PensionDTO pension : pensiones) {
            if(monto <= salarioNeto) {
                monto += pension.getMonto();
                salario -= monto;
            }
        }
        return monto;
    }
}
