package com.planillas.gestionPlanilla.Models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private long id;

    @NotNull
    @Min(value = 1)
    private double primer_pago;

    @NotNull
    @Min(value = 1)
    private double segundo_pago;

    @NotNull
    @Min(value = 1)
    private double salario_neto;

    @NotNull
    @Min(value = 1)
    private double salario_bruto;

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
    @JoinColumn(name = "id", nullable = false)
    private Empleado empleado;

    //* Relación con la entidad Planilla    
    @ManyToOne(optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Planilla planilla;

    @OneToMany(mappedBy = "detalle_planilla")
    private List<Desglose_Ajuste> desglose_ajustes;
}
