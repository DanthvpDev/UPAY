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
import java.util.logging.Logger;
import java.util.logging.Level;

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
import com.planillas.gestionPlanilla.controllers.PlanillasController;

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
    private static final Logger logger = Logger.getLogger(PlanillasController.class.getName());

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


        if (empleados == null || empleados.isEmpty()) {
            logger.log(Level.WARNING, "No existen empleados para procesar.");
            return -1; //* No existen empleados
        }
        for (Empleado empleado : empleados) {
            Detalle_Planilla detalle = new Detalle_Planilla(empleado, planilla);
            int diasIncapacidad = 0;
            int diasPermisos = 0;
            double salarioBruto = 0;
            double salarioNeto = 0;

            //? Se obtiene el nombaramiento actual del empleado
            Optional<Nombramiento> nombramiento = empleado.getNombramientoActual();

            if (nombramiento != null) {
                NombramientoInfoDTO infoNombramiento = nombramiento.get().getInfoNombramiento();
                
                // ? Se asigna el salario global o compuesto
                if (infoNombramiento.isEs_SalarioGlobal()) {
                    detalle.setSalario(infoNombramiento.getSalario_global());
                } else {
                    detalle.setSalario(infoNombramiento.getSalario_base());
                }
                
                //* PERMISOS
                Stream<PermisoDTO> permisosEmpleado = permisos.stream()
                .filter(p -> p.getEmpleadoId().equals(empleado.getEmpleadoId()));

                //? Se calculan los días de permiso
                if (permisosEmpleado != null && permisosEmpleado.count() > 0) {
                    List<PermisoDTO> permisosEmpleadoSinGoce = permisosEmpleado.filter(p -> !p.isGoce_salario()).collect(Collectors.toList());
                    if(permisosEmpleadoSinGoce != null && !permisosEmpleadoSinGoce.isEmpty()) {
                        diasPermisos = ausenciasService.calcularAusencias(permisosEmpleadoSinGoce, fechaActual);
                        detalle.setDias_trabajados(diasTrabajadosDefault - diasPermisos);    
                        double salarioPorDias = detalle.getSalario() / detalle.getDias_trabajados();
                        detalle.setSalario(salarioPorDias);  
                    }
                } 

                //* INCAPACIDADES 
                List<IncapacidadDTO> incapacidadEmpleado = incapacidades.stream()
                .filter(i -> i.getEmpleadoId().equals(empleado.getEmpleadoId()))
                .collect(Collectors.toList());

                diasIncapacidad = ausenciasService.calcularAusencias(incapacidadEmpleado, fechaActual);

                //? Se calculan los días de incapacidad para poder determinar el subsidio
                if (diasIncapacidad > 3) {
                    double subsidio = incapacidadService.calcularSubsidio(diasIncapacidad, detalle.getSalario(), diasTrabajadosDefault);
                    detalle.setSubsidio(subsidio);
                }

                //TODO: Se debe validar que el salario sea compuesto para aplicar los bonos
                if (ajustes != null && !ajustes.isEmpty()) {

                    double salarioBase = detalle.getSalario();

                    Stream<Ajuste_Salarial> bonificaciones = ajustes.stream().filter(a -> !a.isEs_deduccion());
                    List<Ajuste_Salarial> deducciones = ajustes.stream().filter(a -> a.isEs_deduccion()).collect(Collectors.toList());

                    salarioBruto = salarioBase;

                    // * BONIFICACIONES
                    Ajuste_Salarial escalafon = bonificaciones
                            .filter(a -> a.getCategoria() == infoNombramiento.getCategoria() && a.getEstado().equals("ACT") && a.getDescripcion().contains("Escalafón"))
                            .findFirst()
                            .orElse(null);


                    if (escalafon != null) {
                        double valorEscalafon = ajusteService.calcularPorcentaje(escalafon.getValor(), salarioBase);
                        Desglose_Ajuste desgloseEscalafon = new Desglose_Ajuste();
                        desgloseEscalafon.setAjuste_salarial(escalafon);
                        desgloseEscalafon.setDetalle_planilla(detalle);
                        desgloseEscalafon.setMonto(valorEscalafon);
                        desgloseEscalafon.setObservaciones("Bonificación por escalafón");

                        detalle.setDesglose_ajustes(desgloseEscalafon);
                    }

                    detallesDao.save(detalle);
                }
            }
        }
        return 1;
    }
}
