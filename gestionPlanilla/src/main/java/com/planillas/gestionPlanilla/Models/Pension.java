package com.planillas.gestionPlanilla.Models;

import java.io.Serializable;
import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


 @Data
 @Entity
 @Table(name="Pensiones")
public class Pension implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Min(value = 0, message = "El ID de la pensión debe ser un número positivo.")
    private int id;

    @NotNull(message = "Debe ingresar un monto.")
    @Min(value = 1, message = "El monto no puede ser un valor negativo ni 0")
    private double monto;

    @NotNull(message = "La fecha de inicio es obligatoria.")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Calendar fecha_inicio;

    @NotNull(message = "La fecha de fin es obligatoria.")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Calendar fecha_fin;

    @Pattern(regexp = "ACT|INA", message = "El estado de la pensión solo puede ser activo o inactivo.")
    private String estado;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empleadoId", nullable = false)
    private Empleado empleado;
}
