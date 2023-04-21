package es.taw.grupo25.service;

import es.taw.grupo25.dto.CuentaBancaria;
import es.taw.grupo25.entity.CuentaBancariaEntity;
import es.taw.grupo25.repository.CuentaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaBancariaService {
    @Autowired
    CuentaBancariaRepository cuentaBancariaRepository;


    public CuentaBancaria findById(Integer id){
        CuentaBancariaEntity cuentaBancariaEntity = cuentaBancariaRepository.findById(id).orElse(null);
        return cuentaBancariaEntity==null?null:cuentaBancariaEntity.toDTO();
    }

    public CuentaBancaria findByIban(String iban){
        CuentaBancariaEntity cuentaBancariaEntity = cuentaBancariaRepository.findByIban(iban);
        return cuentaBancariaEntity==null?null:cuentaBancariaEntity.toDTO();
    }

    public void guardarCuenta(CuentaBancaria cuentaBancaria){
        cuentaBancariaRepository.save(getEntity(cuentaBancaria));
    }

    public CuentaBancariaEntity getEntity(CuentaBancaria cuentaBancaria){
        CuentaBancariaEntity cuentaBancariaEntity = new CuentaBancariaEntity();

        cuentaBancariaEntity.setId(cuentaBancaria.getId());
        cuentaBancariaEntity.setIban(cuentaBancaria.getIban());
        cuentaBancariaEntity.setCuentaInternasById(cuentaBancaria.getCuentaInternasById());
        cuentaBancariaEntity.setCuentaExternasById(cuentaBancaria.getCuentaExternasById());
        cuentaBancariaEntity.setTransaccionsById_Entrantes(cuentaBancaria.getTransaccionsById_Entrantes());
        cuentaBancariaEntity.setTransaccionsById_Salientes(cuentaBancaria.getTransaccionsById_Salientes());

        return cuentaBancariaEntity;
    }
}
