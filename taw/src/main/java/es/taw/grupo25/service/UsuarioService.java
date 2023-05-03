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

    public void guardarUsuario(Usuario usuario){
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        if(usuario.getId() != null) usuarioEntity.setId(usuario.getId());
        usuarioEntity.setUsuario(usuario.getUsuario());
        usuarioEntity.setPassword(usuario.getPassword());
        usuarioEntity.setFechaRegistro(usuario.getFechaRegistro());

        usuarioRepository.save(usuarioEntity);
        usuario.setId(usuarioEntity.getId());
    }

    public Usuario doAutenticarUsuario(String user, String password){
        UsuarioEntity usuario = usuarioRepository.autenticar(user, password);
        return (usuario == null ? null : usuario.toDTO());
    }

    public Usuario findById(Integer id){
        UsuarioEntity usuario = usuarioRepository.findById(id).orElse(null);
        return (usuario == null ? null : usuario.toDTO());
    }
}
