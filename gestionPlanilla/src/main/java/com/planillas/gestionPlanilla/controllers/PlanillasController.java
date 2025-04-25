package com.planillas.gestionPlanilla.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.time.Month;
import java.time.format.TextStyle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.planillas.gestionPlanilla.DTO.PlanillaDTO;
import com.planillas.gestionPlanilla.Models.Planilla;
import com.planillas.gestionPlanilla.Services.IPlanillasService;

@Controller
@RequestMapping("/planillas")
public class PlanillasController {
    
    @Autowired
    private IPlanillasService planillasService;
    private static final System.Logger logger = System.getLogger(PlanillasController.class.getName());

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
        
        // logger.log(System.Logger.Level.INFO, "Planilla del mes: " + planillaDelMes.toString());
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
}
