/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.planillas.gestionPlanilla.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planillas.gestionPlanilla.DAO.IPermisosDao;
import com.planillas.gestionPlanilla.DTO.PermisoDTO;

/**
 *
 * @author danth
 */
@Service
public class PermisosService implements IPermisosService {


    @Autowired
    private IPermisosDao permisosDao;

    @Override
    public List<PermisoDTO> obtenerPermisosMesAnterior(LocalDate mesAnterior) {
        return (List<PermisoDTO>)permisosDao.findByMes(mesAnterior);
    }
}
