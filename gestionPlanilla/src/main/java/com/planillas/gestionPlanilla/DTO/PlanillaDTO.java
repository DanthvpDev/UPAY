package com.planillas.gestionPlanilla.DTO;

import java.math.BigDecimal;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;


public class PlanillaDTO {
    private long id;
    private String mes;
    private int anio;
    private BigDecimal total_pagos;
    private long total_empleados;

    public PlanillaDTO(long id, int mes, int anio, BigDecimal total_pagos, long total_empleados) {
        this.id = id;
        this.mes = Month.of(mes).getDisplayName(TextStyle.FULL, Locale.of("es", "ES"));
        this.anio = anio;
        this.total_pagos = total_pagos;
        this.total_empleados = total_empleados;
    }
    
    public PlanillaDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = Month.of(mes).getDisplayName(TextStyle.FULL, Locale.of("es", "ES"));
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public BigDecimal getTotal_pagos() {
        return total_pagos;
    }

    public void setTotal_pagos(BigDecimal total_pagos) {
        this.total_pagos = total_pagos;
    }

    public long getTotal_empleados() {
        return total_empleados;
    }

    public void setTotal_empleados(long total_empleados) {
        this.total_empleados = total_empleados;
    }

    @Override
    public String toString() {
        return "id: " + id + ", mes: " + mes + ", anio: " + anio + ", total_pagos: " + total_pagos + ", total_empleados: " + total_empleados;
    }
}
