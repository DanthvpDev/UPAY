/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.planillas.gestionPlanilla.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planillas.gestionPlanilla.DAO.IDetallesDao;
import com.planillas.gestionPlanilla.DTO.DetalleDto;
import com.planillas.gestionPlanilla.DTO.EmpleadoDTO;
import com.planillas.gestionPlanilla.DTO.IncapacidadDTO;
import com.planillas.gestionPlanilla.DTO.NombramientoInfoDTO;
import com.planillas.gestionPlanilla.Models.Detalle_Planilla;
import com.planillas.gestionPlanilla.Models.Empleado;
import com.planillas.gestionPlanilla.Models.Nombramiento;
import com.planillas.gestionPlanilla.Models.Planilla;

import jakarta.transaction.Transactional;

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
    @Transactional
    public void guardarDetallesPlanilla(Planilla planilla) {
        LocalDate fechaActual = LocalDate.now();
        LocalDate mesAnterior = fechaActual.minusMonths(1);
        List<IncapacidadDTO> incapacidades = incapacidadService.obtenerIncapacidadesPorMesActual(mesAnterior);
        final int diasTrabajadosDefault = 20;

        List<Empleado> empleados = empleadoService.listarEmpleadosId();
        if(!empleados.isEmpty() && incapacidades != null) {
            for(Empleado empleado : empleados) {
                Detalle_Planilla detalle = new Detalle_Planilla(empleado, planilla);
                
                List<IncapacidadDTO> incapacidadEmpleado = incapacidades.stream()
                        .filter(i -> i.getEmpleadoId().equals(empleado.getEmpleadoId())).collect(Collectors.toList());

                int diasIncapacidad = 0;
                //* Se calculan los días de incapacidad
                if(incapacidadEmpleado != null && !incapacidadEmpleado.isEmpty()) {
                    diasIncapacidad = incapacidadService.calcularDiasIncapacidad(incapacidadEmpleado, fechaActual);
                }

                //TODO: NO se debe restar, se debe calcular los días para saber si el subsidio cambia
                detalle.setDias_trabajados(diasTrabajadosDefault - diasIncapacidad);

                //* Se obtiene el nombaramiento actual del empleado
                Optional<Nombramiento> nombramiento = empleado.getNombramientoActual();

                if(nombramiento != null) {
                    NombramientoInfoDTO infoNombramiento = nombramiento.get().getInfoNombramiento();

                    if(infoNombramiento.isEs_SalarioGlobal()){
                        detalle.setSalario(infoNombramiento.getSalario_global());
                    }
                    else {
                        detalle.setSalario(infoNombramiento.getSalario_base());
                    }
                }

                detallesDao.save(detalle);
            }
        }
    }
    

    
}
