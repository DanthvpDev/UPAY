/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.planillas.gestionPlanilla.DTO;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 *
 * @author Daniel
 */
public class InfoBaseNombramientoDTO {
    
    @NotNull(message="Este campo es obligatorio")
    private long id;
    @NotBlank(message="Este campo es obligatorio")
    private String id_empleado;
    @NotBlank(message="Este campo es obligatorio")
    private String nombre_completo;
    @NotNull(message="Este campo es obligatorio")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat (pattern="yyyy-MM-dd")
    private Calendar fecha_inicio;
    @NotNull(message="Este campo es obligatorio")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat (pattern="yyyy-MM-dd")
    private Calendar fecha_fin;
    @NotBlank(message="Este campo es obligatorio")
    private String puesto;
    @NotNull(message="Este campo es obligatorio")
    private long puesto_id;

    public InfoBaseNombramientoDTO() {
    }

    public InfoBaseNombramientoDTO(long id, String id_empleado, String nombre_completo, Date fecha_inicio,
            Date fecha_fin, String puesto, long puesto_id) {
        this.id = id;
        this.id_empleado = id_empleado;
        this.nombre_completo = nombre_completo;
        this.fecha_inicio.setTime(fecha_inicio);
        this.fecha_fin.setTime(fecha_fin);
        this.puesto = puesto;
        this.puesto_id = puesto_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(String id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public Calendar getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Calendar fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Calendar getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Calendar fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public long getPuesto_id() {
        return puesto_id;
    }

    public void setPuesto_id(long puesto_id) {
        this.puesto_id = puesto_id;
    }


    
    

}
