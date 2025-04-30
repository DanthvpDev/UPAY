package com.planillas.gestionPlanilla.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;

import com.planillas.gestionPlanilla.DAO.IIncapacidadDao;
import com.planillas.gestionPlanilla.DTO.IncapacidadDTO;

@Service
public class IncapacidadService implements IIncapacidadService {
    
    @Autowired
    private IIncapacidadDao incapacidadDao;

    @Override
    public List<IncapacidadDTO> obtenerIncapacidadesPorMesActual(LocalDate mesAnterior) {
        return (List<IncapacidadDTO>)incapacidadDao.findByFechaInicioAndFechaFinMesActual(mesAnterior);
    }

    @Override
    public int calcularDiasIncapacidad(List<IncapacidadDTO> incapacidades, LocalDate fechaHoy) {
        int diasIncapacidad = 0;
        YearMonth mesAnterior = YearMonth.from(fechaHoy).minusMonths(1);
        LocalDate primerDiaMesAnterior = mesAnterior.atDay(1);
        LocalDate ultimoDiaMesAnterior = mesAnterior.atEndOfMonth();
        
        //*Itera sobre las incapacidades 
        for (IncapacidadDTO incapacidad : incapacidades) {
            LocalDate fechaInicio = incapacidad.getFechaInicio();
            LocalDate fechaFin = incapacidad.getFechaFin();

            //? Si la fecha de inicio de la incapacidad es de un mes anterior al mes pasado, entonces se utiliza el primer día del mes pasado, sino se utiliza la fecha de inicio de la incapacidad
            LocalDate fechaInicioSolapamiento = fechaInicio.isBefore(primerDiaMesAnterior) ? primerDiaMesAnterior : fechaInicio;
            
            //? Si la fecha de fin de la incapacidad es de un mes posterior al mes pasado, entonces se utiliza el último día del mes pasado, sino se utiliza la fecha de fin de la incapacidad
            LocalDate fechaFinSolapamiento = fechaFin.isAfter(ultimoDiaMesAnterior) ? ultimoDiaMesAnterior : fechaFin;

            if(!fechaInicioSolapamiento.isAfter(fechaFinSolapamiento)) {
                diasIncapacidad += (int)ChronoUnit.DAYS.between(fechaInicioSolapamiento, fechaFinSolapamiento) + 1;
            }

        }  
        if(diasIncapacidad > 20) return 20;
        else if(diasIncapacidad < 0) return 0;
        else return diasIncapacidad;
    }
}
