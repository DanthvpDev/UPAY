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
    @Query(value = "SELECT EMPLEADO_ID, FECHA_INICIO, FECHA_FIN, GOCE_SALARIO FROM PERMISOS WHERE FECHA_INICIO <= EOMONTH(:mesAnterior) AND FECHA_FIN >= DATEFROMPARTS(YEAR(:mesAnterior), MONTH(:mesAnterior), 1)", nativeQuery = true)
    public Iterable<PermisoDTO> findByMes(@Param("mesAnterior") LocalDate mesAnterior);

}
