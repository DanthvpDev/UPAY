package com.planillas.gestionPlanilla.Services;

import java.time.LocalDate;
import java.util.List;

import com.planillas.gestionPlanilla.Models.Planilla;

public interface IPlanillasService {
    public Planilla obtenerPlanillaPorMesAnio(LocalDate fechaCalculo);
    public List<Planilla> obtenerPlanillaPorAnio(int anio);
    public double calcularPorcentaje(double valor, double salarioBase);
    
}