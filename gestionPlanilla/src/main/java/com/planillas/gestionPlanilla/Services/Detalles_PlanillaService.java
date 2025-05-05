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
import com.planillas.gestionPlanilla.DTO.CarreraProfesionalDTO;
import com.planillas.gestionPlanilla.DTO.DetalleDto;
import com.planillas.gestionPlanilla.DTO.EmpleadoDTO;
import com.planillas.gestionPlanilla.DTO.IncapacidadDTO;
import com.planillas.gestionPlanilla.DTO.NombramientoInfoDTO;
import com.planillas.gestionPlanilla.DTO.PensionDTO;
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

    @Autowired
    private IPensionService pensionesService;

    @Autowired
    private ICarreraProfesionalService carreraProfesionalService;

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
        List<Ajuste_Salarial> bonificaciones = null;
        List<Empleado> empleados = empleadoService.listarEmpleadosId();
        List<RentaDTO> topesRenta = rentaService.obtenerTopesRenta("ACT");
        List<PensionDTO> pensiones = pensionesService.obtenerPensionesActivas("ACT");
        List<CarreraProfesionalDTO> carrerasProfesionales = carreraProfesionalService.obtenerPuntosCarrerasExistentes();
        final int diasTrabajadosDefault = 20;

        if (ajustes != null && !ajustes.isEmpty()) {
            deducciones = ajustes.stream().filter(a -> a.isEs_deduccion()).collect(Collectors.toList()); 
        }else {
            return -1; //! No existen ajustes salariales
        }
        if (empleados == null || empleados.isEmpty()) {
            return -2; //! No existen empleados
        }
        if (topesRenta == null || topesRenta.isEmpty()) {
            return -3; //! No existen topes de renta
        }

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
            double subsidio = 0;
            double salarioBase = 0;
            double salarioPorDias = 0;
            //? Se obtiene el nombaramiento actual del empleado
            Optional<Nombramiento> nombramiento = empleado.getNombramientoActual();

            if (nombramiento.isPresent()) {
                NombramientoInfoDTO infoNombramiento = nombramiento.get().getInfoNombramiento();

                // ? Se asigna el salario global o compuesto
                if (infoNombramiento.isEs_SalarioGlobal()) {
                    detalle.setSalario(infoNombramiento.getSalario_global()); 
                }else {
                    detalle.setSalario(infoNombramiento.getSalario_base());
                }
                //* PERMISOS

                //? Se obtienen los permisos del empleado
                List<PermisoDTO> permisosEmpleado = permisos.stream()
                        .filter(p -> p.getEmpleadoId().equals(empleado.getEmpleadoId())).collect(Collectors.toList());

                //? Se calculan los días de permiso
                if (!permisosEmpleado.isEmpty()) {

                    //? Se filtran los permisos que no son con goce de salario
                    List<PermisoDTO> permisosEmpleadoSinGoce = permisosEmpleado.stream().filter(p -> !p.isGoce_salario()).collect(Collectors.toList());

                    if (!permisosEmpleadoSinGoce.isEmpty()) {
                        diasPermisos = ausenciasService.calcularAusencias(permisosEmpleadoSinGoce, fechaActual);
                        detalle.setDias_trabajados(diasTrabajadosDefault - diasPermisos);
                        if (detalle.getDias_trabajados() > 0) {
                            salarioPorDias = detalle.getSalario() / detalle.getDias_trabajados();
                        }
                        
                    } else {
                        detalle.setDias_trabajados(diasTrabajadosDefault);
                    }
                } else {
                    detalle.setDias_trabajados(diasTrabajadosDefault);
                }

                if (diasPermisos < 20) {
                    //* INCAPACIDADES
                    List<IncapacidadDTO> incapacidadEmpleado = incapacidades.stream()
                            .filter(i -> i.getEmpleadoId().equals(empleado.getEmpleadoId()))
                            .collect(Collectors.toList());

                    diasIncapacidad = ausenciasService.calcularAusencias(incapacidadEmpleado, fechaActual);

                    //? Se calculan los días de incapacidad para poder determinar el subsidio
                    if (diasIncapacidad > 3) {
                        subsidio = incapacidadService.calcularSubsidio(diasIncapacidad, detalle.getSalario(), diasTrabajadosDefault);
                        detalle.setSubsidio(subsidio);
                    }

                    //? Se asigna el salario base y bruto
                    salarioBase = detalle.getSalario();
                    salarioBruto = detalle.getSalario() - subsidio - salarioPorDias;
                    detalle.setSalario_bruto(salarioBruto);

                    if (!infoNombramiento.isEs_SalarioGlobal()) {
                        bonificaciones = ajustes.stream().filter(a -> !a.isEs_deduccion()).collect(Collectors.toList());

                        // * BONIFICACIONES
                        //? Valida que hayan bonificaciones
                        if (!bonificaciones.isEmpty()) {
                            //? Itera sobre las bonificaciones
                            for (Ajuste_Salarial bono : bonificaciones) {
                                //? Valida que la bonificación se encuentre activa
                                if (bono.getEstado().equals("ACT")) {
                                    //? Valida que tenga la misma categoría o que sea anualidad
                                    // Validation for annuality as it does not depend on the category and must always be calculated
                                    if ((bono.getCategoria() == infoNombramiento.getCategoria()) || bono.getCategoria() == 0) {
                                        Desglose_Ajuste desgloseBono;

                                        //? Valida que la bonificación sea por anualidad
                                        if (bono.getDescripcion().contains("Anualidad")) {

                                            //? Se obtiene el tiempo de servicio del empleado
                                            long aniosCompletos = empleado.getAniosLaborados();

                                            //? Se calcula la bonificación por anualidad
                                            desgloseBono = ajusteService.calcularBonificacion(bono, salarioBase, aniosCompletos);
                                        } else {
                                            desgloseBono = ajusteService.calcularAjuste(bono, salarioBase);
                                        }
                                        
                                        if(bono.getDescripcion().contains("Carrera Profesional")) {
                                            int puntos = carrerasProfesionales.stream()
                                                    .filter(c -> c.getEmpleadoId().equals(empleado.getEmpleadoId()))
                                                    .findFirst()
                                                    .map(CarreraProfesionalDTO::getPuntos)
                                                    .orElse(0);
                                            desgloseBono.setMonto(desgloseBono.getMonto() * puntos);
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
                    //* CALCULO DE PENSIONES
                    if (!pensiones.isEmpty()) {
                        List<PensionDTO> pensionEmpleado = pensiones.stream().filter(p -> p.getEmpleado_id().equals(empleado.getEmpleadoId())).collect(Collectors.toList());

                        if (pensionEmpleado != null && !pensionEmpleado.isEmpty()) {
                            double montoPension = pensionesService.calcularTotalPensiones(pensionEmpleado, salarioNeto);
                            salarioNeto -= montoPension;
                            detalle.setSalario_neto(salarioNeto);
                            detalle.setPensiones_total(montoPension);
                        }
                    }

                    if (!deducciones.isEmpty() && deducciones != null) {
                        for (Ajuste_Salarial deduccion : deducciones) {
                            if (deduccion.getEstado().equals("ACT") && (deduccion.getCategoria() == infoNombramiento.getCategoria() || deduccion.getCategoria() == 0)) {
                                Desglose_Ajuste desgloseDeduccion = ajusteService.calcularAjuste(deduccion, salarioBruto);
                                if (desgloseDeduccion != null && desgloseDeduccion.getMonto() <= salarioBruto) {
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
                    }

                    //* CALCULO IMPUESTO DE RENTA
                    double montoRenta = 0;
                    if (!topesRenta.isEmpty()) {
                        montoRenta = rentaService.calcularMontoRenta(salarioBruto, topesRenta);
                        salarioNeto = salarioNeto - montoRenta;
                    }

                    detalle.setSalario_neto(salarioNeto);
                    detalle.setPrimer_pago(salarioNeto);
                    detalle.setSegundo_pago(salarioNeto);
                    detalle.setRenta_total(montoRenta);
                }
                else {
                    //* Si el permiso es sin goce se incializa todo en 0 ya que no hubo calculos
                    detalle.setSalario_bruto(0);
                    detalle.setSalario_neto(0);
                    detalle.setSubsidio(0);
                    detalle.setRenta_total(0);
                    detalle.setPensiones_total(0);
                    detalle.setDesglose_ajustes(null);
                    detalle.setPrimer_pago(0);
                    detalle.setSegundo_pago(0);
                }
            }
            detallesDao.save(detalle);
        }
        return 1; //! Se crea la planilla
    }
}
