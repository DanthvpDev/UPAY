package com.planillas.gestionPlanilla.DTO;

import java.sql.Date;
import com.planillas.gestionPlanilla.DTO.AusenciasDTO; 


public class IncapacidadDTO extends AusenciasDTO {
    public IncapacidadDTO(String empleadoId, Date fechaInicio, Date fechaFin) {
        super(empleadoId, fechaInicio, fechaFin);
    }   
}
