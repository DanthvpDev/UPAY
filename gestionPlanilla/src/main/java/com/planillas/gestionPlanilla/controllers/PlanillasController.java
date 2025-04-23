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
        // TODO: Crear DTO que obtenga la información necesaria para mostrar las tarjetas de la planilla;
        // TODO: Se deben cambiar todas las listas que tengan tipo Planilla y se debe cambiar el DAO, la Interface del Servicio y el Servicio;
        LocalDate fecha = LocalDate.now();
        //? Obtiene las planillas del año actual para mostrar los totales
        List<Planilla> planillas = planillasService.obtenerPlanillaPorAnio(fecha.getYear());
        //? Obtiene la planilla del mes actual
        Planilla planillaDelMes = planillasService.obtenerPlanillaPorMesAnio(fecha);
        String mensajePlanillaActual;

        //* Planillas del año
        model.addAttribute("planillas", planillas);
        
        if(planillas.isEmpty() || planillas == null) {
            model.addAttribute("mensaje", "No hay planillas para mostrar");
        }
        //* Planilla del mes
        
        model.addAttribute("planillaDelMes", planillaDelMes);
        // logger.log(System.Logger.Level.INFO, "Planilla del mes: " + planillaDelMes.toString());

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
