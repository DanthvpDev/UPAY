package com.planillas.gestionPlanilla.Models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


 @Data
 @Entity
 @Table(name="Grados_Academicos")
public class Carrera_Profesional implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long carreraProfesionalId;

    @NotEmpty(message = "El nombre del título es obligatorio.")
    private String nombre_cert;

    @NotEmpty(message = "El nombre de la institución es obligatorio.")
    private String nombre_institucion;

    @Min(value = 1955, message = "El año mínimo de obtención es 1955.")
    private int anio;

    private boolean borrado;

    //* Relación con la entidad Empleado
    @ManyToOne(optional = false)
    @JoinColumn(name = "empleadoId", nullable = false)
    @NotNull(message = "Debe seleccionar el empleado")
    private Empleado empleado;

    //* Relación con la entidad Grado_Academico
    @ManyToOne(optional = false)
    @JoinColumn(name = "gradoAcademicoId", nullable = false)
    @NotNull(message = "Debe seleccionar el grado académico")
    private Grado_Academico grado_academico;
}
