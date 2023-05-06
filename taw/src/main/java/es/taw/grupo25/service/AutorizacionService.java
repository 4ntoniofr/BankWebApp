/**
 * @author Antonio Fernandez Rodriguez
 */
package es.taw.grupo25.service;

import es.taw.grupo25.dto.Autorizacion;
import es.taw.grupo25.entity.AutorizacionEntity;
import es.taw.grupo25.entity.CuentaInternaEntity;
import es.taw.grupo25.entity.EmpleadoEntity;
import es.taw.grupo25.repository.AutorizacionRepository;
import es.taw.grupo25.repository.CuentaInternaRepository;
import es.taw.grupo25.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorizacionService {

    @Autowired
    private AutorizacionRepository autorizacionRepository;
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private CuentaInternaRepository cuentaInternaRepository;

    public void guardarAutorizacion(Autorizacion autorizacion) {
        this.autorizacionRepository.save(getEntity(autorizacion));
    }

    private AutorizacionEntity getEntity(Autorizacion autorizacion) {
        AutorizacionEntity autorizacionEntity = new AutorizacionEntity();
        autorizacionEntity.setFecha(autorizacion.getFecha());

        EmpleadoEntity empleadoEntity = this.empleadoRepository.findById(autorizacion.getEmpleadoByEmpleadoId().getId()).orElse(null);
        autorizacionEntity.setEmpleadoByEmpleadoId(empleadoEntity);

        CuentaInternaEntity cuentaInternaEntity = this.cuentaInternaRepository.findById(autorizacion.getCuentaInternaByCuentaInternaId().getId()).orElse(null);
        autorizacionEntity.setCuentaInternaByCuentaInternaId(cuentaInternaEntity);

        return autorizacionEntity;
    }
}
