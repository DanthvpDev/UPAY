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
        int i = 0;

        //? Verifica que el salario sea mayor al primer tope (de 0 a +900)

        while(i < tramos.size() && salario > tramos.get(i).getMonto_base()) {
            //? Verifica que el salario sea menor al tope
            if (salario <= tramos.get(i).getMonto_tope()) {
                //? Calcula el monto de la renta
                if(tramos.get(i).getMonto_base() == 0) {
                    //? Si el monto base es 0, no se le aplica el porcentaje
                    monto = 0;
                } else {
                    //? Si el monto base es diferente de 0, se le aplica el porcentaje
                    monto = (tramos.get(i).getMonto_tope() - tramos.get(i).getMonto_base()) * (tramos.get(i).getPorcentaje() / 100);
                }
                monto += (salario - tramos.get(i).getMonto_base()) * (tramos.get(i).getPorcentaje() / 100);
                break;
            } else {
                //? Si el salario es mayor al tope, calcula el monto de la renta
                monto += (tramos.get(i).getMonto_tope() - tramos.get(i).getMonto_base()) * (tramos.get(i).getPorcentaje() / 100);
            }
            i++;
        }
        return monto;
    }
    
}
