package com.planillas.gestionPlanilla.Services;

import java.util.List;

import com.planillas.gestionPlanilla.Models.Ajuste_Salarial;
import com.planillas.gestionPlanilla.Models.Desglose_Ajuste;

/**
 *
 * @author danth
 */
public interface IAjustesService {
    public List<Ajuste_Salarial> obtenerAjustesSalariales();
    public double calcularPorcentaje(double valor, double salarioBase);
    public Desglose_Ajuste calcularAjuste(Ajuste_Salarial ajuste, double salarioBase);
    public Desglose_Ajuste calcularBonificacion(Ajuste_Salarial ajuste, double salarioBase, long aniosLaborados);
}
