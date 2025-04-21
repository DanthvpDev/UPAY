package com.planillas.gestionPlanilla.Models;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

public class Desglose_Ajuste implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Min(value = 1)
    private double monto;

    @NotNull
    private String observaciones;


    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Detalle_Planilla detalle_planilla;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Ajuste_Salarial ajuste_salarial;
}
