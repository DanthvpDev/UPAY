package com.planillas.gestionPlanilla.Models;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="Desglose_Ajustes")
public class Desglose_Ajuste implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long desgloseAjusteId;

    @NotNull
    @Min(value = 1)
    private double monto;

    @NotNull
    private String observaciones;


    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "detallesPlanillaId", nullable = false)
    private Detalle_Planilla detalle_planilla;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ajusteSalarialId", nullable = false)
    private Ajuste_Salarial ajuste_salarial;

    public Desglose_Ajuste(Ajuste_Salarial ajuste_salarial, long desgloseAjusteId, Detalle_Planilla detalle_planilla, double monto, String observaciones) {
        this.ajuste_salarial = ajuste_salarial;
        this.desgloseAjusteId = desgloseAjusteId;
        this.detalle_planilla = detalle_planilla;
        this.monto = monto;
        this.observaciones = observaciones;
    }

    

    public Desglose_Ajuste() {
        // Constructor vacío
    }

    public Desglose_Ajuste(Ajuste_Salarial ajuste_salarial, Detalle_Planilla detalle_planilla, double monto, String observaciones) {
        this.ajuste_salarial = ajuste_salarial;
        this.detalle_planilla = detalle_planilla;
        this.monto = monto;
        this.observaciones = observaciones;
    }

    public long getDesgloseAjusteId() {
        return desgloseAjusteId;
    }

    public void setDesgloseAjusteId(long desgloseAjusteId) {
        this.desgloseAjusteId = desgloseAjusteId;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Detalle_Planilla getDetalle_planilla() {
        return detalle_planilla;
    }

    public void setDetalle_planilla(Detalle_Planilla detalle_planilla) {
        this.detalle_planilla = detalle_planilla;
    }

    public Ajuste_Salarial getAjuste_salarial() {
        return ajuste_salarial;
    }

    public void setAjuste_salarial(Ajuste_Salarial ajuste_salarial) {
        this.ajuste_salarial = ajuste_salarial;
    }




}
