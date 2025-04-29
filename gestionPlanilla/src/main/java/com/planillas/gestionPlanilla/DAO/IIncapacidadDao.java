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
    @Query(value = "SELECT EMPLEADO_ID, FECHA_INICIO, FECHA_FIN FROM INCAPACIDADES WHERE (MONTH(FECHA_INICIO) = MONTH(:mesAnterior) OR MONTH(FECHA_FIN) = MONTH(:mesAnterior)) AND (YEAR(FECHA_INICIO) = YEAR(:mesAnterior) OR YEAR(FECHA_FIN) = YEAR(:mesAnterior))", nativeQuery = true)
    public Iterable<IncapacidadDTO> findByFechaInicioAndFechaFinMesActual(@Param("mesAnterior") LocalDate mesAnterior);

}
