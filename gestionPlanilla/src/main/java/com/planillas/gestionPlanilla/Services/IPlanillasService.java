package com.planillas.gestionPlanilla.Services;

import java.time.LocalDate;
import java.util.List;

import com.planillas.gestionPlanilla.DTO.PlanillaDTO;
import com.planillas.gestionPlanilla.Models.Planilla;

public interface IPlanillasService {
    public Planilla obtenerPlanillaPorId(long id);
    public Planilla obtenerPlanillaPorFechaCalculo(LocalDate fechaCalculo);
    public List<PlanillaDTO> obtenerPlanillaPorAnio(int anio);
    public PlanillaDTO obtenerInformacionBasicaPlanilla(int mes, int anio);
    public Planilla calcularPlanilla(Planilla planilla);
}