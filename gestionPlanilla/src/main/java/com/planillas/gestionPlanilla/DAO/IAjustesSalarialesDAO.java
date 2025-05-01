/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.planillas.gestionPlanilla.DAO;

import org.springframework.data.repository.CrudRepository;

import com.planillas.gestionPlanilla.Models.Ajuste_Salarial;

/**
 *
 * @author danth
 */
public interface IAjustesSalarialesDAO extends CrudRepository<Ajuste_Salarial, Long> {
    public Iterable<Ajuste_Salarial> findAllByEstado(String estado);
}
