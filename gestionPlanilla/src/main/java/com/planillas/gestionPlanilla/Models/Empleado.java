package com.planillas.gestionPlanilla.Models;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


 @Data
 @Entity
 @Table(name="Empleados")

public class Empleado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Pattern(
        regexp = "^[0-9]{9}$",
        message = "El ID debe contener 9 dígitos numéricos. Utilice 0 en los espacios (10xxx0xxx)"
    )
    @Column(name = "Id")
    private String empleadoId;

    @Pattern(regexp = "^[A-Za-z]+$", message = "El nombre solo puede contener letras.")
    @NotEmpty(message = "El nombre es obligatorio.")
    private String nombre;

    @Pattern(regexp = "^[A-Za-z]+$", message = "El apellido solo puede contener letras.")
    @NotEmpty(message = "El primer apellido es obligatorio.")
    @Column(name = "apellido_1")
    private String apellido1;

    @Pattern(regexp = "^[A-Za-z]+$",message = "El apellido solo puede contener letras.")
    @Column(name = "apellido_2")
    private String apellido2;

    @NotBlank(message = "El correo es obligatorio.")
    @Email(message = "El correo debe tener un formato válido (correo@ejemplo.com).")
    private String correo;

    @NotBlank(message = "El teléfono es obligatorio.")
    @Pattern(regexp = "^[0-9]{8}$",message = "El teléfono debe contener 8 dígitos numéricos.")
    private String telefono;

    @NotNull(message = "La fecha de nacimiento es obligatoria.")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha_Nacimiento;

    private boolean borrado;

    //* Relación con la entidad Carrera_Profesional
    @OneToMany(mappedBy="empleado")
    private List<Carrera_Profesional> titulos;

    //* Relación con la entidad Pension
    @OneToMany(mappedBy="empleado")
    private List<Pension> pensiones;

    //* Relación con la entidad Permisos
    @OneToMany(mappedBy="empleado")
    private List<Permiso> permisos;

    //* Relación con la entidad incapacidad
    @OneToMany(mappedBy="empleado")
    private List<Incapacidad> incapacidades;

    //* Relación con la entidad Nombramiento
    @OneToMany(mappedBy="empleado", fetch = FetchType.EAGER)
    private List<Nombramiento> nombramientos;

    @OneToMany(mappedBy="empleado")
    private List<Detalle_Planilla> detalles_planilla;


    public Empleado() {
        this.borrado = false;
    }

    public Empleado(String id) {
        this.empleadoId = id;
    }

    public Empleado(String id, String nombre, String apellido1, String apellido2, String correo, String telefono, Date fecha_Nacimiento, boolean borrado) {
        this.empleadoId = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.correo = correo;
        this.telefono = telefono;
        this.fecha_Nacimiento = fecha_Nacimiento.toLocalDate();
        this.borrado = borrado;
    }

    public Optional<Nombramiento> getNombramientoActual() {
        LocalDate fechaActual = LocalDate.now();
        return nombramientos.stream()
                .filter(nombramiento -> nombramiento.getEstado().equals("ACT") 
                        && fechaActual.isAfter(nombramiento.getFecha_inicio()) 
                        && (nombramiento.getFecha_fin() == null || fechaActual.isBefore(nombramiento.getFecha_fin())))
                .findFirst();
    }

    public String getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(String empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFecha_Nacimiento() {
        return fecha_Nacimiento;
    }

    public void setFecha_Nacimiento(LocalDate fecha_Nacimiento) {
        this.fecha_Nacimiento = fecha_Nacimiento;
    }

    public boolean isBorrado() {
        return borrado;
    }

    public void setBorrado(boolean borrado) {
        this.borrado = borrado;
    }

    public List<Carrera_Profesional> getTitulos() {
        return titulos;
    }

    public void setTitulos(List<Carrera_Profesional> titulos) {
        this.titulos = titulos;
    }

    public List<Pension> getPensiones() {
        return pensiones;
    }

    public void setPensiones(List<Pension> pensiones) {
        this.pensiones = pensiones;
    }

    public List<Permiso> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<Permiso> permisos) {
        this.permisos = permisos;
    }

    public List<Incapacidad> getIncapacidades() {
        return incapacidades;
    }

    public void setIncapacidades(List<Incapacidad> incapacidades) {
        this.incapacidades = incapacidades;
    }

    public List<Nombramiento> getNombramientos() {
        return nombramientos;
    }

    public void setNombramientos(List<Nombramiento> nombramientos) {
        this.nombramientos = nombramientos;
    }

    public List<Detalle_Planilla> getDetalles_planilla() {
        return detalles_planilla;
    }

    public void setDetalles_planilla(List<Detalle_Planilla> detalles_planilla) {
        this.detalles_planilla = detalles_planilla;
    }

    public long getAniosLaborados() {
        //? Calcula los anios que tiene por cada nombramiento
        long aniosCompletos = 0;
        for(Nombramiento nombramientoEmpleado : nombramientos) {
            LocalDate fechaActual = LocalDate.now();
            LocalDate fechaInicio = nombramientoEmpleado.getFecha_inicio();
            LocalDate fechaFin = nombramientoEmpleado.getFecha_fin();
            
            if(fechaFin == null) {
                aniosCompletos += ChronoUnit.YEARS.between(fechaInicio, fechaActual);
            }
            else {
                aniosCompletos += ChronoUnit.YEARS.between(fechaInicio, fechaFin);
            }
        }

        return aniosCompletos;
    }


}
