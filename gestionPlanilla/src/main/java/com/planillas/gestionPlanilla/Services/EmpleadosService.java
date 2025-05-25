/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.planillas.gestionPlanilla.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planillas.gestionPlanilla.DAO.IEmpleadoDao;
import com.planillas.gestionPlanilla.DTO.EmpleadoDTO;
import com.planillas.gestionPlanilla.DTO.InfoBaseNombramientoDTO;
import com.planillas.gestionPlanilla.Models.Empleado;

/**
 *
 * @author danth
 */

 @Service
public class EmpleadosService implements IEmpleadosService {

    @Autowired
    private IEmpleadoDao empleadoDao;

    @Override
    public List<Empleado> listarEmpleadosId() {
        return (List<Empleado>) empleadoDao.findAllEmpleadosNombramientos();
    }

    @Override
    public List<InfoBaseNombramientoDTO> obtenerTodosNombramientos() {
        return (List<InfoBaseNombramientoDTO>)empleadoDao.findAllEmpleadosInfo();
    }
}
