/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.planillas.gestionPlanilla.Services;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planillas.gestionPlanilla.DAO.IDetallesDao;
import com.planillas.gestionPlanilla.DTO.AusenciasDTO;
import com.planillas.gestionPlanilla.DTO.DetalleDto;
import com.planillas.gestionPlanilla.DTO.EmpleadoDTO;
import com.planillas.gestionPlanilla.DTO.IncapacidadDTO;
import com.planillas.gestionPlanilla.DTO.NombramientoInfoDTO;
import com.planillas.gestionPlanilla.DTO.PermisoDTO;
import com.planillas.gestionPlanilla.Models.Ajuste_Salarial;
import com.planillas.gestionPlanilla.Models.Desglose_Ajuste;
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
    private IPermisosService permisosService;

    @Autowired
    private IAusenciasService ausenciasService;

    @Autowired
    private IIncapacidadService incapacidadService;

    @Autowired
    private IEmpleadosService empleadoService;

    @Autowired 
    private IAjustesService ajusteService;

    @Override
    @Transactional
    public int guardarDetallesPlanilla(Planilla planilla) {
        // TODO: Try CATCH
        LocalDate fechaActual = LocalDate.now();
        LocalDate mesAnterior = fechaActual.minusMonths(1);
        List<IncapacidadDTO> incapacidades = incapacidadService.obtenerIncapacidadesPorMesActual(mesAnterior);
        List<PermisoDTO> permisos = permisosService.obtenerPermisosMesAnterior(mesAnterior);
        List<Ajuste_Salarial> ajustes = ajusteService.obtenerAjustesSalariales();
        List<Empleado> empleados = empleadoService.listarEmpleadosId();
        final int diasTrabajadosDefault = 20;

        if(!empleados.isEmpty() && incapacidades != null) {
            for(Empleado empleado : empleados) {
                Detalle_Planilla detalle = new Detalle_Planilla(empleado, planilla);
                int diasIncapacidad = 0;
                int diasPermisos = 0;
                double salarioBruto = 0;
                double salarioNeto = 0;
                
                //* PERMISOS
                List<PermisoDTO> permisosEmpleado = permisos.stream()
                        .filter(p -> p.getEmpleadoId().equals(empleado.getEmpleadoId()))
                        .collect(Collectors.toList());
                
                
                //* INCAPACIDADES
                List<IncapacidadDTO> incapacidadEmpleado = incapacidades.stream()
                        .filter(i -> i.getEmpleadoId().equals(empleado.getEmpleadoId()))
                        .collect(Collectors.toList());
                
                

                //? Se calculan los días de incapacidad
                if(permisosEmpleado != null && !permisosEmpleado.isEmpty()) {
                    diasPermisos = ausenciasService.calcularAusencias(permisosEmpleado, fechaActual);
                    detalle.setDias_trabajados(diasTrabajadosDefault - diasPermisos);
                } 
                if(incapacidadEmpleado != null && !incapacidadEmpleado.isEmpty()) {
                    diasIncapacidad = ausenciasService.calcularAusencias(incapacidadEmpleado, fechaActual);
                }

                //? Se obtiene el nombaramiento actual del empleado
                Optional<Nombramiento> nombramiento = empleado.getNombramientoActual();

                if(nombramiento != null) {
                    NombramientoInfoDTO infoNombramiento = nombramiento.get().getInfoNombramiento();


                    // ? Se asigna el salario global o compuesto
                    if(infoNombramiento.isEs_SalarioGlobal()){
                        detalle.setSalario(infoNombramiento.getSalario_global());
                    }
                    else {
                        detalle.setSalario(infoNombramiento.getSalario_base());
                    }

                    //? Se calculan los días de incapacidad para poder determinar el subsidio
                    if(diasIncapacidad > 3){
                        double subsidio = incapacidadService.calcularSubsidio(diasIncapacidad, detalle.getSalario(), diasTrabajadosDefault);
                        detalle.setSubsidio(subsidio);
                    }
                    else detalle.setSubsidio(0);
                    Detalle_Planilla detalleGuardado = detallesDao.save(detalle);

                    if(detalleGuardado != null) {
                        if(ajustes != null && !ajustes.isEmpty()) {
                            
                            double salarioBase = detalleGuardado.getSalario();

                            Stream<Ajuste_Salarial> bonificaciones = ajustes.stream().filter(a -> !a.isEs_deduccion());
                            List<Ajuste_Salarial> deducciones = ajustes.stream().filter(a -> a.isEs_deduccion()).collect(Collectors.toList());
                            
                            salarioBruto = salarioBase;

                            // * BONIFICACIONES

                            //? Escalafón
                            Ajuste_Salarial escalafon = bonificaciones
                                    .filter(a -> (a.getDescripcion().contains("escalafon") || a.getDescripcion().contains("escalafón")) && a.getCategoria() == infoNombramiento.getCategoria())
                                    .findFirst()
                                    .orElse(null);
                            if(escalafon != null) {
                                double valorEscalafon = ajusteService.calcularPorcentaje(escalafon.getValor(), salarioBase);
                                Desglose_Ajuste desgloseEscalafon = new Desglose_Ajuste();
                                desgloseEscalafon.setAjuste_salarial(escalafon);
                                desgloseEscalafon.setDetalle_planilla(detalleGuardado);
                                desgloseEscalafon.setMonto(valorEscalafon);
                                desgloseEscalafon.setObservaciones("Bonificación por escalafón");
                                
                                detalleGuardado.setDesglose_ajustes(desgloseEscalafon);
                            }
                            
                            
                            
                            
                        }
                    }
                }
                
            }
        }
        return -1;
    }
    
    

    
}
