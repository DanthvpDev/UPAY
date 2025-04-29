package com.planillas.gestionPlanilla.Models;

import java.io.Serializable;
import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


@Entity
@Table(name="Incapacidades")
public class Incapacidad implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long incapacidadId;

    @Pattern(regexp = "EMB|ACC|ENF", message = "El tipo de incapacidad solo puede ser por maternidad, accidente o enfermedad.")
    @NotEmpty(message = "Debe ingresar un tipo de incapacidad.")
    private String tipo;

    @NotNull(message = "La fecha de inicio es obligatoria.")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha_Inicio;

    @NotNull(message = "La fecha de fin es obligatoria.")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha_Fin;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empleadoId", nullable = false)
    @NotNull(message = "Debe seleccionar un empleado.")
    private Empleado empleado;

    public long getIncapacidadId() {
        return incapacidadId;
    }

    public void setIncapacidadId(long incapacidadId) {
        this.incapacidadId = incapacidadId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFecha_Inicio() {
        return fecha_Inicio;
    }

    public void setFecha_Inicio(LocalDate fecha_Inicio) {
        this.fecha_Inicio = fecha_Inicio;
    }

    public LocalDate getFecha_Fin() {
        return fecha_Fin;
    }

    public void setFecha_Fin(LocalDate fecha_Fin) {
        this.fecha_Fin = fecha_Fin;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    

}
