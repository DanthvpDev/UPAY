/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.planillas.gestionPlanilla.Services;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.planillas.gestionPlanilla.DTO.AusenciasDTO;

/**
 *
 * @author danth
 */

 @Service
public class AusenciasService implements IAusenciasService {

    @Override
    /**
     * Calcula las ausencias de un empleado en un mes determinado.
     * @param ausencias Lista de ausencias del empleado.
     * @param fechaHoy Fecha actual.
     * @return Número de días de ausencia en el mes anterior.
     */
    public int calcularAusencias(List<? extends AusenciasDTO> ausencias, LocalDate fechaHoy) {
        int diasAusencia = 0;
        YearMonth mesAnterior = YearMonth.from(fechaHoy).minusMonths(1);
        LocalDate primerDiaMesAnterior = mesAnterior.atDay(1);
        LocalDate ultimoDiaMesAnterior = mesAnterior.atEndOfMonth();
        
        //*Itera sobre las ausencias 
        for (AusenciasDTO ausencia : ausencias) {
            LocalDate fechaInicio = ausencia.getFechaInicio();
            LocalDate fechaFin = ausencia.getFechaFin();

            //? Si la fecha de inicio de la ausencia es de un mes anterior al mes pasado, entonces se utiliza el primer día del mes pasado, sino se utiliza la fecha de inicio de la ausencia
            LocalDate fechaInicioSolapamiento = fechaInicio.isBefore(primerDiaMesAnterior) ? primerDiaMesAnterior : fechaInicio;
            
            //? Si la fecha de fin de la ausencia es de un mes posterior al mes pasado, entonces se utiliza el último día del mes pasado, sino se utiliza la fecha de fin de la ausencia
            LocalDate fechaFinSolapamiento = fechaFin.isAfter(ultimoDiaMesAnterior) ? ultimoDiaMesAnterior : fechaFin;

            if(!fechaInicioSolapamiento.isAfter(fechaFinSolapamiento)) {
                diasAusencia += (int)ChronoUnit.DAYS.between(fechaInicioSolapamiento, fechaFinSolapamiento) + 1;
            }

        }  
        if(diasAusencia > 20) return 20;
        else if(diasAusencia < 0) return 0;
        else return diasAusencia;
    }
}
