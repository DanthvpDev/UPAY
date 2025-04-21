package com.planillas.gestionPlanilla.Models;

import java.io.Serializable;
import java.util.Calendar;

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

@Data
@Entity
@Table(name="Incapacidades")
public class Incapacidad implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Pattern(regexp = "EMB|ACC|ENF", message = "El tipo de incapacidad solo puede ser por maternidad, accidente o enfermedad.")
    @NotEmpty(message = "Debe ingresar un tipo de incapacidad.")
    private String tipo;

    @NotNull(message = "La fecha de inicio es obligatoria.")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Calendar fecha_Inicio;

    @NotNull(message = "La fecha de fin es obligatoria.")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Calendar fecha_Fin;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id", nullable = false)
    @NotNull(message = "Debe seleccionar un empleado.")
    private Empleado empleado;

}
