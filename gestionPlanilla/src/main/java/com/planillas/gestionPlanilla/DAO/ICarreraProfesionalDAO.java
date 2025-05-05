/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.planillas.gestionPlanilla.DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.planillas.gestionPlanilla.DTO.CarreraProfesionalDTO;
import com.planillas.gestionPlanilla.Models.Carrera_Profesional;

/**
 *
 * @author danth
 */
public interface ICarreraProfesionalDAO extends CrudRepository<Carrera_Profesional, Long> {

    @Query(value = "SELECT PUNTOS, GRADO, EMPLEADO_ID FROM CARRERA_PROFESIONAL CP INNER JOIN EMPLEADOs E ON CP.EMPLEADO_ID = E.ID INNER JOIN GRADOS_ACADEMICOS GA ON CP.GRADO_ID = GA.ID WHERE CP.BORRADO = 0 AND E.BORRADO = 0 AND PUNTOS >= 1", nativeQuery = true)
    public Iterable<CarreraProfesionalDTO> findAllByExistentes(); 
}
