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
import com.planillas.gestionPlanilla.DTO.RentaDTO;
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

    @Autowired
    private IRentaService rentaService;

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
        List<Ajuste_Salarial> deducciones = null;
        if(ajustes != null && !ajustes.isEmpty()) {
            deducciones = ajustes.stream().filter(a -> a.isEs_deduccion()).collect(Collectors.toList());
        }
        else {
            return -1; //! No existen ajustes salariales
        } 
        List<Empleado> empleados = empleadoService.listarEmpleadosId();
        List<RentaDTO> topesRenta = rentaService.obtenerTopesRenta("ACT");
        final int diasTrabajadosDefault = 20;


        if (empleados == null || empleados.isEmpty()) {
            return -2; //! No existen empleados
        }

        if(topesRenta == null || topesRenta.isEmpty()) {
            return -3; //! No existen topes de renta
        }

        //TODO: Implementar la renta y las pensiones en la parte de las deducciones
        //TODO: Calcular Carrera Profesional como bonificación
        //TODO: Verificar que funcione para planillas extraordinarias
        //TODO: Hacer la vista donde se muestren los detalles
        //TODO: Implementar planilla aguinaldo y planilla salario escolar



        for (Empleado empleado : empleados) {
            Detalle_Planilla detalle = new Detalle_Planilla(empleado, planilla);
            int diasIncapacidad = 0;
            int diasPermisos = 0;
            double salarioBruto = 0;
            double salarioNeto = 0;

            //? Se obtiene el nombaramiento actual del empleado
            Optional<Nombramiento> nombramiento = empleado.getNombramientoActual();

            if (nombramiento.isPresent()) {
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
                
                double salarioBase = detalle.getSalario();
                salarioBruto = salarioBase;

                //? Si el empleado no recibe subsidio
                if(diasIncapacidad <= 3){
                    //TODO: Se debe validar que el salario sea compuesto para aplicar los bonos
                    if (!infoNombramiento.isEs_SalarioGlobal()) {
                        List<Ajuste_Salarial> bonificaciones = ajustes.stream().filter(a -> !a.isEs_deduccion()).collect(Collectors.toList());
    
                        // * BONIFICACIONES
                        //? Valida que hayan bonificaciones
                        if(!bonificaciones.isEmpty() && bonificaciones != null) {
                            //? Itera sobre las bonificaciones
                            for(Ajuste_Salarial bono : bonificaciones) {
                                //? Valida que la bonificación se encuentre activa
                                if(bono.getEstado().equals("ACT")) {
                                    //? Valida que tenga la misma categoría o que sea anualidad
                                    //? Se hace la validación con anualidad porque la anualidad no depende de la categoría por lo que siempre se debe calcular
                                   if((bono.getCategoria() == infoNombramiento.getCategoria()) || bono.getCategoria() == 0) {
                                    Desglose_Ajuste desgloseBono;
    
                                        //? Valida que la bonificación sea por anualidad
                                        if(bono.getDescripcion().contains("Anualidad")) {
                                            
                                            //? Se obtiene el tiempo de servicio del empleado
                                            long aniosCompletos = empleado.getAniosLaborados();
                                            
                                            //? Se calcula la bonificación por anualidad
                                            desgloseBono = ajusteService.calcularBonificacion(bono, salarioBase, aniosCompletos);
                                        }
                                        else {
                                            desgloseBono = ajusteService.calcularAjuste(bono, salarioBase);
                                        }
                                        desgloseBono.setDetalle_planilla(detalle);
                                        desgloseBono.setAjuste_salarial(bono);
                                        desgloseBono.setObservaciones("Bonificación por " + bono.getDescripcion());
    
                                        detalle.setDesglose_ajustes(desgloseBono);
                                        salarioBruto = salarioBruto + desgloseBono.getMonto();
                                    }
                                }
                            }
                        }    
                    }
    
                    detalle.setSalario_bruto(salarioBruto);
                    salarioNeto = salarioBruto;
    
                    //* DEDUCCIONES
                    if(!deducciones.isEmpty() && deducciones != null) {
                        for(Ajuste_Salarial deduccion : deducciones) {
                            if(deduccion.getEstado().equals("ACT") && (deduccion.getCategoria() == infoNombramiento.getCategoria() || deduccion.getCategoria() == 0)) { 
                                Desglose_Ajuste desgloseDeduccion = ajusteService.calcularAjuste(deduccion, salarioBruto);
                                if(desgloseDeduccion != null && desgloseDeduccion.getMonto() <= salarioBruto) {
                                    //? Se actualiza la variable el salario neto
                                    salarioNeto = salarioNeto - desgloseDeduccion.getMonto();
                                    desgloseDeduccion.setObservaciones("Deducción por " + deduccion.getDescripcion());
                                    detalle.setSalario_neto(salarioNeto);
                                    desgloseDeduccion.setAjuste_salarial(deduccion);
                                    desgloseDeduccion.setDetalle_planilla(detalle);
                                    detalle.setDesglose_ajustes(desgloseDeduccion);  
                                }
                            }
                        }
                        detalle.setSalario_neto(salarioNeto);
                        detalle.setPrimer_pago(salarioNeto);
                        detalle.setSegundo_pago(salarioNeto);
                    }
                }

                logger.log(Level.INFO, "SALRIO NETO: {0}", salarioNeto);
                logger.log(Level.INFO, "SALARIO BRUTO: {0}", salarioBruto);
                logger.log(Level.INFO, "SALARIO BRUTO DETALLE: {0}", detalle.getSalario_bruto());
                logger.log(Level.INFO, "SALRIO NETO DETALLE: {0}", detalle.getSalario_neto());
            }
            else {
                detalle.setSalario_neto(0);
                detalle.setSalario_bruto(0);
                detalle.setPrimer_pago(detalle.getSalario());
                detalle.setSegundo_pago(detalle.getSalario());
            }
            detallesDao.save(detalle);
        }
        return 1; //! Se crea la planilla
    }
}
