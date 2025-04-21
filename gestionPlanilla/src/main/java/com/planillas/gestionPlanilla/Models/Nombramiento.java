package com.planillas.gestionPlanilla.Models;

import java.io.Serializable;

import java.util.Calendar;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Min;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Data
@Entity
@Table(name="Nombramientos")
public class Nombramiento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long nombramientoId;

    @NotNull(message = "La fecha de inicio es obligatoria.")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Calendar fecha_inicio;

    @NotNull(message = "La fecha de fin es obligatoria.")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Calendar fecha_fin;

    @Pattern(regexp = "ACT|INA", message = "El estado del puesto solo puede ser activo o inactivo.")
    private String estado;

    @NotNull(message = "Debe definir si es recibe salario global o compuesto.")
    private boolean Es_SalarioGlobal;


    //* Relación con la entidad Empleados
    @NotNull(message = "Debe seleccionar un empleado.")
    @ManyToOne(optional = false)
    @JoinColumn(name = "empleadoId", nullable = false)
    private Empleado empleado;


    //* Relación con la entidad Puestos
    @NotNull(message = "Debe seleccionar un puesto.")
    @ManyToOne(optional = false)
    @JoinColumn(name = "puestoId", nullable = false)
    private Puesto puesto;
}
