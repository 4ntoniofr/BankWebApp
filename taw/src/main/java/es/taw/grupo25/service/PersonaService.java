package es.taw.grupo25.service;

import es.taw.grupo25.dto.Persona;
import es.taw.grupo25.entity.PersonaEntity;
import es.taw.grupo25.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public Persona findById(Integer id){
        PersonaEntity persona = personaRepository.findById(id).orElse(null);
        return persona==null?null:persona.toDTO();
    }

    public void guardarPersona(Persona dto){
        PersonaEntity persona = new PersonaEntity();

        persona.setId(dto.getId());
        persona.setDni(dto.getDni());
        persona.setNombre(dto.getNombre());
        persona.setPrimerApellido(dto.getPrimerApellido());
        persona.setSegundoApellido(dto.getSegundoApellido());
        persona.setFechaNacimiento(dto.getFechaNacimiento());

        this.personaRepository.save(persona);
        dto.setId(persona.getId());
    }

}
