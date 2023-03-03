package com.dh.service;

import com.dh.dao.IDao;
import com.dh.model.Medicamentos;

public class MedicamentoService {
    private IDao<Medicamentos> medicamentoIDao;

    public MedicamentoService(IDao<Medicamentos> medicamentoIDao) {
        this.medicamentoIDao = medicamentoIDao;
    }

    public Medicamentos salvarMedicamento(Medicamentos medicamento){

        return medicamentoIDao.salvar(medicamento);
    }
}
