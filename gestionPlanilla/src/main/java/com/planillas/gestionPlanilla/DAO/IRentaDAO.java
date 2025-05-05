/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.planillas.gestionPlanilla.DAO;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.planillas.gestionPlanilla.DTO.RentaDTO;
import com.planillas.gestionPlanilla.Models.Topes_Renta;

/**
 *
 * @author danth
 */
public interface IRentaDAO extends CrudRepository<Topes_Renta, Long> {
    @Query(value = "SELECT MONTO_BASE, MONTO_TOPE, PORCENTAJE FROM TOPES_RENTA WHERE ESTADO = :estado", nativeQuery = true)
    public Iterable<RentaDTO> findAllByEstado(@Param("estado") String estado);
}
