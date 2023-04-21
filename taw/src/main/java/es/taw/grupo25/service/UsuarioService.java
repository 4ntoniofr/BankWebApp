package es.taw.grupo25.service;

import es.taw.grupo25.dto.Usuario;
import es.taw.grupo25.entity.UsuarioEntity;
import es.taw.grupo25.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;


    public Usuario doAutenticarUsuario(String user, String password){
        UsuarioEntity usuario = usuarioRepository.autenticar(user, password);
        return (usuario == null ? null : usuario.toDTO());
    }

}
