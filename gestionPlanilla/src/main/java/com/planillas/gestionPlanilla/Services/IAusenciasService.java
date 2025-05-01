/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.planillas.gestionPlanilla.Services;

import java.time.LocalDate;
import java.util.List;

import com.planillas.gestionPlanilla.DTO.AusenciasDTO;

/**
 *
 * @author danth
 */
public interface IAusenciasService {
    public int calcularAusencias(List<? extends AusenciasDTO> ausencias, LocalDate fechaHoy);
}
