package com.planillas.gestionPlanilla.Models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


 @Data
 @Entity
 @Table(name="Empleados")
public class Ajuste_Salarial implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ajusteSalarialId;

    @NotNull(message = "El nombre del ajuste es obligatorio.")
    private String descripcion;

    @NotNull(message = "La fecha de inicio es obligatoria.")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Calendar fecha_inicio;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Calendar fecha_fin;

    @NotNull(message = "Debe especificar si es una deducción o un bono.")
    private boolean es_deduccion;

    @NotNull(message = "Debe especificar si es un valor o un porcentaje.")
    private boolean es_valor;

    @NotNull(message = "El valor del ajuste es obligatorio.")
    @Min(value = 1, message = "El valor del ajuste debe ser mayor a 0.")
    private double valor;
    
    @Min(value = 1, message = "El porcentaje del ajuste debe ser mayor a 0.")
    @Max(value = 3, message = "El porcentaje del ajuste debe ser menor a 2.")
    private int categoria;

    @NotNull(message = "El estado del ajuste es obligatorio.")
    @Pattern(regexp = "ACT|INA", message = "El estado del ajuste solo puede ser activo o inactivo.")
    private String estado;
    
    @OneToMany(mappedBy = "detalle_planilla")
    private List<Desglose_Ajuste> desglose_ajustes;
}
