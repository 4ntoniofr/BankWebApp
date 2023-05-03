package es.taw.grupo25.service;

import es.taw.grupo25.dto.RolCliente;
import es.taw.grupo25.entity.RolClienteEntity;
import es.taw.grupo25.repository.RolClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolClienteService {
    @Autowired
    protected RolClienteRepository rolClienteRepository;

    public RolCliente findByRol(String rol){
        RolClienteEntity rolCliente = rolClienteRepository.findByRol(rol);
        return rolCliente==null?null:rolCliente.toDTO();
    }

    public List<RolCliente> findRolesEmpresa(){
        List<RolClienteEntity> roles = this.rolClienteRepository.findRolesEmpresa();
        return listaToDTO(roles);
    }

    public static List<RolCliente> listaToDTO(List<RolClienteEntity> entities){
        List<RolCliente> roles = new ArrayList<>();
        for(RolClienteEntity entity : entities){
            roles.add(entity.toDTO());
        }
        return roles;
    }
}
