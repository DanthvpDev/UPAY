package com.planillas.gestionPlanilla.Models;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

 @Entity
 @Table(name="Ajustes_Salariales")
public class Ajuste_Salarial implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long ajusteSalarialId;

    @NotNull(message = "El nombre del ajuste es obligatorio.")
    private String descripcion;

    @NotNull(message = "La fecha de inicio es obligatoria.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha_inicio;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha_fin;

    @NotNull(message = "Debe especificar si es una deducción o un bono.")
    private boolean es_deduccion;
    
    @NotNull(message = "Debe especificar si es una deducción o un bono.")
    private boolean usa_anios;

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
    
    @OneToMany(mappedBy = "ajuste_salarial")
    private List<Desglose_Ajuste> desglose_ajustes;

    public Ajuste_Salarial(long ajusteSalarialId,
                            String descripcion,
                            Date fecha_fin,
                            Date fecha_inicio,
                            boolean es_deduccion,
                            boolean usa_anios,
                            boolean es_valor,
                            double valor,
                            int categoria,
                            String estado,
                            List<Desglose_Ajuste> desglose_ajustes) {
        this.ajusteSalarialId = ajusteSalarialId;
        this.descripcion = descripcion;
        this.fecha_inicio = fecha_inicio.toLocalDate();
        this.fecha_fin = fecha_fin.toLocalDate();
        this.es_deduccion = es_deduccion;
        this.usa_anios = usa_anios;
        this.es_valor = es_valor;
        this.valor = valor;
        this.categoria = categoria;
        this.estado = estado;
        this.desglose_ajustes = desglose_ajustes;
    }

    public Ajuste_Salarial() {
    }

    public long getAjusteSalarialId() {
        return ajusteSalarialId;
    }

    public void setAjusteSalarialId(long ajusteSalarialId) {
        this.ajusteSalarialId = ajusteSalarialId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDate getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDate fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public boolean isEs_deduccion() {
        return es_deduccion;
    }

    public void setEs_deduccion(boolean es_deduccion) {
        this.es_deduccion = es_deduccion;
    }

    public boolean isUsa_anios() {
        return usa_anios;
    }

    public void setUsa_anios(boolean usa_anios) {
        this.usa_anios = usa_anios;
    }

    public boolean isEs_valor() {
        return es_valor;
    }

    public void setEs_valor(boolean es_valor) {
        this.es_valor = es_valor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Desglose_Ajuste> getDesglose_ajustes() {
        return desglose_ajustes;
    }

    public void setDesglose_ajustes(List<Desglose_Ajuste> desglose_ajustes) {
        this.desglose_ajustes = desglose_ajustes;
    }

    
}
