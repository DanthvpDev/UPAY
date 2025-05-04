/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.planillas.gestionPlanilla.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planillas.gestionPlanilla.DAO.IAjustesSalarialesDAO;
import com.planillas.gestionPlanilla.Models.Ajuste_Salarial;
import com.planillas.gestionPlanilla.Models.Desglose_Ajuste;

/**
 *
 * @author danth
 */
@Service
public class AjustesService implements IAjustesService {

    @Autowired
    private IAjustesSalarialesDAO ajustesSalariales;

    @Override
    public List<Ajuste_Salarial> obtenerAjustesSalariales() {
        return (List<Ajuste_Salarial>) ajustesSalariales.findAllByEstado("ACT");
    }

    @Override
    public double calcularPorcentaje(double porcentaje, double salarioBase) {
        return (porcentaje / 100) * salarioBase;
    }

    @Override
    public Desglose_Ajuste calcularAjuste(Ajuste_Salarial ajuste, double salario) {
        double montoAjuste = 0;
        Desglose_Ajuste desglose = new Desglose_Ajuste();
        if (!ajuste.isEs_valor()) {
            montoAjuste = calcularPorcentaje(ajuste.getValor(), salario);
        } else {
            montoAjuste = ajuste.getValor();
        }
        desglose.setMonto(montoAjuste);
        return desglose;
    }

    @Override
    public Desglose_Ajuste calcularBonificacion(Ajuste_Salarial ajuste, double salarioBase, long aniosLaborados) {
        double valorBonificacion = 0;
        Desglose_Ajuste desglose = new Desglose_Ajuste();
        if (!ajuste.isEs_valor()) {
            double montoPorcentaje = calcularPorcentaje(ajuste.getValor(), salarioBase);
            valorBonificacion = montoPorcentaje * aniosLaborados;
        } else {
            valorBonificacion = ajuste.getValor() * aniosLaborados;
        }
        desglose.setMonto(valorBonificacion);
        return desglose;
    }
}
