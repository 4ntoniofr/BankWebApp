package es.taw.grupo25.service;

import es.taw.grupo25.dto.RolCliente;
import es.taw.grupo25.entity.RolClienteEntity;
import es.taw.grupo25.repository.RolClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolClienteService {
    @Autowired
    protected RolClienteRepository rolClienteRepository;

    public RolCliente findByRol(String rol){
        RolClienteEntity rolCliente = rolClienteRepository.findByRol(rol);
        return rolCliente==null?null:rolCliente.toDTO();
    }
}
