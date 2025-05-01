/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.planillas.gestionPlanilla.Services;

import java.time.LocalDate;
import java.util.List;

import com.planillas.gestionPlanilla.DTO.PermisoDTO;

/**
 *
 * @author danth
 */
public interface IPermisosService {
    public List<PermisoDTO> obtenerPermisosMesAnterior(LocalDate mesAnterior);
}
