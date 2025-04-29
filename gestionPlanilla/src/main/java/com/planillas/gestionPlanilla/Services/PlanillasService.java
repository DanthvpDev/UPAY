package com.planillas.gestionPlanilla.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import com.planillas.gestionPlanilla.DAO.IPlanillaDao;
import com.planillas.gestionPlanilla.DTO.PlanillaDTO;
import com.planillas.gestionPlanilla.Models.Planilla;

@Service
public class PlanillasService implements IPlanillasService  {


    @Autowired
    private IPlanillaDao planillaDao;

    @Override
    public Planilla obtenerPlanillaPorFechaCalculo(LocalDate fechaCalculo) {
        return  planillaDao.findByFechaCalculo(fechaCalculo);
    }

    @Override
    public List<PlanillaDTO> obtenerPlanillaPorAnio(int anio) {
        return (List<PlanillaDTO>) planillaDao.findByFechaCalculoAnio(anio);
    }

    @Override
    public double calcularPorcentaje(double porcentaje, double salarioBase) {
        return (porcentaje / 100) * salarioBase;
    }    

    @Override
    public PlanillaDTO obtenerInformacionBasicaPlanilla(int mes, int anio) {
        return planillaDao.findByFechaCalculoMesAndAnio(mes, anio);
    }

    @Override
    public Planilla obtenerPlanillaPorId(long id){
        return planillaDao.findByPlanillaId(id);
    }

    @Override 
    @Transactional
    public void calcularPlanilla(Planilla planilla){
        try {
            planillaDao.save(planilla);
        } catch (Exception e) {
            throw e;
        }
    }
}
