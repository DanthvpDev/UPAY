/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.planillas.gestionPlanilla.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

/**
 *
 * @author danth
 */

 @Entity
 @Table(name = "topes_renta")
public class Topes_Renta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Min(value = 0)
    private double monto_tope;

    @NotNull
    @Min(value = 0)
    private double monto_base;

    @NotNull
    @Min(value = 2000)
    private int anio;

    @NotNull
    @Min(value = 0)
    private double porcentaje;

    @NotNull
    @Pattern(regexp = "ACT|INA")
    private String estado;

    public Topes_Renta(long id, double monto_tope, double monto_base, int anio, double porcentaje, String estado) {
        this.id = id;
        this.monto_tope = monto_tope;
        this.monto_base = monto_base;
        this.anio = anio;
        this.porcentaje = porcentaje;
        this.estado = estado;
    }

    public Topes_Renta() {
    } 

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getMonto_tope() {
        return monto_tope;
    }

    public void setMonto_tope(double monto_tope) {
        this.monto_tope = monto_tope;
    }

    public double getMonto_base() {
        return monto_base;
    }

    public void setMonto_base(double monto_base) {
        this.monto_base = monto_base;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
