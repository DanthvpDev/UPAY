package com.planillas.gestionPlanilla.DAO;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.planillas.gestionPlanilla.DTO.PlanillaDTO;
import com.planillas.gestionPlanilla.Models.Planilla;

public interface IPlanillaDao extends CrudRepository<Planilla, Long> {

    public Planilla findByPlanillaId(long id);

    @Query(value = "SELECT ID, FECHA_CALCULO, ES_EXTRAORDINARIA, MES, TIPO_PLANILLA FROM PLANILLAS WHERE MES = MONTH(:fecha) AND YEAR(FECHA_CALCULO) = YEAR(:fecha) AND TIPO_PLANILLA = 'REG'", nativeQuery = true)
    public Planilla findByFechaCalculo(@Param("fecha")LocalDate fechaCalculo);

    /* 
     ? Obtiene las planillas del año especificado, junto con el total de empleados y el total de pagos.
    */ 
    @Query(value = "SELECT P.ID, MES AS MES, YEAR(FECHA_CALCULO) AS ANIO, SUM(DP.SALARIO_NETO) AS TOTAL_PAGOS, COUNT(E.ID) AS TOTAL_EMPLEADOS FROM PLANILLAS P INNER JOIN DETALLES_PLANILLA DP ON P.ID = DP.PLANILLA_ID INNER JOIN EMPLEADOS E ON DP.EMPLEADO_ID = E.ID WHERE YEAR(FECHA_CALCULO) = :anio AND MES != MONTH(GETDATE()) GROUP BY P.ID, MES, YEAR(FECHA_CALCULO)", nativeQuery = true)
    public Iterable<PlanillaDTO> findByFechaCalculoAnio(@Param("anio") int year);


    /* 
     ? Obtiene la planilla del mes y año especificado, junto con el total de empleados y el total de pagos.
    */
    @Query(value = "SELECT P.ID, MES AS MES, YEAR(FECHA_CALCULO) AS ANIO, SUM(DP.SALARIO_NETO) AS TOTAL_PAGOS, COUNT(E.ID) AS TOTAL_EMPLEADOS FROM PLANILLAS P INNER JOIN DETALLES_PLANILLA DP ON P.ID = DP.PLANILLA_ID INNER JOIN EMPLEADOS E ON DP.EMPLEADO_ID = E.ID WHERE MES = :mes AND YEAR(FECHA_CALCULO) = :anio GROUP BY P.ID, MES, YEAR(FECHA_CALCULO)", nativeQuery = true)
    public PlanillaDTO findByFechaCalculoMesAndAnio(@Param("mes") int mes, @Param("anio") int anio);



}
