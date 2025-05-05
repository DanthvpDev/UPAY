/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.planillas.gestionPlanilla.DTO;

/**
 *
 * @author danth
 */
public class CarreraProfesionalDTO {
    private int puntos;
    private String grado;
    private String empleadoId;

    public CarreraProfesionalDTO() {
    }

    public CarreraProfesionalDTO(int puntos, String grado, String empleadoId) {
        this.puntos = puntos;
        this.grado = grado;
        this.empleadoId = empleadoId;
    }

    public int getPuntos() {
        return puntos;
    }
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String getNombreCarrera() {
        return grado;
    }
    public void setNombreCarrera(String grado) {
        this.grado = grado;
    }

    public String getEmpleadoId() {
        return empleadoId;
    }
    public void setEmpleadoId(String empleadoId) {
        this.empleadoId = empleadoId;
    }

}
