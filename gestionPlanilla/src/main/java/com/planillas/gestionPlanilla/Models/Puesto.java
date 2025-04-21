package com.planillas.gestionPlanilla.Models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import jakarta.validation.constraints.Min;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Data
@Entity
@Table(name="Puestos")
public class Puesto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int puestoId;

    @NotEmpty(message = "El nombre del puesto es obligatorio.")
    private String nombre;


    @NotNull(message = "El salario base es obligatorio.")
    @Min(value = 1, message = "El salario base debe ser mayor a 0.")
    private double salario_base;

    @NotNull(message = "El salario global es obligatorio.")
    @Min(value = 1, message = "El salario global debe ser mayor a 0.")
    private double salario_global;

    @NotNull(message = "La categoría del puesto es obligatoria.")
    @Min(value = 0, message = "La categoría debe ser un número entre 0 y 2.")
    private int categoria;

    @Pattern(regexp = "ACT|INA|ELM", message = "El estado del puesto solo puede ser activo o inactivo.")
    private String estado;

    
    //* Relación con la entidad Nombramientos
    @OneToMany(mappedBy = "puesto")
    private List<Nombramiento> nombramientos;
    

}
