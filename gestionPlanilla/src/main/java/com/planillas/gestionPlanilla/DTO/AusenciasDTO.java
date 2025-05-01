/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.planillas.gestionPlanilla.DTO;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.sql.Date;
import java.util.List;
/**
 *
 * @author danth
 */
public class AusenciasDTO {
    protected String empleadoId;
    protected LocalDate fechaInicio;
    protected LocalDate fechaFin;
    
    public AusenciasDTO(String empleadoId, Date fechaInicio, Date fechaFin) {
        this.empleadoId = empleadoId;
        this.fechaInicio = fechaInicio.toLocalDate();
        this.fechaFin = fechaFin.toLocalDate();
    }
    public AusenciasDTO() {
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
    
}
