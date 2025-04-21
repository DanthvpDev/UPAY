package com.planillas.gestionPlanilla.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/planillas")
public class PlanillasController {
    
    @GetMapping("/")
    public String index() {
        // TODO: Crear el servicio para obtener las planillas
        // TODO: -> List<Planilla> planillas = planillasService.obtenerPlanillas();
        // TODO: Hacer el model para enviar las planillas a la vista model.addAttribute("planillas", planillas);
        return "Planillas/index";
    }
}
