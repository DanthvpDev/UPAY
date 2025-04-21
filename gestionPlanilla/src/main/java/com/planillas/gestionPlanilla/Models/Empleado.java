package com.planillas.gestionPlanilla.Models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
    private String id;

    @Pattern(regexp = "^[A-Za-z]+$", message = "El nombre solo puede contener letras.")
    @NotEmpty(message = "El nombre es obligatorio.")
    private String nombre;

    @Pattern(regexp = "^[A-Za-z]+$", message = "El apellido solo puede contener letras.")
    @NotEmpty(message = "El primer apellido es obligatorio.")
    private String apellido1;

    @Pattern(regexp = "^[A-Za-z]+$",message = "El apellido solo puede contener letras.")
    private String apellido2;

    @NotBlank(message = "El correo es obligatorio.")
    @Email(message = "El correo debe tener un formato válido (correo@ejemplo.com).")
    private String correo;

    @NotBlank(message = "El teléfono es obligatorio.")
    @Pattern(regexp = "^[0-9]{8}$",message = "El teléfono debe contener 8 dígitos numéricos.")
    private String telefono;

    @NotNull(message = "La fecha de nacimiento es obligatoria.")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Calendar fecha_Nacimiento;

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

    //* Relación con la entidad Nombramiento
    @OneToMany(mappedBy="empleado")
    private List<Nombramiento> nombramientos;

    @OneToMany(mappedBy="empleado")
    private List<Detalle_Planilla> detalles_planilla;

}
