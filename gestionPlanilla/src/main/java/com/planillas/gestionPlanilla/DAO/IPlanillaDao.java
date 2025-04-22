package com.planillas.gestionPlanilla.DAO;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.planillas.gestionPlanilla.Models.Planilla;

public interface IPlanillaDao extends CrudRepository<Planilla, Long> {
    
    @Query(value = "SELECT * FROM PLANILLAS WHERE MONTH(FECHA_CALCULO) = MONTH(1) AND YEAR(FECHA_CALCULO) = YEAR(1)", nativeQuery = true)
    public Planilla findByFechaCalculo(LocalDate fechaCalculo);

    @Query(value = "SELECT * FROM PLANILLAS WHERE YEAR(FECHA_CALCULO) = 1", nativeQuery = true)
    public Iterable<Planilla> findByFechaCalculoAnio(int year);
}
