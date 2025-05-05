/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.planillas.gestionPlanilla.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planillas.gestionPlanilla.DAO.ICarreraProfesionalDAO;
import com.planillas.gestionPlanilla.DTO.CarreraProfesionalDTO;

/**
 *
 * @author danth
 */
@Service
public class CarreraProfesionalService implements ICarreraProfesionalService {
    
    @Autowired
    private ICarreraProfesionalDAO carreraProfesionalDAO;

    @Override
    public List<CarreraProfesionalDTO> obtenerPuntosCarrerasExistentes() {
        return (List<CarreraProfesionalDTO>) carreraProfesionalDAO.findAllByExistentes();
    }

    // public double calcularMontoPorPuntos(List<CarreraProfesionalDTO> carreras, double valorPorPunto) {
    //     double montoPorPunto = 0.0;
    //     for (CarreraProfesionalDTO carrera : carreras) {
    //         if (carrera.getPuntos() > 0) {
    //             puntos += carrera.getPuntos();
    //         }
    //     }
    //     return montoPorPunto * valorPorPunto;
    // }
}
