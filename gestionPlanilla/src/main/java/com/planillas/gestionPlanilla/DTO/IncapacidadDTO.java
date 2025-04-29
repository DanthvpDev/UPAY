package com.planillas.gestionPlanilla.DTO;

import java.sql.Date;
import java.time.LocalDate;


public class IncapacidadDTO {
    private String empleadoId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public IncapacidadDTO(String empleadoId, Date fechaInicio, Date fechaFin) {
        this.empleadoId = empleadoId;
        this.fechaInicio = fechaInicio.toLocalDate();
        this.fechaFin = fechaFin.toLocalDate();
    }
    
    public IncapacidadDTO() {
    }

    public String getEmpleadoId() {
        return empleadoId;
    }
    public void setEmpleadoId(String empleadoId) {
        this.empleadoId = empleadoId;
    }
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public LocalDate getFechaFin() {
        return fechaFin;
    }
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("IncapacidadDTO{");
        sb.append("empleadoId=").append(empleadoId);
        sb.append(", fechaInicio=").append(fechaInicio);
        sb.append(", fechaFin=").append(fechaFin);
        sb.append('}');
        return sb.toString();
    }

    
}
