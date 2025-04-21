package com.planillas.gestionPlanilla.Models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
@Table(name="Planillas")
public class Planilla implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long planillaId;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Calendar fecha_calculo;

    @NotNull
    private boolean es_extraordinaria;
    
    
    // ?LAS OPCIONES DE TIPO PLANILLA SON:
    // ?REG: REGULAR,
    // ?EXT: EXTRAORDINARIA,
    // ?SE: SALARIO ESCOLAR,
    // ?AGU: AGUINALDO
    
    @Pattern(regexp = "REG|EXT|SE|AGU")
    private String tipo_planilla;


    //* Relación con la entidad Detalle_Planilla
    @OneToMany(mappedBy = "planilla")
    private List<Detalle_Planilla> detalles_planilla;
}
