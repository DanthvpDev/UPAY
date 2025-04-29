/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.planillas.gestionPlanilla.DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.planillas.gestionPlanilla.DTO.EmpleadoDTO;
import com.planillas.gestionPlanilla.Models.Empleado;

/**
 *
 * @author danth
 */
public interface IEmpleadoDao extends CrudRepository<Empleado, String> {

    @Query(value = "SELECT E.ID, E.NOMBRE, E.APELLIDO_1, E.APELLIDO_2, E.CORREO, E.TELEFONO, E.FECHA_NACIMIENTO, E.BORRADO FROM EMPLEADOS E INNER JOIN NOMBRAMIENTOS N ON E.ID = N.EMPLEADO_ID WHERE E.BORRADO = 0 AND N.ESTADO = 'ACT'", nativeQuery = true)
    public Iterable<Empleado> findAllEmpleadosNombramientos();
}
