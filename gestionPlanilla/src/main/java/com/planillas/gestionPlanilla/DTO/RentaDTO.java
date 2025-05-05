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
public class RentaDTO {
    private double monto_base;
    private double monto_top;
    private double porcentaje;

    public RentaDTO() {
    }

    public RentaDTO(BigDecimal monto_base, BigDecimal monto_top, BigDecimal porcentaje) {
        this.monto_base = monto_base.doubleValue();
        this.monto_top = monto_top.doubleValue();
        this.porcentaje = porcentaje.doubleValue();
    }

    public void setMonto_base(double monto_base) {
        this.monto_base = monto_base;
    }

    public void setMonto_top(double monto_top) {
        this.monto_top = monto_top;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public double getMonto_base() {
        return monto_base;
    }
    public double getMonto_tope() {
        return monto_top;
    }
    public double getPorcentaje() {
        return porcentaje;
    }


}
