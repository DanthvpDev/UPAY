/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.planillas.gestionPlanilla.controllers;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.planillas.gestionPlanilla.DTO.InfoBaseNombramientoDTO;
import com.planillas.gestionPlanilla.Services.IEmpleadosService;

import org.springframework.web.bind.annotation.RequestParam;

import com.planillas.gestionPlanilla.Models.Puesto;



/**
 *
 * @author Daniel
 */
@Controller
@RequestMapping("/nombramientos")
public class NombramientosController {

    @Autowired
    private IEmpleadosService empleadosService;

    @GetMapping("/")
    public String index(Model modelo) {
        List<InfoBaseNombramientoDTO> nombramientos = empleadosService.obtenerTodosNombramientos();
        modelo.addAttribute("nombramientos", nombramientos);
        return "Nombramientos/index";
    }

    @GetMapping("/agregar")
    public String ventanaAgregar(InfoBaseNombramientoDTO nombramiento, Model modelo) {
        modelo.addAttribute("nombramiento", nombramiento);
        modelo.addAttribute("puestos", null);
        return "Nombramientos/nuevoNombramiento";
    }
}
