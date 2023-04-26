package es.taw.grupo25.service;

import es.taw.grupo25.dto.CuentaBancaria;
import es.taw.grupo25.dto.Transaccion;
import es.taw.grupo25.entity.CuentaBancariaEntity;
import es.taw.grupo25.entity.TransaccionEntity;
import es.taw.grupo25.repository.CuentaBancariaRepository;
import es.taw.grupo25.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CuentaBancariaService {
    @Autowired
    CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    TransaccionRepository transaccionRepository;


    public CuentaBancaria findById(Integer id) {
        CuentaBancariaEntity cuentaBancariaEntity = cuentaBancariaRepository.findById(id).orElse(null);
        return cuentaBancariaEntity == null ? null : cuentaBancariaEntity.toDTO();
    }

    public CuentaBancaria findByIban(String iban) {
        CuentaBancariaEntity cuentaBancariaEntity = cuentaBancariaRepository.findByIban(iban);
        return cuentaBancariaEntity == null ? null : cuentaBancariaEntity.toDTO();
    }

    public void guardarCuenta(CuentaBancaria cuentaBancaria) {
        cuentaBancariaRepository.save(getEntity(cuentaBancaria));
    }

    private List<TransaccionEntity> listDTOToListEntity(List<Transaccion> transaccions) {
        List<TransaccionEntity> transaccionEntities = new ArrayList<>();
        for (Transaccion transaccion : transaccions) {
            transaccionEntities.add(this.transaccionRepository.findById(transaccion.getId()).orElse(null));
        }
        return transaccionEntities;
    }

    public CuentaBancariaEntity getEntity(CuentaBancaria cuentaBancaria) {
        CuentaBancariaEntity cuentaBancariaEntity = new CuentaBancariaEntity();

        cuentaBancariaEntity.setId(cuentaBancaria.getId());
        cuentaBancariaEntity.setIban(cuentaBancaria.getIban());
        cuentaBancariaEntity.setCuentaInternasById(cuentaBancaria.getCuentaInternasById());
        cuentaBancariaEntity.setCuentaExternasById(cuentaBancaria.getCuentaExternasById());

        return cuentaBancariaEntity;
    }
}
