/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.planillas.gestionPlanilla.DTO;
import com.planillas.gestionPlanilla.DTO.AusenciasDTO; 
import java.time.LocalDate;
import java.sql.Date;

/**
 *
 * @author danth
 */
public class PermisoDTO extends AusenciasDTO {
    private boolean goce_salario;

    public PermisoDTO() {
    }

    public PermisoDTO(String empleadoId, Date fechaInicio, Date fechaFin, boolean goce_salario) {
        super(empleadoId, fechaInicio, fechaFin);
        this.goce_salario = goce_salario;
    }

    public boolean isGoce_salario() {
        return goce_salario;
    }

    public void setGoce_salario(boolean goce_salario) {
        this.goce_salario = goce_salario;
    }




}
