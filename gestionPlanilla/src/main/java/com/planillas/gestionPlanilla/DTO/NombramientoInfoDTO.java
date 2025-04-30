/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.planillas.gestionPlanilla.DTO;

import java.time.LocalDate;
import java.sql.Date;

/**
 *
 * @author danth
 */
public class NombramientoInfoDTO {
    private double salario_base;
    private double salario_global;
    private double salario_hora;
    private boolean es_SalarioGlobal;
    private int categoria;
    private String id_empleado;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;

    public NombramientoInfoDTO() {
    }

    public NombramientoInfoDTO(String id_empleado, double salario_base, double salario_global, boolean es_SalarioGlobal, int categoria, Date fecha_inicio, Date fecha_fin) {
        this.salario_base = salario_base;
        this.salario_global = salario_global;
        this.es_SalarioGlobal = es_SalarioGlobal;
        if(es_SalarioGlobal) {
            this.salario_hora = salario_global / 20;
        } else {
            this.salario_hora = salario_base / 20;
        }
        this.categoria = categoria;
        this.id_empleado = id_empleado;
        this.fecha_inicio = fecha_inicio.toLocalDate();
        this.fecha_fin = fecha_fin.toLocalDate();
    }

    public NombramientoInfoDTO(String id_empleado, double salario_base, double salario_global, boolean es_SalarioGlobal, int categoria, LocalDate fecha_inicio, LocalDate fecha_fin) {
        this.salario_base = salario_base;
        this.salario_global = salario_global;
        this.es_SalarioGlobal = es_SalarioGlobal;
        if(es_SalarioGlobal) {
            this.salario_hora = salario_global / 20;
        } else {
            this.salario_hora = salario_base / 20;
        }
        this.categoria = categoria;
        this.id_empleado = id_empleado;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    public double getSalario_base() {
        return salario_base;
    }

    public void setSalario_base(double salario_base) {
        this.salario_base = salario_base;
    }

    public double getSalario_global() {
        return salario_global;
    }

    public void setSalario_global(double salario_global) {
        this.salario_global = salario_global;
    }

    public double getSalario_hora() {
        return salario_hora;
    }

    public boolean isEs_SalarioGlobal() {
        return es_SalarioGlobal;
    }

    public void setEs_SalarioGlobal(boolean es_SalarioGlobal) {
        this.es_SalarioGlobal = es_SalarioGlobal;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    
}
