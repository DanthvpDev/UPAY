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

}
