/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.planillas.gestionPlanilla.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planillas.gestionPlanilla.DAO.IDetallesDao;
import com.planillas.gestionPlanilla.DTO.DetalleDto;
import com.planillas.gestionPlanilla.DTO.EmpleadoDTO;
import com.planillas.gestionPlanilla.DTO.IncapacidadDTO;
import com.planillas.gestionPlanilla.Models.Detalle_Planilla;
import com.planillas.gestionPlanilla.Models.Empleado;
import com.planillas.gestionPlanilla.Models.Planilla;

/**
 *
 * @author danth
 */
@Service
public class Detalles_PlanillaService implements IDetalles_PlanillaService {
    
    @Autowired
    private IDetallesDao detallesDao;

    @Autowired
    private IIncapacidadService incapacidadService;

    @Autowired
    private IEmpleadosService empleadoService;

    @Override
    public void guardarDetallesPlanilla(Planilla planilla) {
        LocalDate fechaActual = LocalDate.now();
        LocalDate mesAnterior = fechaActual.minusMonths(1);
        List<IncapacidadDTO> incapacidades = incapacidadService.obtenerIncapacidadesPorMesActual(mesAnterior);
        List<Empleado> empleados = empleadoService.listarEmpleadosId();

        for(Empleado empleado : empleados) {
            Detalle_Planilla detalle = new Detalle_Planilla(empleado, planilla, 20);
            detallesDao.save(detalle);
        }
    }

    
}
