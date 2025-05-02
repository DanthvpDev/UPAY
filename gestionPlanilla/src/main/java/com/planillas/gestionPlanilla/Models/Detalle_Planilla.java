package com.planillas.gestionPlanilla.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

 @Entity
 @Table(name="Detalles_Planilla")
public class Detalle_Planilla implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long detallesPlanillaId;

    @NotNull
    @Min(value = 0)
    private double primer_pago;

    @NotNull
    @Min(value = 0)
    private double segundo_pago;

    @NotNull
    @Min(value = 0)
    private double salario_neto;
    
    @NotNull
    @Min(value = 0)
    private double salario;

    @NotNull
    @Min(value = 0)
    private double salario_bruto;

    @Min(value = 0)
    private double pensiones_total;
    
    @Min(value = 0)
    private double subsidio;
    
    @Min(value = 0)
    private double renta_total;

    @NotNull
    @Min(value = 0)
    private int dias_trabajados;

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha_pago1;

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha_pago2;

    //* Relación con la entidad Empleado
    @ManyToOne(optional = false)
    @JoinColumn(name = "empleadoId", nullable = false)
    private Empleado empleado;

    //* Relación con la entidad Planilla    
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "planillaId", nullable = false)
    private Planilla planilla;

    @OneToMany(mappedBy = "detalle_planilla", cascade = CascadeType.PERSIST)
    private List<Desglose_Ajuste> desglose_ajustes = new ArrayList<Desglose_Ajuste>();


    public Detalle_Planilla(Empleado empleado, Planilla planilla, int dias_trabajados) {
        this.planilla = planilla;
        this.empleado = empleado;
        this.dias_trabajados = dias_trabajados;
        this.primer_pago = 1;
        this.segundo_pago = 1;
        this.salario = 1;
        this.salario_neto = 0;
        this.salario_bruto = 2;
        this.renta_total = 1;
        this.fecha_pago1 = LocalDate.now();
        this.fecha_pago2 = LocalDate.now();
    }

    public Detalle_Planilla(Empleado empleado, Planilla planilla) {
        this.planilla = planilla;
        this.empleado = empleado;
        this.primer_pago = 1;
        this.segundo_pago = 1;
        this.salario = 1;
        this.salario_neto = 0;
        this.salario_bruto = 2;
        this.renta_total = 1;
        this.fecha_pago1 = LocalDate.now();
        this.fecha_pago2 = LocalDate.now();
    }


    public long getDetallesPlanillaId() {
        return detallesPlanillaId;
    }

    public void setDetallesPlanillaId(long detallesPlanillaId) {
        this.detallesPlanillaId = detallesPlanillaId;
    }

    public double getPrimer_pago() {
        return primer_pago;
    }

    public void setPrimer_pago(double salarioNeto) {
        this.primer_pago = salarioNeto * 0.6;
    }

    public double getSegundo_pago() {
        return segundo_pago;
    }

    public void setSegundo_pago(double salarioNeto) {
        this.segundo_pago = salarioNeto * 0.4;
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

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public List<Desglose_Ajuste> getDesglose_ajustes() {
        return desglose_ajustes;
    }

    public void setDesglose_ajustes(Desglose_Ajuste desglose_ajustes) {
        if(desglose_ajustes != null) {
            this.desglose_ajustes.add(desglose_ajustes);
            desglose_ajustes.setDetalle_planilla(this);
        }
    }



}
