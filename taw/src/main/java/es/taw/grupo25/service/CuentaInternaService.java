package es.taw.grupo25.service;

import es.taw.grupo25.dto.CuentaInterna;
import es.taw.grupo25.dto.Moneda;
import es.taw.grupo25.entity.*;
import es.taw.grupo25.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Antonio Fernández Rodriguez - 50%
 * @author Valentín García Rosas - 50%
 *
 */

@Service
public class CuentaInternaService {

    @Autowired
    protected CuentaInternaRepository cuentaInternaRepository;
    @Autowired
    protected ClienteRepository clienteRepository;
    @Autowired
    protected CuentaBancariaRepository cuentaBancariaRepository;
    @Autowired
    protected MonedaRepository monedaRepository;
    @Autowired
    protected EstadoCuentaRepository estadoCuentaRepository;

    public List<CuentaInterna> findByClienteId(Integer id){
        List<CuentaInternaEntity> cuentas = cuentaInternaRepository.findCuentasInternasForClient(id);
        return this.listaEntidadesADTO(cuentas);
    }

    public CuentaInterna findById(Integer id){
        CuentaInternaEntity cuenta = cuentaInternaRepository.findById(id).orElse(null);
        return cuenta==null?null:cuenta.toDTO();
    }

    public void guardarCuentaNueva(CuentaInterna cuentaInterna){
        CuentaInternaEntity cuentaInternaEntity = new CuentaInternaEntity();

        cuentaInternaEntity.setPais(cuentaInterna.getPais());
        cuentaInternaEntity.setCantidad(cuentaInterna.getCantidad());

        EstadoCuentaEntity estadoCuenta = estadoCuentaRepository.findById(cuentaInterna.getEstadoCuentaByEstadoCuenta().getId()).orElse(null);
        cuentaInternaEntity.setEstadoCuentaByEstadoCuenta(estadoCuenta);

        ClienteEntity clienteEntity = clienteRepository.findById(cuentaInterna.getClienteByPropietario().getId()).orElse(null);
        cuentaInternaEntity.setClienteByPropietario(clienteEntity);


        MonedaEntity moneda = monedaRepository.findById(cuentaInterna.getMonedaByMoneda()).orElse(null);
        cuentaInternaEntity.setMonedaByMoneda(moneda);

        CuentaBancariaEntity cuentaBancariaEntity = cuentaBancariaRepository.findByIban(cuentaInterna.getCuentaBancariaByCuentaBancaria().getIban());
        cuentaInternaEntity.setCuentaBancariaByCuentaBancaria(cuentaBancariaEntity);

        cuentaInternaRepository.save(cuentaInternaEntity);
        cuentaInterna.setId(cuentaInternaEntity.getId());
    }

    public void guardarCuenta(CuentaInterna cuentaInterna){
        CuentaInternaEntity cuentaInternaEntity = new CuentaInternaEntity();

        cuentaInternaEntity.setId(cuentaInterna.getId());
        cuentaInternaEntity.setPais(cuentaInterna.getPais());
        cuentaInternaEntity.setCantidad(cuentaInterna.getCantidad());

        EstadoCuentaEntity estadoCuenta = estadoCuentaRepository.findById(cuentaInterna.getEstadoCuentaByEstadoCuenta().getId()).orElse(null);
        cuentaInternaEntity.setEstadoCuentaByEstadoCuenta(estadoCuenta);

        ClienteEntity clienteEntity = clienteRepository.findById(cuentaInterna.getClienteByPropietario().getId()).orElse(null);
        cuentaInternaEntity.setClienteByPropietario(clienteEntity);


        MonedaEntity moneda = monedaRepository.findById(cuentaInterna.getMonedaByMoneda()).orElse(null);
        cuentaInternaEntity.setMonedaByMoneda(moneda);

        CuentaBancariaEntity cuentaBancariaEntity = cuentaBancariaRepository.findByIban(cuentaInterna.getCuentaBancariaByCuentaBancaria().getIban());
        cuentaInternaEntity.setCuentaBancariaByCuentaBancaria(cuentaBancariaEntity);

        cuentaInternaRepository.save(cuentaInternaEntity);
    }

    private List<CuentaInterna> listaEntidadesADTO(List<CuentaInternaEntity> cuentas){
        ArrayList dtos =new ArrayList<CuentaInterna>();
        cuentas.forEach((final CuentaInternaEntity cuenta) -> dtos.add(cuenta.toDTO()));
        return dtos;
    }

    public List<CuentaInterna> findCuentaInternasSolicitantes() {
        return listaEntidadesADTO(this.cuentaInternaRepository.findCuentaInternasSolicitantes());
    }

    public List<CuentaInterna> getCuentaInternasByIdCliente(Integer idCliente) {
        return  listaEntidadesADTO(this.cuentaInternaRepository.findCuentasInternasForClient(idCliente));
    }

}
