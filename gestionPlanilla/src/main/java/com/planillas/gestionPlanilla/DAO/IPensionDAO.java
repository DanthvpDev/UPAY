/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.planillas.gestionPlanilla.DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.planillas.gestionPlanilla.DTO.PensionDTO;
import com.planillas.gestionPlanilla.Models.Pension;

/**
 *
 * @author danth
 */
public interface IPensionDAO extends CrudRepository<Pension, Long> {
    
    //? Busca una pensión por su id
    @Query(value = "SELECT P.EMPLEADO_ID, MONTO FROM PENSIONES P INNER JOIN EMPLEADOS E ON P.EMPLEADO_ID = E.ID INNER JOIN NOMBRAMIENTOS N ON E.ID = N.EMPLEADO_ID WHERE P.ESTADO = :estado AND N.ESTADO = :estado", nativeQuery = true)
    public Iterable<PensionDTO> findAllByEstado(@Param("estado")String estado);
    
    

}
