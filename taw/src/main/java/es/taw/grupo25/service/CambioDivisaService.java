package es.taw.grupo25.service;

import es.taw.grupo25.dto.CambioDivisa;
import es.taw.grupo25.dto.CuentaInterna;
import es.taw.grupo25.dto.Moneda;
import es.taw.grupo25.entity.CambioDivisaEntity;
import es.taw.grupo25.entity.CuentaInternaEntity;
import es.taw.grupo25.entity.MonedaEntity;
import es.taw.grupo25.entity.TransaccionEntity;
import es.taw.grupo25.repository.CambioDivisaRepository;
import es.taw.grupo25.repository.MonedaRepository;
import es.taw.grupo25.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Valentín García Rosas
 *
 */

@Service
public class CambioDivisaService {
    @Autowired
    CambioDivisaRepository cambioDivisaRepository;
    @Autowired
    TransaccionRepository transaccionRepository;
    @Autowired
    MonedaRepository monedaRepository;

    public List<CambioDivisa> findByCuentaId(Integer id){
        List<CambioDivisaEntity> cambioDivisaEntities = cambioDivisaRepository.findByCuentaId(id);
        return listaEntidadesADTO(cambioDivisaEntities);
    }

    private List<CambioDivisa> listaEntidadesADTO(List<CambioDivisaEntity> cuentas){
        ArrayList dtos =new ArrayList<CambioDivisa>();
        cuentas.forEach((final CambioDivisaEntity cambio) -> dtos.add(cambio.toDTO()));
        return dtos;
    }

    public void guardarCambioDivisa(CambioDivisa cambioDivisa){
        CambioDivisaEntity cambioDivisaEntity = new CambioDivisaEntity();

        TransaccionEntity transaccionEntity = transaccionRepository.findById(cambioDivisa.getTransaccionByTransaccion().getId()).orElse(null);
        cambioDivisaEntity.setTransaccionByTransaccion(transaccionEntity);

        MonedaEntity monedaCompra = monedaRepository.findById(cambioDivisa.getMonedaByMonedaCompra().getId()).orElse(null);
        cambioDivisaEntity.setMonedaByMonedaCompra(monedaCompra);

        MonedaEntity monedaVenta = monedaRepository.findById(cambioDivisa.getMonedaByMonedaVenta().getId()).orElse(null);
        cambioDivisaEntity.setMonedaByMonedaVenta(monedaVenta);

        cambioDivisaRepository.save(cambioDivisaEntity);
    }
}














