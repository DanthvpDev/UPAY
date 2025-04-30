package com.planillas.gestionPlanilla.Models;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.planillas.gestionPlanilla.DTO.NombramientoInfoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Data
@Entity
@Table(name="Nombramientos")
public class Nombramiento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long nombramientoId;

    @NotNull(message = "La fecha de inicio es obligatoria.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "fechai_Nombramiento")
    private LocalDate fecha_inicio;

    @NotNull(message = "La fecha de fin es obligatoria.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "fechaf_Nombramiento")
    private LocalDate fecha_fin;

    @Pattern(regexp = "ACT|INA", message = "El estado del puesto solo puede ser activo o inactivo.")
    private String estado;

    @NotNull(message = "Debe definir si es recibe salario global o compuesto.")
    @Column(name = "es_Salarioglobal")
    private boolean Es_SalarioGlobal;


    //* Relación con la entidad Empleados
    @NotNull(message = "Debe seleccionar un empleado.")
    @ManyToOne(optional = false)
    @JoinColumn(name = "empleadoId", nullable = false)
    private Empleado empleado;


    //* Relación con la entidad Puestos
    @NotNull(message = "Debe seleccionar un puesto.")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "puestoId", nullable = false)
    private Puesto puesto;

    public long getNombramientoId() {
        return nombramientoId;
    }

    public void setNombramientoId(long nombramientoId) {
        this.nombramientoId = nombramientoId;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isEs_SalarioGlobal() {
        return Es_SalarioGlobal;
    }

    public void setEs_SalarioGlobal(boolean Es_SalarioGlobal) {
        this.Es_SalarioGlobal = Es_SalarioGlobal;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public NombramientoInfoDTO getInfoNombramiento() {

        NombramientoInfoDTO nombramientoInfo = new NombramientoInfoDTO("", getPuesto().getSalario_base(), getPuesto().getSalario_global(), isEs_SalarioGlobal(), getPuesto().getCategoria(), getFecha_inicio(), getFecha_fin());
        
        return nombramientoInfo;
    }


}
