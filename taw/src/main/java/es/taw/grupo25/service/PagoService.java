package es.taw.grupo25.service;

import es.taw.grupo25.dto.CambioDivisa;
import es.taw.grupo25.dto.Pago;
import es.taw.grupo25.entity.CambioDivisaEntity;
import es.taw.grupo25.entity.PagoEntity;
import es.taw.grupo25.entity.TransaccionEntity;
import es.taw.grupo25.repository.PagoRepository;
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
public class PagoService {
    @Autowired
    protected PagoRepository pagoRepository;

    @Autowired
    protected TransaccionRepository transaccionRepository;

    public List<Pago> findByCuentaId(Integer id){
        List<PagoEntity> pagoEntities = pagoRepository.findByCuentaId(id);
        return listaEntidadesADTO(pagoEntities);
    }

    private List<Pago> listaEntidadesADTO(List<PagoEntity> cuentas){
        ArrayList dtos =new ArrayList<Pago>();
        cuentas.forEach((final PagoEntity pago) -> dtos.add(pago.toDTO()));
        return dtos;
    }

    public void guardarPago(Pago pago){
        PagoEntity pagoEntity = new PagoEntity();
        pagoEntity.setId(pago.getId());
        pagoEntity.setMoneda(pago.getMoneda());
        pagoEntity.setCantidad(pago.getCantidad());

        TransaccionEntity transaccionEntity = transaccionRepository.findById(pago.getTransaccionByTransaccion().getId()).orElse(null);
        pagoEntity.setTransaccionByTransaccion(transaccionEntity);

        pagoRepository.save(pagoEntity);
    }
}
