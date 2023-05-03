package es.taw.grupo25.service;

import es.taw.grupo25.dto.CuentaInterna;
import es.taw.grupo25.dto.Moneda;
import es.taw.grupo25.entity.MonedaEntity;
import es.taw.grupo25.repository.MonedaRepository;
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
public class MonedaService {

    @Autowired
    protected MonedaRepository monedaRepository;

    public List<Moneda> findAll(){
        List<MonedaEntity> monedas = monedaRepository.findAll();
        return listaEntidadesADTO(monedas);
    }

    public Moneda findById(Integer id){
        MonedaEntity monedaEntity = monedaRepository.findById(id).orElse(null);
        return monedaEntity==null?null:monedaEntity.toDTO();
    }

    public MonedaEntity getEntity(Moneda moneda){
        MonedaEntity monedaEntity = new MonedaEntity();

        monedaEntity.setId(moneda.getId());
        monedaEntity.setMoneda(moneda.getMoneda());
        monedaEntity.setCambioEuro(moneda.getCambioEuro());

        return monedaEntity;
    }

    private List<Moneda> listaEntidadesADTO(List<MonedaEntity> monedas){
        ArrayList dtos =new ArrayList<CuentaInterna>();
        monedas.forEach((final MonedaEntity moneda) -> dtos.add(moneda.toDTO()));
        return dtos;
    }

}
