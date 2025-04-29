package com.planillas.gestionPlanilla.Services;

import java.time.LocalDate;
import java.util.List;

import com.planillas.gestionPlanilla.DTO.IncapacidadDTO;


public interface IIncapacidadService {
    public List<IncapacidadDTO> obtenerIncapacidadesPorMesActual(LocalDate mesAnterior);
}
