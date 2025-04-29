package com.planillas.gestionPlanilla.Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="Planillas")
public class Planilla implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "Id")
    private long planillaId;

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha_calculo = LocalDate.now();

    @NotNull
    private boolean es_extraordinaria;
    
    @NotNull
    @Min(1)
    @Max(12)
    private int mes;
    
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

    public Planilla() {
    }

    public Planilla(long planillaId, Date fecha_calculo, int mes, String tipo_planilla) {
        this.planillaId = planillaId;
        this.fecha_calculo = fecha_calculo.toLocalDate();
        if(tipo_planilla.equals("EXT")) {
            this.es_extraordinaria = true;
        } else {
            this.es_extraordinaria = false;
        }
        this.mes = mes;
        this.tipo_planilla = tipo_planilla;
    }

    public Planilla(long planillaId, Date fecha_calculo, boolean es_extraordinaria, int mes, String tipo_planilla) {
        this.planillaId = planillaId;
        this.fecha_calculo = fecha_calculo.toLocalDate();
        this.es_extraordinaria = es_extraordinaria;
        this.mes = mes;
        this.tipo_planilla = tipo_planilla;
    }

    public long getPlanillaId() {
        return planillaId;
    }

    public void setPlanillaId(long planillaId) {
        this.planillaId = planillaId;
    }

    public LocalDate getFecha_calculo() {
        return fecha_calculo;
    }

    public void setFecha_calculo(LocalDate fecha_calculo) {
        this.fecha_calculo = fecha_calculo;
    }

    public boolean isEs_extraordinaria() {
        return es_extraordinaria;
    }

    public void setEs_extraordinaria(boolean es_extraordinaria) {
        this.es_extraordinaria = es_extraordinaria;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String getTipo_planilla() {
        return tipo_planilla;
    }

    public void setTipo_planilla(String tipo_planilla) {
        this.tipo_planilla = tipo_planilla;
    }

    public List<Detalle_Planilla> getDetalles_planilla() {
        return detalles_planilla;
    }

    public void setDetalles_planilla(List<Detalle_Planilla> detalles_planilla) {
        this.detalles_planilla = detalles_planilla;
    }


    
}
