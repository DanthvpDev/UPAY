/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.planillas.gestionPlanilla.DAO;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.planillas.gestionPlanilla.DTO.PermisoDTO;
import com.planillas.gestionPlanilla.Models.Permiso;

/**
 *
 * @author danth
 */
public interface IPermisosDao extends CrudRepository<Permiso, Long> {
    //? Permisos
    @Query(value = "SELECT EMPLEADO_ID, FECHA_INICIO, FECHA_FIN FROM PERMISOS WHERE (MONTH(FECHA_INICIO) = MONTH(:mesAnterior) OR MONTH(FECHA_FIN) = MONTH(:mesAnterior)) AND (YEAR(FECHA_INICIO) = YEAR(:mesAnterior) OR YEAR(FECHA_FIN) = YEAR(:mesAnterior))", nativeQuery = true)
    public Iterable<PermisoDTO> findByMes(@Param("mesAnterior") LocalDate mesAnterior);

}
