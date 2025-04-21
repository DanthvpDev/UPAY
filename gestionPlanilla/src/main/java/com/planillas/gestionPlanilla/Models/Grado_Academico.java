package com.planillas.gestionPlanilla.Models;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


 @Data
 @Entity
 @Table(name="Grados_Academicos")
public class Grado_Academico implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "El grado académico es obligatorio.")
    @Pattern(regexp = "CURS|CERT|DIPL|BACH|LICI|MAES|DOCT", message = "El grado académico solo puede contener letras y números.")
    private String grado;

    @Min(value = 0, message = "Los puntos deben ser un número entre 0 y 4.")
    private int puntos;

    @Min(value = 0, message = "La categoría debe ser un número entre 0 y 2.")
    private int categoria;

    @OneToMany(mappedBy="grado_academico")
    private List<Carrera_Profesional> titulos;
}
