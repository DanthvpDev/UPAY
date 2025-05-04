/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.planillas.gestionPlanilla.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planillas.gestionPlanilla.DAO.IRentaDAO;
import com.planillas.gestionPlanilla.DTO.RentaDTO;

/**
 *
 * @author danth
 */

 @Service
public class RentaService implements IRentaService {

    @Autowired
    private IRentaDAO rentaDAO;

    @Override
    public List<RentaDTO> obtenerTopesRenta(String estado) {
        return (List<RentaDTO>) rentaDAO.findAllByEstado(estado);
    }

    @Override
    public double calcularMontoRenta(double salario, List<RentaDTO> tramos) {
        double monto = 0;
        int i = 1;
        if(salario > tramos.get(0).getMonto_tope()) {
            while (salario > tramos.get(i).getMonto_tope()) {
                double tope = tramos.get(i).getMonto_tope();
                double base = tramos.get(i).getMonto_base();
                double porcentaje = tramos.get(i).getPorcentaje();
                if (salario > base) {
                    if (salario >= tope) {
                        monto = (tope - base) * (porcentaje / 100);
                    } else {
                        monto = (salario - base) * (porcentaje / 100);
                    }
                }
                i++;
            }
        }
        return monto;
    }
    
}
