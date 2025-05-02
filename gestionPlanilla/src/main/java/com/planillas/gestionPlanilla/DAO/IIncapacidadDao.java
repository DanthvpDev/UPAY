package com.planillas.gestionPlanilla.DAO;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.planillas.gestionPlanilla.DTO.IncapacidadDTO;
import com.planillas.gestionPlanilla.Models.Incapacidad;

public interface IIncapacidadDao extends CrudRepository<Incapacidad, Long> {

    /*
     ? Obtiene las incapacidades del mes anterior al actual para calcular el total de días trabajados
     * @return Iterable<IncapacidadDTO>
     */
    @Query(value = "SELECT EMPLEADO_ID, FECHA_INICIO, FECHA_FIN FROM INCAPACIDADES WHERE FECHA_INICIO <= EOMONTH(:mesAnterior) AND FECHA_FIN >= DATEFROMPARTS(YEAR(:mesAnterior), MONTH(:mesAnterior), 1)", nativeQuery = true)
    public Iterable<IncapacidadDTO> findByFechaInicioAndFechaFinMesActual(@Param("mesAnterior") LocalDate mesAnterior);

}
