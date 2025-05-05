/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.planillas.gestionPlanilla.DTO;

import java.math.BigDecimal;

/**
 *
 * @author danth
 */
public class PensionDTO {
    private String empleado_id;
    private double monto;
    
    public PensionDTO() {
    }

    public PensionDTO(String empleado_id, BigDecimal monto) {
        this.empleado_id = empleado_id;
        this.monto = monto.doubleValue();
    }

    public String getEmpleado_id() {
        return empleado_id;
    }
    public void setEmpleado_id(String empleado_id) {
        this.empleado_id = empleado_id;
    }
    public double getMonto() {
        return monto;
    }
    public void setMonto(double monto) {
        this.monto = monto;
    }
}
