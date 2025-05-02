package com.planillas.gestionPlanilla.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.time.Month;
import java.time.format.TextStyle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.planillas.gestionPlanilla.DTO.IncapacidadDTO;
import com.planillas.gestionPlanilla.DTO.PlanillaDTO;
import com.planillas.gestionPlanilla.Models.Planilla;
import com.planillas.gestionPlanilla.Services.IDetalles_PlanillaService;
import com.planillas.gestionPlanilla.Services.IIncapacidadService;
import com.planillas.gestionPlanilla.Services.IPlanillasService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/planillas")
public class PlanillasController {
    
    @Autowired
    private IDetalles_PlanillaService detallesPlanillaService;
    
    @Autowired
    private IPlanillasService planillasService;

    @Autowired
    private IIncapacidadService incapacidadService;

    @GetMapping("/")
    public String index(Model model) {

        LocalDate fecha = LocalDate.now();
        //? Obtiene las planillas del año actual para mostrar los totales
        List<PlanillaDTO> planillas = planillasService.obtenerPlanillaPorAnio(fecha.getYear());
        //? Obtiene la planilla del mes actual
        PlanillaDTO planillaDelMes = planillasService.obtenerInformacionBasicaPlanilla(fecha.getMonthValue(), fecha.getYear());
        String mensajePlanillaActual;

        //* Planillas del año
        model.addAttribute("planillas", planillas);
        
        if(planillas.isEmpty() || planillas == null) {
            model.addAttribute("mensaje", "No hay planillas para mostrar");
        }
        //* Planilla del mes
        model.addAttribute("planillaDelMes", planillaDelMes);
        
        if(planillaDelMes == null) {
            //* Obteine el nombre del mes actual y lo agrega al model para mostrarlo
            Month mesActual = fecha.getMonth();
            String nombreMesActual = mesActual.getDisplayName(TextStyle.FULL, Locale.of("es", "ES"));
            model.addAttribute("mes", nombreMesActual);
            mensajePlanillaActual = "No se ha calculado la planilla de este mes. ¿Desea calcularla?";
            model.addAttribute("mensajePlanillaActual", mensajePlanillaActual);
        }
        else {
            model.addAttribute("mensajePlanillaActual", null);
        }

        return "Planillas/index";
    }

    @GetMapping("/calcularPlanilla")
    public String formularioPlanilla(Planilla planilla) {    
        if(planilla.getFecha_calculo() == null) {
            planilla.setFecha_calculo(LocalDate.now());
        } 
        return "Planillas/calculoPlanillas";
    }

    @PostMapping("/calcularPlanilla")
    public String calcularPlanilla(@Valid Planilla planilla, Errors errores, Model model){
        if(errores.hasErrors()){
            return "Planillas/calculoPlanillas";
        }
        Planilla planilaCalculada = planillasService.calcularPlanilla(planilla);
        if(planilaCalculada == null) {
            model.addAttribute("mensaje", "Error al calcular la planilla");
            return "redirect:/planillas/";
        }
        model.addAttribute("mensaje", "Planilla calculada con éxito");
        return "redirect:/planillas/";
    }
}
