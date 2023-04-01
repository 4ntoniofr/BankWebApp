package es.taw.grupo25.controller;

import es.taw.grupo25.entity.*;
import es.taw.grupo25.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/gestor")
public class GestorController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CuentaInternaRepository cuentaInternaRepository;

    @Autowired
    private AutorizacionRepository autorizacionRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private CuentaBancariaRepository cuentaBancariaRepository;

    @Autowired
    private EstadoCuentaRepository estadoCuentaRepository;

    @GetMapping("/")
    public String doMostrarOpciones() {
        return "gestor/index";
    }

    @GetMapping("/pendientes")
    public String doMostrarPendientes(Model model) {
        List<ClienteEntity> list = this.clienteRepository.clientesNoAutorizados();
        List<ClienteEntity> listPersonas = new ArrayList<>();
        List<ClienteEntity> listEmpresas = new ArrayList<>();
        for (ClienteEntity cliente : list) {
            if (cliente.getPersonaByPersonaId() != null) {
                listPersonas.add(cliente);
            } else {
                listEmpresas.add(cliente);
            }
        }
        model.addAttribute("personas", listPersonas);
        model.addAttribute("empresas", listEmpresas);
        return "gestor/pendientes";
    }

    @GetMapping("/autorizar")
    public String doAutorizarCliente(@RequestParam("id") Integer idCliente, Model model) {
        ClienteEntity cliente = this.clienteRepository.findById(idCliente).orElse(null);
        if (cliente == null) {
            return "redirect:/gestor/";
        }
        CuentaInternaEntity cuentaInterna = new CuentaInternaEntity();
        CuentaBancariaEntity cuentaBancaria = new CuentaBancariaEntity();

        cuentaInterna.setCuentaBancariaByCuentaBancaria(cuentaBancaria);
        cuentaInterna.setAutorizacionsById(null);

        cuentaInterna.setClienteByPropietario(cliente);

        model.addAttribute("cuenta", cuentaInterna);

        return "gestor/autorizar";
    }

    @PostMapping("/autorizar")
    public String doAutorizar(@ModelAttribute("autorizacionCliente") CuentaInternaEntity cuentaInterna) {
        EstadoCuentaEntity estadoCuenta = this.estadoCuentaRepository.findByEstado("ACTIVA");
        cuentaInterna.setEstadoCuentaByEstadoCuenta(estadoCuenta);

        cuentaInterna.setBloqueada((byte) 0);
        cuentaInterna.setCantidad(0.0);

        EmpleadoEntity gestor = this.empleadoRepository.findById(1).orElse(null); //TODO: do it using sessions
        cuentaInterna.getClienteByPropietario().setEmpleadoByAutorizador(gestor);

        this.clienteRepository.save(cuentaInterna.getClienteByPropietario());
        this.cuentaBancariaRepository.save(cuentaInterna.getCuentaBancariaByCuentaBancaria());
        this.cuentaInternaRepository.save(cuentaInterna);

        return "redirect:/gestor/pendientes";
    }

    @GetMapping("/rechazar")
    public String doRechazar(@RequestParam("id") Integer idCliente){
        clienteRepository.deleteById(idCliente);
        return "redirect:/gestor/pendientes";
    }
}
