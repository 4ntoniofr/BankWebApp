package es.taw.grupo25.service;

import es.taw.grupo25.dto.Cliente;
import es.taw.grupo25.dto.CuentaInterna;
import es.taw.grupo25.dto.Transaccion;
import es.taw.grupo25.entity.ClienteEntity;
import es.taw.grupo25.entity.CuentaBancariaEntity;
import es.taw.grupo25.entity.TransaccionEntity;
import es.taw.grupo25.repository.ClienteRepository;
import es.taw.grupo25.repository.CuentaBancariaRepository;
import es.taw.grupo25.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author José Francisco Artacho Martin - 50%
 * @author Valentín García Rosas - 50%
 *
 */
@Service
public class TransaccionService {

    @Autowired
    protected TransaccionRepository transaccionRepository;

    @Autowired
    protected CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    protected ClienteRepository clienteRepository;

    @Autowired
    protected CuentaInternaService cuentaInternaService;

    public void guardarTransaccion(Transaccion transaccion){
        TransaccionEntity transaccionEntity = new TransaccionEntity();
        transaccionEntity.setFechaInstruccion(transaccion.getFechaInstruccion());
        transaccionEntity.setFechaEjecucion(transaccion.getFechaEjecucion());
        transaccionEntity.setPagosById(transaccion.getPagosById());

        CuentaBancariaEntity cuentaBancariaEntityOrigen = cuentaBancariaRepository.findById(transaccion.getCuentaBancariaByCuentaOrigen().getId()).orElse(null);
        transaccionEntity.setCuentaBancariaByCuentaOrigen(cuentaBancariaEntityOrigen);

        CuentaBancariaEntity cuentaBancariaEntityDestino = cuentaBancariaRepository.findById(transaccion.getCuentaBancariaByCuentaDestino().getId()).orElse(null);
        transaccionEntity.setCuentaBancariaByCuentaDestino(cuentaBancariaEntityDestino);

        transaccionRepository.save(transaccionEntity);
        transaccion.setId(transaccionEntity.getId());
    }

    public List<Transaccion> findAllByIdCuenta(Integer id){
        List<TransaccionEntity> transaccionEntities = transaccionRepository.findAllTransactionsById(id);
        return listaEntidadesADTO(transaccionEntities);
    }

    public List<Transaccion> findAllByCliente(Cliente cliente){
        List<CuentaInterna> cuentas = this.cuentaInternaService.findByClienteId(cliente.getId());
        List<Transaccion> transaccions = new ArrayList<>();
        for(CuentaInterna ci : cuentas){
            transaccions.addAll(findAllByIdCuenta(ci.getId()));
        }
        return transaccions;
    }

    public static List<Transaccion> listaEntidadesADTO(List<TransaccionEntity> transacciones){
        ArrayList dtos =new ArrayList<CuentaInterna>();
        transacciones.forEach((final TransaccionEntity transaccion) -> dtos.add(transaccion.toDTO()));
        return dtos;
    }

}
