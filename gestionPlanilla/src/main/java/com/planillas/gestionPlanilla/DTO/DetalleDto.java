/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.planillas.gestionPlanilla.DTO;

import java.time.LocalDate;

/**
 *
 * @author danth
 */
public class DetalleDto {
    public long id;
    public String nombreEmpleado;
    private double primer_pago;
    private double segundo_pago;
    private double salario;
    private double salario_neto;
    private double salario_bruto;
    private double pensiones_total;
    private double subsidio;
    private double renta_total;
    private int dias_trabajados;
    private LocalDate fecha_pago1;
    private LocalDate fecha_pago2;
    private String empleadoId;
    private long planillaId;

    public DetalleDto(long id, String empleadoId, long planillaId, String nombreEmpleado, double primer_pago, double segundo_pago, double salario, double salario_neto, double salario_bruto, double pensiones_total, double subsidio, double renta_total, int dias_trabajados, LocalDate fecha_pago1, LocalDate fecha_pago2) {
        this.id = id;
        this.nombreEmpleado = nombreEmpleado;
        this.primer_pago = primer_pago;
        this.segundo_pago = segundo_pago;
        this.salario = salario;
        this.salario_neto = salario_neto;
        this.salario_bruto = salario_bruto;
        this.pensiones_total = pensiones_total;
        this.subsidio = subsidio;
        this.renta_total = renta_total;
        this.dias_trabajados = dias_trabajados;
        this.fecha_pago1 = fecha_pago1;
        this.fecha_pago2 = fecha_pago2;
        this.planillaId = planillaId;
        this.empleadoId = empleadoId;
    }

    public DetalleDto(String empleadoId, long planillaId, int dias_trabajados) {
        this.planillaId = planillaId;
        this.empleadoId = empleadoId;
        this.dias_trabajados = dias_trabajados;
        this.primer_pago = 1;
        this.segundo_pago = 1;
        this.salario = 1;
        this.salario_neto = 1;
        this.salario_bruto = 1;
        this.renta_total = 1;
        this.fecha_pago1 = LocalDate.now();
        this.fecha_pago2 = LocalDate.now();
    }

    public DetalleDto() {
        this.dias_trabajados = dias_trabajados;
        this.primer_pago = 1;
        this.segundo_pago = 1;
        this.salario = 1;
        this.salario_neto = 1;
        this.salario_bruto = 1;
        this.renta_total = 1;
        this.fecha_pago1 = LocalDate.now();
        this.fecha_pago2 = LocalDate.now();
    }

    public long getId() {
        return id;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public double getPrimer_pago() {
        return primer_pago;
    }

    public void setPrimer_pago(double primer_pago) {
        this.primer_pago = primer_pago;
    }

    public double getSegundo_pago() {
        return segundo_pago;
    }

    public void setSegundo_pago(double segundo_pago) {
        this.segundo_pago = segundo_pago;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public double getSalario_neto() {
        return salario_neto;
    }

    public void setSalario_neto(double salario_neto) {
        this.salario_neto = salario_neto;
    }

    public double getSalario_bruto() {
        return salario_bruto;
    }

    public void setSalario_bruto(double salario_bruto) {
        this.salario_bruto = salario_bruto;
    }

    public double getPensiones_total() {
        return pensiones_total;
    }

    public void setPensiones_total(double pensiones_total) {
        this.pensiones_total = pensiones_total;
    }

    public double getSubsidio() {
        return subsidio;
    }

    public void setSubsidio(double subsidio) {
        this.subsidio = subsidio;
    }

    public double getRenta_total() {
        return renta_total;
    }

    public void setRenta_total(double renta_total) {
        this.renta_total = renta_total;
    }

    public int getDias_trabajados() {
        return dias_trabajados;
    }

    public void setDias_trabajados(int dias_trabajados) {
        this.dias_trabajados = dias_trabajados;
    }

    public LocalDate getFecha_pago1() {
        return fecha_pago1;
    }

    public void setFecha_pago1(LocalDate fecha_pago1) {
        this.fecha_pago1 = fecha_pago1;
    }

    public LocalDate getFecha_pago2() {
        return fecha_pago2;
    }

    public void setFecha_pago2(LocalDate fecha_pago2) {
        this.fecha_pago2 = fecha_pago2;
    }

    public String getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(String empleadoId) {
        this.empleadoId = empleadoId;
    }

    public long getPlanillaId() {
        return planillaId;
    }

    public void setPlanillaId(long planillaId) {
        this.planillaId = planillaId;
    }



}
