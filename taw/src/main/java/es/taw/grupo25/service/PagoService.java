package es.taw.grupo25.service;

import es.taw.grupo25.dto.Pago;
import es.taw.grupo25.entity.PagoEntity;
import es.taw.grupo25.entity.TransaccionEntity;
import es.taw.grupo25.repository.PagoRepository;
import es.taw.grupo25.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagoService {
    @Autowired
    protected PagoRepository pagoRepository;

    @Autowired
    protected TransaccionRepository transaccionRepository;

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
