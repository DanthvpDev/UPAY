package com.planillas.gestionPlanilla.Models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


 @Data
 @Entity
 @Table(name="Empleados")
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
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Calendar fecha_pago1;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Calendar fecha_pago2;

    //* Relación con la entidad Empleado
    @ManyToOne(optional = false)
    @JoinColumn(name = "empleadoId", nullable = false)
    private Empleado empleado;

    //* Relación con la entidad Planilla    
    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "planillaId", nullable = false)
    private Planilla planilla;

    @OneToMany(mappedBy = "detalle_planilla")
    private List<Desglose_Ajuste> desglose_ajustes;
}
