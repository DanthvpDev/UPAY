package com.planillas.gestionPlanilla.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
}
