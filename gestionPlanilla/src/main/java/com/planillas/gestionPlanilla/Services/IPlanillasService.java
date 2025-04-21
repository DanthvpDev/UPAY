package com.planillas.gestionPlanilla.Services;

import java.util.List;

import com.planillas.gestionPlanilla.Models.Planilla;

public interface IPlanillasService {
    public List<Planilla> obtenerPlanillas();
    public Planilla obtenerPlanillaPorId(Long id);
}